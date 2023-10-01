package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;

public class CustomWaitAction extends AbstractGameAction {
    public CustomWaitAction(float setDur) {
        setValues(null, null, 0);
        if (Settings.FAST_MODE && setDur > 0.1F) {
            this.duration = 0.1F;
        } else {
            this.duration = setDur;
        }
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    public void update() {
        tickDuration();
    }
}
