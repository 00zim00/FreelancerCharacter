package ffzim.stances;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.powers.BasePower;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class LimitBreakPower extends BasePower {
    public static final String POWER_ID = makeID("LimitBreakPower");

    private float totalDamBonusPercent;
    private final float damage_Percent = 1.5F;
    private boolean startTracker = false;
    private AbstractCard currentCard;

    public LimitBreakPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        totalDamBonusPercent = damage_Percent;

        relicCheck();

        updateDescription();


    }

    public void relicCheck() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasRelic(makeID("Freelancer"))) {
            // Player has the relic "Relic A"
            addToBot(new ApplyPowerAction(p, p, new BlackMageLimitBreak(p, 0), 0));
        } else if (p.hasRelic(makeID("Job:Blackmage"))) {
            addToBot(new ApplyPowerAction(p, p, new BlackMageLimitBreak1MP(p, 0), 0));
        } else if (p.hasRelic(makeID("Job:Whitemage"))) {
            addToBot(new ApplyPowerAction(p, p, new BlackMageLimitBreak(p, 0), 0));
        } else if (p.hasRelic(makeID("Job:Bluemage"))) {
            addToBot(new ApplyPowerAction(p, p, new BlackMageLimitBreak(p, 0), 0));
        } else if (p.hasRelic(makeID("Job:Dragoon"))) {
            addToBot(new ApplyPowerAction(p, p, new BlackMageLimitBreak(p, 0), 0));
        } else if (p.hasRelic(makeID("Job:Darkknight"))) {
            addToBot(new ApplyPowerAction(p, p, new BlackMageLimitBreak(p, 0), 0));
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        currentCard = card;
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL || (type == DamageInfo.DamageType.THORNS && currentCard.hasTag(CustomTags.FFSPELL))) {
            currentCard = null;
            return damage * damage_Percent;
        }
        currentCard = null;
        return damage;
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
            addToBot(new ApplyPowerAction(p, p, new LimitBreakPower(p, 0), 0));
            relicCheck();
            updateDescription ();
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 1) {
            this.amount = 0;

        }
    }

    @Override
    public void updateDescription () {
        //percent_Calc = this.amount * damage_Percent;
        //this.description = DESCRIPTIONS[0] + totalDamBonusPercent + DESCRIPTIONS[2];
    }
}


