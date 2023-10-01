package ffzim.cards.Generic;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

;

public class Undraw extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Undraw",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (magicNumber >= 4) {
            //super.setCostForTurn(-2);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void atTurnStart() {
        setMagic(0);
        setSecondMagic(4);
        cardsToPreview = new Draw(false,Math.max((this.baseSecondMagicNumber) - 1, 0));
        cardsToPreview.initializeDescription();
    }

    public Undraw() {
        this(true, 4);
    }

    public Undraw(boolean makePreview, int baseSecondMagicNumber) {
        super(cardInfo, true);
        selfRetain = true;
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFSPELL);
        setMagic((magicNumber >= 1) ? magicNumber : 0);
        setSecondMagic(4 - magicNumber);
        this.baseSecondMagicNumber = baseSecondMagicNumber;
        if (makePreview) {
            cardsToPreview = new Draw(false, Math.max((this.baseSecondMagicNumber) - 1, 0));
            cardsToPreview.initializeDescription();
        }
        super.initializeDescription();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (cardsToPreview instanceof Draw) {
                (cardsToPreview).upgrade();
                (cardsToPreview).initializeDescription();
            }
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int OnDeck = 1;
        if (upgraded){OnDeck = 2;}
        if (!AbstractDungeon.player.hand.isEmpty()) {
            addToBot(new PutOnDeckAction(p, p, OnDeck, false));
        }
        final int originalBaseMagicNumber = baseMagicNumber;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard cardToAdd = new Draw(true,baseSecondMagicNumber-1);
                if (upgraded) {
                    cardToAdd.upgrade();
                }
                cardToAdd.baseMagicNumber = originalBaseMagicNumber + 1;
                cardToAdd.magicNumber = cardToAdd.baseMagicNumber;
                cardToAdd.initializeDescription();
                AbstractDungeon.player.hand.addToBottom(cardToAdd);
                isDone = true;
            }
        });

}

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Undraw();
    }
}