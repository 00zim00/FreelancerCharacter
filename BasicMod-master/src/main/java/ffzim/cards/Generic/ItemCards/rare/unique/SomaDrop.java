package ffzim.cards.Generic.ItemCards.rare.unique;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.variables.PlayerSaveable;
import ffzim.powers.GainEnergyPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;
import static ffzim.helpers.UniqueCard.ApplyUniqueCard;

public class SomaDrop extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "SomaDrop",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    private final CardRarity thisRarity = AbstractCard.CardRarity.RARE;
    private static final int permENERGYGAIN = 1;
    public SomaDrop() {
        super(cardInfo, false);
        setMagic(permENERGYGAIN);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
        tags.add(CustomTags.FFUNIQUE);
        this.tags.add(CardTags.HEALING);
        ApplyUniqueCard(this,cardInfo.baseId);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        PlayerSaveable playerSaveable = PlayerSaveable.getInstance(p);
        playerSaveable.increaseSomaDrop(1);
        addToBot(new ApplyPowerAction(p, p, new GainEnergyPower( p, permENERGYGAIN), permENERGYGAIN));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SomaDrop();
    }
}
