package ffzim.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static ffzim.BasicMod.makeID;

public class SelfDestructInvisPower extends BasePower implements InvisiblePower {

    public static final String POWER_ID = makeID("SelfDestructInvisPower");
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;


    // OnPlayerDeathPower 	Hook for when the player dies. Can stop the player from dying.
    public SelfDestructInvisPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.owner = owner;
        this.amount = -1;
        updateDescription();
    }

    public int getTotalEnemyHP() {
        int totalHP = 0;

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!monster.isDeadOrEscaped()) {
                totalHP += monster.currentHealth;
            }
        }

        return totalHP;
    }

    public void atEndOfRound() {
        int totalHP2 = getTotalEnemyHP();
        System.out.println("totalHP2: " + totalHP2);
        if (totalHP2 >= 0) {
            System.out.println("totalHP2.5: " + totalHP2);
            flash();
            addToBot(new LoseHPAction(this.owner, this.owner, 99999));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));

        }
        System.out.println("totalHP3: " + totalHP2);
    }
}