//package ffzim.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.rooms.AbstractRoom;
//
//public class CampfireAction extends AbstractGameAction {
//    @Override
//    public void update() {
//        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
//            isDone = true;
//            return;
//        }
//
//        AbstractDungeon.overlayMenu.proceedButton.hide();
//        AbstractDungeon.overlayMenu.cancelButton.hide();
//        AbstractDungeon.getCurrRoom().phase = AbstractRoom.RoomPhase.EVENT;
//        AbstractDungeon.getCurrRoom().event = null;
//
//        enterCampfire();
//
//        isDone = true;
//    }
//
//    private void enterCampfire() {
//        AbstractDungeon.dynamicBanner.hide();
//        AbstractDungeon.overlayMenu.hideCombatPanels();
//        AbstractDungeon.previousScreen = null;
//        AbstractDungeon.getCurrRoom().monsters = null;
//        AbstractDungeon.getCurrRoom().rewards = null;
//        AbstractDungeon.dungeonMapScreen.dismissable = true;
//        AbstractDungeon.fadeIn();
//        AbstractDungeon.isScreenUp = true;
//        AbstractDungeon.screen = AbstractDungeon.CurrentScreen.NONE;
//        AbstractDungeon.overlayMenu.showBlackScreen();
//        AbstractDungeon.overlayMenu.cancelButton.hide();
//        AbstractDungeon.overlayMenu.proceedButton.hide();
//        AbstractDungeon.overlayMenu.showCampfireMenu();
//    }
//}
