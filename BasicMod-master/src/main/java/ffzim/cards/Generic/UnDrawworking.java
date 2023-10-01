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
//;
//
//public class UnDrawworking extends BaseCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "UnDraw",
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
//
//    public UnDrawworking() {
//        this(true,4);
//    }
//
//
//    public UnDrawworking(boolean makePreview, int baseSecondMagicNumber) {
//        super(cardInfo, true);
//        selfRetain = true;
//        PurgeField.purge.set(this, true);
//        tags.add(CustomTags.FFSPELL);
//        setMagic((magicNumber >= 1) ? magicNumber : 0);
//        setSecondMagic(4 - magicNumber);
//        this.baseSecondMagicNumber = baseSecondMagicNumber;
//        if (makePreview) {
//            cardsToPreview = new Draw(false,4-magicNumber);
//        }
//        super.initializeDescription();
//    }
//
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        AbstractCard cardToAdd = new Draw(true,baseSecondMagicNumber-1);
//        cardToAdd.baseMagicNumber = this.baseMagicNumber + 1;
//        cardToAdd.magicNumber = cardToAdd.baseMagicNumber;
//        cardToAdd.initializeDescription();
//        AbstractDungeon.player.hand.addToBottom(cardToAdd);
//    }
//
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new UnDrawworking();
//    }
//}