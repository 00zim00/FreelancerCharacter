package ffzim.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static ffzim.BasicMod.makeID;
public class VenomPower extends BasePower {

    public static final String POWER_ID = makeID("VenomPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    private static final int POISONED_DAM = 3;
    public VenomPower(AbstractCreature owner, int VenomAmount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, VenomAmount);;
        this.owner = owner;
        this.amount = VenomAmount;
        updateDescription();
    }

    public void atEndOfRound() {
        if (!this.owner.isDeadOrEscaped()) { // Check if the owner is still present
            flash();
            addToBot(new ApplyPowerAction(this.owner, this.owner, new PoisonPower(this.owner, this.owner, POISONED_DAM)));
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + POISONED_DAM + DESCRIPTIONS[1];
    }
}
