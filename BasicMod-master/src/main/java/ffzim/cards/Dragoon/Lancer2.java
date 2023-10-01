package ffzim.cards.Dragoon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Lancer2 extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Lancer2",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 4;
    private static final int ENERGY_GAIN = 1;

    public Lancer2() {
        super(cardInfo, true);

        setDamage(DAMAGE, UPG_DAMAGE);
        baseMagicNumber = magicNumber = ENERGY_GAIN;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        if (p.hand.group.size() > 0) {
            AbstractCard lastCardPlayed = p.hand.group.get(p.hand.group.size() - 1);
            if (lastCardPlayed.type == CardType.ATTACK) {
                // Modify damage for double damage
                damage *= 2;
            }
        }
            addToBot(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
                    new DamageInfo(p, damage, damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }
        @Override
        public void upgrade() {
            if (!upgraded) {
                upgradeName();
                upgradeDamage(UPG_DAMAGE);
            }
        }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new Lancer2();
    }
}