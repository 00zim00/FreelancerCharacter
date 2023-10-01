package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import ffzim.actions.ArmorBreakAction;

import static ffzim.BasicMod.makeID;

public class GainEnergyPower extends BasePower {

    public static final String POWER_ID = makeID("GainEnergyPower");

    public GainEnergyPower(AbstractCreature owner, int Amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, true, owner, Amount);
        this.owner = owner;
        this.amount = Amount;
        updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        addToBot(new GainEnergyAction(this.amount));
        updateDescription();
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}