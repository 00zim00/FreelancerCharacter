package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.actions.RemoveJumpAction;

import java.math.MathContext;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;


public class EvasionPower extends BasePower implements BetterOnApplyPowerPower, OnReceivePowerPower
{

    public static final String POWER_ID = makeID("EvasionPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
//    private static final boolean TURN_BASED = false;

    private int CurrentJump;

    private int CurrentJumpCap;
    private int CurrentJumpMax;

    private int CurrentJumpDifference;
    public int CurrentEvasion;

    public EvasionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, true, owner, amount);
        this.owner = owner;
        this.amount = amount;
        this.priority = 95;
        this.updateDescription();
    }

    @Override
    public void onInitialApplication() {
        System.out.println("this.amount14: " + this.amount);
        CurrentJumpCap = 25;
        updateDescription ();
//        if (!owner.hasPower(JumpDodgePower.POWER_ID)) {
//            int ApplyJumpEvasion = Math.min(CurrentJumpCap,CurrentJump);
//            addToBot(new ApplyPowerAction(owner, owner, new EvasionPower(owner, ApplyJumpEvasion)));
//
//        }
    }



    //@Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, DamageInfo damageInfo) {
        System.out.println("this.amount13: " + this.amount);
        CheckJump();
        System.out.println("this.amount12: " + this.amount);
        if (damageType == DamageInfo.DamageType.NORMAL && damageInfo.output > 0 && this.owner.hasPower(this.ID) && AbstractDungeon.cardRandomRng.random(99) < CurrentEvasion) {
            flash();
            return 0; // Make the damage 0 if the dodge triggers
        }
        return damage;
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
    {
        System.out.println("this.amount11: " + this.amount);
        updateDescription();
        return true;
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if(power instanceof JumpDodgePower)
        {
            CheckJump();
            System.out.println("this.amount7: " + this.amount);
            int stackAmount = power.amount;
            System.out.println("stackAmount1: " + stackAmount);
            CurrentJumpDifference = Math.max(CurrentJumpCap - CurrentJump, 0);
            System.out.println("CurrentJumpCap " + CurrentJumpCap);
            System.out.println("CurrentJumpCap " + CurrentJumpDifference);
            if (CurrentJumpDifference > 0) {
                flash();
                System.out.println("this.amount8A: " + CurrentJumpDifference);
                int ApplyJumpEvasion = Math.min(stackAmount, CurrentJumpDifference);
                addToBot(new ApplyPowerAction(owner, owner, new EvasionPower(owner, ApplyJumpEvasion)));
                System.out.println("this.amount8B: " + this.amount);
                power.updateDescription();
                updateDescription();
            }
            return true;
        }



        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        System.out.println("this.amount7.-1: " + this.amount);
        return stackAmount;
    }

        public void stackPower ( int stackAmount){
            System.out.println("this.amount5: " + this.amount);
            flash();
            this.fontScale = 8.0F;
            if ((this.amount + stackAmount) > 100) {
                this.amount = 100;
            } else {
                this.amount += stackAmount;
            }
            System.out.println("this.amount+: " + this.amount);
            CheckJump();
        }
        public void atEndOfRound () {
            CheckJump();
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));


//            int total; // Use lowercase "int" instead of "Int"
//            total = this.amount - CurrentJumpMax;
//                flash();
//                addToTop(new RemoveJumpAction(this.owner, this.owner, total, total));
        }

    @Override
    public void onRemove() {
        updateDescription ();
    }

    public void CheckJump() {
        JumpDodgePower jumpDodgePower = (JumpDodgePower) this.owner.getPower(JumpDodgePower.POWER_ID);

        if (jumpDodgePower != null) {
            CurrentJumpCap = Math.max(jumpDodgePower.JUMPCAP,25);
            System.out.println("CurrentJumpMax : " + CurrentJumpCap );
            CurrentJump = jumpDodgePower.amount;;
            System.out.println("CurrentJump: " + CurrentJump);
            CurrentJumpMax = Math.min(CurrentJumpCap,CurrentJump);
            System.out.println("CurrentJump: " + CurrentJumpMax);

        }
        CurrentEvasion = Math.min(this.amount,100);
        System.out.println("CurrentEvasion: " + Math.min(this.amount,100));
    }

    @Override
    public void reducePower(int reduceAmount) {
        flash();
        System.out.println("this.amount3: " + this.amount);
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        System.out.println("this.amount4: " + this.amount);
        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            updateDescription ();

        }
    }

        @Override
        public void updateDescription () {
            System.out.println("this.amount1: " + this.amount);
        CheckJump();
            System.out.println("this.amount2: " + this.amount);
            this.description = DESCRIPTIONS[1] + CurrentEvasion + DESCRIPTIONS[2] + 100 + DESCRIPTIONS[3];
        }
    }
