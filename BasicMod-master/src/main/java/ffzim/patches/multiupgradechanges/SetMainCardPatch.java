//package ffzim.patches.multiupgradechanges;
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree;
//import com.evacipated.cardcrawl.mod.stslib.util.CardVertex;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import ffzim.cards.BaseCard;
//import ffzim.util.CustomTags;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Iterator;
//
//import static com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree.cardGraph;
//import static com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree.takenList;
//import static ffzim.BasicMod.makeID;
//import static ffzim.util.TextureLoader.getCardTextureString;
//
//@SpirePatch(
//        clz = MultiUpgradeTree.class,
//        method = "renderCards",
//        paramtypez = {SpriteBatch.class}
//)
//public class SetMainCardPatch {
//    private static AbstractCard baseMainCard;
//    private static boolean overideColor;
//    private static boolean overideGreen;
//    @SpirePrefixPatch
//    public static void Prefix(SpriteBatch sb) {
//        baseMainCard = MultiUpgradeTree.mainCard;
//        System.out.println("Test3: " + (baseMainCard != null));
//        AbstractCard FakeCard = MultiUpgradeTree.mainCard;
//        System.out.println("Test4.5: " + FakeCard);
//        System.out.println("Test4.7: " + (FakeCard != null));
//        if (FakeCard != null) {
//            if (FakeCard instanceof BaseCard && FakeCard.hasTag(CustomTags.FFMATERIA)) {
//                FakeCard.timesUpgraded = 0;
//                System.out.println("Test55: " + FakeCard);
//                AbstractCard thisInputCard0 = MultiUpgradeTree.mainCard;
//                BaseCard thisInputCard = ((BaseCard) thisInputCard0);
//                MultiUpgradeTree.mainCard.rawDescription = FakeCard.rawDescription;
//
//                thisInputCard.rawDescription = FakeCard.rawDescription;
//
//                String[] cardIDParts = FakeCard.cardID.split(":");
//                if (cardIDParts.length > 1) {
//                    String cardName = cardIDParts[1];
//                    System.out.println("Test66: " + FakeCard.cardID);
//                    String textureString = getCardTextureString(makeID(FakeCard.cardID), FakeCard.type);
//                    System.out.println("Test77: " + textureString);
//                    String modifiedTextureString = textureString.replace("default", cardName);
//                    System.out.println("Test77: " + modifiedTextureString);
//                    Path path = Paths.get(modifiedTextureString);
//                    System.out.println("Check Boo: " + Files.exists(path));
//                    //if (Files.exists(path)) {
//                    thisInputCard.loadCardImage(modifiedTextureString);
//                    //}
//                }
//
//                //inputCard.setPreview();
//                thisInputCard.applyPowers();
//                thisInputCard.initializeDescription();
//                MultiUpgradeTree.mainCard.applyPowers();
//                MultiUpgradeTree.mainCard.initializeDescription();
//                MultiUpgradeTree.mainCard = thisInputCard;
//            }
//        }
//    }
//
//
//    @SpirePostfixPatch
//    public static void Postfix(SpriteBatch sb) {
//
//    }
//}
