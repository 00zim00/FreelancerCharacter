package ffzim.cards.Generic;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.CustomWaitAction;
import ffzim.actions.MagicStoneAdditionAction;
import ffzim.cards.BaseCard;
import ffzim.cards.BlueMage.BlueMagic.AutoLife;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class MagicStoneRedo extends BaseCard implements OnObtainCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "MagicStoneRedo",
            1,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int Equip_Vaule = 1;
    public static int MAX_Equip;
    public static int UPG_MAX_Equip;
    public static int ACTIVE_NEEDED;
    public static AbstractCard thisAutoCard;
    private boolean isInPreview;

    public MagicStoneRedo() {this(true);}
    public MagicStoneRedo(boolean makePreview) {
        super(cardInfo, false);
        this.misc = 0;
        setSecondMagic(MAX_Equip,UPG_MAX_Equip);
        this.baseMagicNumber = this.misc;
//        if (makePreview) {
//            cardsToPreview = thisAutoCard;
//        }
        this.isInPreview = makePreview;
        this.initializeDescription();
        //setExhaust(true);
    }

//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            if (isInPreview ) {
//                this.cardsToPreview.upgrade();
//            }
//            //this.upgradedCost = true;
//            this.upgradeName();
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
//        }
//    }

    @Override
    public void onObtainCard() {
        System.out.println("MAX_Equip: " + MAX_Equip);
        System.out.println("UPG_MAX_Equip: " + UPG_MAX_Equip);
        System.out.println("ACTIVE_NEEDED: " + ACTIVE_NEEDED);
        System.out.println("thisAutoCard: " + thisAutoCard);
        System.out.println("thisAutoCard.cardID: " + thisAutoCard.cardID);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println("MAX_Equip: " + MAX_Equip);
        System.out.println("UPG_MAX_Equip: " + UPG_MAX_Equip);
        System.out.println("ACTIVE_NEEDED: " + ACTIVE_NEEDED);
        System.out.println("thisAutoCard: " + thisAutoCard);
        System.out.println("thisAutoCard.cardID: " + thisAutoCard.cardID);

        AbstractCard cardToAdd = thisAutoCard;
        if (this.upgraded) {cardToAdd.upgrade();}
        if (makeID("AutoLife").equals(thisAutoCard.cardID)) {
        //if ("AutoLife".equals(thisAutoCard.cardID)) {
            ((AutoLife) cardToAdd).isActive = true;
        }


        addToBot(new MagicStoneAdditionAction(this.secondMagicNumber, this.uuid));
        addToBot(new CustomWaitAction(0.5f));
        //addToBot(new MagicStoneActivateAction(p,p,cardToAdd,this,this.secondMagicNumber, this.uuid));
    }

    public void applyPowers() {
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MagicStoneRedo();
    }
}