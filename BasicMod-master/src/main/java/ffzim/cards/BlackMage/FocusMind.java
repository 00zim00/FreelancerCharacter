package ffzim.cards.BlackMage;


import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.MagicPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class FocusMind extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "FocusMind",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int Exhaustive = 2;
    private static final int UPG_Exhaustive = 2;
    public FocusMind() {
        super(cardInfo, true);
        setMagic(Exhaustive,UPG_Exhaustive);
        //setInnate(true);
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        PersistFields.setBaseValue(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, ExhaustiveField.ExhaustiveFields.exhaustive.get(this) + 2);
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 4);
        ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MagicPower(p, 1), 1));
    }



//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        // Reduce the cost of all cards with the tag "FFSPELL" in the hand, draw pile, and discard pile by 1 for this combat
//        for (AbstractCard card : p.hand.group) {
//            if (card.hasTag(CustomTags.FFSPELL)) {
//                if (card.cost > 1) {
//                    card.modifyCostForCombat(-1); // Reduce the cost by 1
//                }
//            }
//        }
//
//        for (AbstractCard card : p.drawPile.group) {
//            if (card.hasTag(CustomTags.FFSPELL)) {
//                if (card.cost > 1) {
//                    card.modifyCostForCombat(-1); // Reduce the cost by 1
//                }
//            }
//        }
//
//        for (AbstractCard card : p.discardPile.group) {
//            if (card.hasTag(CustomTags.FFSPELL)) {
//                if (card.cost > 1) {
//                    card.modifyCostForCombat(-1); // Reduce the cost by 1
//                }
//            }
//        }
//    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FocusMind();
    }
}

