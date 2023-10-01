package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class SwordMagicNew extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Sword Magic",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BASE_DAMAGE = 6;
    private static final int UPGRADED_DAMAGE = 3;

    private static final int SPELLCOST = 0;

    private static final int UPG_SPELLCOST = 1;

    private static final int UPG2_SPELLCOST = 1;
    public SwordMagicNew() {
        super(cardInfo, false);
        this.exhaust = true;
        this.baseDamage = BASE_DAMAGE;
        this.damage = this.baseDamage;
        //this.secondMagicNumber = Energy

    }
    // renderSmallEnergy();

    // Pick a spell, it plays the spell. Changes damatge to not thorns (so str can be used)
    // Play a spell card whos cost you can afford.
    //Upgraded, refund 1 energy.

    @Override
    public void use(AbstractPlayer player, AbstractMonster monster) {
        addToBot(new DamageAction(monster, new DamageInfo(player, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : player.hand.group) {
            if ((card.type == CardType.ATTACK || card.hasTag(CustomTags.FFSPELL))
                    && card.canUse(player, null)
                    && card != this) {
                validCards.addToTop(card);
            }
        }

        if (!validCards.isEmpty()) {
            AbstractDungeon.gridSelectScreen.open(validCards, 1, "Choose a card to play for 0 cost:", false, false, true, false);
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
                        selectedCard.freeToPlayOnce = true;
                        selectedCard.applyPowers();
                        selectedCard.current_y = -200.0F * Settings.scale;
                        selectedCard.target_x = Settings.WIDTH / 2.0F;
                        selectedCard.target_y = Settings.HEIGHT / 2.0F;
                        selectedCard.targetAngle = 0.0F;
                        selectedCard.lighten(false);
                        selectedCard.drawScale = 0.12F;
                        selectedCard.targetDrawScale = 0.75F;
                        selectedCard.damageTypeForTurn = DamageInfo.DamageType.NORMAL;
                        selectedCard.applyPowers();

                        AbstractMonster randomEnemy = AbstractDungeon.getRandomMonster();

                        if (randomEnemy != null) {
                            AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(selectedCard, randomEnemy));
                        }

                    }
                    this.isDone = true;
                }
            });
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SwordMagicNew();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeDamage(UPGRADED_DAMAGE);
        }
    }
}
