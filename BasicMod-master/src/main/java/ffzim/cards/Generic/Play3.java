package ffzim.cards.Generic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;

public class Play3 extends BaseCard {

    public static final String ID = makeID("Play2");
    private AbstractCard selectedCard;
    private final static CardInfo cardInfo = new CardInfo(
            "Play3",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public Play3() {
        super(cardInfo, false);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> validCards = new ArrayList<>();


//        for (AbstractCard card : p.hand.group) {
//            if (card.type == CardType.ATTACK && (card.target == CardTarget.ENEMY || card.target == CardTarget.ALL_ENEMY)) {
//                validCards.add(card);
//            }
//        }

        if (!validCards.isEmpty()) {
            CardGroup cardChoiceGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard validCard : validCards) {
                cardChoiceGroup.addToBottom(validCard);

            }

            AbstractDungeon.gridSelectScreen.open(cardChoiceGroup, 1, "Choose a card to play", false);
///



            //
//            selectedCard.use(AbstractDungeon.player, null);


            //            selectedCard.use(AbstractDungeon.player, null);
//            selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
//
//            addToTop((AbstractGameAction)new NewQueueCardAction(card, this.target, false, true));
//            addToTop((AbstractGameAction)new UnlimboAction(card));
//            if (!Settings.FAST_MODE) {
//                addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));
//            } else {
//                addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_FASTER));
//            }
        }
    }



    @Override
    public AbstractCard makeCopy() {
        return new Play3();
    }
}
