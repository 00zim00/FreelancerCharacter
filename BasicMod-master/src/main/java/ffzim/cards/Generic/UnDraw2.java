//package ffzim.cards;
//
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.util.CardInfo;
//import ffzim.util.CustomTags;
//
//import static ffzim.BasicMod.makeID;
//
//public class UnDraw2 extends BaseCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "UnDraw2",
//            0,
//            CardType.SKILL,
//            CardTarget.NONE,
//            CardRarity.COMMON,
//            MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//
//    private  int DRAW = 1;
//    private int Revolving = 4;
//
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        if (magicNumber >= 4) {
//            //super.setCostForTurn(-2);
//            return false;
//        } else {
//            return true;
//        }
//    }
//
//    @Override
//    public void atTurnStart() {
//        setMagic(0);
//    }
//    public UnDraw2() {
//        this(true);
//    }
//
//    public UnDraw2(boolean makePreview) {
//        super(cardInfo, true);
//        selfRetain = true;
//        draw = 1;
//        PurgeField.purge.set(this, true);
//        tags.add(CustomTags.FFSPELL);
//        setMagic((magicNumber >= 1) ? magicNumber : 0);
//        if (makePreview) {
//            cardsToPreview = new Draw(false);
//        }
//        Revolving = 4 - magicNumber;
////        this.rawDescription = String.format("Draw %d Card. NL ffzim:Revolving %d - UnDraw.",
////                DRAW,
////                Revolving);
//        initializeDescription();
//    }
//
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        AbstractCard cardToAdd = new Draw();
//        cardToAdd.baseMagicNumber = this.baseMagicNumber + 1;
//        cardToAdd.magicNumber = cardToAdd.baseMagicNumber;
//        AbstractDungeon.player.hand.addToBottom(cardToAdd);
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new UnDraw2();
//    }
//}