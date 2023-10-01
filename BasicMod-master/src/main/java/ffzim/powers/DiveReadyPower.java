package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static ffzim.BasicMod.makeID;

public class DiveReadyPower extends BasePower {
    public static final String POWER_ID = makeID("DiveReadyPower");

    public DiveReadyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, true, owner, amount);
        this.owner = owner;
        this.amount = 0;
        updateDescription();
        loadRegion("intangible");
    }

    @Override
    public void onRemove() {
        if (owner.hasPower(JumpDodgePower.POWER_ID)) {
            flash();
            addToBot(new RemoveSpecificPowerAction(owner, owner, JumpDodgePower.POWER_ID));
        }
    }
}


