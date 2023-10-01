package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.cards.Generic.MagicStone;

public class  AutoPlayRemoverAction extends AbstractGameAction {
        private static final float DUR = 0.25F;
    private final String CardID;

    private AbstractCard card;

    public AutoPlayRemoverAction(AbstractCreature target, AbstractCreature source, AbstractCard card) {
            setValues(target, source, this.amount);
            this.actionType = ActionType.BLOCK;
            this.CardID = card.cardID;
            this.card = card;
            this.duration = 0.25F;
        }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        //AbstractCard cardToAdd = new MagicStone();
        p.drawPile.removeCard(card);
        //p.drawPile.addToRandomSpot(cardToAdd);
        isDone = true;
    }
}


