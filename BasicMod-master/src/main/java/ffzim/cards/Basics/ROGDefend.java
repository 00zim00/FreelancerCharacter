package ffzim.cards.Basics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.EvasionPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class ROGDefend extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ROGDefend",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 3;
    private static final int EVASION_AMT = 7;
    private static final int UPG_EVASION_AMT = 7;

    public ROGDefend() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(EVASION_AMT, UPG_EVASION_AMT);
        tags.add(CardTags.STARTER_DEFEND);
        tags.add(CustomTags.FFJOBDEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new EvasionPower(p, magicNumber), magicNumber));
//        if (upgraded) {
//            int temporaryHPAmount = UPG_REGEN_AMT; // Change this value to the desired Temporary HP amount
//            addToBot(new AddTemporaryHPAction(p, p, temporaryHPAmount));
//        }
//        int temporaryHPAmount = REGEN_AMT; // Change this value to the desired Temporary HP amount
//        addToBot(new AddTemporaryHPAction(p, p, temporaryHPAmount));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ROGDefend();
    }
}