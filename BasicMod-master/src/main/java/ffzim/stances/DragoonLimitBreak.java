package ffzim.stances;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.FFThunderAction;
import ffzim.actions.SpearTestAction;
import ffzim.powers.BasePower;
import ffzim.powers.EvasionPower;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class DragoonLimitBreak extends BasePower {
    public static final String POWER_ID = makeID("DragoonLimitBreak");

    private boolean startTracker = false;
    private static final int XTIMES = 6;
    public DragoonLimitBreak(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, true, owner, amount);
        this.owner = owner;
        this.amount = amount;
        //this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {

    }

    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new ApplyPowerAction(this.owner, this.owner, new EvasionPower(this.owner, 100)));

    }

//    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
//
//    }
    public void atStartOfTurnPostDraw() {
        // Damage based on current Jump
        // Maybe double current Jump. split its damage between all tagets
        // Or devide by 6 and do 6 attacks. +X so its atleast one.
        for (int i = 0; i < XTIMES; i++) {
            AbstractMonster m = AbstractDungeon.getRandomMonster();
            //addToBot(new SpearTestAction(m));
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {

    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount = 0;
    }

    @Override
    public void onRemove() {
        if (!startTracker){
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(p, p, new LimitBreakPower(p, 0), 0));
        }
    }

    @Override
    public void reducePower(int reduceAmount) {
        this.amount = 0;
    }

    @Override
    public void atStartOfTurn() {
        if(startTracker){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }else{
            startTracker = true;
        }
    }

//    public void atEndOfRound() {
//        flash();
//        if (this.amount == 0) {
//            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "ProtectPower"));
//        } else {
//            addToBot((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, "ProtectPower", 1));
//        }
//    }


    public void updateDescription() {
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0];
//        } else {
//            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//        }
    }
}

