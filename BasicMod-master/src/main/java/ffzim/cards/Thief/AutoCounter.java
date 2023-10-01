package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.CounterPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class AutoCounter extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AutoCounter",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int COUNTERS = 1;
    private static final int UPG_COUNTERS   = 1;

    public AutoCounter() {
        super(cardInfo, true);
        setMagic(COUNTERS,UPG_COUNTERS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CounterPower(p, magicNumber),magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AutoCounter();
    }
}