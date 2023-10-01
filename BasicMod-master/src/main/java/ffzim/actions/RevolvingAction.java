package ffzim.actions;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import ffzim.cards.BaseCard;
//import ffzim.keywords.Revolving;
import ffzim.cards.RevolvingFields;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

import java.util.UUID;

public class RevolvingAction extends AbstractGameAction {
    private static final float DUR = 0.25F;
    private int currentRevolving;
    private final String myCard;
    private boolean revUpgraded = false;
    private final AbstractCard thisCard;

    private boolean firstProcessDone = false;
    private static UUID tempCardUUID;

    public RevolvingAction(AbstractCreature target, AbstractCreature source, AbstractCard thisCard, String newCardName, boolean UpgradeMe, int revolvingCount) {
        setValues(target, source, this.amount);
        this.currentRevolving = revolvingCount;
        //String tempID = ":" + newCardName;
        myCard = makeID(newCardName);
        System.out.println("NEW `myCards1`: " + myCard);
        this.revUpgraded = UpgradeMe;
        this.thisCard = thisCard;
        this.duration = 0.5F;
    }

    @Override
    public void update() {
        // FIRST PART
        BaseCard cardToAdd = (BaseCard) CardLibrary.getCopy(myCard);
        System.out.println("card4 ID: " + cardToAdd);
        System.out.println("Tags after adding1: " + cardToAdd.tags);
        cardToAdd.tags.add(CustomTags.FFTEMPTAG);
        System.out.println("card5 ID: " + cardToAdd);
        System.out.println("Tags after adding2: " + cardToAdd.tags);
        tempCardUUID = cardToAdd.uuid;
        addToTop(new MakeTempCardInHandAction(cardToAdd, 1, false));
        tempCardUUID = cardToAdd.uuid;
        System.out.println("Temp uuid1: " + tempCardUUID);
        isDone = true;
    }

    public UUID getTempCardUUID() {
        return tempCardUUID;
    }
}

        //AbstractDungeon.actionManager.addToTop(new RevolvingModifyAction(target,source, myCard, currentRevolving));


        // SECOND PART
//        boolean allCardsProcessed = true; // Flag to track if all cards are processed
//        System.out.println("firstProcessDone: " + firstProcessDone);
//            for (AbstractCard cardInHand : AbstractDungeon.player.hand.group) {
//                // String tempCard = makeID(":" + myCard);
//                System.out.println("second is true?: " + (cardInHand.cardID.equals(myCard)));
//                // BaseCard cardToAdd = (BaseCard) CardLibrary.getCopy(tempCard);
//                System.out.println("firstProcessDone: " + myCard);
//                if (cardInHand.cardID.equals(myCard)) {
//                    RevolvingFields.revolving.set(cardInHand,this.currentRevolving - 1);
//                    cardInHand.baseMagicNumber = this.currentRevolving - 1;
//                    cardInHand.magicNumber = cardInHand.baseMagicNumber;
//                    cardInHand.tags.remove(CustomTags.FFTEMPTAG);
//                    cardInHand.initializeDescription();
//                    allCardsProcessed = false;
//            }
//                if (allCardsProcessed) {
//                    isDone = true; // All cards are processed, mark the action as done
//                } else {
//                    tickDuration(); // Continue looping
//            }
//        }
//    }
//}


//    @Override
//    public void update() {
//        System.out.println("firstProcessDone: " + firstProcessDone);
//
//        if (!firstProcessDone) {
//            firstProcessDone = true;
//            this.currentRevolving -= 1;
//            PurgeField.purge.set(thisCard, true);
//            BaseCard cardToAdd = (BaseCard) CardLibrary.getCopy(myCard);
//            cardToAdd = (BaseCard) modifyCard(cardToAdd, cardToAdd.baseMagicNumber - 1);
//            cardToAdd.tags.add(CustomTags.FFTEMPTAG);
//            addToBot(new MakeTempCardInHandAction(cardToAdd, 1, false));
//        } else {
//            for (AbstractCard cardInHand : AbstractDungeon.player.hand.group) {
//                System.out.println("second is true?: " + (cardInHand.cardID.equals(myCard)));
//                BaseCard cardToAdd = (BaseCard) CardLibrary.getCopy(myCard);
//                System.out.println("firstProcessDone: " + myCard);
//
//                if (cardInHand.cardID.equals(myCard)) {
//                    cardInHand.baseMagicNumber = this.currentRevolving - 1;
//                    cardInHand.magicNumber = cardInHand.baseMagicNumber;
//                    cardInHand.tags.remove(CustomTags.FFTEMPTAG);
//                    cardInHand.initializeDescription();
//                }
//            }
//            isDone = true;
//        }
//    }
//}




//            cardToAdd.baseMagicNumber = this.currentRevolving - 1;
//            cardToAdd.magicNumber = cardToAdd.baseMagicNumber;
//            System.out.println("baseMagicNumber0: " + cardToAdd.baseMagicNumber);
//
//
//
//
//            //BaseCard cardToAdd = (BaseCard) CardLibrary.cards.get(myCard);
//            BaseCard cardToAdd = (BaseCard)  CardLibrary.getCopy(myCard);
//
//            AbstractCard AbstractToAdd = cardToAdd.makeCopy();
//
//            if (cardToAdd != null) {
//                // Modify properties of the copied card
//                cardToAdd.baseMagicNumber = cardToAdd.baseMagicNumber - 1;
//            }
//
//            System.out.println("NEW myCards1: " + cardToAdd);
//            System.out.println("Pre Rev: " + this.currentRevolving);
//            RevolvingFields.revolving.set(cardToAdd, this.currentRevolving);
//            RevolvingFields.revolvedValue = this.currentRevolving;
//            System.out.println("Add Rev: " + RevolvingFields.revolving.get(cardToAdd));
//
//            cardToAdd.baseMagicNumber = this.currentRevolving - 1;
//            cardToAdd.magicNumber = cardToAdd.baseMagicNumber;
//            System.out.println("baseMagicNumber0: " + cardToAdd.baseMagicNumber);
//
//
//
//            //cardToAdd.RevolvingFields.revolving = this.currentRevolving;
//            if (revUpgraded) {
//                cardToAdd.upgrade();
//            }
//            RevolvingFields.decrement(cardToAdd);
//
//            cardToAdd.initializeDescription();
//
//            addToBot(new MakeTempCardInHandAction(cardToAdd, 1, false));
//
//        }
//        isDone = true;
//    }
//}


