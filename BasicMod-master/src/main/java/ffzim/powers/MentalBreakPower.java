package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class MentalBreakPower extends BasePower {

    public static final String POWER_ID = makeID("MentalBreakPower");

    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    public MentalBreakPower(AbstractCreature owner, int Amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, Amount);;
        this.owner = owner;
        this.amount = Amount;
        updateDescription();
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, AbstractCard card) {
        if (card != null && card.hasTag(CustomTags.FFSPELL)) {
            return damage * 1.5f; // Increase damage by 50% from cards with FFSPELL tag
        }
        return damage;
    }

    public void atEndOfRound() {
        flash();
        if (this.amount <= 0) {
            addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}
