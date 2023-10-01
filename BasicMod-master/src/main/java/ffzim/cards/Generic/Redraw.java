package ffzim.cards.Generic;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import ffzim.actions.PutAllOnDeckAction;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;


public class Redraw extends BaseCard {

    public static final String ID = makeID("Redraw");
    private AbstractCard cardToPutBack;
    private final static CardInfo cardInfo = new CardInfo(
            "Redraw",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public Redraw() {
        super(cardInfo, true);
    this.baseMagicNumber = this.magicNumber = 1;
    setExhaust(true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            setExhaust(false);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            this.magicNumber = p.hand.size();
        }else{
            this.magicNumber = p.hand.size() - 1;
        }
        for (AbstractCard card : p.hand.group) {
            if (card != this) { // Skip the currently played card
                AbstractDungeon.actionManager.addToBottom(new PutAllOnDeckAction(p));
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ShuffleAction(p.drawPile));

        // Draw cards equal to the magicNumber
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));

        AbstractDungeon.player.hand.refreshHandLayout();
        }

    @Override
    public AbstractCard makeCopy() {
        return new Redraw();
    }
}


