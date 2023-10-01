package ffzim.cards.Generic.ItemCards.rare.unique;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import ffzim.cards.BaseCard;
import ffzim.cards.variables.PlayerSaveable;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;
import static ffzim.helpers.UniqueCard.ApplyUniqueCard;

public class SpeedPlus extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "SpeedPlus",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    private final CardRarity thisRarity = AbstractCard.CardRarity.RARE;
    private static final int permDEXGAIN = 1;
    public SpeedPlus() {
        super(cardInfo, false);
        setMagic(permDEXGAIN);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
        tags.add(CustomTags.FFUNIQUE);
        this.tags.add(CardTags.HEALING);
        ApplyUniqueCard(this,cardInfo.baseId);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        PlayerSaveable playerSaveable = PlayerSaveable.getInstance(p);
        playerSaveable.increaseSpeedPlus(1);


        addToBot(new ApplyPowerAction(p, p, new DexterityPower( p, permDEXGAIN), permDEXGAIN));
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_SLOT_GAIN"));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SpeedPlus();
    }
}
