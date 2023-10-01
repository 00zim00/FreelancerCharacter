package ffzim.cards.Generic;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class MagicStone extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MagicStone",
            -2,
            CardType.CURSE,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            CardColor.COLORLESS); 

// add a upgrade that when exhuasted at end of turn gain 1 bonus energy


    public static final String ID = makeID(cardInfo.baseId);

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }
    public MagicStone() {
        super(cardInfo, true);
        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MagicStone();
    }
}