package ffzim.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;

public class BouncingSlimeAction extends AbstractGameAction {
    private static final float DURATION = 0.01F;

    private static final float POST_ATTACK_WAIT_DUR = 0.1F;

    private int numTimes;

    private int amount;

    public BouncingSlimeAction(AbstractCreature target, int amount, int numTimes) {
        this.target = target;
        this.actionType = AbstractGameAction.ActionType.DEBUFF;
        this.duration = 0.01F;
        this.numTimes = numTimes;
        this.amount = amount;
    }

    public void update() {
        if (this.target == null) {
            this.isDone = true;
            return;
        }
        if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
            this.isDone = true;
            return;
        }
        if (this.numTimes > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.numTimes--;
            AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            addToTop(new BouncingSlimeAction(randomMonster, this.amount, this.numTimes));
            addToTop(new VFXAction(new PotionBounceEffect(this.target.hb.cX, this.target.hb.cY, randomMonster.hb.cX, randomMonster.hb.cY), 0.4F));
        }
        if (this.target.currentHealth > 0) {
            int rng = MathUtils.random(1, 3);
            if (rng == 1){
                addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player, new VulnerablePower(this.target, this.amount,false), this.amount, true, AttackEffect.POISON));
                addToTop(new WaitAction(0.1F));
            }
            if (rng == 2){
                addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player, new WeakPower(this.target, this.amount,false), this.amount, true, AttackEffect.POISON));
                addToTop(new WaitAction(0.1F));
            }
            if (rng == 3) {
                addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player, new SlowPower(this.target,this.amount), this.amount, true, AttackEffect.POISON));
                addToTop(new WaitAction(0.1F));
            }
            

        }
        this.isDone = true;
    }
}
