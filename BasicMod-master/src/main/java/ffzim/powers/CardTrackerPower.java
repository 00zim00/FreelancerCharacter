package ffzim.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class CardTrackerPower extends BasePower implements CloneablePowerInterface, BetterOnApplyPowerPower, OnReceivePowerPower
{
    public static final String POWER_ID = makeID("CardTrackerPower");
    private static final boolean TURN_BASED = true;

    public int regenCounter;

    public int tempHPCounter;

    public boolean wasDamaged = false;
    public int wasDamagedMyTurn = 0;
    private AbstractCard lastCard;
    public boolean isSpell = false;
    public CardTrackerPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, TURN_BASED, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        regenCounter = 0;
    }

    @Override
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
    {
        return true;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            if (!AbstractDungeon.actionManager.turnHasEnded) {
                wasDamagedMyTurn = 2;
            }
                wasDamaged = true;
        }
        return damageAmount;
    }

//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        isSpell = false;
//        System.out.println("isSpell1: " + isSpell);
//        System.out.println("FFSPELL: " + card.hasTag(CustomTags.FFSPELL));
//        if (card.hasTag(CustomTags.FFSPELL)) {
//            isSpell = true;
//            System.out.println("isSpell2: " + isSpell);
//        }
//    }
//
//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        System.out.println("isSpell3: " + isSpell);
//        AbstractPower strengthPower = owner.getPower("Strength");
//        System.out.println("Power1: " + (strengthPower instanceof StrengthPower));
//        if (isSpell && strengthPower instanceof StrengthPower) {
//            //isSpell = false;
//            System.out.println("damage1: " + damage);
//            System.out.println("damage2: " + (damage - strengthPower.amount));
//            return damage - strengthPower.amount;
//        }
//        return damage;
//    }

//    public float atDamageGive(float damage, DamageInfo.DamageType type) {
//        AbstractPower strengthPower = AbstractDungeon.player.getPower("StrengthPower");
//        if (strengthPower instanceof StrengthPower) {
//
//
////            if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
////                AbstractCard lastPlayedCard = AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1);
////                if (lastPlayedCard.hasTag(CustomTags.FFSPELL)) {
////                    return damage - this.amount;
////                }
//            }
//        }
//        return damage;
//    }


//    @Override
//    public void receivePostAction(AbstractGameAction action) {
//        if (action instanceof AddTemporaryHPAction) {
//            tempHPCounter += action.amount; // Access the amount field of the action
//            // Now you can use the 'amount' variable for your logic
//        }
//    }

        @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
            AbstractPower regenPower = AbstractDungeon.player.getPower("RegenPower");
            if (regenPower instanceof RegenPower) {
                flash();
                regenCounter += power.amount;
                System.out.println("regenCounter1: "+ regenCounter);
            }
            return true;
    }


    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        AbstractPower regenPower = AbstractDungeon.player.getPower("RegenPower");
            if (stackAmount >= 0 && regenPower instanceof RegenPower) {
                flash();
                regenCounter += stackAmount;
                System.out.println("regenCounter2: "+ regenCounter);
            }
        return stackAmount;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            wasDamagedMyTurn -= 1;
            if (wasDamagedMyTurn <= 0){
                wasDamagedMyTurn = 0;
                wasDamaged = false;
            }
        }
    }

    public void updateDescription() {

    }

    @Override
    public AbstractPower makeCopy() {
        return new CardTrackerPower(owner, amount);
    }
}

