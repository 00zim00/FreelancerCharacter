//package ffzim.powers;
//
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.cards.status.Burn;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import ffzim.cards.CounterAttack;
//
//public class CounterAttackPower extends AbstractPower {
//    public static final String POWER_ID = "CounterAttack";
//    private boolean triggeredThisTurn = false;
//
//    public CounterAttackPower(AbstractPlayer owner) {
//        this.name = "Counter Attack";
//        this.ID = POWER_ID;
//        this.owner = owner;
//        this.amount = -1; // This power is not based on amount
//        this.updateDescription();
//        this.loadRegion("rebound");
//    }
//
//    @Override
//    public void updateDescription() {
//        this.description = "If you take unblocked damage, play Counter Attack: Deal damage equal to the damage taken.";
//    }
//
//    @Override
//    public void onAttacked(DamageInfo info, int damageAmount) {
//        if (info.owner != null && damageAmount > 0 && !triggeredThisTurn) {
//            triggeredThisTurn = true;
//            AbstractCard counterAttackCard = new CounterAttack(info.owner, damageAmount);
//            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(counterAttackCard));
//        }
//    }
//
//    @Override
//    public void atEndOfTurn(boolean isPlayer) {
//        if (isPlayer) {
//            triggeredThisTurn = false;
//        }
//    }
//}
