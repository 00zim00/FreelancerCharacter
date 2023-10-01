package ffzim.cards.Generic.ItemCards.common;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static com.megacrit.cardcrawl.potions.AbstractPotion.playPotionSound;
import static ffzim.BasicMod.makeID;

public class Ether extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Ether",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int ENERGY = 2;

    public Ether() {
        super(cardInfo, true);
        setMagic(ENERGY);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
        tags.add(CustomTags.FFPOTION);
        tags.add(CustomTags.FFCOMMON);
        setDisplayRarity(CardRarity.COMMON);
        //getCardDescriptors()
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
        int energyGain = Math.min(AbstractDungeon.player.energy.energyMaster, magicNumber);
        int energyDifference = (AbstractDungeon.player.energy.energyMaster - EnergyPanel.totalCount);
        energyGain = Math.min(energyGain, energyDifference);
        if (energyGain < 0) {energyGain = 0;}
        addToBot(new GainEnergyAction(energyGain));
        playPotionSound();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Ether();
    }
}
