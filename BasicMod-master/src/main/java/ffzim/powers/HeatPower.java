package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static ffzim.BasicMod.makeID;


public class HeatPower extends BasePower {

    public static final String POWER_ID = makeID("HeatPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public HeatPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.priority = 95;
        this.updateDescription();
        this.loadRegion("flight");
    }

    // https://github.com/daviscook477/BaseMod/blob/master/mod/src/main/java/basemod/abstracts/AbstractCardModifier.java
    //public float modifyDamageFinal
    // public float modifyDamage
    // public String modifyDescription
    // public Color getGlow(


    //@Override
    public float OnAttacked(float damage, DamageInfo.DamageType damageType, DamageInfo damageInfo) {
        if (damageType == DamageInfo.DamageType.NORMAL && damageInfo.output > 0 && this.owner.hasPower(this.ID) && AbstractDungeon.cardRandomRng.random(99) < this.amount*5) {
            flash();
            return 0; // Make the damage 0 if the dodge triggers
        }
        return damage;
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        if (this.amount + stackAmount> 100) {
            this.amount = 100;
        }else{
            this.amount += stackAmount;
        }
    }

    public void atEndOfRound() {
        flash();
        addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
