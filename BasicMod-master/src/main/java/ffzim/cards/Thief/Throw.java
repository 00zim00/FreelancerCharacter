package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.Generic.ItemCards.common.Pinwheel;
import ffzim.cards.Generic.ItemCards.rare.WingEdge;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Throw extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Throw",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);
    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE   = 2;
    private static final int DAMAGE_MULTI = 3;
    private static final int UPG_DAMAGE_MULTI  = 1;
    public Throw() {
        super(cardInfo, true);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(DAMAGE_MULTI,UPG_DAMAGE_MULTI);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCards = new CardGroup(com.megacrit.cardcrawl.cards.CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : p.hand.group) {
            if (card != this) {
                //if (!(card.rarity == CardRarity.SPECIAL) && !(card.rarity == CardRarity.CURSE) && card != this) {
                validCards.addToTop(card);
            }
        }
        if (!validCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose card to Exhaust:", false, false, false, false);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        if (selectedCard.rarity == CardRarity.COMMON){
                            if (selectedCard.cardID.equals(Pinwheel.ID)){
                                damage = damage + (magicNumber * 2);
                            }else{
                                damage = damage + magicNumber;
                            }
                        }else if (selectedCard.rarity == CardRarity.UNCOMMON){
                            if (selectedCard.cardID.equals(WingEdge.ID)){
                                damage = damage + ((magicNumber * 2)* 2);
                            }else{
                                damage = damage + (magicNumber * 2);
                            }
                        }else if (selectedCard.rarity == CardRarity.RARE){
                            if (selectedCard.cardID.equals(WingEdge.ID)){
                                damage = damage + ((magicNumber * 3)* 2);
                            }else{
                                damage = damage + (magicNumber * 3);
                            }
                        }
                        AbstractDungeon.actionManager.addToTop(new ExhaustSpecificCardAction(selectedCard, p.hand));
                        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                    }
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Throw();
    }
}
