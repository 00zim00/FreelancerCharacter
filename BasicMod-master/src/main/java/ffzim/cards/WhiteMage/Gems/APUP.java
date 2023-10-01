package ffzim.cards.WhiteMage.Gems;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.keywords.APLevelUpAction;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class APUP extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "APUP",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            CardColor.COLORLESS); 


    public static final String ID = makeID(cardInfo.baseId);
    private boolean isInPreview;
    public int baseCurrentAP;
    public static final int baselevel1AP = 8;
    private static final int baselevel2AP = 20;

    public APUP() {this(true);}
    public APUP(boolean makePreview) {
        super(cardInfo, false);
//        this.misc = 0;
//        secondMagicNumber = this.timesUpgraded;
//        this.baseMagicNumber = this.misc;
//        this.magicNumber = this.baseMagicNumber;
//        this.baseDamage = this.misc;
//        APLevelUp(this);
        this.isInPreview = makePreview;
        PersistFields.setBaseValue(this,10);
        super.initializeDescription();
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println("timesUpgraded2: " + timesUpgraded);
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : p.hand.group) {
            if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
                validCards.addToTop(card);
            }
        }
        for (AbstractCard card : p.drawPile.group) {
            if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
                validCards.addToTop(card);
            }
        }
        for (AbstractCard card : p.discardPile.group) {
            if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
                validCards.addToTop(card);
            }
        }

        for (AbstractCard card : p.masterDeck.group) {
            if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
                validCards.addToTop(card);
            }
        }

        for (AbstractCard card : p.exhaustPile.group) {
            if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
                validCards.addToTop(card);
            }
        }
        if (!validCards.isEmpty()) {
            for (int i = 0; i < validCards.size(); i++) {
                AbstractCard card = validCards.group.get(i);
                ((BaseCard) card).addAP(card,25);
                AbstractDungeon.actionManager.addToBottom(new APLevelUpAction(card));
                card.applyPowers();
            }
        }
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new APUP();
    }
}