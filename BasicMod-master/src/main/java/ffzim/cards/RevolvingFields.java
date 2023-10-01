package ffzim.cards;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        clz=AbstractCard.class,
        method=SpirePatch.CLASS
)
public class RevolvingFields
{
    public static SpireField<Integer> revolving = new SpireField<>(() -> -1);
    public static SpireField<Integer> baseRevolving = new SpireField<>(() -> -1);
    public static SpireField<Boolean> isRevolvingUpgraded = new SpireField<>(() -> false);
    //public static SpireField<AbstractCard> nextRevolving = new SpireField<>(() -> null); // New SpireField for the second card
    public static SpireField<String> nextRevolving = new SpireField<>(() -> "");
    public static boolean revolvingNow;
    public static int revolvedValue;

    public static void setBaseValue(AbstractCard card,String card2, int amount)
    {

        System.out.println("magic Check: " + card.baseMagicNumber);
        System.out.println("Pre Bas1: " + RevolvingFields.baseRevolving.get(card));
        baseRevolving.set(card, amount);
        System.out.println("Post Bas1: " + RevolvingFields.baseRevolving.get(card));;
        if (card.baseMagicNumber >= 1) {
            System.out.println("Pre Bas1: " + RevolvingFields.baseRevolving.get(card));
            revolving.set(card, card.baseMagicNumber);
            System.out.println("Post Bas1: " + RevolvingFields.baseRevolving.get(card));

        }else{
            System.out.println("Pre Bas2: " + RevolvingFields.baseRevolving.get(card));
            revolving.set(card, amount);
            System.out.println("Post Bas2: " + RevolvingFields.baseRevolving.get(card));
        }

//        System.out.println("Pre Rev1: " + RevolvingFields.revolving.get(card));
//        revolving.set(card, amount);
//        System.out.println("Post Rev1: " + RevolvingFields.revolving.get(card));
        nextRevolving.set(card, card2);
        card.initializeDescription();

    }

    public static void upgrade(AbstractCard card,String card2, int amount)
    {
        isRevolvingUpgraded.set(card, true);
        setBaseValue(card,card2, baseRevolving.get(card) + amount);
    }

    public static void decrement(AbstractCard card)
    {
        revolving.set(card, revolving.get(card) - 1);
    }
}
