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
import ffzim.CustomEffect.SpearFallFX;

public class SpearTestAction extends AttackDamageRandomEnemyAction {
    public SpearTestAction(AbstractCard card) {
        super(card);
    }

    public void update() {
        if (!Settings.FAST_MODE)
            addToTop((AbstractGameAction)new WaitAction(0.1F));
        super.update();
        if (this.target != null) {
            addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new SpearFallFX(this.target.drawX, this.target.drawY)));
            addToTop((AbstractGameAction)new VFXAction((AbstractGameEffect)new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect)));
            addToTop((AbstractGameAction)new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
        }
    }
}
