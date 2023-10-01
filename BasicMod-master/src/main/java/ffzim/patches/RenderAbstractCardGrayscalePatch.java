package ffzim.patches;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree;
import com.evacipated.cardcrawl.mod.stslib.util.Grayscale;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ShaderHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import ffzim.cards.BaseCard;
import ffzim.util.CustomTags;
import ffzim.util.Shadowify;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;
import static ffzim.util.TextureLoader.getCardTextureString;

@SpirePatch(
        clz = AbstractCard.class,
        method = "render",
        paramtypez = {SpriteBatch.class}
)
public class RenderAbstractCardGrayscalePatch {
    @SpirePrefixPatch
    public static SpriteBatch Prefix(AbstractCard __instance, SpriteBatch sb) {
        if (__instance instanceof BaseCard) {
            if (((BaseCard) __instance).isRenderInGrayscale()) {
                sb.end();
                sb.setShader(Grayscale.program);
                sb.begin();
            }
            if (((BaseCard) __instance).hasTag(CustomTags.FFSHADOW) && !((BaseCard) __instance).isRenderInGrayscale()) {
                sb.end();
                sb.setShader(Shadowify.program);
                sb.begin();
            }
        } else {
            if (__instance.hasTag(CustomTags.FFSHADOW)) {
                sb.end();
                sb.setShader(Shadowify.program);
                sb.begin();
            }
        }
        return sb;
    }

        @SpirePostfixPatch
        public static void Postfix (AbstractCard __instance, SpriteBatch sb){
            if (__instance instanceof BaseCard) {
                if (((BaseCard) __instance).isRenderInGrayscale()) {
                    ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
                }
                if (((BaseCard) __instance).hasTag(CustomTags.FFSHADOW) && !((BaseCard) __instance).isRenderInGrayscale()) {
                    ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
                }
            } else {
                if (__instance.hasTag(CustomTags.FFSHADOW)) {
                    ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
                }
            }
    }
}
