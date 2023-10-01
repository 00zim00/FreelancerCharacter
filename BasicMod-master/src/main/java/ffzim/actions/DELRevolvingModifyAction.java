package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.cards.RevolvingFields;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ffzim.BasicMod.makeID;

public class DELRevolvingModifyAction extends AbstractGameAction {
    private static final float DUR = 0.25F;
    private int currentRevolving;
    private final String myCard;

    private final String myCard2;
    private boolean revUpgraded = false;

    private boolean firstProcessDone = false;

    private UUID tempCardUUID;

    public DELRevolvingModifyAction(AbstractCreature target, AbstractCreature source, String newCardName, int revolvingCount, UUID tempCardUUID) {
        setValues(target, source, this.amount);
        this.currentRevolving = revolvingCount;
        myCard = makeID(newCardName);
        myCard2 = newCardName;
        System.out.println("NEW `myCards1`: " + myCard);
        this.tempCardUUID = tempCardUUID;
        this.duration = 0.5F;
    }


    @Override
    public void update() {
        System.out.println("Temp uuid3: " + tempCardUUID);
        System.out.println("firstProcessDone: " + firstProcessDone);

        List<AbstractCard> cardsInHandCopy = new ArrayList<>(AbstractDungeon.player.hand.group);

        for (AbstractCard cardInHand : cardsInHandCopy) {
            if (cardInHand.hasTag(CustomTags.FFTEMPTAG)) {
                RevolvingFields.revolving.set(cardInHand, this.currentRevolving - 1);
                cardInHand.baseMagicNumber = this.currentRevolving - 1;
                cardInHand.magicNumber = cardInHand.baseMagicNumber;
                cardInHand.tags.remove(CustomTags.FFTEMPTAG);
                cardInHand.initializeDescription();
                isDone = true;
                // Continue looping
            }
            tickDuration();
        }
    }
}




//        System.out.println("Temp uuid3: " + tempCardUUID);
//        boolean allCardsProcessed = true; // Flag to track if all cards are processed
//        System.out.println("firstProcessDone: " + firstProcessDone);
//            for (AbstractCard cardInHand : AbstractDungeon.player.hand.group) {
//                // String tempCard = makeID(":" + myCard);
//                System.out.println("second is true?: " + cardInHand.hasTag(CustomTags.FFTEMPTAG));
//                // BaseCard cardToAdd = (BaseCard) CardLibrary.getCopy(tempCard);
//                System.out.println(" myCard: " + myCard);
//                if (cardInHand.cardID.equals(myCard2)) {
//                    System.out.println("Tags of card " + myCard2 + " in hand: " + cardInHand.tags);
//                }
//                System.out.println(" myCard2: " + myCard2);
//                if (cardInHand.cardID.equals("ffzim:Undraw")) {
//                    System.out.println("Tags of card " + myCard2 + " in draw1: " + cardInHand.tags);
//                }
//                if (cardInHand.cardID.equals("Undraw")) {
//                    System.out.println("Tags of card " + myCard2 + " in draw2: " + cardInHand.tags);
//                }
//
//                if (cardInHand.cardID.equals(myCard)) {
//                    System.out.println("Tags of card " + myCard + " in hand: " + cardInHand.tags);
//                }
//
//                if (cardInHand.hasTag(CustomTags.FFTEMPTAG)) {
//                    RevolvingFields.revolving.set(cardInHand,this.currentRevolving - 1);
//                    cardInHand.baseMagicNumber = this.currentRevolving - 1;
//                    cardInHand.magicNumber = cardInHand.baseMagicNumber;
//                    cardInHand.tags.remove(CustomTags.FFTEMPTAG);
//                    cardInHand.initializeDescription();
//                    isDone = true;
//            }
//                    tickDuration(); // Continue looping
//            }
//        }
//    }


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


