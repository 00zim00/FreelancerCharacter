package ffzim.cards.Generic.ItemCards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.variables.PlayerSaveable;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class LuckPlus extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "LuckPlus",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int permLUCKGAIN = 25;
    public LuckPlus() {
        super(cardInfo, false);
        setMagic(permLUCKGAIN);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        PlayerSaveable playerSaveable = PlayerSaveable.getInstance(p);
        playerSaveable.increaseLuckPlus(permLUCKGAIN);
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ORB_SLOT_GAIN"));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new LuckPlus();
    }
}
