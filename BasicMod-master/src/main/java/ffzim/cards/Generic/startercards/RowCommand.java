package ffzim.cards.Generic.startercards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.BackRowPower;
import ffzim.powers.FrontRowPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class RowCommand extends BaseCard implements StartupCard, BranchingUpgradesCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "RowCommand",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private boolean chosen = false;
    public RowCommand() {this(true);}

    public RowCommand(boolean makePreview) {
        super(cardInfo, true);
        //setLevelUp(true);
        //PurgeField.purge.set(this, true);
        //MultiCardPreview.add(this, new BackRow(false), new FrontRow(false), new Swap(false));
        //horizontal.set(this, true);
    }

//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeName();
//            MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
//            if (isBranchUpgrade()) {
//                branchUpgrade();
//            } else {
//                baseUpgrade();
//            }
//        }
//    }
//
//    public void baseUpgrade() {
//        this.name = "BackRow";
//    }
//
//    public void branchUpgrade() {
//        this.name = "FrontRow";
//    }

    @Override
    public boolean atBattleStartPreDraw() {
        return upgraded;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        validCards.addToTop(new BackRow());
        validCards.addToTop(new FrontRow());
        if (!validCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose Row:", false, false, false, false);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

                        if (selectedCard.cardID.equals(new BackRow().cardID)) {
                            addToBot(new ApplyPowerAction(p, p, new BackRowPower(p, 0, upgraded), 0));
                        }

                        if (selectedCard.cardID.equals(new FrontRow().cardID)) {
                            addToBot(new ApplyPowerAction(p, p, new FrontRowPower(p, 0, upgraded), 0));
                        }
                    }
                    this.isDone = true;
                }
            });
        }
    }
//            AbstractCard cardToAdd = new Swap();
//            if (upgraded) {cardToAdd.upgrade();}
//            addToBot(new MakeTempCardInHandAction(cardToAdd, 1));
//            if (upgraded){
//                p.drawPile.removeCard(this);
//            }
//    }



    @Override
    public AbstractCard makeCopy() { //Optional
        return new RowCommand();
    }
}
