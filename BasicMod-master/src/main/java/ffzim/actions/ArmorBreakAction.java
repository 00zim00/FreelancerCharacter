package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;

public class ArmorBreakAction extends AbstractGameAction {
        private static final float DUR = 0.25F;

        public ArmorBreakAction (AbstractCreature target, AbstractCreature source) {
            setValues(target, source, this.amount);
            this.actionType = AbstractGameAction.ActionType.BLOCK;
            this.duration = 0.25F;
        }

    public void update() {
        if (!this.target.isDying && !this.target.isDead &&
                this.duration == 0.25F &&
                this.target.currentBlock > 0) {
            int reducedBlock = this.target.currentBlock / 2; // Reduce block by half
            CardCrawlGame.sound.play("BLOCK_BREAK");
            this.target.loseBlock(reducedBlock);
        }

        tickDuration();
    }
}


