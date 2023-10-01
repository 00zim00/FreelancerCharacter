package ffzim.cards.Generic.startercards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.RandomTempItemCardInHandAction;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ItemCommand extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ItemCommand",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public ItemCommand() {this(true);}
    public ItemCommand(boolean makePreview) {
        super(cardInfo, true);
        setExhaust(true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded){
            addToBot(new RandomTempItemCardInHandAction(1,CardRarity.COMMON,false));
        }else{
            addToBot(new RandomTempItemCardInHandAction(1,CardRarity.UNCOMMON,false));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ItemCommand();
    }
}
