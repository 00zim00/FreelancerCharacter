package ffzim.cards.Chocobo.attacker;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import ffzim.cards.BaseCard;
import ffzim.stances.LimitGaugePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ChocoBeak extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoBeak",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            CardColor.COLORLESS); 

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BUFF = 1;

    public ChocoBeak() {
        super(cardInfo, true);
        setMagic(getChocoAttackLevel());
        setSecondMagic(BUFF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int Vul = magicNumber + secondMagicNumber;
        addToBot(new ApplyPowerAction(p, p, new VulnerablePower(m, Vul,false), Vul));
    }

    public void applyPowers() {
        setMagic(getChocoAttackLevel());
        initializeDescription();
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoBeak();
    }
}