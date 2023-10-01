package ffzim.actions.keywords;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import ffzim.cards.BaseCard;

public class APLevelUpAction extends AbstractGameAction {
    private AbstractCard card;


    public APLevelUpAction(AbstractCard card
    ) {
        setValues(target, source, this.amount);
        this.card = card;
        this.duration = 0.25F;
    }


    public void update() {
        if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
            ((BaseCard) card).APLevelUp(card);
        }
        isDone = true;
    }
}


