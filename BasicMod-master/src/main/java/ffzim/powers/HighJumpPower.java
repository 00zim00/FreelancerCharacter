package ffzim.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.actions.HighJumpAction;

import static ffzim.BasicMod.makeID;

public class HighJumpPower extends BasePower implements BetterOnApplyPowerPower, OnReceivePowerPower
{
    public static final String POWER_ID = makeID("HighJumpPower");
    private static final int UPG_JUMP = 25;
    public boolean APPLYJUMP = false;
    private static int FirstStack;

    public HighJumpPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("intangible");
    }

    @Override
    public void onInitialApplication() {
        if (owner.hasPower(JumpDodgePower.POWER_ID)) {
            JumpRecalc();
            System.out.println("JUMP00: ");
        }
        System.out.println("JUMPB: ");

    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
    {
        return true;
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power instanceof JumpDodgePower)
        {
            flash();
            JumpRecalc();
            power.amount = power.amount * (amount + 1) ;
            power.updateDescription();
        }
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if(power instanceof JumpDodgePower)
        {
            flash();
            return stackAmount * (amount + 1);
        }
        return stackAmount;
    }


    public void JumpRecalc() {
        System.out.println("JUMPC: ");
        AbstractPower jumpDodgePower = AbstractDungeon.player.getPower(makeID("JumpDodgePower"));
        if (jumpDodgePower instanceof JumpDodgePower) {
            JumpDodgePower jumpDodge = (JumpDodgePower) jumpDodgePower;
            System.out.println("JUMPD: ");
            jumpDodge.JUMPCAP = Math.min((UPG_JUMP*amount)+UPG_JUMP, 100);
            jumpDodgePower.updateDescription();
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        if (this.amount + stackAmount> 3) {
            this.amount = 3;
        }else{
            this.amount += stackAmount;
        }
    }
    @Override
    public void onRemove() {
    }

    @Override
    public void reducePower(int reduceAmount) {
            flash();
            this.fontScale = 8.0F;
            this.amount -= reduceAmount;
            JumpRecalc();
            if (this.amount == 0)
                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
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

