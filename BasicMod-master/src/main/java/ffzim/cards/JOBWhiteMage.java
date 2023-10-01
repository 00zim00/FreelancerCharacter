package ffzim.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.Basics.CLDefend;
import ffzim.cards.Basics.CLStrike;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import java.util.ArrayList;
import java.util.Iterator;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class JOBWhiteMage extends BaseCard implements OnObtainCard {
    private final static CardInfo cardInfo = new CardInfo(
            "JOBWhiteMage",
            0,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    public JOBWhiteMage() {
        super(cardInfo, true);
        setInnate(true);
        MultiCardPreview.add(this, new CLStrike(), new CLDefend());
        this.tags.add(CardTags.HEALING);

    }

    // PostCampfire boolean
    // PostCampfire boolean
    // PostCampfire boolean

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        onObtainCard();
    }

    @Override
    public void onObtainCard() {

        //addToBot(new SFXAction(modID + ":Confirm"));
        CardCrawlGame.sound.playV(modID + ":Confirm", 1.0f);
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;

        int numStrike = 0;
        for (AbstractCard card : masterDeck) {
            if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)) {
                numStrike++;
            }
        }
        int numDefend = 0;
        for (AbstractCard card : masterDeck) {
            if (card.tags.contains(CardTags.STARTER_DEFEND)) {
                numDefend++;
            }
        }
        // Remove cards with STARTER_STRIKE tag from the masterDeck using an iterator
        Iterator<AbstractCard> iterator = masterDeck.iterator();
        while (iterator.hasNext()) {
            AbstractCard card = iterator.next();
            if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)) {
                iterator.remove();
            }
            if (card.tags.contains(CardTags.STARTER_DEFEND)) {
                iterator.remove();
            }
        }

        // Remove cards with STARTER_STRIKE tag from the masterDeck using an iterator
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (int i = 0; i < numDefend; i++) {
            group.addToBottom(new CLDefend());
        }
        for (int i = 0; i < numStrike; i++) {
            group.addToBottom(new CLStrike());
        }

        AbstractDungeon.gridSelectScreen.openConfirmationGrid(group, "New Cards.");

        AbstractDungeon.actionManager.addToBottom(new WaitAction(3.0F));

        for (AbstractCard card : masterDeck) {
            if (card instanceof JOBWhiteMage) {
//                AbstractDungeon.effectsQueue.add(new com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect(new JOBWhiteMage()));
                AbstractDungeon.player.masterDeck.removeCard(card);
                break; // Stop the loop once the card is removed
            }
        }
    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new JOBWhiteMage();
    }
}

