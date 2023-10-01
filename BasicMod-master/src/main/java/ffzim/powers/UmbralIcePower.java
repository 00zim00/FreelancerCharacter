package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnCreateBlockInstancePower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashSet;

import static ffzim.BasicMod.makeID;

public class UmbralIcePower extends BasePower implements OnCreateBlockInstancePower
{

    public static final String POWER_ID = makeID("AstralIcePower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public UmbralIcePower(AbstractCreature owner, int Amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, Amount);
        this.owner = owner;
        this.amount = Amount;
        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        if (owner.hasPower(AstralFirePower.POWER_ID)) {
            addToBot( new RemoveSpecificPowerAction(this.owner, this.owner, AstralFirePower.POWER_ID));
        }
    }
    @Override
    public void onCreateBlockInstance(HashSet<AbstractBlockModifier> blockTypes, Object instigator) {
        AbstractPlayer p = AbstractDungeon.player;
        //AbstractDungeon.effectsQueue.add(new FrostOrbActivateEffect(p.hb.cX,p.hb.cY));
        //CardCrawlGame.sound.play("ORB_FROST_CHANNEL", 0.1F);
        //addToBot(new SFXAction( "STS_SFX_FrostOrb_GainDefense_1_v1", 1.0f, false));
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        addToBot(new GainBlockAction(this.owner, this.owner, this.amount));
    }
//    public float modifyBlockLast(float blockAmount) {
//        if (this == specificPower) {
//        AbstractPlayer p = AbstractDungeon.player;
//        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        addToBot(new GainBlockAction(p, p, this.amount));
//        return blockAmount;
//    }

//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.type == AbstractCard.CardType.ATTACK) {
//            flash();
//            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//        }
//    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}