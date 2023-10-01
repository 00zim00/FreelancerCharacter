package ffzim.actions.keywords;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import static ffzim.BasicMod.modID;

public class LevelUpAction extends AbstractGameAction {

    private final boolean isLevelUp;
    private final String UpCardID;
    private final String DownCardID;
    private final boolean toDraw;
    private final boolean toHand;
    private final boolean toDiscard;
    private final boolean upgraded;
    private final boolean purged;

    private boolean hasPlayedSound = false;

    public LevelUpAction(AbstractCreature target, AbstractCreature source,
                         boolean isLevelUp,
                         AbstractCard downCard,
                         AbstractCard upCard,
                         boolean toDraw,
                         boolean toHand,
                         boolean toDiscard,
                         boolean upgraded,
                         boolean purged
    ) {
        setValues(target, source, this.amount);
        this.isLevelUp = isLevelUp;
        this.DownCardID = downCard.cardID;
        this.UpCardID = upCard.cardID;
        this.toDraw = toDraw;
        this.toHand = toHand;
        this.toDiscard = toDiscard;
        this.upgraded = upgraded;
        this.purged = purged;
        this.duration = 0.25F;
        System.out.println("DownCardID1: " + UpCardID);
    }


    public void update() {
        System.out.println("purgedCheck: " + purged);
        if (!purged) {
            if (!isLevelUp) {
                AbstractCard cardToAdd = CardLibrary.getCard(DownCardID).makeStatEquivalentCopy();
                if (upgraded) {
                    cardToAdd.upgrade();
                }
                if (toDraw) {
                    addToBot(new MakeTempCardInDrawPileAction(cardToAdd, 1, false, true));
                } else if (toHand) {
                    addToBot(new MakeTempCardInHandAction(cardToAdd, 1, false));
                } else {
                    addToBot(new MakeTempCardInDiscardAction(cardToAdd, 1));
                }
            } else {
                AbstractCard cardToAdd = CardLibrary.getCard(UpCardID).makeStatEquivalentCopy();
                System.out.println("UpCardID2: " + UpCardID);
                if (upgraded) {
                    cardToAdd.upgrade();
                }
                if (toDraw) {
                    addToBot(new MakeTempCardInDrawPileAction(cardToAdd, 1, false, true));
                } else if (toHand) {
                    addToBot(new MakeTempCardInHandAction(cardToAdd, 1, false));
                } else {
                    addToBot(new MakeTempCardInDiscardAction(cardToAdd, 1));
                }
            }
            if (!hasPlayedSound) {
                addToBot(new SFXAction(modID + ":SpellLevelUp", 1.0f, true));
                hasPlayedSound = true;
            }        }
        isDone = true;
    }
}


