package ffzim.cards.Generic.ItemCards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.powers.NullVulnerablePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class NullVulnerable extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "NullVulnerable",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BUFF = 2;
    public NullVulnerable() {
        super(cardInfo, false);
        setMagic(BUFF);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new NullVulnerablePower( p, BUFF), BUFF));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new NullVulnerable();
    }
}
