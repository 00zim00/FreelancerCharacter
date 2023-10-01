//package ffzim.patches.multiupgradechanges;
//
//import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree;
//import com.evacipated.cardcrawl.mod.stslib.util.Grayscale;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.helpers.ShaderHelper;
//import ffzim.cards.BaseCard;
//import ffzim.util.CustomTags;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//import static ffzim.BasicMod.makeID;
//import static ffzim.util.TextureLoader.getCardTextureString;
//
//@SpirePatch(
//        clz = AbstractCard.class,
//        method = "render",
//        paramtypez = {SpriteBatch.class}
//)
//public class RenderAbstractCardGrayscalePatchBU {
//    @SpirePrefixPatch
//    public static SpriteBatch Prefix(AbstractCard __instance, SpriteBatch sb) {
//
//        if (MultiUpgradeTree.mainCard != null && __instance instanceof BaseCard ){
//            BaseCard MainCard = ((BaseCard) MultiUpgradeTree.mainCard);
//            BaseCard tempCard = ((BaseCard) __instance);
//            int mainCardTimes = MultiUpgradeTree.mainCard.timesUpgraded;
//
//        if (__instance instanceof BaseCard && __instance.hasTag(CustomTags.FFMATERIA)) {
//            if (((BaseCard) __instance).materiaLevel >=1){
//                MultiCardPreview.clear(__instance);
//            }
//            if (((BaseCard) __instance).materiaLevel == 0){
//                tempCard = (BaseCard) tempCard.makeCopy();
//
//                tempCard.timesUpgraded = 0;
//                MultiUpgradeTree.mainCard.rawDescription = tempCard.rawDescription;
//                __instance.rawDescription = tempCard.rawDescription;
//
//                    String[] cardIDParts = tempCard.cardID.split(":");
//                    if (cardIDParts.length > 1) {
//                        String cardName = cardIDParts[1];
//                        String textureString = getCardTextureString(makeID(tempCard.cardID), tempCard.type);
//                        String modifiedTextureString = textureString.replace("default", cardName);
//                        Path path = Paths.get(modifiedTextureString);
//                        //if (Files.exists(path)) {
//                        MainCard.loadCardImage(modifiedTextureString);
//                        //}
//                    }
//                }
//
//
//                if (__instance.name.equals("Cura")) {
//                    tempCard.setRenderInGrayscale(tempCard, true);
//                }
//                if (__instance.name.equals("Curaga")) {
//                    tempCard.setRenderInGrayscale(tempCard, true);
//                }
//
//            }else if (mainCardTimes == 1) {
//                if (((BaseCard) __instance).materiaLevel >1) {
//                    tempCard.setRenderInGrayscale(tempCard, true);
//                    tempCard.setRenderInFullColor(tempCard, false);
//                }else{
//                    tempCard.setRenderInGrayscale(tempCard, false);
//                    tempCard.setRenderInFullColor(tempCard, true);
//                }
//            }else if (mainCardTimes == 2) {
//                if (((BaseCard) __instance).materiaLevel >2) {
//                    tempCard.setRenderInGrayscale(tempCard, true);
//                    tempCard.setRenderInFullColor(tempCard, false);
//                }else{
//                    tempCard.setRenderInGrayscale(tempCard, false);
//                    tempCard.setRenderInFullColor(tempCard, true);
//                }
//            } else if (mainCardTimes >= 3) {
//                if (((BaseCard) __instance).materiaLevel >3) {
//                    tempCard.setRenderInGrayscale(tempCard, true);
//                    tempCard.setRenderInFullColor(tempCard, false);
//                }else{
//                    tempCard.setRenderInGrayscale(tempCard, false);
//                    tempCard.setRenderInFullColor(tempCard, true);
//                }
//            }
//
//                if (tempCard.isRenderInFullColor() || tempCard.isRenderInFullColor() && !((BaseCard) __instance).isRenderInGrayscale()) {
//                    System.out.println("ISA: " + (sb.isDrawing()));
//                    if (sb.isDrawing()) {
//                        sb.end();
//                        sb.setShader(null); // Set shader to null to use the default shader
//                        sb.begin();
//                    }
//
//                } else if (((BaseCard) __instance).isRenderInGrayscale()  || tempCard.isRenderInGrayscale()) {
//                    System.out.println("ISB: ");
//                    sb.end();
//                    sb.setShader(Grayscale.program);
//                    sb.begin();
//                }
//
//
//
//            return sb;
//        }
//        if (__instance instanceof BaseCard) {
//            if (((BaseCard) __instance).isRenderInGrayscale()) {
//                sb.end();
//                sb.setShader(Grayscale.program);
//                sb.begin();
//            }
//        }
//        return sb;
//    }
//
//    @SpirePostfixPatch
//    public static void Postfix(AbstractCard __instance, SpriteBatch sb) {
//        if (__instance instanceof BaseCard) {
//            if (((BaseCard) __instance).isRenderInGrayscale()) {
//                ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
//            }
//
//        }
//    }
//}
