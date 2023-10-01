package ffzim.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.character.Freelancer;
import ffzim.powers.HighJumpPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class HighJump extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "HighJump",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int FLIGHT = 2;
    private static final int UPG_FLIGHT  = 1;

    public HighJump() {
        super(cardInfo, true);
        setMagic(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new HighJumpPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HighJump();
    }
}