package ffzim.cards.Chocobo.Healer;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.stances.LimitGaugePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ChocoSurge extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoSurge",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            CardColor.COLORLESS); 



    public static final String ID = makeID(cardInfo.baseId);


    private static final int BUFF = 3;
    private static final int UPG_BUFF = 2;

    public ChocoSurge() {
        super(cardInfo, true);
        setMagic(BUFF,UPG_BUFF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LimitGaugePower(m, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoSurge();
    }
}