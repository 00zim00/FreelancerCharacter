package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class SwordMagicBU extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SwordMagicBU",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BASE_DAMAGE = 7;
    private static final int UPGRADED_DAMAGE = 3;

    public SwordMagicBU() {
        super(cardInfo, false);
        this.exhaust = true;
        this.baseDamage = BASE_DAMAGE;
        this.damage = this.baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard cardToReduceCost = getNextFFSpellCard();
        if (cardToReduceCost != null) {
            cardToReduceCost.modifyCostForCombat(-1);
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    // Helper method to find the next FFSpell card in player's hand
    private AbstractCard getNextFFSpellCard() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.hasTag(CustomTags.FFSPELL)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwordMagicBU();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADED_DAMAGE);
        }
    }
}
