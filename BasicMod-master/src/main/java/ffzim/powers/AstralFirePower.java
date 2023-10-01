package ffzim.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static ffzim.BasicMod.makeID;

public class AstralFirePower extends BasePower {

    public static final String POWER_ID = makeID("AstralFirePower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public AstralFirePower(AbstractCreature owner, int Amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, Amount);
        this.owner = owner;
        this.amount = Amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        if (owner.hasPower(UmbralIcePower.POWER_ID)) {
            addToBot( new RemoveSpecificPowerAction(this.owner, this.owner, UmbralIcePower.POWER_ID));
        }
    }
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage += this.amount;
        }
        return damage;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            flash();
            addToTop( new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}