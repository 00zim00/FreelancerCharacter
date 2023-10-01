package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static ffzim.BasicMod.makeID;

public class LoseThornsPower extends BasePower {

    public static final String POWER_ID = makeID("LoseThornsPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public LoseThornsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);;
        this.owner = owner;
        this.amount = amount;
        this.type = AbstractPower.PowerType.DEBUFF;
        updateDescription();
        //loadRegion("thorns");
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    public void atStartOfTurnPostDraw(){
        flash();
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new ThornsPower(this.owner, -this.amount), -this.amount));
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        System.out.println("Current Thorns: " + (this.owner.getPower("Thorns")).amount);
        if (owner.hasPower("Thorns")) {
            int thornsAmount = (this.owner.getPower("Thorns")).amount;
            if (thornsAmount == amount) {
                addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "Thorns"));
            }
        }
    }
}
