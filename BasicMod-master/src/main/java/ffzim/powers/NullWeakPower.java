package ffzim.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static ffzim.BasicMod.makeID;

public class NullWeakPower extends BasePower implements CloneablePowerInterface, OnReceivePowerPower
{
    public static final String POWER_ID = makeID("NullWeakPower");
    public NullWeakPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            if(power instanceof WeakPower && power.amount >= 0) {
                flash();
                    int tmpPowerAmount = power.amount;
                    power.amount -= this.amount;
                    addToBot( new ReducePowerAction(this.owner, this.owner, POWER_ID, tmpPowerAmount));

            }
            return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
            if (power instanceof WeakPower && power.amount >= 0) {
                flash();
                int tmpThisAmount = this.amount;
                addToBot( new ReducePowerAction(this.owner, this.owner, POWER_ID, stackAmount));
                return stackAmount - tmpThisAmount;
            }
        return stackAmount;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new NullWeakPower(owner, amount);
    }
}

