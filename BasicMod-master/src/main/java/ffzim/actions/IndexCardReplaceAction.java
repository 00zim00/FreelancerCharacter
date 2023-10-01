package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;
import ffzim.powers.SelfDestructPower;

import java.util.ArrayList;

import static basemod.BaseMod.logger;

public class IndexCardReplaceAction extends AbstractGameAction {
    private AbstractCard thisCard = null;
    private AbstractCard toCard = null;
    private ArrayList<AbstractCard> arrayLocation;

    public IndexCardReplaceAction(AbstractCard thisCard, AbstractCard toCard, ArrayList<AbstractCard> arrayLocation) {
        this.thisCard = thisCard;
        this.toCard = toCard;
        this.arrayLocation = arrayLocation;
        this.duration = 0.5F;
    }

    public void update() {
        boolean cardAdded = false;

        int index = arrayLocation.indexOf(thisCard);
        if (index >= 0) {
            arrayLocation.set(index, toCard);
        }//else{
        //    AbstractDungeon.player.masterDeck.addToTop(toCard);
        //}

        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.cardID.equals(toCard.cardID) && card.upgraded == toCard.upgraded) {
                AbstractCard tempCard = card.makeSameInstanceOf();
                AbstractDungeon.player.discardPile.addToTop(tempCard);
                cardAdded = true;
                break;
            }
        }

        if (cardAdded) {
            this.isDone = true;
        }
    }
}