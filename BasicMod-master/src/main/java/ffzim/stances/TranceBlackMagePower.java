package ffzim.stances;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import ffzim.powers.BasePower;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class TranceBlackMagePower extends BasePower {
    public static final String POWER_ID = makeID("TranceBlackMagePower");

    public final int trance_Gain = 1;

    private boolean turnTracker;
    public static String currentJob = "TranceBlackMagePower";
    public TranceBlackMagePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, false, owner, amount);
        this.owner = owner;
        this.amount = 0;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        turnTracker = true;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(CustomTags.FFSPELL) && turnTracker && !owner.hasPower(LimitBreakPower.POWER_ID)) {
            flash();
            turnTracker = false;
            addToBot(new ApplyPowerAction(this.owner, this.owner, new LimitGaugePower(this.owner, trance_Gain), trance_Gain));
        }
    }


    public void atStartOfTurn() {
        turnTracker = true;
    }

//    public void stackPower(int stackAmount) {
//        super.stackPower(stackAmount);
//        this.amount += stackAmount;
//        updateDescription();
//    }

    @Override
    public void updateDescription () {
        this.description = DESCRIPTIONS[0];
    }
}


