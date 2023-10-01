//package ffzim.patches;
//
//import com.evacipated.cardcrawl.modthespire.lib.*;
//import com.megacrit.cardcrawl.cards.CardGroup;
//import com.megacrit.cardcrawl.helpers.ModHelper;
//import com.megacrit.cardcrawl.random.Random;
//import com.megacrit.cardcrawl.relics.AbstractRelic;
//import com.megacrit.cardcrawl.unlock.UnlockTracker;
//import com.megacrit.cardcrawl.helpers.CardLibrary;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.localization.LocalizedStrings;
//import javassist.CtBehavior;
//import ffzim.util.CustomTags;
//import java.util.ArrayList;
//
//public class BaseCardGetRewardCardsPatch {
//
//    @SpirePatch(
//            cls = "com.megacrit.cardcrawl.dungeons.AbstractDungeon",
//            method = "getRewardCards"
//    )
//    public static class AbstractDungeonGetRewardCardsPatch {
//
//        public static void Postfix(ArrayList<AbstractCard> rewards, int numCards, AbstractCard.CardRarity rarity, boolean allowBasics, boolean isPurgeCard) {
//            // Filter out cards with FFSEEONCE tag and those that have been seen
//            ArrayList<AbstractCard> filteredCards = new ArrayList<>();
//
//            for (AbstractCard card : rewards) {
//                if (!card.hasTag(CustomTags.FFUNIQUE) || !UnlockTracker.isCardSeen(card.cardID)) {
//                    filteredCards.add(card);
//                }
//            }
//
//            // Clear the original rewards and add the filtered cards back
//            rewards.clear();
//            rewards.addAll(filteredCards);
//        }
//    }
//}