package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.actions.RemoveJumpAction;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;


public class JumpDodgePower extends BasePower
{

    public static final String POWER_ID = makeID("JumpDodgePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
//    private static final boolean TURN_BASED = false;

    public int JUMPCAP = 25;
    public int JUMP_REMOVER = 1;
    private static final int JUMP_MULTI = 1;
    private boolean removedThisRound = false;
    public JumpDodgePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
    }

    public void playApplyPowerSfx() {
        addToBot(new WaitAction(1.0f));
        CardCrawlGame.sound.play(modID + ":Jump", 0.05F);
    }


    @Override
    public void onInitialApplication() {
        if (!owner.hasPower(DiveReadyPower.POWER_ID)) {
            addToBot(new ApplyPowerAction(owner, owner, new DiveReadyPower(owner, 1)));
        }
        if (!owner.hasPower(EvasionPower.POWER_ID)) {
            int ApplyJumpEvasion = Math.min(JUMPCAP,this.amount);
            addToBot(new ApplyPowerAction(owner, owner, new EvasionPower(owner, ApplyJumpEvasion)));
        }
    }

    //@Override
//    public float atDamageReceive(float damage, DamageInfo.DamageType damageType, DamageInfo damageInfo) {
//        int checkedCap = CheckCap();
//        if (damageType == DamageInfo.DamageType.NORMAL && damageInfo.output > 0 && this.owner.hasPower(this.ID) && AbstractDungeon.cardRandomRng.random(99) < checkedCap * JUMP_MULTI) {
//            flash();
//            return 0; // Make the damage 0 if the dodge triggers
//        }
//        return damage;
//    }

    public int CheckCap() {
        int capchecked = amount;
        if (capchecked > JUMPCAP) {
            capchecked = JUMPCAP;
        }
        return capchecked;
    }

        public void stackPower ( int stackAmount){
            this.fontScale = 8.0F;
            if (this.amount + stackAmount > JUMPCAP) {
                this.amount = JUMPCAP;
            } else {
                this.amount += stackAmount;
            }
        }
        public void atEndOfRound () {
            System.out.println("JUMP_REMOVER4: " + JUMP_REMOVER);
            if (!removedThisRound) {
                flash();
                addToTop(new RemoveJumpAction(this.owner, this.owner, this.amount, this.JUMP_REMOVER));
                removedThisRound = true;
            }
            removedThisRound = false;
        }

    @Override
    public void onRemove() {
        System.out.println("JUMP_REMOVER66: " + JUMP_REMOVER);
        updateDescription ();
        if (owner.hasPower(EvasionPower.POWER_ID)) {
            addToBot(new ReducePowerAction(this.owner, this.owner, EvasionPower.POWER_ID, this.amount));
        }
        if (owner.hasPower(DiveReadyPower.POWER_ID)) {
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, DiveReadyPower.POWER_ID));
        }
    }
    @Override
    public void reducePower(int reduceAmount) {
        flash();
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            System.out.println("JUMP_REMOVER55: " + JUMP_REMOVER);
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            updateDescription ();
//        if (!removedThisRound) {
//            flash();
//            this.fontScale = 8.0F;
//            this.amount -= reduceAmount;
//            if (this.amount == 0) {
//                System.out.println("JUMP_REMOVER55: " + JUMP_REMOVER);
//                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//                updateDescription ();
//            }
//            removedThisRound = false;
        }
    }

        @Override
        public void updateDescription () {
            int checkedCap = CheckCap();
            this.description = DESCRIPTIONS[1] + checkedCap + DESCRIPTIONS[2] + JUMPCAP + DESCRIPTIONS[3];
        }
    }
