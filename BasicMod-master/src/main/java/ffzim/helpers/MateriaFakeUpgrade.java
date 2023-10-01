package ffzim.helpers;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import ffzim.cards.BaseCard;

import static ffzim.BasicMod.makeID;
import static ffzim.util.TextureLoader.getCardTextureString;

public class MateriaFakeUpgrade  //implements MultiUpgradeCard
{

    private AbstractCard FakeCard;
    private BaseCard FakeCard2;
    private BaseCard thisInputCard;

    public void setCustomUpgradeDataMethod(AbstractCard inputCard, int fakeCardlevel, Boolean setNameOnly) {


        if (inputCard instanceof BaseCard) {
            BaseCard FakeCard2 = ((BaseCard) FakeCard);
            thisInputCard = (BaseCard) inputCard;
            boolean ApCardPreview = ((BaseCard) inputCard).isCardPreview(inputCard);

            if (ApCardPreview){

                if (fakeCardlevel == 1 && ((BaseCard) inputCard).getAPLevel1Card(inputCard) != null) {
                    FakeCard = ((BaseCard) inputCard).getAPLevel1Card(inputCard);
                    FakeCard2 = ((BaseCard) FakeCard);
                    FakeCard2.setCardLevel(1);
                }
                if (fakeCardlevel == 2 && ((BaseCard) inputCard).getAPLevel2Card(inputCard) != null) {
                    FakeCard = ((BaseCard) inputCard).getAPLevel2Card(inputCard);
                    FakeCard2 = ((BaseCard) FakeCard);
                    FakeCard2.setCardLevel(2);
                }
                if (fakeCardlevel == 3 && ((BaseCard) inputCard).getAPLevel3Card(inputCard) != null) {
                    FakeCard = ((BaseCard) inputCard).getAPLevel3Card(inputCard);
                    FakeCard2 = ((BaseCard) FakeCard);
                    FakeCard2.setCardLevel(3);
                }
                if (fakeCardlevel == 4 && ((BaseCard) inputCard).getAPLevel4Card(inputCard) != null) {
                    FakeCard = ((BaseCard) inputCard).getAPLevel4Card(inputCard);
                    FakeCard2 = ((BaseCard) FakeCard);
                    FakeCard2.setCardLevel(4);
                }
                BaseCard FakeCard = ((BaseCard) FakeCard2);
                if (FakeCard != null && !setNameOnly) {
                    MultiCardPreview.clear(FakeCard);
                    setAPLevelCard();
                }else if (FakeCard != null) {
                    thisInputCard.name = FakeCard.name;
                    MultiCardPreview.clear(FakeCard);
                }
            }
        }
    }

    public void setAPLevelCard() {
        thisInputCard.rawDescription = FakeCard.rawDescription;

        String[] cardIDParts = FakeCard.cardID.split(":");
        if (cardIDParts.length > 1) {
            String cardName = cardIDParts[1];
            String textureString = getCardTextureString(makeID(FakeCard.cardID), FakeCard.type);
            String modifiedTextureString = textureString.replace("default", cardName);
            thisInputCard.loadCardImage(modifiedTextureString);
            thisInputCard.setCardLevel(((BaseCard) FakeCard).getCardLevel(FakeCard));
            MultiCardPreview.clear(thisInputCard);
        }

        //inputCard.setPreview();
        thisInputCard.applyPowers();
        thisInputCard.initializeDescription();
    }

    private void setPreview() {

    }

}