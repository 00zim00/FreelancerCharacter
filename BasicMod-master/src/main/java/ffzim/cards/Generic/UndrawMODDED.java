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
//public class UndrawMODDED extends BaseCard
//{
//    private final static CardInfo cardInfo = new CardInfo(
//            "Undraw",
//            0,
//            CardType.SKILL,
//            CardTarget.NONE,
//            CardRarity.COMMON,
//            MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//
//    private int BaseRevolving = 4;
//    public UndrawMODDED() {
//        this(true);
//    }
//
//    public UndrawMODDED(boolean makePreview) {
//        super(cardInfo, true);
//        if (makePreview) {
//            cardsToPreview = new UndrawMODDED(false);
//            revolving.set(cardsToPreview, revolving.get(this) - 1);
//        }
//        RevolvingFields.setBaseValue(this,"Draw", BaseRevolving);
//        System.out.println("baseMagicNumber4: " + baseMagicNumber);
////        setMagic((magicNumber >= 1) ? magicNumber : 0);
////        System.out.println("baseMagicNumber5: " + baseMagicNumber);
////        secondMagicNumber = 4 - magicNumber;
////        System.out.println("baseMagicNumber6: " + secondMagicNumber);
//        setMagic(revolving.get(this));
//        selfRetain = true;
//        setSecondMagic(RevolvingFields.revolving.get(this));
//        //tags.add(CustomTags.FFSPELL);
//        super.initializeDescription();
//    }
//
////        @Override
////    public void triggerWhenCopied() {
////        //Add a tag and if tag then overide revolving
////
////            revolving.set(this, revolving.get(this) - 1);
////            setMagic(revolving.get(this));
////            System.out.println("initialize Des: " + magicNumber);
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
//        return new UndrawMODDED();
//    }
//}