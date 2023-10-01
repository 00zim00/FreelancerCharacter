package ffzim.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import ffzim.BasicMod;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "getRandomChest"
)
public class AbstractChestPatch {
    @SpirePostfixPatch
    public static AbstractChest Postfix(AbstractChest __result) {
        String chestClassName = __result.getClass().getSimpleName();
        BasicMod.cachedChest = chestClassName;
        System.out.println("result0: " + chestClassName);
        return __result;
    }
}