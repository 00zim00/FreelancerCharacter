package ffzim.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import ffzim.cards.BaseCard;
import ffzim.cards.variables.UniqueSaveable;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.colorlessCardPool;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.shuffleRng;
import static com.megacrit.cardcrawl.helpers.CardLibrary.LibraryType.COLORLESS;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "getColorlessCardFromPool"
)
public class ColorlessCardFilterUniqueItemPatch {
    @SpirePostfixPatch
    public static AbstractCard Postfix(AbstractCard card, AbstractCard.CardRarity rarity) {        ArrayList<AbstractCard> cardsToRemove = new ArrayList<>();
        boolean Sozu = !AbstractDungeon.player.hasRelic("Sozu");
        float commonChance;
        ArrayList<AbstractCard> commonColorlessCards = new ArrayList<>();
        if (rarity == AbstractCard.CardRarity.UNCOMMON && ((card.hasTag(CustomTags.FFITEM) || card.hasTag(CustomTags.FFMATERIA)))){
            int actNum = AbstractDungeon.actNum;
            int ascensionLevel = AbstractDungeon.ascensionLevel;
            if (actNum == 1) {
                commonChance = 0.62F;
            } else if (actNum == 2) {
                commonChance = 0.56F;
                if (ascensionLevel >= 12) {
                    commonChance = 0.28F;
                }
            } else if (actNum == 3) {
                commonChance = 0.25F;
                if (ascensionLevel >= 12) {
                    commonChance = 0.15F;
                }
            }else{
                commonChance = 0.0F;
            }
            float rngRoll = (float) (Math.random() * 100F) + 1F;
            if (rngRoll <= (commonChance * 100F)){
                rarity = AbstractCard.CardRarity.COMMON;
                for (AbstractCard commonCard : CardLibrary.getCardList(COLORLESS)) {
                    if (commonCard.rarity == AbstractCard.CardRarity.COMMON && !(commonCard.type == AbstractCard.CardType.STATUS || commonCard.type == AbstractCard.CardType.CURSE)) {
                        commonColorlessCards.add(commonCard);
                    }
                }
            }
        }
        AbstractCard newCard;
        if ((card.hasTag(CustomTags.FFUNIQUE) && UniqueSaveable.getUniqueIsSeen(card.cardID) == 1)){
            do {
                if (rarity == AbstractCard.CardRarity.COMMON && !commonColorlessCards.isEmpty()){
                    Collections.shuffle(commonColorlessCards, new Random(shuffleRng.randomLong()));
                    newCard = commonColorlessCards.get(0);
                }else{
                    newCard = colorlessCardPool.getRandomCard(true, rarity);
                }
            } while (UniqueSaveable.getUniqueIsSeen(newCard.cardID) == 1 && card.hasTag(CustomTags.FFPOTION) == Sozu);

            return newCard;
        }else{

            if (rarity == AbstractCard.CardRarity.COMMON && !commonColorlessCards.isEmpty()){
                do {
                    Collections.shuffle(commonColorlessCards, new Random(shuffleRng.randomLong()));
                    newCard = commonColorlessCards.get(0);
                } while (UniqueSaveable.getUniqueIsSeen(newCard.cardID) == 1 && card.hasTag(CustomTags.FFPOTION) == Sozu);
                return newCard;
            }
            return card;
        }
    }
}