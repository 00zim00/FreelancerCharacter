package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.powers.HighJumpPower;
import ffzim.powers.JumpDodgePower;

import static ffzim.BasicMod.makeID;

public class CheckEvasionAction extends AbstractGameAction {
    private static final float DUR = 0.25F;
    private int newStackAmount;
    private int stackAmount;
    private boolean firstApply = true;

    public CheckEvasionAction(AbstractCreature target, AbstractCreature source, int amount, int stackAmount) {
        this.target = target;
        this.source = source;
        this.amount = amount;
        this.stackAmount = stackAmount;
        this.duration = 0.25F;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_FAST) {
            AbstractPlayer p = AbstractDungeon.player;
                JumpDodgePower jumpDodgePower = (JumpDodgePower) target.getPower(makeID("JumpDodgePower"));
                if (jumpDodgePower != null) {
                    firstApply = false;
                    int currentStacks = jumpDodgePower.amount;
                    System.out.println("Current stacks of JumpDodgePower: " + currentStacks);
                    currentStacks = this.amount * currentStacks;
                    System.out.println("Current stacks of JumpDodgePower2: " + currentStacks);
                    addToTop(new ApplyPowerAction(p, p, new JumpDodgePower(p,currentStacks)));
                    jumpDodgePower.updateDescription();
                }
            }

            isDone = true;
        }
    }