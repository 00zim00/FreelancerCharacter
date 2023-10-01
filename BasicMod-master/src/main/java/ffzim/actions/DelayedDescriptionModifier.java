package ffzim.actions;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import com.megacrit.cardcrawl.cards.AbstractCard;
import basemod.abstracts.AbstractCardModifier;

public class DelayedDescriptionModifier extends AbstractCardModifier {
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + " NL This card's description is modified.";
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DelayedDescriptionModifier();
    }
}


//    DelayedDescriptionModifier delayedDescriptionModifier = new DelayedDescriptionModifier();
//    CardModifierManager.addModifier(cardToAdd, delayedDescriptionModifier);
