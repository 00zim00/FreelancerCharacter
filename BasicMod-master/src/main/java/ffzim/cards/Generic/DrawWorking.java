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
//public class DrawWorking extends BaseCard {
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
//    }
//
//    public DrawWorking() {
//        this(true, 4);
//    }
//
//    public DrawWorking(boolean makePreview, int baseSecondMagicNumber) {
//        super(cardInfo, true);
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
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        AbstractCard cardToAdd = new UnDraw(true,baseSecondMagicNumber-1);
//        cardToAdd.baseMagicNumber = this.baseMagicNumber + 1;
//        cardToAdd.magicNumber = cardToAdd.baseMagicNumber;
//        cardToAdd.initializeDescription();
//        AbstractDungeon.player.hand.addToBottom(cardToAdd);
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new DrawWorking();
//    }
//}