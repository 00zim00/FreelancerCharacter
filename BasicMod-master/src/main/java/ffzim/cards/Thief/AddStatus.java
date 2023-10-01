package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.helpers.ADD_STATUS_CARD_IDS;
import ffzim.powers.AddStatusPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class AddStatus extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AddStatus",
            3,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public AddStatus() {
        super(cardInfo, true);
        setCostUpgrade(2);
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            this.upgradedCost = true;
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : p.hand.group) {
            if (ADD_STATUS_CARD_IDS.VULNERABLE_CARD_IDS.contains(card.cardID)) {
                card.tags.add(CustomTags.FFVULNERABLE);
                //validCards.addToTop(card);
            }
            if (ADD_STATUS_CARD_IDS.WEAK_CARD_IDS.contains(card.cardID)) {
                card.tags.add(CustomTags.FFWEAK);
                //validCards.addToTop(card);
            }
            if (ADD_STATUS_CARD_IDS.POISON_CARD_IDS.contains(card.cardID)) {
                card.tags.add(CustomTags.FFPOISON);
                //validCards.addToTop(card);
            }
            if (card.hasTag(CustomTags.FFVULNERABLE) || card.hasTag(CustomTags.FFWEAK) || card.hasTag(CustomTags.FFPOISON) || card.hasTag(CustomTags.FFVENOM) || card.hasTag(CustomTags.FFSLOW) || card.hasTag(CustomTags.FFTROUBLE)) {
                validCards.addToTop(card);
            }

        }
            if (!validCards.isEmpty()) {
                AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose Card:", false, false, true, false);
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                            AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                            AbstractPower addStatusPower = AbstractDungeon.player.getPower(makeID("AddStatusPower"));
                            if (addStatusPower instanceof AddStatusPower) {
                                ((AddStatusPower) addStatusPower).AddStatusCARD = selectedCard; // Cast to AddStatusPower and update the CARD
                                addStatusPower.updateDescription();
                            }
                            addToBot(new ApplyPowerAction(p, p, new AddStatusPower(p, 1,selectedCard), 1));
                            addToBot(new ExhaustSpecificCardAction(selectedCard, p.hand));
                        }
                        this.isDone = true;
                    }
                });
            }
        }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AddStatus();
    }
}
