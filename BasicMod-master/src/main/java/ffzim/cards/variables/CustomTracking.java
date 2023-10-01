//package ffzim.cards.variables;
//
//import basemod.BaseMod;
//import basemod.interfaces.OnStartBattleSubscriber;
//import basemod.interfaces.PostInitializeSubscriber;
//import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.rooms.AbstractRoom;
//import ffzim.powers.JumpDodgePower;
//
//@SpireInitializer
//public class CustomTracking implements
//        PostInitializeSubscriber,
//        OnStartBattleSubscriber
//{
//    public static boolean combatStarted = false;
//
//    public static void initialize() {
//        BaseMod.subscribe(new CustomTracking());
//    }
//
//    @Override
//    public void receivePostInitialize()
//    {
//
//    }
//
//
//    public void onReceivePower(AbstractPower power, AbstractCreature source) {
//        super.onReceivePower(power, source);
//
//        // Custom logic when this character gains a power
//        if (power instanceof MyCustomPower) {
//            // Execute logic for the specific power gained
//        }
//    }
//    public void receiveOnBattleStart(AbstractRoom abstractRoom)
//    {
//        CardGroup[] cardGroups = new CardGroup[] {
//                AbstractDungeon.player.drawPile,
//                AbstractDungeon.player.hand,
//                AbstractDungeon.player.discardPile,
//                AbstractDungeon.player.exhaustPile
//        };
//
//
//
//
//    public static void onCombatStart(AbstractRoom room) {
//        combatStarted = true;
//        // Other logic related to combat start
//    }
//
//    public static void onCombatEnd() {
//        if (combatStarted) {
//            // Perform actions related to combat end
//            // Reset variables if needed
//            combatStarted = false;
//        }
//    }
//}