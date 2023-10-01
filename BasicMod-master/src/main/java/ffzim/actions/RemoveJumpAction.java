package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.cards.Generic.MagicStone;
import ffzim.powers.JumpDodgePower;

import static ffzim.BasicMod.makeID;

public class RemoveJumpAction extends AbstractGameAction {
    private static final float DUR = 0.25F;
    private int StackAmount;
    private int JUMP_REMOVER;

    public RemoveJumpAction(AbstractCreature target, AbstractCreature source, int amount, int JUMP_REMOVER) {
        this.target = target;
        this.source = source;
        this.StackAmount = amount;
        this.JUMP_REMOVER = JUMP_REMOVER;
        this.actionType = ActionType.BLOCK;
        this.duration = 0.25F;
    }

    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        AbstractPower jumpDodgePower = AbstractDungeon.player.getPower(makeID("JumpDodgePower"));
        if (jumpDodgePower instanceof JumpDodgePower) {
            JumpDodgePower jumpDodge = (JumpDodgePower) jumpDodgePower;
            int JUMPTOTAL = Math.max(StackAmount / JUMP_REMOVER, 1);
            addToTop(new ReducePowerAction(p, p, makeID("JumpDodgePower"), JUMPTOTAL));
            jumpDodge.updateDescription();
            isDone = true;
        }
    }
}


