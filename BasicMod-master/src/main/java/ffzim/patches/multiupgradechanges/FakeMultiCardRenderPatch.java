//package ffzim.patches.multiupgradechanges;
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree;
//import com.evacipated.cardcrawl.mod.stslib.util.CardVertex;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ShaderHelper;
//import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
//import ffzim.BasicMod;
//import ffzim.cards.BaseCard;
//import ffzim.cards.BlackMage.Fire;
//import ffzim.cards.WhiteMage.Gems.HealingMateria;
//import ffzim.util.CustomTags;
//
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Iterator;
//
//import static com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree.*;
//import static ffzim.BasicMod.makeID;
//import static ffzim.util.TextureLoader.getCardTextureString;
//
//@SpirePatch(
//        clz = MultiUpgradeTree.class,
//        method = "open",
//        paramtypez = {AbstractCard.class, boolean.class}
//)
//public class FakeMultiCardRenderPatch {
//    private static AbstractCard baseMainCard;
//    private static AbstractCard simpleCopyBase;
//    private static boolean overideColor;
//    private static boolean overideGreen;
//
//    @SpirePrefixPatch
//    public static Boolean Prefix(AbstractCard card, boolean manualMainCard) {
//        baseMainCard = null;;
//        if (card instanceof BaseCard) {
//
//            if (!((BaseCard) card).isCardPreview(card)) {
//                baseMainCard = card;
//                MultiUpgradeTree.manualMainCard = true;
//                MultiUpgradeTree.mainCard = card.makeStatEquivalentCopy();
//                return true;
//            }
//            return manualMainCard;
//        }
//        return manualMainCard;
//    }
//    }
