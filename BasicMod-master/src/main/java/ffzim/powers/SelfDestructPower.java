package ffzim.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import ffzim.actions.CustomWaitAction;
import ffzim.actions.SelfDestructAction;

import static ffzim.BasicMod.makeID;

public class SelfDestructPower extends BasePower {

    public static final String POWER_ID = makeID("SelfDestructPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

    public SelfDestructPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);

        this.owner = owner;
        this.amount = amount;
        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            flash();
            addToBot(new VFXAction(new ExplosionSmallEffect(this.owner.hb.cX, this.owner.hb.cY), 0.2F));
            addToBot( new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(this.amount, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            addToBot(new CustomWaitAction(0.0F));
            System.out.println("Are monsters dead11? " + AbstractDungeon.getMonsters().areMonstersDead());
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    System.out.println("Are monsters dead22? " + AbstractDungeon.getMonsters().areMonstersDead());
                    addToBot(new SelfDestructAction(owner, owner, amount, SelfDestructPower.POWER_ID));
                    isDone = true;
                }
            });
        }
    }
}