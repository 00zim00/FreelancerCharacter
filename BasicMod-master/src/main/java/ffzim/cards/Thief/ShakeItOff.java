package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.CardTrackerPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ShakeItOff extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ShakeItOff",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK   = 2;

    public ShakeItOff() {
        super(cardInfo, false);
        setBlock(BLOCK ,UPG_BLOCK );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        CardTrackerPower cardTrackerPower = (CardTrackerPower) p.getPower(CardTrackerPower.POWER_ID);
        if (cardTrackerPower != null && cardTrackerPower.wasDamaged) {
            addToBot(new GainBlockAction(p, p, block));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShakeItOff();
    }
}
