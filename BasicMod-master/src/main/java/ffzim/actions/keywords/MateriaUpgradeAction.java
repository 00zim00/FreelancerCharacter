package ffzim.actions.keywords;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.Grayscale;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import ffzim.cards.BaseCard;
import ffzim.util.CustomTags;
import com.badlogic.gdx.graphics.Color;

import static com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree.deltaX;
import static com.evacipated.cardcrawl.mod.stslib.ui.MultiUpgradeTree.deltaY;
import static com.megacrit.cardcrawl.core.Settings.renderScale;
import static ffzim.BasicMod.modID;

public class MateriaUpgradeAction extends AbstractGameAction {

    private AbstractPlayer p;
    private AbstractMonster m;
    private AbstractCard materia;
    private AbstractCard AP1;
    private AbstractCard AP2;
    private AbstractCard AP3;
    private AbstractCard AP4;
    private AbstractCard tempCard;

    private boolean hasPlayedSound = false;

    public MateriaUpgradeAction(AbstractPlayer player, AbstractMonster monster, AbstractCard materia)
    {
        setValues(target, source, this.amount);
        this.p = player;
        this.m = monster;
        this.materia = materia;
        this.AP1 =  ((BaseCard) materia).getAPLevel1Card(materia);
        this.AP2 = ((BaseCard) materia).getAPLevel2Card(materia);
        this.AP3 = ((BaseCard) materia).getAPLevel3Card(materia);
        this.AP4 = ((BaseCard) materia).getAPLevel4Card(materia);
        this.duration = 0.25F;
    }


    public void update() {
        int timesUpgraded = materia.timesUpgraded;
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        int currentEnergy = EnergyPanel.totalCount;

        System.out.println("timesUpgraded: " + timesUpgraded);
        if (timesUpgraded >= 0 && AP1 != null) {
            ((BaseCard) AP1).setRenderInGrayscale(AP1, false);
            validCards.addToTop(AP1);
        }
        if (AP2 != null) {
            if (timesUpgraded >= 1 && AP2.cost <= currentEnergy) {
                ((BaseCard) AP2).setRenderInGrayscale(AP2, false);
                validCards.addToTop(AP2);
            }
        }
        if (AP3 != null) {
            if (timesUpgraded >= 2 && AP3.cost <= currentEnergy) {
                ((BaseCard) AP3).setRenderInGrayscale(AP3, false);
                validCards.addToTop(AP3);
            }
        }
        if (AP4 != null) {
            if (timesUpgraded >= 3 && AP4.cost <= currentEnergy) {
                ((BaseCard) AP4).setRenderInGrayscale(AP4, false);
                validCards.addToTop(AP4);
            }
        }

        if (!validCards.isEmpty()) {
            for (int i = 0; i < validCards.size(); i++) {
                AbstractCard cardToRefresh = validCards.getNCardFromTop(i);
                if (cardToRefresh != null) {
                    cardToRefresh.applyPowers();
                    cardToRefresh.calculateCardDamage(m);
                }
            }
            if (validCards.size() > 1) {
                AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose Spell:", false, false, true, false);
            }
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() || validCards.size() == 1) {
                        AbstractCard selectedCard = validCards.getTopCard();
                        if (!(validCards.size() == 1)) {
                            selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        }
                        selectedCard.freeToPlayOnce = false;
                        selectedCard.current_y = -200.0F * Settings.scale;
                        selectedCard.target_x = Settings.WIDTH / 2.0F;
                        selectedCard.target_y = Settings.HEIGHT / 2.0F;
                        selectedCard.targetAngle = 0.0F;
                        selectedCard.lighten(false);
                        selectedCard.drawScale = 0.12F;
                        selectedCard.targetDrawScale = 0.75F;
                        selectedCard.calculateCardDamage(m);
                        selectedCard.purgeOnUse = true;
                        selectedCard.applyPowers();
                        AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(selectedCard, m));
                    }
                    this.isDone = true;
                }
            });
        }
        this.isDone = true;
    }

}



