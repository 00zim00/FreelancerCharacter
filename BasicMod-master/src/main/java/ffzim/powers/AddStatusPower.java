package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import ffzim.actions.ArmorBreakAction;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.List;

import static ffzim.BasicMod.makeID;

public class AddStatusPower extends BasePower implements OnReceivePowerPower {

    public static final String POWER_ID = makeID("AddStatusPower");



    private static boolean HAS_POISON = false;
    private static boolean HAS_SLOW = false;
    private static boolean HAS_TROUBLE = false;
    private static boolean HAS_VENOM = false;
    private static boolean HAS_VUL = false;
    private static boolean HAS_WEAK = false;

    public AbstractCard AddStatusCARD;

    public AddStatusPower(AbstractCreature owner, int Amount, AbstractCard card) {
        super(POWER_ID, PowerType.BUFF, false, owner, Amount);
        this.owner = owner;
        this.amount = Amount;
        AddStatusCARD = card;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        cardStatusCheck();

    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractCard card = null;
        if (!AbstractDungeon.actionManager.cardsPlayedThisTurn.isEmpty()) {
            card = AbstractDungeon.actionManager.cardsPlayedThisTurn.get(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - 1);
        }

        if (card != null && !card.hasTag(CustomTags.FFSPELL)) {
            if (damageAmount > 0 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL && !card.hasTag(CustomTags.FFSPELL)) {
                flash();
                for (int i = 0; i < this.amount; i++) {
                    ArrayList<AbstractPower> debuffPowers = new ArrayList<>();
                    if (HAS_POISON) {
                        debuffPowers.add(new PoisonPower(target, this.owner, 1));
                    }
                    if (HAS_SLOW) {
                        debuffPowers.add(new SlowPower(target, 1));
                    }
                    if (HAS_TROUBLE) {
                        debuffPowers.add(new TroublePower(target, 1));
                    }
                    if (HAS_VENOM) {
                        debuffPowers.add(new VenomPower(target, 0));
                    }
                    if (HAS_VUL) {
                        debuffPowers.add(new VulnerablePower(target, 1, false));
                    }
                    if (HAS_WEAK) {
                        debuffPowers.add(new WeakPower(target, 1, false));
                    }

                    if (!debuffPowers.isEmpty()) {
                        AbstractPower selectedDebuff = debuffPowers.get(AbstractDungeon.cardRandomRng.random(debuffPowers.size() - 1));
                        addToBot(new ApplyPowerAction(target, this.owner, selectedDebuff, 1,true));
                    }
                }
            }
        }
    }


    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof JumpDodgePower) {
            cardStatusCheck();
            updateDescription();
        }
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if (power instanceof AddStatusPower) {
            cardStatusCheck();
            updateDescription();
        }
        return stackAmount;
    }

    public void atEndOfRound() {
    }

    public void stackPower(int stackAmount, AbstractCard newCard) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
        cardStatusCheck();
    }

    public void cardStatusCheck() {
        if (AddStatusCARD.hasTag(CustomTags.FFPOISON)) {
            HAS_POISON = true;
        }
        if (AddStatusCARD.hasTag(CustomTags.FFSLOW)) {
            HAS_SLOW = true;
        }
        if (AddStatusCARD.hasTag(CustomTags.FFTROUBLE)) {
            HAS_TROUBLE = true;
        }
        if (AddStatusCARD.hasTag(CustomTags.FFVENOM)) {
            HAS_VENOM = true;
        }
        if (AddStatusCARD.hasTag(CustomTags.FFVULNERABLE)) {
            HAS_VUL = true;
        }
        if (AddStatusCARD.hasTag(CustomTags.FFWEAK)) {
            HAS_WEAK = true;
        }

        updateDescription();
    }

    public void updateDescription() {
        //DeBuffCalc();

        StringBuilder descriptionBuilder = new StringBuilder(DESCRIPTIONS[0]);
        List<String> debuffDescriptions = new ArrayList<>();

        if (HAS_POISON) {
            debuffDescriptions.add(DESCRIPTIONS[3]);
        }
        if (HAS_SLOW) {
            debuffDescriptions.add(DESCRIPTIONS[4]);
        }
        if (HAS_TROUBLE) {
            debuffDescriptions.add(DESCRIPTIONS[5]);
        }
        if (HAS_VENOM) {
            debuffDescriptions.add(DESCRIPTIONS[6]);
        }
        if (HAS_VUL) {
            debuffDescriptions.add(DESCRIPTIONS[7]);
        }
        if (HAS_WEAK) {
            debuffDescriptions.add(DESCRIPTIONS[8]);
        }

        int numDebuffs = debuffDescriptions.size();
        if (numDebuffs > 0) {
            descriptionBuilder.append(" ");
            for (int i = 0; i < numDebuffs; i++) {
                descriptionBuilder.append(debuffDescriptions.get(i));
                if (i < numDebuffs - 2) {
                    descriptionBuilder.append(", ");
                } else if (i == numDebuffs - 2) {
                    descriptionBuilder.append(" or ");
                } else if (i == numDebuffs - 1) {
                    descriptionBuilder.append(".");
                }
            }
                if (this.amount >= 2){
                    descriptionBuilder.append(DESCRIPTIONS[1]);
                    descriptionBuilder.append(this.amount);
                    descriptionBuilder.append(DESCRIPTIONS[2]);
            }
        }

        this.description = descriptionBuilder.toString();
    }
}
