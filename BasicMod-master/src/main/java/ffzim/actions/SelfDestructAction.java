package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.powers.SelfDestructInvisPower;
import ffzim.powers.SelfDestructPower;

public class SelfDestructAction extends AbstractGameAction {
    private final String powerID;

    public SelfDestructAction(AbstractCreature target, AbstractCreature source, int amount, String powerID) {
        setValues(target, source, amount);
        this.powerID = powerID;
        //this.actionType = ActionType.DAMAGE;
        this.duration = 0.25F;
    }

    public void update() {
        if (!AbstractDungeon.getMonsters().areMonstersDead()) {
            addToBot(new LoseHPAction(this.target, this.target, 99999));
            addToBot(new RemoveSpecificPowerAction(this.target, this.target, powerID));
            this.isDone = true;
        }
        tickDuration();
    }
}
