//package ffzim.cards.Generic;
//
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.cards.BaseCard;
//import ffzim.cards.RevolvingFields;
//import ffzim.util.CardInfo;
//
//import static ffzim.BasicMod.makeID;
//import static ffzim.cards.RevolvingFields.revolving;
//
//public class DrawMODED extends BaseCard
//{
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
//    private int BaseRevolving = 4;
//
//    public DrawMODED() {
//        this(true);
//    }
//
//    public DrawMODED(boolean makePreview) {
//        super(cardInfo, true);
//        if (makePreview) {
//            cardsToPreview = new Undraw(false);
//            revolving.set(cardsToPreview, revolving.get(this) - 1);
//        }
//        RevolvingFields.setBaseValue(this,"Undraw", BaseRevolving);
//        selfRetain = true;
//        setMagic(RevolvingFields.revolving.get(this));
//        setSecondMagic(RevolvingFields.revolving.get(this));
//        //tags.add(CustomTags.FFSPELL);
//
//
////        System.out.println("baseMagicNumber1: " + baseMagicNumber);
////        setMagic((magicNumber >= 1) ? magicNumber : 0);
////        System.out.println("baseMagicNumber2: " + baseMagicNumber);
////        secondMagicNumber = 4 - magicNumber;
////        System.out.println("baseMagicNumber3: " + secondMagicNumber);
//
//        super.initializeDescription();
//    }
//
////    @Override
////    public void triggerWhenCopied() {
////        revolving.set(this, revolving.get(this) - 1);
////        setMagic(RevolvingFields.revolving.get(this));
////        System.out.println("initialize Des: " + magicNumber);
////        super.initializeDescription();
////    }
//
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new DrawMODED();
//    }
//}