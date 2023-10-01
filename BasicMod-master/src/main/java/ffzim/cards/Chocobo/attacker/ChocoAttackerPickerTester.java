package ffzim.cards.Chocobo.attacker;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import java.util.Objects;

import static ffzim.BasicMod.makeID;

public class ChocoAttackerPickerTester extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoAttackerPickerTester",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);
    public CardGroup ChocoAttackerCardsGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private final AbstractCard CardOption1 = new ChocoStrike();
    private final AbstractCard CardOption2 = new ChocoBeak();
    private final AbstractCard CardOption3 = new ChocoBlast();
    private final AbstractCard CardOption4 = new ChocoUnleash();
    private static final int BUFF = 3;
    private static final int UPG_BUFF = 2;

    public ChocoAttackerPickerTester() {
        super(cardInfo, true);
        setCardsOptions();
    }

    public void setCardsOptions(){
        ChocoAttackerCardsGroup.clear();
        ChocoAttackerCardsGroup.addToTop(CardOption1);
        ChocoAttackerCardsGroup.addToTop(CardOption2);
        ChocoAttackerCardsGroup.addToTop(CardOption3);
        ChocoAttackerCardsGroup.addToTop(CardOption4);
    }




    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCardList =  ChocoAttackerCardsGroup;
        validCardList.addToTop(new Defend_Red());
        System.out.println("validCardList: " + validCardList);
        if (!validCardList.isEmpty()) {
            CardGroup cardsToRemove = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard gainedCard : gainedChocoboCards.group) {
                for (AbstractCard validCard : validCardList.group) {
                    if (gainedCard.cardID.equals(validCard.cardID)) {
                        cardsToRemove.addToTop(validCard);
                    }
                }
            }

            validCardList.group.removeAll(cardsToRemove.group);
        }

        System.out.println("gainedChocobo22: " + gainedChocoboCards);
        if (!validCardList.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(validCardList, 1, "Choose 1 to Play", false, false, true, false);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        if (Objects.equals(selectedCard.cardID, new Defend_Red().cardID)) {
                            gainedChocoboCards.clear();
                        } else {
                            //gainedChocoboCards.add(selectedCard);
                            gainedChocoboCards.addToTop(selectedCard);
                        }
                    }
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoAttackerPickerTester();
    }
}