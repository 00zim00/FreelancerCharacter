package ffzim.cards.DarkKnight;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class DivineVeil extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "DivineVeil",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);
    private static final int MULTIPLYER = 1;
    private static final int UPG_MULTIPLYER = 2;

    public static final String ID = makeID(cardInfo.baseId);
    public DivineVeil() {
        super(cardInfo, true);
        setMagic(MULTIPLYER,UPG_MULTIPLYER);
    }

//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeName();
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
//        }
//    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Change to gain X temp HP, then gain Block equal to temp HP.

        int tempHP = TempHPField.tempHp.get(p);
        tempHP = (tempHP * magicNumber);
        addToBot(new GainBlockAction(p, p, tempHP));
        //addToBot(new RemoveAllTemporaryHPAction(p, p));
        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
    }

    public AbstractCard makeCopy() {
        return new DivineVeil();
    }
}
