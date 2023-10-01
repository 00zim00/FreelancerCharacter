package ffzim.cards.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import ffzim.cards.BaseCard;
import ffzim.cards.BlackMage.Fire;
import static ffzim.BasicMod.makeID;


public class SecondMagicNumber extends DynamicVariable {

    @Override
    public int baseValue(AbstractCard card) {
        return ((BaseCard)card).baseSecondMagicNumber;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((BaseCard)card).isSecondMagicNumberModified;
    }

//    @Override
//    public void setIsModified(AbstractCard card, boolean b) {
//        ((BaseCard) card).isDefaultSecondMagicNumberModified = b;
//    }

    @Override
    public String key() {
        return makeID("M2");
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((BaseCard)card).upgradedSecondMagicNumber;
    }

    @Override
    public int value(AbstractCard card) {
        return ((BaseCard)card).secondMagicNumber;
    }


//    @Override
//    public int baseValue(AbstractCard card, boolean upgraded) {
//        if (card instanceof BaseCard) {
//            BaseCard baseCard = (BaseCard) card;
//            if (upgraded) {
//                return baseCard.baseSecondMagicNumber + baseCard.secondMagicUpgrade;
//            }
//        }
//        return baseValue(card);

}