package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static ffzim.BasicMod.makeID;

public class GilToss extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GilToss",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

// implements AlternateCardCostModifier

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE_GAIN_PERCENTAGE = 10;
    private static final int MINDAMAGE = 1;
    private static final int MAXDAMAGE = 5;
    private static final int UPG_MINDAMAGE = 1;
    private static final int UPG_MAXDAMAGE = 10;


    // GainGoldTextEffect
    // RainingGold
    // TouchPickupGold
    public GilToss() {
        super(cardInfo, true);
        setMagic(DAMAGE_GAIN_PERCENTAGE);
        int goldAmount = player != null ? player.gold : 0;
        if (upgraded) {
            this.rawDescription = String.format("Deal between %d and %s damage. NL +1%% damage per current 1 Gold you have. NL Lose Gold equal to the damage.",
                    (int)(((UPG_MINDAMAGE * goldAmount) / 100.0f) + UPG_MINDAMAGE),
                    FontHelper.colorString(Integer.toString((int)(((UPG_MAXDAMAGE * goldAmount) / 100.0f) + UPG_MAXDAMAGE)), "g"));
        } else {
            this.rawDescription = String.format("Deal between %d and %d damage. NL +1%% damage per current 1 Gold you have. NL Lose Gold equal to the damage.",
                    (int)(((MINDAMAGE * goldAmount) / 100.0f) + MINDAMAGE),
                    (int)(((MAXDAMAGE * goldAmount) / 100.0f) + MAXDAMAGE));

        }
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {;
        int roll = AbstractDungeon.aiRng.random(2);
        if (roll == 0) {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_MUGGER_1A"));
        } else {
            AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new SFXAction("VO_MUGGER_1B"));
        }
        if (upgraded) {
            this.damage = AbstractDungeon.cardRandomRng.random(UPG_MINDAMAGE, UPG_MAXDAMAGE);
        } else {
            this.damage = AbstractDungeon.cardRandomRng.random(MINDAMAGE, MAXDAMAGE);
        }
        int originalDamage = this.damage;
        int goldAmount = player.gold;
        if (goldAmount > 0) {
            float damageBonus = (originalDamage * goldAmount) / 100.0f;
            this.damage = (int)(damageBonus + originalDamage);
        }
        AbstractDungeon.actionManager.addToBottom((new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT)));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.1F));

        if (goldAmount > 0) {
            player.loseGold(this.damage);
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GilToss();
    }
}