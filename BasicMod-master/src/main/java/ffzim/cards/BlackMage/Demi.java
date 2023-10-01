package ffzim.cards.BlackMage;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import static ffzim.BasicMod.makeID;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class Demi extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Demi",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BASE_DAMAGE_PERCENT = 30;
    private int reducedDamage;
    private AbstractMonster targetEnemy;

    public Demi() {
        super(cardInfo, false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            targetEnemy = m; // Store the target enemy when the card is used
            int calculatedDamage = (int) Math.ceil(targetEnemy.currentHealth * BASE_DAMAGE_PERCENT / 100.0);;
            if (targetEnemy.currentHealth - calculatedDamage >= 1) {
                reducedDamage = calculatedDamage;
            } else {
                reducedDamage = targetEnemy.currentHealth - 1;
            }
            addToBot(new DamageAction(targetEnemy, new DamageInfo(p, reducedDamage, damageTypeForTurn), AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public void applyPowers() {
        if (targetEnemy != null) {
            int calculatedDamage = (int) Math.ceil(targetEnemy.currentHealth * BASE_DAMAGE_PERCENT / 100.0);
            if (targetEnemy.currentHealth - calculatedDamage >= 1) {
                reducedDamage = calculatedDamage;
            } else {
                reducedDamage = targetEnemy.currentHealth - 1;
            }
            isDamageModified = reducedDamage != baseDamage;
        }
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (targetEnemy != null) {
            int calculatedDamage = (int) Math.ceil(targetEnemy.currentHealth * BASE_DAMAGE_PERCENT / 100.0);
            if (targetEnemy.currentHealth - calculatedDamage >= 1) {
                reducedDamage = calculatedDamage;
            } else {
                reducedDamage = targetEnemy.currentHealth - 1;
            }
            isDamageModified = reducedDamage != baseDamage;
        }
        super.calculateCardDamage(mo);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Demi();
    }
}
