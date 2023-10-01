package ffzim.cards.Generic.ItemCards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class HolyWater extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "HolyWater",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public HolyWater() {
        super(cardInfo, false);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard cards : p.hand.group) {
            if (cards.type == CardType.STATUS) {
                addToBot(new ExhaustSpecificCardAction(cards, p.hand));
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HolyWater();
    }
}
