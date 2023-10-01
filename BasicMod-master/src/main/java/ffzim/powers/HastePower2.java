package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static ffzim.BasicMod.makeID;

public class HastePower2 extends AbstractPower {
    public static final String POWER_ID = makeID("Haste");

    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("Draw");

    public static final String NAME = powerStrings.NAME;

    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HastePower2(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Haste";
        this.owner = owner;
        this.amount = amount;
        updateDescription();
        loadRegion("haste");
        if (amount < 0) {
            this.type = AbstractPower.PowerType.DEBUFF;
            loadRegion("haste2");
        } else {
            this.type = AbstractPower.PowerType.BUFF;
            loadRegion("haste");
        }
        this.isTurnBased = false;
        AbstractDungeon.player.gameHandSize += amount;
    }

    public void onRemove() {
        AbstractDungeon.player.gameHandSize -= this.amount;
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0)
            addToTop((AbstractGameAction)new RemoveSpecificPowerAction(this.owner, this.owner, "Haste"));
    }

    public void updateDescription() {
        if (this.amount > 0) {
            if (this.amount == 1) {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
            } else {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3];
            }
            this.type = AbstractPower.PowerType.BUFF;
        } else {
            if (this.amount == -1) {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
            } else {
                this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[4];
            }
            this.type = AbstractPower.PowerType.DEBUFF;
        }
    }
}


