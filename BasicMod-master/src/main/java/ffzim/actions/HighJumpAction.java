package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.powers.HighJumpPower;
import ffzim.powers.JumpDodgePower;

import static ffzim.BasicMod.makeID;

public class HighJumpAction extends AbstractGameAction {
    private static final float DUR = 0.25F;
    private int newStackAmount;
    private int stackAmount;
    private boolean firstApply = true;

    public HighJumpAction(AbstractCreature target, AbstractCreature source, int amount, int stackAmount) {
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
            if (firstApply) {
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
                HighJumpPower highJumpPower = (HighJumpPower) target.getPower(makeID("HighJumpPower"));
                if (highJumpPower != null) {;
                    //highJumpPower.APPLYJUMP = true;
                    highJumpPower.updateDescription();
                    System.out.println("JUMPj: "+ highJumpPower.APPLYJUMP);
                }
            }


//            if (highJumpPower instanceof HighJumpPower) {
//                HighJumpPower highjump = (HighJumpPower) highJumpPower;
//                System.out.println("JUMPj: "+ highjump.APPLYJUMP);
//                highjump.APPLYJUMP = true;
//                System.out.println("JUMPf: "+ highjump.APPLYJUMP);
//                highJumpPower.updateDescription();
//            }
            isDone = true;
        }
    }
}

//            AbstractPower jumpDodgePower = target.getPower("ffzim:JumpDodgePower");
//            if (jumpDodgePower instanceof JumpDodgePower) {
//                JumpDodgePower jumpDodge = (JumpDodgePower) jumpDodgePower;
//                int newStackAmount = (amount + 1) * stackAmount;
//                System.out.println("JUMP_REMOVER4: " + stackAmount);
//                System.out.println("JUMP_REMOVER5: " + newStackAmount);
//                // Perform any logic or actions you need here
//                isDone = true;
//

                // Check your condition for ending the loop
//                if (!jumpDodge.shouldContinueLoop()) {
//                    isDone = true;
//                    return;
//                }
//
//                // Schedule the next iteration of the action
//                AbstractDungeon.actionManager.addToTop(new YourCustomAction(target, amount, newStackAmount));
//            }
//        }
//        tickDuration();
//    }
//}


//    AbstractPower jumpDodgePower = AbstractDungeon.player.getPower(JumpDodgePower.POWER_ID);
//        if (jumpDodgePower instanceof JumpDodgePower) {
//                JumpDodgePower jumpDodge = (JumpDodgePower) jumpDodgePower;
//                System.out.println("JUMPD: " + jumpDodge.JUMPCAP);
//                jumpDodge.JUMPCAP = Math.min((UPG_JUMP*amount)+UPG_JUMP, 100);
//                System.out.println("JUMPE: " + jumpDodge.JUMPCAP);
//                jumpDodgePower.updateDescription();