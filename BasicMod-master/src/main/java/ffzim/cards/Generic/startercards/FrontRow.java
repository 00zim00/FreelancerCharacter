package ffzim.cards.Generic.startercards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.*;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class FrontRow extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FrontRow",
            -2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BUFF = 1;
    private static final int UPG_BUFF = 1;
    private static final int DEBUFF = -1;
    private static final int UPG_DEBUFF = 0;
    private boolean isInPreview;
    public FrontRow() {this(true);}
    public FrontRow(boolean makePreview) {
        super(cardInfo, true);
        setMagic(BUFF,UPG_BUFF);
        setSecondMagic(DEBUFF,UPG_DEBUFF);
        PurgeField.purge.set(this, true);
        if (makePreview) {
            cardsToPreview = new BackRow(false);
        }
        this.isInPreview = makePreview;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (isInPreview) {
                this.cardsToPreview.upgrade();
            }
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FrontRowPower(p, 0,upgraded), 0));
    }



    @Override
    public AbstractCard makeCopy() { //Optional
        return new FrontRow();
    }
}
