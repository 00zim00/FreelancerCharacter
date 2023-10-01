package ffzim.powers;

import basemod.patches.com.megacrit.cardcrawl.characters.AbstractPlayer.TurnStartHooks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static ffzim.BasicMod.makeID;

public class IntangibleBluePower extends BasePower
{
    public static final String POWER_ID = makeID("IntangibleBluePower");
    public IntangibleBluePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }
    public static int setTurn;

    @Override
    public void onInitialApplication() {
        //setTurn = AbstractDungeon.actionManager.turn;
        setTurn = 0;
        updateDescription();
    }

    @Override
    public void atStartOfTurn(){
        setTurn = setTurn +1;
        if (setTurn % 2 == 0 && setTurn != 0) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new IntangiblePlayerPower(this.owner, 1), 1));

            // need to do a if check for current turn timer to. so that its 1 stack each 2 turns.
            //addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        updateDescription();
    }


    public void stackPower ( int stackAmount){
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
            updateDescription();
    }

    @Override
    public void updateDescription () {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];

        }
    }
}


