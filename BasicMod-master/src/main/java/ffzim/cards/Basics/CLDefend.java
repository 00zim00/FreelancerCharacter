package ffzim.cards.Basics;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class CLDefend extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CLDefend",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 3;
    private static final int UPG_BLOCK = 1;
    private static final int REGEN_AMT = 2;
    private static final int UPG_REGEN_AMT = 2;

    public CLDefend() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(REGEN_AMT, UPG_REGEN_AMT);
        tags.add(CustomTags.FFSPELL);
        tags.add(CardTags.STARTER_DEFEND);
        tags.add(CustomTags.FFJOBDEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
//        if (upgraded) {
//            int temporaryHPAmount = UPG_REGEN_AMT; // Change this value to the desired Temporary HP amount
//            addToBot(new AddTemporaryHPAction(p, p, temporaryHPAmount));
//        }
//        int temporaryHPAmount = REGEN_AMT; // Change this value to the desired Temporary HP amount
//        addToBot(new AddTemporaryHPAction(p, p, temporaryHPAmount));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CLDefend();
    }
}