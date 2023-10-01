//package ffzim.patches;
//
//import com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree;
//import com.evacipated.cardcrawl.mod.stslib.util.CardVertex;
//import com.evacipated.cardcrawl.modthespire.lib.*;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import javassist.CtBehavior;
//import ffzim.util.CustomTags;
//
//import static com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree.takenList;
//
//@SpirePatch(
//        clz = MultiUpgradeTree.class, // The class containing the method you want to patch
//        method = "renderCards" // The method you want to patch
//)
//public class CustomRenderCardsPatch {
//    // Your patch code goes here
//
//    @SpireInsertPatch(
//            locator = CustomLocator.class, // Create a custom locator class for precise positioning
//            localvars = { "v" } // Add any local variables you need here
//    )
//    public static void InsertConditionalCheck(CardVertex v) {
//        // Add your conditional check here
//        if (takenList.contains(v.card) && !(v.card.hasTag(CustomTags.FFMATERIA))) {
//            // Your patch code goes here
//            // This is where you should place your conditional patch logic
//        }
//    }
//
//    public static class CustomLocator extends SpireInsertLocator {
//        @Override
//        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
//            // Implement your custom logic to locate the insertion point here
//            // For example, you can look for a specific method call or line of code
//            // Return an array of integers specifying the insertion point(s)
//
//            // For illustration purposes, assume you want to insert before a specific method call
//            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "renderCardTip");
//            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
//        }
//    }
//}
