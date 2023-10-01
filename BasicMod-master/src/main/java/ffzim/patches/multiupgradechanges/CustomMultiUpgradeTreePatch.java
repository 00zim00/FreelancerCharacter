//package ffzim.patches.multiupgradechanges;
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree;
//import ffzim.util.CustomTags;
//import javassist.CannotCompileException;
//import javassist.expr.ExprEditor;
//import javassist.expr.MethodCall;
//
//import com.evacipated.cardcrawl.modthespire.lib.*;
//
//import static com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree.takenList;
//import static ffzim.BasicMod.modID;
//
//@SpirePatch(
//        clz = MultiUpgradeTree.class, // The class you're patching
//        method = "renderCards", // The method you're patching
//        paramtypez = {SpriteBatch.class}
//)
//public class CustomMultiUpgradeTreePatch {
//
//    @SpireInstrumentPatch // Indicates this is an instrument patch
//    public static ExprEditor Instrument() {
//        return new ExprEditor() {
//            @Override
//            public void edit(MethodCall m) throws CannotCompileException {
//                // Check if the method call matches the one you want to modify
//                if (m.getMethodName().equals("contains") && m.getClassName().equals("java.util.ArrayList")) {
//                    // Replace the line with your custom code
//                    m.replace("if (takenList.contains(v.card) && !(c.hasTag("+modID+".util.CustomTags.FFMATERIA))) { $_ = $proceed($$); }");
//                }
//            }
//        };
//    }
//}
