package ffzim.cards.Generic.ItemCards.common;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class GoldNeedle extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "GoldNeedle",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public GoldNeedle() {
        super(cardInfo, false);
        FleetingField.fleeting.set(this, true);
        setSelfRetain(true);
        tags.add(CustomTags.FFITEM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(FrailPower.POWER_ID)) {
            addToBot( new RemoveSpecificPowerAction(p, p, FrailPower.POWER_ID));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GoldNeedle();
    }
}
