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

public class BlackMageLimitBreak1MP extends BasePower {
    public static final String POWER_ID = makeID("BlackMageLimitBreak1MP");

    private boolean startTracker = false;

    private int cardsDoubledThisTurn = 0;
    public BlackMageLimitBreak1MP(AbstractCreature owner, int amount) {
        super(POWER_ID, NeutralPowertypePatch.NEUTRAL, true, owner, amount);
        this.owner = owner;
        this.amount = amount;
        //this.type = NeutralPowertypePatch.NEUTRAL;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        for (AbstractCard handCard : AbstractDungeon.player.hand.group)
            if (handCard.hasTag(CustomTags.FFSPELL) && handCard.cost > 1) {
                handCard.setCostForTurn(1);
            }
    }

    public void onCardDraw(AbstractCard card) {
        if (card.hasTag(CustomTags.FFSPELL) && card.cost > 1) {
            card.setCostForTurn(1);
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount = 0;
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

    @Override
    public void onRemove() {
        if (!startTracker){
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(p, p, new BlackMageLimitBreak1MP(p, 0), 0));
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

