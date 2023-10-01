package ffzim.cards.Generic.ItemCards.uncommon;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cardModifiers.ShadowMod;
import ffzim.cards.BaseCard;
import ffzim.powers.EnSpellInvisPower;
import ffzim.powers.EvasionPower;
import ffzim.powers.MagicPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;

import static basemod.BaseMod.addKeyword;
import static basemod.helpers.CardModifierManager.addModifier;
import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class EnSpell extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "EnSpell",
            1,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            //CardRarity.UNCOMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    public EnSpell() {
        super(cardInfo, false);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Needs to act like a power. All cards
        // Since EnSpell is added like Str, it effects all cards
        // I cant alter non BaseCards damage automatically.
        // Unless I do some snecko card mod things.
        // Unless i make it a card mod instead if useing the MagicPower pipeline

        addToBot(new ApplyPowerAction(p, p, new EnSpellInvisPower(p, 0)));

        for (AbstractCard card : p.hand.group) {
            if (card != this && !card.hasTag(CustomTags.FFENSPELL) && !card.hasTag(CustomTags.FFSPELL)) { // Skip the currently played card
                card.rawDescription = modID+":EnSpelled. NL " + card.rawDescription;
                card.tags.add(CustomTags.FFENSPELL);
                card.initializeDescription();
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EnSpell();
    }
}
