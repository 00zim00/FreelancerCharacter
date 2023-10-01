package ffzim.actions;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import ffzim.cards.BlueMage.BlueMagic.AutoLife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MagicStoneActivateAction extends AbstractGameAction {
    private int increaseAmount;
    private UUID uuid;

    private AbstractCard newCard;
    private AbstractCard thisCard;
    private AbstractCard cardToPlay;
    private int maxEquip;

    public MagicStoneActivateAction(AbstractCreature target, AbstractCreature source, AbstractCard newCard, AbstractCard thisCard, int maxEquip, AbstractCard cardToPlay) {
        this.target = target;
        this.source = source;
        this.maxEquip = maxEquip;
        this.newCard = newCard;
        this.thisCard = thisCard;
        this.cardToPlay = cardToPlay;
        this.duration = 0.1F;
        this.uuid = thisCard.uuid;
    }

    public void update() {
        if (thisCard.misc <= 0) {
//            AbstractCard cardToObtain = newCard;
//            if (thisCard.upgraded) {
//                cardToObtain.upgrade();
//            }
            AbstractDungeon.effectList.add(new BorderLongFlashEffect(Color.LIGHT_GRAY));
            ShowCardAndObtainEffect s = new ShowCardAndObtainEffect(newCard, (float) Settings.WIDTH * 3.0f / 4.0F, (float) Settings.HEIGHT / 2.0F);
            s.duration = 1.4f;
            s.startingDuration = 1.4f;
            AbstractDungeon.effectList.add(s);
            AbstractDungeon.effectList.add(new UpgradeShineEffect((float) Settings.WIDTH * 3.0f / 4.0F, (float) Settings.HEIGHT / 2.0F));

            for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                if (card.uuid.equals(this.uuid)) {
                    AbstractDungeon.player.masterDeck.removeCard(card);
                    break;
                }
            }
            if (AbstractDungeon.isPlayerInDungeon()) {
                //AbstractCard cardToPlay = newCard.makeSameInstanceOf();
                AbstractDungeon.player.limbo.group.add(cardToPlay);
                cardToPlay.freeToPlayOnce = true;
                cardToPlay.current_y = -200.0F * Settings.scale;
                cardToPlay.target_x = Settings.WIDTH / 2.0F;
                cardToPlay.target_y = Settings.HEIGHT / 2.0F;
                cardToPlay.targetAngle = 0.0F;
                cardToPlay.lighten(false);
                cardToPlay.drawScale = 0.12F;
                cardToPlay.targetDrawScale = 0.75F;
                cardToPlay.applyPowers();
                AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);
                addToTop(new NewQueueCardAction(cardToPlay, m, false, true));
                addToTop(new UnlimboAction(cardToPlay));
                if (!Settings.FAST_MODE) {
                    addToTop(new WaitAction(Settings.ACTION_DUR_MED));
                } else {
                    addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
                }
            }
                thisCard.exhaust = false;
                thisCard.purgeOnUse = true;
        }
        isDone = true;
        }
    }


