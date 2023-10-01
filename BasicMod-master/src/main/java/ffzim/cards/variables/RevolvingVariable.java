package ffzim.cards.variables;


import basemod.abstracts.DynamicVariable;
import ffzim.cards.RevolvingFields;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static ffzim.BasicMod.makeID;

public class RevolvingVariable extends DynamicVariable // Change class name
{
    @Override
    public String key()
    {
        return makeID("revolving"); // Change variable key
    }

    @Override
    public boolean isModified(AbstractCard card)
    {
        return RevolvingFields.revolving.get(card).intValue() != RevolvingFields.baseRevolving.get(card).intValue(); // Change field names
    }

    @Override
    public int value(AbstractCard card)
    {
        return RevolvingFields.revolving.get(card); // Change field name
    }

    @Override
    public int baseValue(AbstractCard card)
    {
        return RevolvingFields.baseRevolving.get(card); // Change field name
    }

    @Override
    public boolean upgraded(AbstractCard card)
    {
        return RevolvingFields.isRevolvingUpgraded.get(card); // Change field name
    }
}
