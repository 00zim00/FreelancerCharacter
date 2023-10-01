package ffzim.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.LiftOption;
import ffzim.cards.Generic.ItemCards.Excalibur;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;

public class ChocoboLicense extends BaseRelic
{
    public static final String ID = makeID("ChocoboLicense");

    private static int TRANCE_COUNTER = 0;

    private boolean jobBlackMage;

    public ChocoboLicense() {
        super(ID, "ChocoboLicense", RelicTier.SPECIAL, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    public void onObtainCard(AbstractCard card) {
        if (card.cardID.equals(makeID("BlackMage"))) {
            imageName = "BlackMage";
            jobBlackMage = true;
        }
    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new LiftOption((this.counter < 3)));
    }

    public void onEnterRoom(AbstractRoom room) {
        if (AbstractDungeon.player.masterDeck.contains(new Excalibur()) && room instanceof com.megacrit.cardcrawl.rooms.RestRoom && AbstractDungeon.actNum == 4 && CardCrawlGame.playtime <= 720) {

        }else if (room instanceof com.megacrit.cardcrawl.rooms.RestRoom && AbstractDungeon.actNum == 4 && CardCrawlGame.playtime <= 720) {

        }
    }


    public BaseRelic makeCopy() {
        return new ChocoboLicense();
    }
}
