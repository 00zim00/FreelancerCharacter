package ffzim.cards.Chocobo.attacker;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cardModifiers.ShadowMod;
import ffzim.cards.BaseCard;
import ffzim.cards.Chocobo.ChocoAttacker;
import ffzim.cards.DarkKnight.ShadowWall;
import ffzim.stances.LimitGaugePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static basemod.helpers.CardModifierManager.addModifier;
import static ffzim.BasicMod.makeID;
import static ffzim.util.CustomTags.FFSHADOW;

public class ChocoUnleash extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoUnleash",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            CardColor.COLORLESS); 



    public static final String ID = makeID(cardInfo.baseId);


    private static final int REDUCE_ENERGY = 0;
    private static final int UPG_BUFF = 2;

    public ChocoUnleash() {
        super(cardInfo, true);
        setMagic(getChocoAttackLevel()/2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : p.hand.group) {
            if ((card.type == CardType.ATTACK && !card.hasTag(FFSHADOW))
                    && card.canUse(p, null)
                    && card != this
                    && !card.cardID.equals(new ChocoAttacker().cardID)
            ) {
                validCards.addToTop(card);
            }
            if (!validCards.isEmpty()) {
                AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose 1 card to copy", false, false, true, false);
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                            AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                            setMagic(getChocoAttackLevel()/2);
                            selectedCard = selectedCard.makeStatEquivalentCopy();
                            addModifier(selectedCard, new ShadowMod());
                            selectedCard.costForTurn = Math.max(selectedCard.cost - (getChocoAttackLevel()/2),0);
                            AbstractDungeon.player.hand.addToTop(selectedCard);
                            selectedCard.initializeDescription();
                            selectedCard.applyPowers();
                            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(selectedCard, 1, false));
                        }
                        this.isDone = true;
                    }
                });
            }
        }
    }

    public void applyPowers() {
        setMagic(getChocoAttackLevel()/2);
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoUnleash();
    }
}