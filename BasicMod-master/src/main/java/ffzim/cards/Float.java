package ffzim.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.character.Freelancer;
import ffzim.powers.FloatPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Float extends BaseCard {

    public static final String POWER_ID = makeID("Float");
    private final static CardInfo cardInfo = new CardInfo(
            "Float",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect



    private static final int FLIGHT = 2;
    private static final int UPG_FLIGHT  = 1;

    public Float() {
        super(cardInfo, true);
        setMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FloatPower(p, magicNumber),1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Float();
    }
}