//package ffzim.cards.variables;
//
//import basemod.abstracts.DynamicVariable;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//
//public class MagicNumber2 extends DynamicVariable
//{
//    @Override
//    public String key()
//    {
//        return "ffzim:M2";
//    }
//
//    @Override
//    public boolean isModified(AbstractCard card) {
//        // Implement logic to check if the card's value has been modified by this variable
//        return false;
//    }
//
//    @Override
//    public int value(AbstractCard card) {
//        // Implement logic to calculate the value of the dynamic variable for the given card
//        return card.magicNumber2; // Example: return the card's magicNumber as the dynamic value
//    }
//
//    @Override
//    public int baseValue(AbstractCard card) {
//        // Implement logic to get the base value of the dynamic variable for the given card
//        return card.baseMagicNumber2; // Example: return the card's baseMagicNumber as the base value
//    }
//
//    @Override
//    public boolean upgraded(AbstractCard card) {
//        // Implement logic to check if the card has been upgraded in a way related to this dynamic variable
//        return false;
//    }
//}