package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class FFThunderAction extends AttackDamageRandomEnemyAction {
    public FFThunderAction(AbstractCard card) {
        super(card);
    }

    public void update() {
        if (!Settings.FAST_MODE)
            addToTop(new WaitAction(0.1F));
        super.update();
        if (this.target != null) {
            addToTop(new VFXAction(new LightningEffect(this.target.drawX, this.target.drawY)));
            addToTop(new VFXAction(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect)));
            addToTop(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
        }
    }
}
