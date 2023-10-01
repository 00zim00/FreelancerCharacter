package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class MinusStrike extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MinusStrike",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private AbstractMonster targetEnemy;

    public MinusStrike() {
        super(cardInfo, false);
        setExhaust(true);
        this.tags.add(CardTags.STRIKE);
//        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
//            this.magicNumber = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
//        } else {
//            this.magicNumber = 0;
//        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        targetEnemy = m;
        int calculatedDamage = (int) Math.ceil(AbstractDungeon.player.maxHealth- AbstractDungeon.player.currentHealth);;
            addToBot(new DamageAction(targetEnemy, new DamageInfo(p, calculatedDamage, damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
        exhaust = true;
    }

    @Override
    public void applyPowers() {
            int calculatedDamage = (int) Math.ceil(AbstractDungeon.player.maxHealth- AbstractDungeon.player.currentHealth);
            isDamageModified = calculatedDamage != baseDamage;
            super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int calculatedDamage = (int) Math.ceil(AbstractDungeon.player.maxHealth- AbstractDungeon.player.currentHealth);
        isDamageModified = calculatedDamage != baseDamage;
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MinusStrike();
    }
}
