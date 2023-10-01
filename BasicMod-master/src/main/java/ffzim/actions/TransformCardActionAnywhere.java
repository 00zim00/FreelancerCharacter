//package ffzim.actions;
//
//import com.badlogic.gdx.graphics.Color;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.powers.RetainCardPower;
//
//public class TransformCardActionAnywhere extends AbstractGameAction {
//    private AbstractCard replacement;
//
//    public TransformCardActionAnywhere(AbstractCard replacement) {
//        this.replacement = replacement;
//
//        if (Settings.FAST_MODE) {
//            this.startDuration = 0.05F;
//        } else {
//            this.startDuration = 0.15F;
//        }
//        this.duration = this.startDuration;
//    }
//
//    public void update() {
//        if (this.duration == this.startDuration) {
//            AbstractCard targetCard = findCardInPiles(AbstractDungeon.player.drawPile.group);
//            if (targetCard == null) {
//                targetCard = findCardInPiles(AbstractDungeon.player.hand.group);
//            }
//            if (targetCard == null) {
//                targetCard = findCardInPiles(AbstractDungeon.player.discardPile.group);
//            }
//
//            if (targetCard != null) {
//                AbstractCard newCard = replacement.makeStatEquivalentCopy();
//                newCard.current_x = targetCard.current_x;
//                newCard.current_y = targetCard.current_y;
//                newCard.target_x = targetCard.target_x;
//                newCard.target_y = targetCard.target_y;
//                newCard.drawScale = 1.0F;
//                newCard.targetDrawScale = targetCard.targetDrawScale;
//                newCard.angle = targetCard.angle;
//                newCard.targetAngle = targetCard.targetAngle;
//                newCard.superFlash(Color.WHITE.cpy());
//
//                if (targetCard.current_x == -200.0F * Settings.scale) {
//                    AbstractDungeon.player.drawPile.group.set(AbstractDungeon.player.drawPile.group.indexOf(targetCard), newCard);
//                } else if (targetCard.current_x == -400.0F * Settings.scale) {
//                    AbstractDungeon.player.hand.group.set(AbstractDungeon.player.hand.group.indexOf(targetCard), newCard);
//                } else if (targetCard.current_x == 200.0F * Settings.scale) {
//                    AbstractDungeon.player.discardPile.group.set(AbstractDungeon.player.discardPile.group.indexOf(targetCard), newCard);
//                }
//            }
//
//            AbstractDungeon.player.hand.glowCheck();
//            AbstractDungeon.player.hand.applyPowers();
//            //AbstractDungeon.player.hand.autoSetCardPositions = false;
//        }
//        tickDuration();
//    }
//
//    private AbstractCard findCardInPiles(java.util.ArrayList<AbstractCard> pile) {
//        for (AbstractCard card : pile) {
//            if (card.cardID.equals(replacement.cardID)) {
//                return card;
//            }
//        }
//        return null;
//    }
//}
