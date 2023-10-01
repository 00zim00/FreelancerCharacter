package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.cards.BlackMage.Haste;
import ffzim.cards.BlackMage.Slow;

import java.util.ArrayList;

import static basemod.BaseModInit.DESCRIPTION;

public class ChooseCardAction extends AbstractGameAction {
        private static final float DUR = 0.25F;

        private boolean chosen = false;

        public ChooseCardAction(AbstractCreature target, AbstractCreature source) {
            setValues(target, source, this.amount);
            this.duration = Settings.ACTION_DUR_FAST;
            this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
        }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractCard hasteCard = new Haste();
            AbstractCard slowCard = new Slow();
            ArrayList<AbstractCard> cards = new ArrayList<>();
            cards.add(hasteCard);
            cards.add(slowCard);
            AbstractDungeon.cardRewardScreen.customCombatOpen(cards, DESCRIPTION, false);                tickDuration();
        } else if (!chosen) {
            AbstractCard selectedCard = AbstractDungeon.cardRewardScreen.discoveryCard;
            if (selectedCard != null) {
                chosen = true;
                addToTop(new MakeTempCardInHandAction(selectedCard));
            }
            this.isDone = true;
        }
    }
}


