package ffzim.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static ffzim.BasicMod.makeID;

public class BladeBarrierPower extends BasePower implements CloneablePowerInterface {

    public static final String POWER_ID = makeID("BladeBarrierPower");

    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int blockAmount;

    private int thornsAmount;

    //private  int secondMagic;


    private  int PowerStack;
    //private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);

    //public static final String NAME = powerStrings.NAME;

    //public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int baseThorns;
    private int thornUPGAmount;

    private boolean isCardUpgraded;
    public BladeBarrierPower(AbstractCreature owner, int block, int magicNumber) {
        super(POWER_ID, TYPE, TURN_BASED, owner, block);
        this.owner = owner;
        this.blockAmount = block;
        this.thornsAmount = magicNumber;
        //this.PowerStack = block;
        System.out.println("Current upgrade: " + isCardUpgraded);
        updateDescription();

    }

//    public void updateDescription() {
//        //this.fontScale = 8.0F;
//        //this.blockAmount += PowerStack;
//        //this.thornsAmount += PowerStack/2;
//        //System.out.println("Stack upgrade: " + isCardUpgraded);
//        //this.stackAmount += stackAmount;
//        this.description = DESCRIPTIONS[0] + this.blockAmount + DESCRIPTIONS[1] + this.thornsAmount + DESCRIPTIONS[2];
//    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.blockAmount += stackAmount;
        this.thornsAmount += (stackAmount/2)+1;
        super.stackPower(stackAmount);
        updateDescription();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        System.out.println("ID " + POWER_ID);
        System.out.println("DES " + this.description);
        if (card.type == AbstractCard.CardType.ATTACK) {;
            System.out.println("STACK AMMOUNT " + player.getPower(POWER_ID).amount);
            flash();
            addToTop(new GainBlockAction(player, blockAmount, Settings.FAST_MODE));
            addToBot(new ApplyPowerAction(player, player, new ThornsPower(player, this.thornsAmount)));
            addToBot(new ApplyPowerAction(player,player, new LoseThornsPower(player, this.thornsAmount)));

        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.blockAmount + DESCRIPTIONS[1] + this.thornsAmount + DESCRIPTIONS[2];
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        if (this.PowerStack == 0) {
//            addToBot((AbstractGameAction) new RemoveSpecificPowerAction(this.owner, this.owner, "BladeBarrierPower"));
//        } else {
//            addToBot((AbstractGameAction) new ReducePowerAction(this.owner, this.owner, "BladeBarrierPower", 1));
//        }
    }


    @Override
    public AbstractPower makeCopy() {
        return new BladeBarrierPower( owner, blockAmount, thornsAmount);
    }
}