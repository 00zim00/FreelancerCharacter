package ffzim.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.RefundAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class OsmoseAction extends AbstractGameAction {
    private boolean freeToPlayOnce;

    private int damage;

    private AbstractPlayer p;

    private AbstractMonster m;
    private AbstractCard card;
    private DamageInfo.DamageType damageTypeForTurn;

    private int energyOnUse;

    public OsmoseAction(AbstractPlayer p, AbstractMonster m, AbstractCard card, int damage, DamageInfo.DamageType damageTypeForTurn, boolean freeToPlayOnce, int energyOnUse) {
        this.p = p;
        this.m = m;
        this.damage = damage;
        this.card = card;
        this.freeToPlayOnce = freeToPlayOnce;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
        this.damageTypeForTurn = damageTypeForTurn;
        this.energyOnUse = energyOnUse;
    }

    public void update() {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1)
            effect = this.energyOnUse;
        if (this.p.hasRelic("Chemical X")) {
            effect += 2;
            this.p.getRelic("Chemical X").flash();
        }

        if (this.card.upgraded)
            effect++;

        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, effect, false), effect));

        if (!this.freeToPlayOnce){
            this.p.energy.use(EnergyPanel.totalCount);
            addToBot(new RefundAction(this.card, effect-1));
        }
        this.isDone = true;
    }
}


