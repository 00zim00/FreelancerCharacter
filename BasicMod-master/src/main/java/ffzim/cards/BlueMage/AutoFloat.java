package ffzim.cards.BlueMage;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.ProtectPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class AutoFloat extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AutoFloat",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int Trance = 1;
    private static final int UPG_Trance = 0;

    public AutoFloat() {
        super(cardInfo, true);
        setMagic(Trance,UPG_Trance);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ProtectPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AutoFloat();
    }
}