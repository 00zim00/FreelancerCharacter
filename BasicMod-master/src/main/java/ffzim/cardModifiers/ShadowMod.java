package ffzim.cardModifiers;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardPowerTip;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import ffzim.util.CustomTags;

import static basemod.BaseMod.addKeyword;
import static basemod.helpers.CardModifierManager.addModifier;
import static basemod.helpers.CardModifierManager.removeSpecificModifier;
import static ffzim.BasicMod.makeID;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class ShadowMod extends AbstractCardModifier {

    private static final String SHADOW_ID = makeID("ShadowMod");

    public ShadowMod() {
        super();
    }

    @Override
    public String modifyName(String rawDescription, AbstractCard card) {
        return "[#c639de]" + card.name;

//        if (!card.hasTag(CustomTags.FFSHADOW)){
//            return "[#c639de]" + card.name;
//        }else{
//            return card.name;
//        }
        //return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[2];
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        //return rawDescription + "NL #yShadow.";

        //Keyword shadowKeyword = CardLibrary.keywords.get("ffzim:shadow");

        //return rawDescription + " NL " + Keywords.getKeywordDescription("ffzim:shadow");
        //return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("GemModifiers")).TEXT[2];
        return rawDescription;
    }


    @Override
    public void onInitialApplication(AbstractCard card) {
//        if (!card.hasTag(CustomTags.FFSHADOW)) {
            card.purgeOnUse = true;
            card.isEthereal = true;
            card.tags.add(CustomTags.FFSHADOW);
            //card.keywords.add("shadow");
            //card.keywords.add(makeID("Shadow"));

//            card.keywords.add("ffzim:Shadow");
//            card.keywords.add("Shadow");
//            addKeyword("Shadow",card);
//            card.keywords.add("shadow");
            card.initializeDescription();
            card.applyPowers();
//        }
    }
@Override
public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {


        //AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
        }





    // Apply the modifier to a card
    public static void applyToCard(AbstractCard card) {
        //CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        //Keyword keyword = cardStrings.KEYWORDS.get(KEYWORD_ID);
//        if (!card.hasTag(CustomTags.FFSHADOW)){
//            card.purgeOnUse = true;
//            card.isEthereal = true;
//            card.tags.add(CustomTags.FFSHADOW);
//            card.initializeDescription();
//            card.applyPowers();
//        }
        if (!card.hasTag(CustomTags.FFSHADOW)){
            addModifier(card, new ShadowMod());
        }
    }

    public static void removeFromCard(AbstractCard card) {
       // card.applyPowers();
       // removeSpecificModifier(card, ShadowMod.SHADOW_ID);
    //AbstractCardModifier.removeSpecificModifier(card, SHADOW_ID);
    }

    @Override
    public AbstractCardModifier makeCopy() {
            return new ShadowMod();
    }
}