//package ffzim.cards;
//
//import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
//import com.badlogic.gdx.math.MathUtils;
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
//import com.megacrit.cardcrawl.actions.utility.WaitAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
//import ffzim.cards.Basics.CLDefend;
//import ffzim.cards.Basics.CLStrike;
//import ffzim.util.CardInfo;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//
//import static ffzim.BasicMod.makeID;
//
//public class JOBWhiteMageWorking extends BaseCard implements OnObtainCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "JOBWhiteMage",
//            0,
//            CardType.POWER,
//            CardTarget.SELF,
//            CardRarity.COMMON,
//            MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//
//    public JOBWhiteMageWorking() {
//        super(cardInfo, true);
//        setInnate(true);
//        MultiCardPreview.add(this, new CLStrike(), new CLDefend());
//        this.tags.add(CardTags.HEALING);
//
//    }
//
//    // PostCampfire boolean
//    // PostCampfire boolean
//    // PostCampfire boolean
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//    }
//
//    @Override
//    public void onObtainCard() {
//        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
//        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;
//
//        int numStrike = 0;
//        for (AbstractCard card : masterDeck) {
//            if (card.tags.contains(CardTags.STARTER_STRIKE)) {
//                numStrike++;
//            }
//        }
//        int numDefend = 0;
//        for (AbstractCard card : masterDeck) {
//            if (card.tags.contains(CardTags.STARTER_DEFEND)) {
//                numDefend++;
//            }
//        }
//        // Remove cards with STARTER_STRIKE tag from the masterDeck using an iterator
//        Iterator<AbstractCard> iterator = masterDeck.iterator();
//        while (iterator.hasNext()) {
//            AbstractCard card = iterator.next();
//            if (card.tags.contains(CardTags.STARTER_STRIKE)) {
//                iterator.remove();
//            }
//            if (card.tags.contains(CardTags.STARTER_DEFEND)) {
//                iterator.remove();
//            }
//        }
//
//        for (int i = 0; i < numStrike; i++) {
//            AbstractCard cardToAdd = new CLStrike(); // Replace "Cure" with the class of your desired card
//            float cardX = Settings.WIDTH / 2.0F;
//            float cardY = Settings.HEIGHT / 2.0F;
//            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(cardToAdd, cardX, cardY));
//            CardCrawlGame.sound.playA("CARD_OBTAIN", MathUtils.random(-0.2F, -0.3F));
//            ;
//
//        }
//        for (int i = 0; i < numDefend; i++) {
//            AbstractCard cardToAdd = new CLDefend(); // Replace "CLDefend" with the class of your desired card
//            float cardX = Settings.WIDTH / 2.0F;
//            float cardY = Settings.HEIGHT / 2.0F;
//            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(cardToAdd, cardX, cardY));
//            CardCrawlGame.sound.playA("CARD_OBTAIN", MathUtils.random(-0.2F, -0.3F));
//        }
//        AbstractDungeon.actionManager.addToBottom(new WaitAction(3.0F));
//
//        for (AbstractCard card : masterDeck) {
//            if (card instanceof JOBWhiteMageWorking) {
////                AbstractDungeon.effectsQueue.add(new com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect(new JOBWhiteMage()));
//                AbstractDungeon.player.masterDeck.removeCard(card);
//                break; // Stop the loop once the card is removed
//            }
//        }
//    }
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new JOBWhiteMageWorking();
//    }
//}
//
