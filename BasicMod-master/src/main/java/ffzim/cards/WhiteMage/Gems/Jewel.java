package ffzim.cards.WhiteMage.Gems;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.WhiteMage.Cure;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class Jewel extends BaseCard { //implements AddAudioSubscriber {
    private final static CardInfo cardInfo = new CardInfo(
            "Jewel",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

//    public void receiveAddAudio() {
//        BaseMod.addAudio("ffzim:Jewel", "Resources/audio/Jewel.mp3");
//    }

    public static final String ID = makeID(cardInfo.baseId);

    public Jewel() {
        super(cardInfo, true);

        selfRetain = true;
       // PurgeField.purge.set(this, true);
        cardsToPreview = new Cure();
        tags.add(CustomTags.FFSPELL);
       // FleetingField.fleeting.set(this,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

//        ArrayList<AbstractCard> gemCards = new ArrayList<>();
//        for (AbstractCard card : CardLibrary.getAllCards()) {
//            if (card.hasTag(CustomTags.FFGEM)) {
//                gemCards.add(card);
//            }
//        }
//
//        if (!gemCards.isEmpty()) {
//            // Get a random card from the gemCards list
//            Random rng = new Random();
//            AbstractCard randomGemCard = gemCards.get(rng.random(gemCards.size() - 1));
//            if (randomGemCard != null) {
//                AbstractCard copy = randomGemCard.makeStatEquivalentCopy();
//                copy.setCostForTurn(0); // Set the cost to 0 for this turn
//                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(copy, m, this.energyOnUse, true));
//            }
//        }
            addToBot(new SFXAction(modID + ":Jewel"));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Jewel();
    }
}