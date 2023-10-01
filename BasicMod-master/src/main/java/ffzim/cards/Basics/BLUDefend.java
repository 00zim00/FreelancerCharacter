package ffzim.cards.Basics;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.TransformCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ffzim.BasicMod.makeID;

public class BLUDefend extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "DefendMimicry",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int CardPICK = 2;
    private static final int UPG_CardPICK = 1;

    public BLUDefend() {
        super(cardInfo, true);
        setMagic(CardPICK,UPG_CardPICK);
        PurgeField.purge.set(this, true);
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        List<AbstractCard> jobDefendCards = new ArrayList<>();
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card.hasTag(CustomTags.FFJOBDEFEND)) {
                if (upgraded) {card.upgrade();}
                jobDefendCards.add(card);
            }
        }
        CardGroup addingCardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        Collections.shuffle(jobDefendCards);
        int maxCards = Math.min(magicNumber, jobDefendCards.size());

        for (int i = 0; i < maxCards; i++) {
            addingCardGroup.addToTop(jobDefendCards.get(i));
        }

        if (!addingCardGroup.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(addingCardGroup, 1, "Choose Defend Mimicry.", false, false, false, false);
        }
        String thisID = this.cardID;
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                    AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                    AbstractCard swapCard = selectedCard.makeStatEquivalentCopy();
                    swapCard = swapCard.makeCopy();
                    selectedCard.freeToPlayOnce = true;
                    selectedCard.current_y = -200.0F * Settings.scale;
                    selectedCard.target_x = Settings.WIDTH / 2.0F;
                    selectedCard.target_y = Settings.HEIGHT / 2.0F;
                    selectedCard.targetAngle = 0.0F;
                    selectedCard.lighten(false);
                    selectedCard.drawScale = 0.12F;
                    selectedCard.targetDrawScale = 0.75F;
                    selectedCard.applyPowers();
                    AbstractMonster randomEnemy = AbstractDungeon.getRandomMonster();

                    if (randomEnemy != null) {
                        AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(selectedCard, randomEnemy));
                    }


                    for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
                        if (card.cardID.equals(thisID)) {
                            int index = p.discardPile.group.indexOf(card);
                            AbstractCard swapCardInstance = swapCard.makeCopy();
                            if (card.upgraded) {swapCardInstance.upgrade();}
                            p.discardPile.group.set(index, swapCardInstance.makeStatEquivalentCopy());
                        }
                    }

                    for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
                        if (card.cardID.equals(thisID)) {
                            int index = p.exhaustPile.group.indexOf(card);
                            AbstractCard swapCardInstance = swapCard.makeCopy();
                            if (card.upgraded) {swapCardInstance.upgrade();}
                            p.exhaustPile.group.set(index, swapCardInstance.makeStatEquivalentCopy());
                        }
                    }

                    for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                        if (card.cardID.equals(thisID)) {
                            int index = p.drawPile.group.indexOf(card);
                            AbstractCard swapCardInstance = swapCard.makeCopy();
                            if (card.upgraded) {swapCardInstance.upgrade();}
                            p.drawPile.group.set(index, swapCardInstance.makeStatEquivalentCopy());
                        }
                    }

                    for (AbstractCard card : AbstractDungeon.player.hand.group) {
                        if (card.cardID.equals(thisID)) {
                            int index = AbstractDungeon.player.hand.group.indexOf(card);
                            AbstractCard swapCardInstance = swapCard.makeCopy();
                            if (card.upgraded) {swapCardInstance.upgrade();}
                            AbstractDungeon.actionManager.addToBottom(new TransformCardInHandAction(index, swapCardInstance.makeStatEquivalentCopy()));
                        }
                    }

                }
                this.isDone = true;
            }
        });
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BLUDefend();
    }
}