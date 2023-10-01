package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;

public class PutAllOnDeckAction extends AbstractGameAction {
    private AbstractPlayer p;

    public PutAllOnDeckAction(AbstractCreature target) {
        this.p = (AbstractPlayer) target;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = 0.0F;
    }

    public void update() {
        ArrayList<AbstractCard> cardsToMove = new ArrayList<>(this.p.hand.group);

        for (AbstractCard card : cardsToMove) {
            this.p.hand.moveToDeck(card, false);
            cardsToMove.clear();
        }

        this.isDone = true;
    }
}

