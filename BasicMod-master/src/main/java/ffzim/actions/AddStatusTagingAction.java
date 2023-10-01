package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.helpers.ADD_STATUS_CARD_IDS;
import ffzim.powers.TroublePower;
import ffzim.util.CustomTags;

import java.util.Arrays;
import java.util.List;

public class AddStatusTagingAction extends AbstractGameAction {
    private static final float DUR = 0.25F;

    private static boolean HAS_VUL = false;
    private static boolean HAS_WEAK = false;
    private static boolean HAS_POISON = false;
    AbstractCard card;
    public AddStatusTagingAction(AbstractCreature target, AbstractCreature source, AbstractCard card) {
        setValues(target, source);
        this.card = card;

        this.duration = 0.25F;
    }

    public void update() {
//        AbstractPlayer p = AbstractDungeon.player;
//            if (card.hasTag(CustomTags.FFVULNERABLE)) {
//                HAS_VUL = true;
//            }
//            if (card.hasTag(CustomTags.FFWEAK)) {
//                HAS_WEAK = true;
//            }
//            if (card.hasTag(CustomTags.FFPOISON)) {
//                HAS_POISON = true;
//            }
//        addToBot(new ApplyPowerAction(p, p, new TroublePower(p, this.magicNumber), this.magicNumber));
//
//
//
        this.isDone = true;
    }
}

