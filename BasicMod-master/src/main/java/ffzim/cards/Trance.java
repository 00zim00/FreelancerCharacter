package ffzim.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.character.Freelancer;
import ffzim.stances.LimitGaugePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Trance extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Trance",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int Trance = 0;
    private static final int UPG_Trance = 0;

    public Trance() {
        super(cardInfo, true);
        setMagic(Trance,UPG_Trance);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LimitGaugePower(p, 10), 10));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Trance();
    }
}