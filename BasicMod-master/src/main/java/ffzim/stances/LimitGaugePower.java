package ffzim.stances;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.powers.BasePower;

import static ffzim.BasicMod.makeID;

public class LimitGaugePower extends BasePower {
    public static final String POWER_ID = makeID("TrancePower");

    private int percent_Calc;
    private final int damage_Percent = 20;
    public final int trance_Gain = 1;
    private Boolean DamageType = false;
    public static String currentJob = "Freelancer";
    public LimitGaugePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        DamageType = false;
        if (info.type == DamageInfo.DamageType.NORMAL && info.owner instanceof AbstractMonster) {
            // Store the source of the damage
            DamageType = true;
        }

        return damageAmount;
    }

    @Override
    public int onLoseHp(int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player;
            if (damageAmount > 0 && DamageType) {
                flash();
                addToBot(new ApplyPowerAction(p, p, new LimitGaugePower(p, trance_Gain), trance_Gain));
            }
        DamageType = false;
        return damageAmount;
    }

    public void atStartOfTurn() {
        if (this.amount >= 10) {
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(p, p, new LimitBreakPower(p, 0), 0));

            if (owner.hasPower(TranceBlackMagePower.POWER_ID)) {
                addToTop(new ApplyPowerAction(p, p, new BlackMageLimitBreak1MP(p, 0), 0));
            }
            this.amount -= 10;
        }
    }

    public void onRemove() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new LimitGaugePower(p, 0), 0));
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if ((this.amount += stackAmount) > 10){
            this.amount = 10;
        }else{
            this.amount += stackAmount;
        }
        if (owner.hasPower(LimitBreakPower.POWER_ID)) {
            System.out.println("A: ");
            this.amount = 0;
        }else {
            AbstractPlayer p = AbstractDungeon.player;
            if (this.amount >= 10 && !AbstractDungeon.actionManager.turnHasEnded) {
                addToBot(new ApplyPowerAction(p, p, new LimitBreakPower(p, 0), 0));

                if (owner.hasPower(TranceBlackMagePower.POWER_ID)) {
                    addToTop(new ApplyPowerAction(p, p, new BlackMageLimitBreak1MP(p, 0), 0));
                }

                this.amount -= 10;
            }
        }
    }

    @Override
    public void updateDescription () {
        //percent_Calc = this.amount * damage_Percent;
        this.description = DESCRIPTIONS[0] + trance_Gain + DESCRIPTIONS[1];
    }
}


