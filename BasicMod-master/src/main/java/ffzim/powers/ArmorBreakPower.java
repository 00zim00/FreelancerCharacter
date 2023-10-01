package ffzim.powers;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockInstance;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnCreateBlockInstancePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.actions.ArmorBreakAction;
import ffzim.util.CustomTags;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnCreateBlockInstancePower;
import com.evacipated.cardcrawl.mod.stslib.blockmods.BlockInstance;

import java.util.HashSet;

import static ffzim.BasicMod.makeID;

public class ArmorBreakPower extends BasePower {

    public static final String POWER_ID = makeID("ArmorBreakPower");

    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;

    public ArmorBreakPower(AbstractCreature owner, int Amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, Amount);
        ;
        this.owner = owner;
        this.amount = Amount;
        updateDescription();
    }

    public void atEndOfRound() {
        System.out.println("AA END OF ROUND ");
        flash();
        AbstractCreature target = this.owner;
        System.out.println("Monster " + target);
        if (target != null && !target.isDying && !target.isDead && target.currentBlock > 0) {
            addToBot(new ArmorBreakAction(target, target));
        }
        if (this.amount <= 0) {
            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        } else {
            addToBot((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
            //updateDescription();
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
        }
    }
}