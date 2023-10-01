package ffzim.cards.Basics;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class DefendRevolve extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "StrikeFreelancer",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private boolean isInPreview;
    public DefendRevolve() {this(true, 1);}
    public DefendRevolve(boolean makePreview, int baseSecondMagicNumber) {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
        selfRetain = true;
        PurgeField.purge.set(this, true);
        setMagic((magicNumber >= 1) ? magicNumber : 0);
        setSecondMagic(1 - magicNumber);
        this.baseSecondMagicNumber = baseSecondMagicNumber;
        if (makePreview) {
            cardsToPreview = new AttackRevolve(false, Math.max((this.baseSecondMagicNumber) - 1, 0));
        }
        this.isInPreview = makePreview;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeDamage(UPG_BLOCK);
            if (isInPreview ) {
                this.cardsToPreview.upgrade();
            }
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (magicNumber >= 1) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        final int originalBaseMagicNumber = baseMagicNumber;
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard cardToAdd = new AttackRevolve(true,baseSecondMagicNumber-1);
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
        return new DefendRevolve();
    }
}