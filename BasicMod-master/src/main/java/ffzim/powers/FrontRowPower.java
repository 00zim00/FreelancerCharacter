package ffzim.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static ffzim.BasicMod.makeID;


public class FrontRowPower extends BasePower {

    public static final String POWER_ID = makeID("FrontRowPower");

    private static final int STR = 1;
    private static final int DEX = -1;

    private final boolean upgradedCheck;

    public FrontRowPower(AbstractCreature owner, int amount, boolean upgraded) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.upgradedCheck = upgraded;
        this.updateDescription();
    }
    @Override
    public void onInitialApplication() {
        if (!upgradedCheck) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, STR), STR));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, DEX), DEX));
        }else{
            addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, STR), STR));
        }
        AbstractPower backRowPower = AbstractDungeon.player.getPower(BackRowPower.POWER_ID);
        if (backRowPower instanceof BackRowPower) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, BackRowPower.POWER_ID));;
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
            this.amount = 0;
    }

    @Override
    public void onRemove() {
        if (!upgradedCheck) {
            addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -STR), -STR));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, -DEX), -DEX));
        }else{
            addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -STR), -STR));
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
