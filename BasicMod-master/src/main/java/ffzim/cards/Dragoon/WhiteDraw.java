package ffzim.cards.Dragoon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class WhiteDraw extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "WhiteDraw",
            0,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DRAW = 1;
    private static final int UPG_DRAW = 1;

    public WhiteDraw() {
        super(cardInfo, true);
        tags.add(CustomTags.FFSPELL);
        setMagic(DRAW,UPG_DRAW);
    }

    private boolean energyGained = false;

    private static int CostFinal  = -4;

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            addToTop(new EmptyDeckShuffleAction());
        }
        energyGained = false;
        CostFinal  = -4;
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                int highestCost = 0;
                for (AbstractCard card : DrawCardAction.drawnCards) {
                    highestCost = Math.max(highestCost, card.cost);
                    if (highestCost > CostFinal){
                        CostFinal = highestCost;
                    }
                    addToTop(new AbstractGameAction() {
                        @Override
                        public void update() {
                            if (CostFinal > 0 && !energyGained) {
                                addToTop(new GainEnergyAction(CostFinal));
                                energyGained = true;
                            }

                            this.isDone = true;
                        }
                    });
                }
                isDone = true;
            }
        }));

    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new WhiteDraw();
    }
}