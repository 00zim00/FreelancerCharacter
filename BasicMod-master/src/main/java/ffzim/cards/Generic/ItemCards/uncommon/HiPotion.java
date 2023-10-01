package ffzim.cards.Generic.ItemCards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static com.megacrit.cardcrawl.potions.AbstractPotion.playPotionSound;
import static ffzim.BasicMod.makeID;

public class HiPotion extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "HiPotion",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int TEMPHP = 9;

    @Override
    public boolean canUpgrade() {
        return false;
    }
    public HiPotion() {
        super(cardInfo, false);
        setMagic(TEMPHP);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
        tags.add(CustomTags.FFPOTION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            r.onUsePotion();
        }
        boolean hasSacredBarkRelic = player.hasRelic("SacredBark");
        if (hasSacredBarkRelic) {
            magicNumber = magicNumber * 2;
            AbstractRelic SacredBarkRelic = player.getRelic("SacredBark");
            SacredBarkRelic.flash();
        }
        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
        playPotionSound();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HiPotion();
    }
}
