//package ffzim.actions;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
//
//public class HolyAction extends AbstractGameAction {
//    private boolean freeToPlayOnce;
//
//    private int damage;
//
//    private AbstractPlayer p;
//
//    private AbstractMonster m;
//
//    private DamageInfo.DamageType damageTypeForTurn;
//
//    private int energyOnUse;
//
//    public HolyAction(AbstractPlayer p, AbstractMonster m, int damage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
//        this.p = p;
//        this.m = m;
//        this.damage = damage;
//        this.freeToPlayOnce = freeToPlayOnce;
//        this.duration = Settings.ACTION_DUR_XFAST;
//        this.actionType = AbstractGameAction.ActionType.SPECIAL;
//        this.damageTypeForTurn = damageTypeForTurn;
//        this.energyOnUse = energyOnUse;
//    }
//
//    public void update() {
//        if (this.p.hasRelic("Chemical X")) {
//            this.p.getRelic("Chemical X").flash();
//        }
//        addToBot(new DamageAction(this.m, new DamageInfo(this.p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
//        if (!this.freeToPlayOnce)
//            this.p.energy.use(EnergyPanel.totalCount);
//        this.isDone = true;
//    }
//}
//
//
