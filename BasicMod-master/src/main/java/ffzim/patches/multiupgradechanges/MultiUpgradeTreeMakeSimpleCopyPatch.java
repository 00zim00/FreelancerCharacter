//package ffzim.patches.multiupgradechanges;
//
//import com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import ffzim.cards.BaseCard;
//
//@SpirePatch(
//        clz = MultiUpgradeTree.class,
//        method = "makeSimpleCopy",
//        paramtypez = {AbstractCard.class}
//)
//public class MultiUpgradeTreeMakeSimpleCopyPatch {
//    private static AbstractCard baseMainCard;
//    private static AbstractCard simpleCopyBase;
//    private static boolean overideColor;
//    private static boolean overideGreen;
//    @SpirePrefixPatch
//    public static AbstractCard Prefix(AbstractCard card) {
//        baseMainCard = MultiUpgradeTree.mainCard;
//        simpleCopyBase = null;
//        System.out.println("Hmm1: " + card + baseMainCard);
//        System.out.println("Hmm2: " + !((BaseCard) card).isCardPreview(card));
//        if (card instanceof BaseCard) {
//            if (((BaseCard) card).isRenderInFullColor()) {
//                overideColor = true;
//            }
//            System.out.println("Test7: " + !((BaseCard) card).isCardPreview(card));
//            if (!((BaseCard) card).isCardPreview(card)) {
//                simpleCopyBase = card;
//            }
//            return card;
//        }
//        return card;
//    }
//
//    @SpirePostfixPatch
//    public static AbstractCard Postfix(AbstractCard card) {
//        if (card instanceof BaseCard) {
//            ((BaseCard) card).materiaLevel = ((BaseCard) baseMainCard).materiaLevel;
//            System.out.println("Hmm3: " + card);
//            System.out.println("Test7: " + (simpleCopyBase != null));
//            if (simpleCopyBase != null) {
//                if (overideColor) {
//                    //((BaseCard) card).setRenderInFullColor(card, true);
//                    overideColor = false;
//                }
//                //AbstractCard copy = card.makeCopy();
//                ((BaseCard) card).setCardPreview(false);
//                simpleCopyBase = null;
//                return card;
//            }
//            return card;
//        }
//        return card;
//    }
//}
