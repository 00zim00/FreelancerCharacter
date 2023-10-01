package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.EvasionPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Distract extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "Distract",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BUFF = 15;
    private static final int UPG_BUFF   = 10;

    public Distract() {
        super(cardInfo, true);
        setMagic(BUFF ,UPG_BUFF );
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new EvasionPower(m, this.magicNumber), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Distract();
    }
}
