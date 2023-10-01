package ffzim.cards.BlueMage.BlueMagic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MalleablePower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Malleable extends BaseCard
{

    public static final String POWER_ID = makeID("Malleable");
    private final static CardInfo cardInfo = new CardInfo(
            "Malleable",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    private static final int BUFF = 3;
    private static final int UPG_BUFF = 1;
    public Malleable() {
        super(cardInfo, true);
        setMagic(BUFF,UPG_BUFF);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFBLUEMAGIC);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MalleablePower(p, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Malleable();
    }
}