//package ffzim.cards.Generic;
//
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.cards.BaseCard;
//import ffzim.util.CardInfo;
//import ffzim.util.CustomTags;
//
//import static ffzim.BasicMod.makeID;
//
//public class DrawWorking2 extends BaseCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "Draw",
//            0,
//            CardType.SKILL,
//            CardTarget.NONE,
//            CardRarity.COMMON,
//            MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        if (magicNumber >= 4) {
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void atTurnStart() {
//        setMagic(0);
//        setSecondMagic(4);
//    }
//
//    public DrawWorking2() {
//        this(true, 4);
//    }
//
//    public DrawWorking2(boolean makePreview, int baseSecondMagicNumber) {
//        super(cardInfo, true);
//        if (makePreview) {
//            cardsToPreview = new UnDraw(false,4-magicNumber);;
//        }
//
//
//
//        selfRetain = true;
//        PurgeField.purge.set(this, true);
//        tags.add(CustomTags.FFSPELL);
//        setMagic((magicNumber >= 1) ? magicNumber : 0);
//        setSecondMagic(4 - magicNumber);
//        this.baseSecondMagicNumber = baseSecondMagicNumber;
//        if (makePreview) {
//            cardsToPreview = new UnDraw(false,4-magicNumber);;
//        }
//        super.initializeDescription();
//    }
//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeName();
//            if (cardsToPreview instanceof UnDraw) {
//                (cardsToPreview).upgrade();
//            }
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            this.initializeDescription();
//        }
//    }
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        if (this.upgraded) {
//            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 2));
//        }else{
//            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
//        }
//        AbstractCard cardToAdd = new UnDraw(true,baseSecondMagicNumber-1);
//        if (upgraded) {
//            cardToAdd.upgrade();
//        }
//        cardToAdd.baseMagicNumber = this.baseMagicNumber + 1;
//        cardToAdd.magicNumber = cardToAdd.baseMagicNumber;
//        cardToAdd.initializeDescription();
//        AbstractDungeon.player.hand.addToBottom(cardToAdd);
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new DrawWorking2();
//    }
//}