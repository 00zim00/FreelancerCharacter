package ffzim.stances;

import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.powers.BasePower;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class BlackMageLimitBreak extends BasePower {
    public static final String POWER_ID = makeID("BlackMageLimitBreak");

    private boolean startTracker = false;

    private int cardsDoubledThisTurn = 0;
    public BlackMageLimitBreak(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, true, owner, amount);
        this.owner = owner;
        this.amount = amount;
        //this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {

    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && card.hasTag(CustomTags.FFSPELL))
                //&& AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - this.cardsDoubledThisTurn <= this.amount)
        {
            //this.cardsDoubledThisTurn++;
            AbstractMonster m = null;
            if (action.target != null)
                m = (AbstractMonster) action.target;
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
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount = 0;
    }

    @Override
    public void onRemove() {
        if (!startTracker){
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(p, p, new LimitBreakPower(p, 0), 0));
        }
    }

    @Override
    public void reducePower(int reduceAmount) {
        this.amount = 0;
    }

    @Override
    public void atStartOfTurn() {
        if(startTracker){
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }else{
            startTracker = true;
        }
    }


    public void updateDescription() {
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0];
//        } else {
//            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//        }
    }
}

