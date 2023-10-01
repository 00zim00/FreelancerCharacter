package ffzim.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.shop.ShopScreen;
import ffzim.BasicMod;
import ffzim.util.CustomTags;

@SpirePatch(
        clz = ShopScreen.class,
        method = "setPrice"
)
public class ItemShipPricePatch {
    @SpirePostfixPatch
    public static void postfix(ShopScreen screen, AbstractCard card) {
        if (card.hasTag(CustomTags.FFITEM)){
            card.price = (int)(card.price * 0.5F);
        }
    }
}