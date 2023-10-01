package ffzim.campfire;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.vfx.campfire.CampfireLiftEffect;
import ffzim.cards.Generic.ItemCards.common.GysahlGreen;

import static ffzim.BasicMod.makeID;
import static ffzim.helpers.ChestItemRewards.getChestItemRewards;

public class ChocoboOption extends AbstractCampfireOption {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("Lift Option");

    public static final String[] TEXT = uiStrings.TEXT;

    public ChocoboOption(boolean active) {
        this.label = TEXT[0];
        this.usable = active;
        this.description = active ? TEXT[1] : TEXT[2];
        this.img = ImageMaster.CAMPFIRE_TRAIN_BUTTON;
    }

    public void useOption() {
        if (this.usable)
            AbstractDungeon.effectList.add(new CampfireLiftEffect());

        int rngRoll = (int) (Math.random() * 100) + 1;

        int actNum = AbstractDungeon.actNum;
        int ascensionLevel = AbstractDungeon.ascensionLevel;
        int maxReward;
        if (actNum == 2) {
            rngRoll += 25;
        } else if (actNum == 3) {
            rngRoll += 50;
            if (ascensionLevel >= 12) {
                rngRoll += 25;
            }
        } else if (actNum == 4) {
            rngRoll += 75;
            if (ascensionLevel >= 12) {
                rngRoll += 37;
            }
        }

        if (AbstractDungeon.player.hasRelic(makeID("ChocoboLicense")) && AbstractDungeon.player.masterDeck.contains(new GysahlGreen())) {
            (AbstractDungeon.player.getRelic(makeID("ChocoboLicense"))).counter++;

            AbstractRelic rel = AbstractDungeon.player.getRelic(makeID("ChocoboLicense"));
            rngRoll = rngRoll + (rel.counter * 15);

            int loop;
            CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
                if (card.cardID.equals(makeID("GysahlGreen"))) {
                    validCards.addToTop(card);
                }
            }
            if (!validCards.isEmpty()) {
                (AbstractDungeon.player.getRelic(makeID("ChocoboLicense"))).counter+= validCards.size();
                for (AbstractCard cardToRemove : validCards.group) {
                    AbstractDungeon.player.masterDeck.removeCard(cardToRemove);
                }
            }
        }

        if (actNum == 1 || actNum == 2 ) {
            rngRoll = Math.min(rngRoll,100);
        }


        String chest = null;
        if (rngRoll <= 50) {
            chest = "SmallChest";
        } else if (rngRoll <= 83) {
            chest = "MediumChest";
        } else if (rngRoll <= 100) {
            chest = "LargeChest";
        } else {
            chest = "BossChest";
        }



        getChestItemRewards(chest);



    }
}
