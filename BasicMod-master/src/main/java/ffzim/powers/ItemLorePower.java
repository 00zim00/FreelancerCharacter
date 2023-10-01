package ffzim.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class ItemLorePower extends BasePower {
    public static final String POWER_ID = makeID("ItemLorePower");
    private int usePerTurn = 1;
    private int drawPerTurn;
    public ItemLorePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        this.drawPerTurn = this.amount;
        updateDescription();
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }



//    public void onCardDraw(AbstractCard card) {
//        if (card.hasTag(CustomTags.FFITEM) && this.drawPerTurn > 0) {
//            this.drawPerTurn--;
//            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
//        }
//    }

            public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.hasTag(CustomTags.FFITEM) && this.usePerTurn > 0) {
            flash();
            AbstractMonster m = null;
            if (action.target != null)
                m = (AbstractMonster)action.target;
            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            if (m != null)
                tmp.calculateCardDamage(m);
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            this.usePerTurn--;
        }
    }

    public void atEndOfTurn(boolean isPlayer) {
        this.usePerTurn = 1;
        this.drawPerTurn = this.amount;
    }
}
