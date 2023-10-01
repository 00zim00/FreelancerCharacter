package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static ffzim.BasicMod.makeID;

public class EnSpellInvisPower extends BasePower implements BetterOnApplyPowerPower, OnReceivePowerPower, InvisiblePower
{
    public static final String POWER_ID = makeID("EnSpellInvisPower");
    public int magicStacks = 0;

    public EnSpellInvisPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999)
            this.amount = 999;
        if (this.amount <= -999)
            this.amount = -999;
        updateDescription();
        this.canGoNegative = true;
    }

    @Override
    public void onInitialApplication() {
        MagicPower magicPower = (MagicPower) this.owner.getPower(MagicPower.POWER_ID);
        if (magicPower != null) {
            this.amount = magicPower.amount;
        }else{
            this.amount = 0;
        }
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
    {
        MagicPower magicPower = (MagicPower) this.owner.getPower(MagicPower.POWER_ID);
        if (magicPower != null) {
            this.amount = magicPower.amount;
        }
        return true;
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power instanceof MagicPower) {
            MagicPower magicPower = (MagicPower) this.owner.getPower(MagicPower.POWER_ID);
            if (magicPower != null) {
                this.amount = magicPower.amount;
            }
            return true;
        }

        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if(power instanceof MagicPower) {
            MagicPower magicPower = (MagicPower) this.owner.getPower(MagicPower.POWER_ID);
            if (magicPower != null) {
                this.amount += stackAmount;
            }
            return stackAmount;
        }
        return stackAmount;
    }


    public void stackPower(int stackAmount) {
        MagicPower magicPower = (MagicPower) this.owner.getPower(MagicPower.POWER_ID);
        if (magicPower != null) {
            this.amount = magicPower.amount;
        }else{
            this.amount = 0;
        }
    }

    public void reducePower(int reduceAmount) {
        MagicPower magicPower = (MagicPower) this.owner.getPower(MagicPower.POWER_ID);
        if (magicPower != null) {
            this.amount = magicPower.amount;
        }else{
            this.amount = 0;
        }
    }


    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL)
            return damage + this.amount;
        return damage;
    }
}


