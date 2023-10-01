package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.Collections;

public class RandomTempItemCardInHandAction extends AbstractGameAction {

    private AbstractCard.CardRarity rarity;
    private int amount;
    private boolean isOtherCardInCenter;

    public RandomTempItemCardInHandAction(Integer amount, AbstractCard.CardRarity rarity, boolean isOtherCardInCenter) {
        this.isOtherCardInCenter = isOtherCardInCenter;
        this.amount = amount;
        this.rarity = rarity;
        this.actionType = ActionType.CARD_MANIPULATION;
    }


    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> matchingCards = new ArrayList<>();
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (!card.hasTag(CustomTags.FFUNIQUE) && card.hasTag(CustomTags.FFITEM) && !card.hasTag(AbstractCard.CardTags.HEALING) && card.rarity == rarity) {
                // Check additional conditions before adding the card
                boolean isMatch = true;

                if (p.hasRelic("Sozu") && card.hasTag(CustomTags.FFPOTION)) {
                    isMatch = false;
                }

                if (isMatch) {
                    matchingCards.add(card);
                }
            }
        }

            if (!matchingCards.isEmpty()) {
                Collections.shuffle(matchingCards, AbstractDungeon.cardRng.random);
                for (int i = 0; i < amount; i++) {
                    AbstractCard rngCard = matchingCards.get(i % matchingCards.size());
                    addToBot(new MakeTempCardInHandAction(rngCard, 1, isOtherCardInCenter));
                }
            }
            isDone = true;
        }
    }
