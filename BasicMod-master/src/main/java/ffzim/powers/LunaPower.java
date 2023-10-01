package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class LunaPower extends BasePower {
    public static final String POWER_ID = makeID("LunaPower");

    private int DEBUFF_TRACKER = 0;

    public LunaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster monster) {
        if (card.type == AbstractCard.CardType.ATTACK && !card.hasTag(CustomTags.FFSPELL)) {
            this.flash();
            addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
            DEBUFF_TRACKER += amount;
            System.out.println("amount: " + amount);
            System.out.println("DEBUFF_TRACKER1: " + DEBUFF_TRACKER);
        }
        if ((card.type == AbstractCard.CardType.SKILL || card.hasTag(CustomTags.FFSPELL)) && DEBUFF_TRACKER != 0) {            this.flash();
            addToBot(new ReducePowerAction(this.owner, this.owner, StrengthPower.POWER_ID, DEBUFF_TRACKER));
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    System.out.println("DEBUFF_TRACKER2: " + DEBUFF_TRACKER);
                    DEBUFF_TRACKER = 0;
                    System.out.println("DEBUFF_TRACKER2: " + DEBUFF_TRACKER);
                    isDone = true;
                }
            });
        }

    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void reducePower(int reduceAmount) {
        flash();
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        updateDescription();
        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        updateDescription();
    }

    @Override
    public void onRemove() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(
                new ReducePowerAction(owner, owner, new StrengthPower(owner, DEBUFF_TRACKER), DEBUFF_TRACKER));
        DEBUFF_TRACKER = 0;
    }

    @Override
    public void updateDescription () {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}



