//package ffzim.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//
//public class PlayCardActionCard extends AbstractGameAction {
//
//    private int damage;
//
//    public PlayCardActionCard(AbstractCreature target, int damage) {
//        this.target = target;
//        this.damage = damage;
//    }
//
//    @Override
//    public void update() {
//        if (target instanceof AbstractMonster) {
//            AbstractMonster monster = (AbstractMonster) target;
//            AbstractDungeon.actionManager.addToTop(new DamageAllEnemiesAction(monster, new int[]{damage}, damageTypeForTurn, AttackEffect.NONE));
//        }
//        isDone = true;
//    }
//}
