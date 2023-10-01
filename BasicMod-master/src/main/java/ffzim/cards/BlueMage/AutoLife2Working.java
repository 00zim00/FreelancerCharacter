//package ffzim.cards.BlueMage;
//
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.actions.AutoPlayRemoverAction;
//import ffzim.cards.Generic.MagicStone;
//import ffzim.powers.AutoLifePower;
//
//import static ffzim.BasicMod.makeID;
//
//public class AutoLife2Working extends AbstractCard implements StartupCard
//{
//    public static final String ID = makeID("AutoLife2Working");
//    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//    public static final String NAME = cardStrings.NAME;
//    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//    public static final String IMG = "path_to_card_image.png";
//    private static final int COST = 0;
//    private static final int HPPRECENT = 20;
//    private static final int UPG_HPPRECENT= 10;
//
//    public AutoLife2Working() {
//        super(
//                ID,
//                NAME,
//                IMG,
//                COST,
//                DESCRIPTION,
//                CardType.POWER,
//                CardColor.RED,
//                CardRarity.RARE,
//                CardTarget.SELF
//        );
//        baseMagicNumber = magicNumber = HPPRECENT;
//        PurgeField.purge.set(this, true);
//        //AutoplayField.autoplay.set(this, true); // Set the card to autoplay at the start of the run
//        cardsToPreview = new MagicStone();
//        this.tags.add(CardTags.HEALING);
//
////        @Override
////        public void upgrade() {
////            if (!this.upgraded) {
////                this.cardsToPreview.upgrade();
////                this.upgradeName();
////                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
////                this.initializeDescription();
////                this.magicNumber = baseMagicNumber = REGEN+UPG_REGEN;
////            }
////        }
//    }
//
//    @Override
//    public boolean atBattleStartPreDraw() {
//        AbstractPlayer p = AbstractDungeon.player;
//        addToBot(new ApplyPowerAction(p, p, new AutoLifePower(p, this.magicNumber,1), 1));
//        addToBot(new AutoPlayRemoverAction(p, p, this));
//
//        return true; // Return true to indicate that the card should be automatically played at the start of combat
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        // You can apply any additional modifications to the transformed card here
//        // "this" refers to the current card being used
//    }
//
//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeMagicNumber(UPG_HPPRECENT+HPPRECENT);
//            upgradeName();
//            this.cardsToPreview.upgrade();
//            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            AutoplayField.autoplay.set(this, true);
//            this.tags.add(CardTags.HEALING);
//            this.initializeDescription();
//        }
//    }
//
//    @Override
//    public AbstractCard makeCopy() {
//        return new AutoLife2Working();
//    }
//}
