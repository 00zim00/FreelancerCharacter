//package ffzim.powers;
//
//import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
//import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.PowerStrings;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//
//import static ffzim.BasicMod.makeID;
//
//public class ShellPlusPower extends BasePower implements BetterOnApplyPowerPower, OnReceivePowerPower {
//    public static final String POWER_ID = makeID("ShellPlusPower");
//
//    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings("ShellPlusPower");
//
//    public static final String NAME = powerStrings.NAME;
//    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//
////    public ShellPower(AbstractCreature owner, int amount) {
////        this.name = NAME;
////        this.ID = "ShellPower";
////        this.owner = owner;
////        this.amount = amount;
////        updateDescription();
////        loadRegion("artifact");
////        this.type = AbstractPower.PowerType.BUFF;
////    }
//
//    private static final PowerType TYPE = PowerType.BUFF;
//    private static final boolean TURN_BASED = false;
//
//    public ShellPlusPower(AbstractCreature owner, int amount) {
//        super(NAME, PowerType.BUFF, false, owner, amount);;
//        this.name = NAME;
//        this.ID = "ShellPlusPower";
//        this.owner = owner;
//        this.amount = amount;
//        updateDescription();
//        loadRegion("artifact");
//
//    }
//    @Override
//    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1)
//    {
//        return true;
//    }
//
//    @Override
//    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
//        if(power.amount != 0 && target == owner && power.type == PowerType.DEBUFF) {
//            flash();
//            power.amount *= 0;
//            return false;
//        }else {
//            return true;
//        }
//    }
//
//    @Override
//    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
//        if(power.amount != 0 && target == owner && power.type == PowerType.DEBUFF) {
//            flash();
//            return stackAmount / 2;
//        }
//        return stackAmount;
//    }
//
//
//    public void updateDescription() {
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
//        }
//    }
//}
