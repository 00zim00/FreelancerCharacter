package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static ffzim.BasicMod.makeID;

public class CurlUpPower extends BasePower
{
    public static final String POWER_ID = makeID("CurlUpPower");
    public CurlUpPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
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
        if (damageAmount < this.owner.currentHealth && damageAmount > 0 && info.owner != null && info.type == DamageInfo.DamageType.NORMAL) {
            flash();
            addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
            addToBot((AbstractGameAction)new ReducePowerAction(this.owner,this.owner,POWER_ID, this.amount));

            //addToBot((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Curl Up"));
            updateDescription();

        }
        return damageAmount;
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
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }
}


