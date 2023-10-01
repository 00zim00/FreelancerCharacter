package ffzim.cards.Generic.ItemCards.uncommon;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import ffzim.actions.CustomWaitAction;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class MagicTag extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "MagicTag",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public MagicTag() {
        super(cardInfo, false);
        FleetingField.fleeting.set(this, true);
        setSelfRetain(true);
        tags.add(CustomTags.FFITEM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCards = new CardGroup(com.megacrit.cardcrawl.cards.CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : p.hand.group) {
            if (!card.cardID.equals(CurseOfTheBell.ID) && card.rarity == CardRarity.CURSE) {
                validCards.addToTop(card);
            }
        }
        if (!validCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "Remove Curse:", false, false, false, false);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);

                        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                            if (c.uuid.equals(selectedCard.uuid)) {
                                AbstractDungeon.player.masterDeck.removeCard(selectedCard);
                            }
                        }
                        AbstractDungeon.actionManager.addToTop(new CustomWaitAction(0.2f));
                        AbstractDungeon.effectsQueue.add(new PurgeCardEffect(selectedCard));
                        AbstractDungeon.actionManager.addToTop(new CustomWaitAction(0.2f));
                        AbstractDungeon.player.hand.removeCard(selectedCard);
                    }
                    this.isDone = true;
                }
            });
        }
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new MagicTag();
    }
}
