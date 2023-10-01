package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.DamageModApplyingPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.SpeechBubble;

import java.util.List;

import static ffzim.BasicMod.makeID;

public class JumpPowerOldBroke extends BasePower implements DamageModApplyingPower {
    public static final String POWER_ID = makeID("JumpPowerOldBroke");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public JumpPowerOldBroke(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);;
        this.owner = owner;
        this.amount = amount;
        //this.img = new Texture("path/to/your/power/image.png"); // Replace with the path to your power image
        updateDescription();
    }

//    @Override
//    public void updateDescription() {
//        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
//    }
private float chanceToDodge = 0.15f;

private boolean dodgeApplied = false;



    @Override
    public boolean shouldPushMods(DamageInfo info, Object instigator, List<AbstractDamageModifier> damageModifiers) {
        System.out.println("dodgeApplied1 " + dodgeApplied);
        if (!dodgeApplied && instigator instanceof AbstractMonster) {
            System.out.println("dodgeApplied2 " + dodgeApplied);
            int jumpStacks = getJumpStacks();
            float chanceToDodge = jumpStacks * 1.00f;

            // Calculate dodge and set the flag if successful
            if (AbstractDungeon.miscRng.randomBoolean(chanceToDodge)) {
                System.out.println("dodgeApplied3 " + dodgeApplied);
                dodgeApplied = true;
                return true;
            }
        }

        return false;
    }

    @Override
    public List<AbstractDamageModifier> modsToPush(DamageInfo info, Object instigator, List<AbstractDamageModifier> damageModifiers) {
        if (dodgeApplied) {
            // Reset the flag
            dodgeApplied = false;
            System.out.println("dodgeApplied4 " + dodgeApplied);
            // Apply dodge effects or other modifications
            if (AbstractDungeon.miscRng.randomBoolean(chanceToDodge)) {
                info.output = 0;
                showMissText();
                System.out.println("MISS ");
            } else {
                System.out.println("HIT ");
                showHitText();
            }
        }
        System.out.println("ENDDDD");
        return damageModifiers;
    }

    public int getJumpStacks() {
        AbstractCreature owner = this.owner;
        if (owner != null) {
            AbstractPower jumpPower = owner.getPower(JumpPowerOldBroke.POWER_ID);
            if (jumpPower != null && jumpPower instanceof JumpPowerOldBroke) {
                return jumpPower.amount;
            }
        }
        return 0;
    }

    public static void showMissText() {
        AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 1.0f, "MISS", false));
    }

    public static void showHitText() {
        AbstractDungeon.effectList.add(new SpeechBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 1.0f, "HIT!", false));
    }

    public void atStartOfTurn() {
        flash();
        addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "JumpPower"));
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0)
            addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "JumpPower"));
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount += stackAmount;
    }

}
