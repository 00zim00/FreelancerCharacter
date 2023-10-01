package ffzim.cards.Thief;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.StealAction;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Steal extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Steal",
            0,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

// implements AlternateCardCostModifier

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE_GAIN_PERCENTAGE = 10;
    private static final int MINDAMAGE = 1;
    private static final int MAXDAMAGE = 5;
    private static final int UPG_MINDAMAGE = 1;
    private static final int UPG_MAXDAMAGE = 10;


    // GainGoldTextEffect
    // RainingGold
    // TouchPickupGold
    public Steal() {
        super(cardInfo, true);
        setMagic(DAMAGE_GAIN_PERCENTAGE);
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {;
        addToBot(new StealAction(m,p));


//        ArrayList<AbstractCard> rewardCards = AbstractDungeon.getRewardCards();
//        int randomIndex = AbstractDungeon.cardRng.random(rewardCards.size());
//        AbstractCard chosenCard = rewardCards.get(randomIndex);
//        ArrayList<AbstractCard> chosenCardList = new ArrayList<>(Arrays.asList(chosenCard));
//        if (rewardCards != null && !rewardCards.isEmpty()){
//            AbstractDungeon.cardRewardScreen.open(chosenCardList, null, "Steal Success!");
//        }

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Steal();
    }
}