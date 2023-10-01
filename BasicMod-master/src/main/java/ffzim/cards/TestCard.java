package ffzim.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class TestCard extends BaseCard {

    public static final String POWER_ID = makeID("TestCard");
    private final static CardInfo cardInfo = new CardInfo(
            "TestCard",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect



    private static final int FLIGHT = 2;
    private static final int UPG_FLIGHT  = 1;

    public TestCard() {
        super(cardInfo, true);
        setMagic(1);
        RevolvingFields.setBaseValue(this,"TestCard", 3);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //addToBot(new ApplyPowerAction(p, p, new FloatPower(p, magicNumber),1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new TestCard();
    }
}