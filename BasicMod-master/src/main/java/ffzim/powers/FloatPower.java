package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static ffzim.BasicMod.makeID;

public class FloatPower extends BasePower implements BetterOnApplyPowerPower
{
    public static final String POWER_ID = makeID("FloatPower");
    public FloatPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, true, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        System.out.println("JUMP_REMOVERcalled -1: ");
        if (owner.hasPower(JumpDodgePower.POWER_ID)) {
            System.out.println("JUMP_REMOVERcalled 0: ");
            jumphalving();
        }
    }

    public void jumphalving() {
        System.out.println("JUMP_REMOVERcalled: ");
        AbstractPower jumpDodgePower = AbstractDungeon.player.getPower(makeID("JumpDodgePower"));
        if (jumpDodgePower instanceof JumpDodgePower) {
            System.out.println("JUMP_REMOVERcalled22jg2: ");
            JumpDodgePower jumpDodge = (JumpDodgePower) jumpDodgePower;
            jumpDodge.JUMP_REMOVER = Math.max(amount + 1, 1);
            jumpDodge.updateDescription();
        }
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        System.out.println("JUMP_REMOVERcalled 3: ");
        jumphalving();
        return true;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }
    @Override
    public void onRemove() {
        jumphalving();
        System.out.println("JUMP_REMOVER77: ");
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
        @Override
        public void reducePower(int reduceAmount) {
            flash();
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
            jumphalving();
            if (this.amount == 0) {
                System.out.println("JUMP_REMOVER8: ");
                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            }
        }

    public void atEndOfRound() {
        flash();
        if (this.amount == 0) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }
}

