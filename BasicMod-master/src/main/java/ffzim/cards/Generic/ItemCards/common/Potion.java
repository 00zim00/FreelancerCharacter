package ffzim.cards.Generic.ItemCards.common;

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

public class Potion extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Potion",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);

//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        boolean hasSozuRelic = player.hasRelic("Sozu");
//        return !hasSozuRelic;
//    }
    private static final int TEMPHP = 6;

    @Override
    public boolean canUpgrade() {
        return false;
    }

    public Potion() {
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
        playPotionSound();
        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Potion();
    }
}
