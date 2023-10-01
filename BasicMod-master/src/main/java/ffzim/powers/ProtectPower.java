package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static ffzim.BasicMod.makeID;

public class ProtectPower extends BasePower {
    public static final String POWER_ID = makeID("ProtectPower");

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    public static final String NAME = powerStrings.NAME;

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public ProtectPower(AbstractCreature owner, int turns) {
        super(POWER_ID, TYPE, TURN_BASED, owner, turns);
        this.name = NAME;
        this.ID = "ProtectPower";
        this.owner = owner;
        this.amount = turns;
        updateDescription();
        this.priority = 76;
    }

//    public void playApplyPowerSfx() {
//        CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
//    }

    public float atDamageFinalReceive(float damage, DamageInfo.DamageType type) {
            if (damage >= 1.0F) {
                damage *= 0.5f;
            }
        return damage;
    }


    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] +  this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }

    public void atEndOfRound() {
        flash();
        if (this.amount == 0) {
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "ProtectPower"));
        } else {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, "ProtectPower", 1));
        }
    }
}

