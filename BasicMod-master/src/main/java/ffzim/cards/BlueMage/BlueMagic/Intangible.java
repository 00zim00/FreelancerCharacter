package ffzim.cards.BlueMage.BlueMagic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.IntangibleBluePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Intangible extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Vanish",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BUFF = 1;
    public Intangible() {
        super(cardInfo, true);
        setMagic(BUFF);
        setExhaust(true);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    // For the next X turns, every 2 turns gain


    // Change to power? Cost 3? every second turn gain intangible. but your damge is also 1 max.
    // Gain 1 Intangible every 2nd/3rd turn. While your Intangible Reduce ALL damage you deal to 1.
    //Gain 1 Intangible every 2nd turn. While your Intangible Reduce ALL damage you deal by 50%.

    // Can change to ever 4/3 turns.

//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeName();
//            setExhaust(false);
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
//        }
//    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            addToBot(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, magicNumber), magicNumber));
        }
        addToBot(new ApplyPowerAction(p, p, new IntangibleBluePower(p, 2), 2));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Intangible();
    }
}