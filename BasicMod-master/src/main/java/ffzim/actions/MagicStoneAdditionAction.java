package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;

import java.util.UUID;

public class MagicStoneAdditionAction extends AbstractGameAction {
    private int increaseAmount;
    private UUID uuid;

    public MagicStoneAdditionAction(int incAmount, UUID targetUUID) {
        this.increaseAmount = incAmount;
        this.duration = 0.1F;
        this.uuid = targetUUID;
    }

    public void update() {
        if (this.duration == 0.1F) {
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (!c.uuid.equals(this.uuid))
                        continue;
                    c.misc += this.increaseAmount;
                    if (c.misc < 0) {c.misc = 0;}
                    c.applyPowers();
                    c.baseMagicNumber = c.misc;
                    c.isDamageModified = false;
                }
                for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                    c.misc += this.increaseAmount;
                    if (c.misc < 0) {c.misc = 0;}
                    c.applyPowers();
                    c.baseDamage = c.misc;
                    c.initializeDescription();
                }
        }
        tickDuration();
    }
}


