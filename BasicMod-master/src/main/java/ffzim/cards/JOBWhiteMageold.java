//package ffzim.cards;
//
//import basemod.abstracts.CustomCard;
//import com.badlogic.gdx.math.MathUtils;
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
//
//public class JOBWhiteMageold extends CustomCard implements OnObtainCard {
//
//    private static final String cardID = "JOBWhiteMageOld";
//    private static final String NAME = "JOB White Mage";
//    private static final String IMG_PATH = "images/events/vampires.jpg";
//    private static final int COST = 0;
//    private static final String DESCRIPTION = "Your card description.";
//
//    // Define your card properties here instead of CardInfo
//    private static final AbstractCard.CardType TYPE = AbstractCard.CardType.SKILL;
//    private static final AbstractCard.CardColor COLOR = CardColor.RED; // Assuming you have defined CardColor.RED
//    private static final AbstractCard.CardRarity RARITY = CardRarity.SPECIAL;
//    private static final AbstractCard.CardTarget TARGET = AbstractCard.CardTarget.SELF;
//
//    public JOBWhiteMageold() {
//        super(cardID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//    }
//
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//
//    }
//
//    @Override
//    public void upgrade() {
//        // Empty upgrade method since the card doesn't have upgrades
//    }
//
//    @Override
//    public void onObtainCard() {
//        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
//    //    replaceAttacks();
//        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
//    }
//
//        @Override
//    public AbstractCard makeCopy() { //Optional
//        return new JOBWhiteMageold();
//    }
//}
//
