package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static ffzim.BasicMod.makeID;


public class BackRowPower extends BasePower {

    public static final String POWER_ID = makeID("BackRowPower");
    private static final int MAG = 1;
    private static final int DEX = 1;
    private static final int STR = -1;

    private final boolean upgradedCheck;

    public BackRowPower(AbstractCreature owner, int amount, boolean upgraded) {
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
            addToBot(new ApplyPowerAction(this.owner, this.owner, new MagicPower(this.owner, MAG), MAG));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, DEX), DEX));
        }
        AbstractPower frontRowPower = AbstractDungeon.player.getPower(FrontRowPower.POWER_ID);
        if (frontRowPower instanceof FrontRowPower) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, FrontRowPower.POWER_ID));;
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
            addToBot(new ApplyPowerAction(this.owner, this.owner, new MagicPower(this.owner, -MAG), -MAG));
            addToBot(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, -DEX), -DEX));
        }

    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
    }
}
