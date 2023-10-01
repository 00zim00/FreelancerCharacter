//package ffzim.cards.Chocobo.Healer;
//
//import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.cards.BaseCard;
//import ffzim.util.CardInfo;
//import ffzim.util.CustomTags;
//
//import static ffzim.BasicMod.makeID;
//
//public class ChocoCure extends BaseCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "ChocoCure",
//            0,
//            CardType.SKILL,
//            CardTarget.SELF,
//            CardRarity.SPECIAL,
//            CardColor.COLORLESS); 
//
//
//
//    public static final String ID = makeID(cardInfo.baseId);
//    private static final int TEMPHP = 3;
//    private static final int UPG_TEMPHP = 3;
//
//    public ChocoCure() {
//        super(cardInfo, true);
//        setMagic(TEMPHP, UPG_TEMPHP);
//        PurgeField.purge.set(this, true);
//        tags.add(CustomTags.FFSPELL);
//    }
//
//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeName();
//            upgradeMagicNumber(UPG_TEMPHP);
//            this.rawDescription = cardStrings.DESCRIPTION;
//            this.initializeDescription();
//        }
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new ChocoCure();
//    }
//}