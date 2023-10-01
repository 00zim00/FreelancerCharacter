package ffzim.cardModifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import ffzim.cards.BaseCard;
import ffzim.icons.*;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;

public class MateriaMod extends AbstractCardModifier {
    private static final Texture apBarEmptyIcon = TextureLoader.getTexture(ApBarEmptyIcon.ICON_LOCATION);
    private static final Texture apBar20 = TextureLoader.getTexture(ApBar20Icon.ICON_LOCATION);
    private static final Texture apBar40 = TextureLoader.getTexture(ApBar40Icon.ICON_LOCATION);
    private static final Texture apBar50 = TextureLoader.getTexture(ApBar50Icon.ICON_LOCATION);
    private static final Texture apBar60 = TextureLoader.getTexture(ApBar60Icon.ICON_LOCATION);
    private static final Texture apBar80 = TextureLoader.getTexture(ApBar80Icon.ICON_LOCATION);
    private static final Texture apBarFullIcon = TextureLoader.getTexture(ApBarFullIcon.ICON_LOCATION);
    private static final Texture cardLevelIcon = TextureLoader.getTexture(CardLevelIcon.ICON_LOCATION);
    private static final float texWidth = apBarFullIcon.getWidth();
    private static final float texHeight = apBarFullIcon.getHeight();
    private static float offsetX = 134;
    private static float offsetY = 60 + texHeight;
    private static float textOffsetX = 0;
    private static float textOffsetY = 0;

    public MateriaMod() {
        super();
    }

    public static String MOD_ID = makeID("MateriaMod");
    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.applyPowers();
    }

    public void onRemove() {
    }
    @Override
    public void onRender(AbstractCard card, SpriteBatch sb) {
        if (card instanceof BaseCard){
            BitmapFont font = FontHelper.cardTitleFont;
            font.getData().setScale(0.75f);
            BitmapFont levelFont = FontHelper.turnNumFont;
            levelFont.getData().setScale(0.5f);
            BaseCard tempCard = (BaseCard) card;
            tempCard.APRefreshVaules(card);
            int TotalLevel = ((BaseCard) card).cardLevel + 1;
            String TotalLevelString = Integer.toString(TotalLevel);
            ExtraIcons.renderIcon(card, cardLevelIcon, 0, 0, Color.WHITE,TotalLevelString,levelFont, 0, 3, Color.WHITE);
            int nextApMax = ((BaseCard) card).getNextApLevel(card);
            int ApMax = ((BaseCard) card).getMaxApLevel(card);
            int APFraction = nextApMax / 5;
            if (card.misc != ApMax){
                if (card.misc <= APFraction / 2){
                    ExtraIcons.renderIcon(card, apBarEmptyIcon, offsetX, offsetY, Color.WHITE,card.misc + "/" + nextApMax,font, textOffsetX, textOffsetY, Color.WHITE);
                }else if (card.misc <= APFraction && tempCard.misc <= APFraction/2){
                    ExtraIcons.renderIcon(card, apBar20, offsetX, offsetY, Color.WHITE,card.misc + "/" + nextApMax,font, textOffsetX, textOffsetY, Color.WHITE);
                }else if (tempCard.misc <= APFraction * 2){
                    ExtraIcons.renderIcon(card, apBar40, offsetX, offsetY, Color.WHITE,card.misc + "/" + nextApMax,font, textOffsetX, textOffsetY, Color.WHITE);
                }else if (tempCard.misc <= APFraction * 2.5){
                    ExtraIcons.renderIcon(card, apBar50, offsetX, offsetY, Color.WHITE,card.misc + "/" + nextApMax,font, textOffsetX, textOffsetY, Color.WHITE);
                }else if (tempCard.misc <= APFraction * 3){
                    ExtraIcons.renderIcon(card, apBar60, offsetX, offsetY, Color.WHITE,card.misc + "/" + nextApMax,font, textOffsetX, textOffsetY, Color.WHITE);
                }else{
                    ExtraIcons.renderIcon(card, apBar80, offsetX, offsetY, Color.WHITE,card.misc + "/" + nextApMax,font, textOffsetX, textOffsetY, Color.WHITE);
                }
            }else{
                ExtraIcons.renderIcon(card, apBarFullIcon, offsetX, offsetY, Color.WHITE);
            }


        }
    }

//    @Override
//    public void onRender(AbstractCard card, SpriteBatch sb) {
//        if (card instanceof BaseCard){
//            BitmapFont font = FontHelper.cardTitleFont;
//            font.getData().setScale(0.75f);
//            BitmapFont levelFont = FontHelper.turnNumFont;
//            levelFont.getData().setScale(0.5f);
//            BaseCard tempCard = (BaseCard) card;
//            int TotalLevel = ((BaseCard) card).cardLevel + 1;
//            String TotalLevelString = Integer.toString(TotalLevel);
//            ExtraIcons.renderIcon(card, cardLevelIcon, 0, 0, Color.WHITE,TotalLevelString,levelFont, 0, 3, Color.WHITE);
//            int apMax = ((BaseCard) card).secondMagicNumber;
//            int APFractionMax = ((BaseCard) card).getMaxApLevel(card);
//            int APFraction = APFractionMax / 5;
//            if (card.misc <= APFraction / 2){
//                ExtraIcons.renderIcon(card, apBarEmptyIcon, offsetX, offsetY, Color.WHITE,card.misc + "/" + apMax,font, textOffsetX, textOffsetY, Color.WHITE);
//            }else if (card.misc <= APFraction && tempCard.misc <= APFraction/2){
//                ExtraIcons.renderIcon(card, apBar20, offsetX, offsetY, Color.WHITE,card.misc + "/" + apMax,font, textOffsetX, textOffsetY, Color.WHITE);
//            }else if (tempCard.misc <= APFraction * 2){
//                ExtraIcons.renderIcon(card, apBar40, offsetX, offsetY, Color.WHITE,card.misc + "/" + apMax,font, textOffsetX, textOffsetY, Color.WHITE);
//            }else if (tempCard.misc <= APFraction * 3){
//                ExtraIcons.renderIcon(card, apBar60, offsetX, offsetY, Color.WHITE,card.misc + "/" + apMax,font, textOffsetX, textOffsetY, Color.WHITE);
//            }else if (tempCard.misc <= APFraction * 4){
//                ExtraIcons.renderIcon(card, apBar80, offsetX, offsetY, Color.WHITE,card.misc + "/" + apMax,font, textOffsetX, textOffsetY, Color.WHITE);
//            }else if (tempCard.misc >= APFractionMax){
//                ExtraIcons.renderIcon(card, apBarFullIcon, offsetX, offsetY, Color.WHITE);
//            }
//        }
//    }
//


    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, MateriaMod.MOD_ID);
    }

    @Override
    public String identifier(AbstractCard card) {
        return MOD_ID;
    }


    public static boolean hasMateriaMod(AbstractCard card) {
        return CardModifierManager.hasModifier(card, MOD_ID);
    }

    @Override
    public AbstractCardModifier makeCopy() {
            return new MateriaMod();
    }
}