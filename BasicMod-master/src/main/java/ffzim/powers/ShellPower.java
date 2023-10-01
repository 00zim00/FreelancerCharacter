package ffzim.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;


import static ffzim.BasicMod.makeID;

public class ShellPower extends BasePower implements CloneablePowerInterface, BetterOnApplyPowerPower, OnReceivePowerPower
{
    public static final String POWER_ID = makeID("ShellPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public ShellPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }
    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
    {
        return true;
    }

//    @Override
//    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
//        if(power.amount != 0 && target == owner && power.type == PowerType.DEBUFF) {
//            flash();
//            power.amount *= 0;
//            return false;
//        }else {
//            return true;
//        }
//    }

        @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            if(power.amount >= 0 && power.type == PowerType.DEBUFF) {
                flash();
                power.amount -= 1;
            }
            return true;
        //            System.out.println("If 2: " + (power.amount == 0 && target == owner && power.type == PowerType.DEBUFF) );
//        //    if(power.amount != 0 && target == owner && power.type == PowerType.DEBUFF) {
//        if(power.amount == 0 && target == owner && power.type == PowerType.DEBUFF) {
//            flash();
//            power.amount *= 0;
//            System.out.println("power.amount1: " + power.amount);
//            return false;
//        }else {
//            System.out.println("power.amount2: " + power.amount);
//            return true;
//        }
    }


    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        //if (power.amount <= 0 && target == owner && power.type == PowerType.DEBUFF) {
            if (stackAmount >= 0 && power.type == PowerType.DEBUFF) {
                flash();
                return stackAmount - 1;
            }
        return stackAmount;
    }

    public void atEndOfRound() {
        flash();
        if (this.amount == 0) {
            addToBot( new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot( new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] +  this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ShellPower(owner, amount);
    }
}

