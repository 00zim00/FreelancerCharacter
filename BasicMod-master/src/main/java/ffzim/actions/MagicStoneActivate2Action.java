package ffzim.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import ffzim.cards.BlueMage.BlueMagic.AutoLife;

import java.util.List;
import java.util.UUID;

public class MagicStoneActivate2Action extends AbstractGameAction {
    private int increaseAmount;
    private DamageInfo info;
    private UUID uuid;

    private AbstractCard newCard;
    private AbstractCard thisCard;
    private static int maxEquip;
    private boolean isFirstPartCompleted = false;
    private List<AbstractCard> addedCards;

    public MagicStoneActivate2Action(AbstractCreature target, AbstractCreature source) {
        this.target = target;
        this.source = source;
        this.maxEquip = maxEquip;
        this.newCard = newCard;
        this.thisCard = thisCard;
        this.duration = 0.1F;
        //this.uuid = targetUUID;
    }

    public void update() {
        if (this.duration == 0.1F) {
            if (addedCards != null && !addedCards.isEmpty()) {
                AbstractCard addedCard = addedCards.get(addedCards.size() - 1);

                // Apply changes to the added card
                addedCard.freeToPlayOnce = true;
                addedCard.current_y = -200.0F * Settings.scale;
                addedCard.target_x = Settings.WIDTH / 2.0F;
                addedCard.target_y = Settings.HEIGHT / 2.0F;
                addedCard.targetAngle = 0.0F;
                addedCard.lighten(false);
                addedCard.drawScale = 0.12F;
                addedCard.targetDrawScale = 0.75F;
                addedCard.applyPowers();

                AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(addedCard, target));
            }
            tickDuration();
        }
    }
}


