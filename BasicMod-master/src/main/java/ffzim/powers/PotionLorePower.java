//package ffzim.powers;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.HealAction;
//import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
//import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.rooms.AbstractRoom;
//import ffzim.actions.ArmorBreakAction;
//
//import static ffzim.BasicMod.makeID;
//
//public class PotionLorePower extends BasePower {
//
//    public static final String POWER_ID = makeID("ArmorBreakPower");
//
//    private static final PowerType TYPE = PowerType.DEBUFF;
//    private static final boolean TURN_BASED = true;
//
//    public PotionLorePower(AbstractCreature owner, int Amount) {
//        super(POWER_ID, TYPE, TURN_BASED, owner, Amount);
//        ;
//        this.owner = owner;
//        this.amount = Amount;
//        updateDescription();
//        loadRegion("hex");
//    }
//
//    public void onUsePotion() {
//        flash();
//        if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
//            addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
//            addToBot((AbstractGameAction)new HealAction((AbstractCreature)AbstractDungeon.player, (AbstractCreature)AbstractDungeon.player, 5));
//        } else {
//            AbstractDungeon.player.heal(5);
//        }
//    }
//
//
//    public void atEndOfRound() {
//
//    }
//
//    public void stackPower(int stackAmount) {
//        this.fontScale = 8.0F;
//        this.amount += stackAmount;
//    }
//
//    public void updateDescription() {
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
//        }
//    }
//}