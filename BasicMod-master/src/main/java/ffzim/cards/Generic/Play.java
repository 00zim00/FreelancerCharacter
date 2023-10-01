package ffzim.cards.Generic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import com.megacrit.cardcrawl.core.Settings;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;

import static ffzim.BasicMod.makeID;
public class Play extends BaseCard {

    public static final String ID = makeID("Play");
    private AbstractCard selectedCard;
    private final static CardInfo cardInfo = new CardInfo(
            "Play",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public Play() {
        super(cardInfo, true);
        setExhaust(true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            setExhaust(false);
        }
    }

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : player.hand.group) {
            if ((card.type == CardType.ATTACK || card.type == CardType.SKILL || card.type == CardType.POWER)
                    && card.canUse(player, null)
                    && card != this) {
                validCards.addToTop(card);
            }
        }

        if (!validCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose a card to play for 0 cost:", false, false, true, false);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        selectedCard.freeToPlayOnce = true;
                        selectedCard.applyPowers();
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

                    }
                    this.isDone = true;
                }
            });
        }
    }



    @Override
    public AbstractCard makeCopy() {
        return new Play();
    }
}
