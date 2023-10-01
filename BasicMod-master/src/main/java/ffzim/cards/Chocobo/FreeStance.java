package ffzim.cards.Chocobo;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.unique.AddCardToDeckAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.IndexCardReplaceAction;
import ffzim.cards.BaseCard;
import ffzim.cards.Chocobo.Healer.ChocoHealer;
import ffzim.cards.Chocobo.Healer.ChocoSurge;
import ffzim.cards.WhiteMage.Cura;
import ffzim.cards.WhiteMage.Cure;
import ffzim.util.CardInfo;

import java.util.ArrayList;
import java.util.Collections;

import static ffzim.BasicMod.makeID;

public class FreeStance extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FreeStance",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);



    public static final String ID = makeID(cardInfo.baseId);

    public FreeStance() {
        super(cardInfo, true);
        PurgeField.purge.set(this, true);
//        AbstractCard Card2 =new Cura();
//        rawDescription = Card2.rawDescription;
//        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
//        validCards.addToTop(new ChocoAttacker());
//        validCards.addToTop(new ChocoDefender());
//        validCards.addToTop(new ChocoHealer());
        validCards.addToTop(new FreeStance());
        if (this.upgraded) {
            for (AbstractCard card : validCards.group) {
                card.upgrade();
            }
        }
        ArrayList<AbstractCard> validCardList = new ArrayList<>(validCards.group);
        addToBot(new SelectCardsAction(validCardList,1,"Choose 1 to Replace.",false,c -> true,cards -> {
            if (!cards.isEmpty()) {
                AbstractCard selectedCard = validCardList.get(0);

                // all stuffed and not working

                addToBot(new IndexCardReplaceAction(this,selectedCard, p.masterDeck.group));
//                ArrayList<AbstractCard> masterGroup = p.masterDeck.group;
//                int index = masterGroup.indexOf(selectedCard);
//                if (index >= 0) {
//                    masterGroup.set(index, selectedCard);
//                }
//
//                AbstractCard tempSelectedCard = selectedCard.makeSameInstanceOf();
//                p.masterDeck.removeCard(this);
//                p.masterDeck.addToTop(selectedCard);
//
//                addToBot(new MakeTempCardInDiscardAction(tempSelectedCard, 1));
                //p.discardPile.addToTop(tempSelectedCard);
            }
        }));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FreeStance();
    }
}