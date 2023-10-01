package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import static ffzim.BasicMod.makeID;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import ffzim.util.CustomTags;

public class SwordMagic extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SwordMagic",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BASE_DAMAGE = 7;
    private static final int UPGRADED_DAMAGE = 3;

    public SwordMagic() {
        super(cardInfo, false);
        this.exhaust = true;
        this.baseDamage = BASE_DAMAGE;
        this.damage = this.baseDamage;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADED_DAMAGE);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : p.hand.group) {
            if ((card.type == CardType.ATTACK)
                    && card.canUse(p, null)
                    && card != this
                    && card.hasTag(CustomTags.FFSPELL)) {
                validCards.addToTop(card);
            }
        }

        if (!validCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose Spell:", false, false, true, false);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        selectedCard.tags.remove(CustomTags.FFSPELL);
                        selectedCard.freeToPlayOnce = true;
                        selectedCard.damage /= 2;
                        selectedCard.current_y = -200.0F * Settings.scale;
                        selectedCard.target_x = Settings.WIDTH / 2.0F;
                        selectedCard.target_y = Settings.HEIGHT / 2.0F;
                        selectedCard.targetAngle = 0.0F;
                        selectedCard.lighten(false);
                        selectedCard.drawScale = 0.12F;
                        selectedCard.targetDrawScale = 0.75F;
                        selectedCard.calculateCardDamage(m);
                        if (selectedCard.damageTypeForTurn == DamageInfo.DamageType.THORNS) {
                            selectedCard.damageTypeForTurn = DamageInfo.DamageType.NORMAL; // Change damage type only if THORNS
                        }
                        selectedCard.applyPowers();
                        AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(selectedCard, m));
                    }
                    this.isDone = true;
                }
            });
        }
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwordMagic();
    }
}
