package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class DarksidePower extends BasePower {
    public static final String POWER_ID = makeID("LunaPower");

    private int HEAL = 2;
    private int UPG_HEAL = 1;

    public DarksidePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster monster) {
        if (card.type == AbstractCard.CardType.ATTACK && !card.hasTag(CustomTags.FFSPELL)) {
            AbstractPlayer p = AbstractDungeon.player;
            p.heal(this.amount);

            this.flash();

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

//    @Override
//    public void onRemove() {
//        this.flash();
//        AbstractDungeon.actionManager.addToBottom(
//                new ReducePowerAction(owner, owner, new StrengthPower(owner, DEBUFF_TRACKER), DEBUFF_TRACKER));
//        DEBUFF_TRACKER = 0;
//    }

    @Override
    public void updateDescription () {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}



