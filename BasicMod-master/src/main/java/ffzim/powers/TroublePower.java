package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static ffzim.BasicMod.makeID;

public class TroublePower extends BasePower
{
    public static final String POWER_ID = makeID("TroublePower");

    private int percent_Calc;
    private final int damage_Percent = 10;
    public TroublePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        percent_Calc = this.amount * damage_Percent;
        updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        if (info.owner == owner) {
            for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (damageAmount > 0 && monster != null && monster != this.owner) {
                    flash();
                    percent_Calc = this.amount * damage_Percent;
                    damageAmount = (damageAmount / 100) * percent_Calc;
                    addToBot(new DamageAction(monster, new DamageInfo(p, damageAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                }
            }
        }
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
        percent_Calc = this.amount * damage_Percent;
        this.description = DESCRIPTIONS[0] + percent_Calc + DESCRIPTIONS[2];
    }
}


