package ffzim.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.character.Freelancer;
import ffzim.powers.EvasionPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class SmokeScreen extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Smoke Screen",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int EVASION = 30;
    private static final int UPG_EVASION = 20;

    public SmokeScreen() {
        super(cardInfo, true);
        setMagic(EVASION,EVASION);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EvasionPower(p,magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SmokeScreen();
    }
}