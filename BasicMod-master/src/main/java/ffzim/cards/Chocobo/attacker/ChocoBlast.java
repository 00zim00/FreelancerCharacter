package ffzim.cards.Chocobo.attacker;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.stances.LimitGaugePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ChocoBlast extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoBlast",
            0,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.SPECIAL,
            CardColor.COLORLESS); 

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE = 1;
    private static final int UPG_BUFF = 2;

    public ChocoBlast() {
        super(cardInfo, true);
        setDamage(DAMAGE);
        setMagic(getChocoAttackLevel());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SHIELD));
    }

    public void applyPowers() {
        setMagic(getChocoAttackLevel());
        setDamage(magicNumber + DAMAGE);
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoBlast();
    }
}