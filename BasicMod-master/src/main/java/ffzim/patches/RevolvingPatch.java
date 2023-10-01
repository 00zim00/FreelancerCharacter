package ffzim.patches;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import ffzim.actions.RevolvingAction;
import ffzim.actions.DELRevolvingModifyAction;
import ffzim.cards.RevolvingFields;
import ffzim.relics.OnRevolvingRelic;
import ffzim.powers.interfaces.OnRevolvingPower;


import ffzim.relics.OnRevolvingRelic;
import javassist.CtBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RevolvingPatch // Change class name
{
    @SpirePatch2(
            clz = UseCardAction.class,
            method = "update"
    )
    public static class OnPlay
    {
        public static List<AbstractCard> limboCards = new ArrayList<>();
        @SpireInsertPatch(
                locator = Locator1.class
        )
        public static void Insert1(AbstractCard ___targetCard)
        {
            if (RevolvingFields.revolving.get(___targetCard) > 1) { // Change field name
                AbstractPlayer p = AbstractDungeon.player; // Get the player
                String card2 = RevolvingFields.nextRevolving.get(___targetCard);

                // NEWEST
                RevolvingAction revolvingAction = new RevolvingAction(p, p, ___targetCard, card2, ___targetCard.upgraded, RevolvingFields.revolving.get(___targetCard));
                AbstractDungeon.actionManager.addToBottom(revolvingAction);
                UUID tempCardUUID = revolvingAction.getTempCardUUID();
                System.out.println("Temp uuid2: " + tempCardUUID);


                AbstractDungeon.actionManager.addToTop(new DELRevolvingModifyAction(p, p, card2, RevolvingFields.revolving.get(___targetCard),tempCardUUID));


                PurgeField.purge.set(___targetCard, true);

                // NEWEST


                //                System.out.println("card2 ID: " + card2);
//                System.out.println("card3 ID: " + makeID(card2));
//                //String tempCard = (BaseCard) CardLibrary.getCopy(":" + card2);
//                BaseCard cardToAdd = (BaseCard) CardLibrary.getCopy(makeID(card2));
//                System.out.println("card4 ID: " + cardToAdd);
//                cardToAdd.tags.add(CustomTags.FFTEMPTAG);
//                System.out.println("card5 ID: " + cardToAdd);
//
//
//                AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(cardToAdd, 1, false));




//                //String cardToAdd = RevolvingFields.revolving.get(R)
//                boolean upgraded = ___targetCard.upgraded; // Get whether the target card is upgraded
//                int revolvingCount = RevolvingFields.revolving.get(___targetCard); // Get the revolving count of the target card
//                //AbstractCard card2 = RevolvingFields.nextRevolving.get(___targetCard);
//                //String card2 = RevolvingFields.nextRevolving.get(___targetCard).cardID;
//                // Create and add the RevolvingAction to the top of the action queue
//                //AbstractDungeon.actionManager.addToBottom(new CustomWaitAction(0.5f));
//
//                //AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(___targetCard,p.hand,true));
//                //AbstractDungeon.player.hand.removeCard(___targetCard);
//                //AbstractDungeon.player.hand.removeCard(___targetCard);
//
////                AbstractCard cardToMove = ___targetCard; // Get the card instance
////                AbstractDungeon.player.hand.removeCard(___targetCard);
////                limboCards.add(___targetCard);
////                limboCards.clear();
//
//                //___targetCard.returnToHand = true;
            }
        }

        @SpireInsertPatch(
                locator = Locator2.class
        )
        public static void Insert2(AbstractCard ___targetCard)
        {
            if (RevolvingFields.revolving.get(___targetCard) > 1) { // Change field name
                ___targetCard.returnToHand = false;
                RevolvingFields.decrement(___targetCard); // Change field name

                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    if (r instanceof OnRevolvingRelic) { // Change interface name
                        ((OnRevolvingRelic) r).onRevolving(___targetCard, RevolvingFields.revolving.get(___targetCard)); // Change field name
                    }
                }
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    if (p instanceof OnRevolvingPower) { // Change interface name
                        ((OnRevolvingPower) p).onRevolving(___targetCard, RevolvingFields.revolving.get(___targetCard)); // Change field name
                    }
                }
            }
        }

        private static class Locator1 extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "freeToPlayOnce");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
        private static class Locator2 extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "exhaustOnUseOnce");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch2(
            clz = AbstractRoom.class,
            method = "applyEndOfTurnPreCardPowers"
    )
    public static class ResetCounter
    {
        public static void Prefix()
        {
            List<CardGroup> groups = new ArrayList<>();
            groups.add(AbstractDungeon.player.hand);
            groups.add(AbstractDungeon.player.drawPile);
            groups.add(AbstractDungeon.player.discardPile);
            groups.add(AbstractDungeon.player.exhaustPile);

            for (CardGroup group : groups) {
                for (AbstractCard card : group.group) {
                    if (RevolvingFields.baseRevolving.get(card) >= 0) { // Change field name
                        RevolvingFields.revolving.set(card, RevolvingFields.baseRevolving.get(card)); // Change field name
                        card.baseMagicNumber = RevolvingFields.revolving.get(card);
                        card.magicNumber = card.baseMagicNumber;
                        card.initializeDescription();
                    }
                }
            }
        }
    }
}
