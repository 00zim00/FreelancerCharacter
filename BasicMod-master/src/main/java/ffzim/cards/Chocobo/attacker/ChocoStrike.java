package ffzim.cards.Chocobo.attacker;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.helpers.MateriaUpdatePreview;
import ffzim.stances.LimitGaugePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ChocoStrike extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoStrike",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);



    public static final String ID = makeID(cardInfo.baseId);


    private static final int DAMAGE = 5;
    private static final int UPG_BUFF = 2;

    public ChocoStrike() {
        super(cardInfo, true);
        setMagic(getChocoAttackLevel());
        setDamage(magicNumber + DAMAGE);
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        setMagic(getChocoAttackLevel());
        setDamage(magicNumber + DAMAGE);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    public void applyPowers() {
        setMagic(getChocoAttackLevel());
        setDamage(magicNumber + DAMAGE);
        initializeDescription();
    }

    public void initializeDescription() {
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoStrike();
    }
}