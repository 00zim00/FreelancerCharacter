package ffzim.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.stances.LimitGaugePower;

import static ffzim.BasicMod.makeID;

public class WhiteMage extends BaseRelic
{
    public static final String ID = makeID("WhiteMage");

    private static int TRANCE_COUNTER = 0;

    public WhiteMage() {
        super(ID, "WhiteMage", RelicTier.SPECIAL, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        TRANCE_COUNTER = this.counter;
        AbstractPlayer p = AbstractDungeon.player;
        flash();
        addToTop(new ApplyPowerAction(p, p,new LimitGaugePower(AbstractDungeon.player, TRANCE_COUNTER), TRANCE_COUNTER));
    }

    public void onVictory() {
        LimitGaugePower limitGaugePower = (LimitGaugePower) AbstractDungeon.player.getPower(LimitGaugePower.POWER_ID);
        if (limitGaugePower != null) {
            TRANCE_COUNTER = limitGaugePower.amount;
            this.counter = TRANCE_COUNTER;
        }
    }

    public BaseRelic makeCopy() {
        return new WhiteMage();
    }
}
