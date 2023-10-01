package ffzim.helpers;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import ffzim.cards.BaseCard;
import ffzim.cards.variables.UniqueSaveable;
import ffzim.util.CustomTags;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.clear;
import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;

public class MateriaUpdatePreview {

    public static void setPreview(AbstractCard card) {
        setPreviewContinued(card,false, 0);

    }

    public static void setPreview(AbstractCard card, Boolean isInDes) {
        setPreviewContinued(card,isInDes, 0);
    }

    public static void setPreview(AbstractCard card,Boolean isInDes, int modifier) {
        setPreviewContinued(card,isInDes, modifier);
    }

    private static void setPreviewContinued(AbstractCard card, Boolean isInDes, int modifier) {
        if (card instanceof BaseCard) {
            BaseCard tempCard = ((BaseCard) card);
            BaseCard Card1 = ((BaseCard) tempCard.getAPLevel1Card(card));
            if (Card1 != null) {
                ((BaseCard) Card1).setRenderInGrayscale(Card1, false);
            }
            BaseCard Card2 = ((BaseCard) tempCard.getAPLevel2Card(card));
            BaseCard Card3 = ((BaseCard) tempCard.getAPLevel3Card(card));
            BaseCard Card4 = ((BaseCard) tempCard.getAPLevel4Card(card));

            if (AbstractDungeon.isPlayerInDungeon()) {
                AbstractCard[] cardsToCheck = {Card2, Card3, Card4};
                for (AbstractCard cardToCheck : cardsToCheck) {
                    if (cardToCheck != null) {
                        ((BaseCard) cardToCheck).setRenderInGrayscale(cardToCheck, true);
                    }
                }
                AbstractCard[] cardsToCheck2 = {Card1, Card2, Card3, Card4};
                for (AbstractCard cardToCheck2 : cardsToCheck2) {
                    if (cardToCheck2 != null) {
                        ((BaseCard) cardToCheck2).applyPowers();
                        ((BaseCard) cardToCheck2).calculateCardDamage(null);
                    }
                }

                int tempTimes = modifier + card.timesUpgraded;
                switch (tempTimes) {
                    case 0:
                        break;
                    case 1:

                        if (Card2 != null) {
                            Card2.setRenderInGrayscale(Card2, false);
                        }
                        break;
                    case 2:

                        if (Card2 != null) {
                            Card2.setRenderInGrayscale(Card2, false);
                        }
                        if (Card3 != null) {
                            Card3.setRenderInGrayscale(Card3, false);
                        }
                        break;
                    case 3:

                        if (Card2 != null) {
                            Card2.setRenderInGrayscale(Card2, false);
                        }
                        if (Card3 != null) {
                            Card3.setRenderInGrayscale(Card3, false);
                        }
                        if (Card4 != null) {
                            Card4.setRenderInGrayscale(Card4, false);
                        }
                        break;
                }
            }
            boolean currentHorizontal = horizontal.get(card);

            clear(card);
            if (Card2 == null){
                MultiCardPreview.add(card, Card1);
            }else if (Card3 == null){
                MultiCardPreview.add(card, Card1, Card2);
            }else if (Card4 == null){
                MultiCardPreview.add(card, Card1, Card2, Card3);
            }else{
                MultiCardPreview.add(card, Card1, Card2, Card3,Card4);
            }
            if (currentHorizontal){
                horizontal.set(card, true);
            }
            if (!isInDes){
                card.initializeDescription();
            }
        }
    }
}
