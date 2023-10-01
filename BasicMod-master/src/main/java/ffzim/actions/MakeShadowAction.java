package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static ffzim.util.CustomTags.FFSHADOW;

public class MakeShadowAction extends AbstractGameAction {
        private static final float DUR = 0.25F;

        AbstractCard cardToShadow;
        AbstractCard baseCard;

    public MakeShadowAction(AbstractCard baseCard, AbstractCard newCard) {
            this.baseCard = baseCard;
            this.cardToShadow = newCard;
            this.actionType = ActionType.BLOCK;
            this.duration = 0.25F;
        }

    public void update() {
        cardToShadow.purgeOnUse = true;
        cardToShadow.isEthereal = true;
        cardToShadow.tags.add(FFSHADOW);
        cardToShadow.name = "[#c639de]" + cardToShadow.name;
        cardToShadow.keywords.add("Shadow");
        cardToShadow.rawDescription += " NL Shadow.";

        cardToShadow.initializeDescription();

        tickDuration();
    }
}


