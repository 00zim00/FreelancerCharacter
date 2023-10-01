//package ffzim.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.localization.UIStrings;
//import ffzim.cards.Generic.MagicStone;
//
//import java.util.ArrayList;
//
//public class TransmuteCardAction extends AbstractGameAction {
//    private AbstractPlayer p = AbstractDungeon.player;
//    private static final String ID = "TransmuteCardAction";
//    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
//    public static final String[] TEXT = uiStrings.TEXT;
//
//    private AbstractCard targetCard;
//
//    public TransmuteCardAction(AbstractCard targetCard) {
//        this.targetCard = targetCard;
//    }
//
//    public void update() {
//        if (targetCard != null) {
//
//            // Change the targeted card to MagicStone
//            AbstractCard magicStone = new MagicStone();
////            magicStone.current_x = targetCard.current_x;
////            magicStone.current_y = targetCard.current_y;
////            magicStone.target_x = targetCard.target_x;
////            magicStone.target_y = targetCard.target_y;
////            magicStone.drawScale = targetCard.drawScale;
////            magicStone.targetDrawScale = targetCard.targetDrawScale;
////            magicStone.angle = targetCard.angle;
////            magicStone.targetAngle = targetCard.targetAngle;
//
//            // Replace the targeted card with MagicStone
//            ArrayList<AbstractCard> handGroup = targetCard.p.hand.group;
//            int index = handGroup.indexOf(targetCard);
//            if (index >= 0) {
//                handGroup.set(index, magicStone);
//            }
//        }
//
//        isDone = true;
//    }
//}
