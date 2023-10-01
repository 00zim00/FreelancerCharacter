package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.Basics.ROGStrike;

import java.util.ArrayList;
import java.util.List;

import static ffzim.BasicMod.makeID;

public class CounterPower extends BasePower {
    public static final String POWER_ID = makeID("CounterPower");

    private boolean FinishedThisTurn;
    private int triggersThisTurn;
    private int counterThisTurn;
    ArrayList<AbstractGameAction> actionsToExecute = new ArrayList<>();

    public CounterPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        this.triggersThisTurn = this.amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        FinishedThisTurn = false;
        //triggersThisTurn = this.amount;
        updateDescription();
    }


    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        FinishedThisTurn = !(triggersThisTurn > 0);
        if (info.owner != null && info.owner != owner
                && damageAmount > 0
                && !FinishedThisTurn
                && info.type != DamageInfo.DamageType.THORNS
                && info.type != DamageInfo.DamageType.HP_LOSS) {
            triggersThisTurn -= 1;
            flash();
            // Find BasicStrike cards in the player's deck
            List<AbstractCard> basicStrikeCards = new ArrayList<>();
            for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                if (card.hasTag(AbstractCard.CardTags.STARTER_STRIKE)) {
                    basicStrikeCards.add(card);
                }
            }
            if (basicStrikeCards.isEmpty()) {
                basicStrikeCards.add(new Shiv());
            }
            if (info.owner instanceof AbstractMonster) {
                AbstractMonster Target = (AbstractMonster) info.owner;
                AbstractCard randomBasicStrike = basicStrikeCards.get(AbstractDungeon.cardRandomRng.random(basicStrikeCards.size() - 1));                AbstractDungeon.player.limbo.group.add(randomBasicStrike);
                randomBasicStrike.freeToPlayOnce = true;
                randomBasicStrike.current_y = -200.0F * Settings.scale;
                randomBasicStrike.target_x = Settings.WIDTH / 2.0F;
                randomBasicStrike.target_y = Settings.HEIGHT / 2.0F;
                randomBasicStrike.targetAngle = 0.0F;
                //randomBasicStrike.lighten(false);
                randomBasicStrike.lighten(true);
                randomBasicStrike.drawScale = 0.12F;
                randomBasicStrike.targetDrawScale = 0.75F;
                randomBasicStrike.calculateCardDamage(Target);
                randomBasicStrike.purgeOnUse = true;
                randomBasicStrike.applyPowers();

                //addToTop((AbstractGameAction)new DamageAction(info.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));

                actionsToExecute.add(new NewQueueCardAction(randomBasicStrike, info.owner, false, false));

                //AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(randomBasicStrike, info.owner, false, false));
                addToTop(new UnlimboAction(randomBasicStrike));
                updateDescription();
            }
        }
        return damageAmount;
    }

//    public void atStartOfTurn() {
//       if (counterThisTurn){
//
//       }
//       // hmm. i cant do it here unless i save and pass the attackers info to here.
//        flash();
//        FinishedThisTurn = false;
//        triggersThisTurn = amount;
//        updateDescription();
//    }


    public void atStartOfTurnPostDraw() {
        for (AbstractGameAction action : actionsToExecute) {
            AbstractDungeon.actionManager.addToBottom(action);
        }
        actionsToExecute.clear();
        flash();
        FinishedThisTurn = false;
        triggersThisTurn = amount;
        updateDescription();
    }

    public void stackPower(int stackAmount) {
        flash();
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        this.triggersThisTurn += stackAmount;
        updateDescription();
    }

    @Override
    public void reducePower(int reduceAmount) {
        flash();
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        updateDescription();
    }

    @Override
    public void onRemove() {
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3];

        }
    }
}



