package ffzim.cards.Basics;

import com.evacipated.cardcrawl.mod.stslib.actions.common.RefundAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class BLKDefend extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BLKDefend",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 2;
    private static final int ENERGY = 1;

    public BLKDefend() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFJOBDEFEND);
        magicNumber = baseMagicNumber = ENERGY;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if (upgraded) {
            addToBot(new RefundAction(this));
        }
    }
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BLKDefend();
    }
}