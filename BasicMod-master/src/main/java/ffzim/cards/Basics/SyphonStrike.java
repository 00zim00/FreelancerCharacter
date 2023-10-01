package ffzim.cards.Basics;

import com.evacipated.cardcrawl.mod.stslib.actions.common.RefundAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class SyphonStrike extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SyphonStrike",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 6;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int ENERGY_GAIN = 1;

    public SyphonStrike() {
        super(cardInfo, false);
        setDamage(DAMAGE, UPGRADE_DAMAGE);
        setMagic(ENERGY_GAIN);
        this.tags.add(CardTags.STRIKE);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int originalHealth = m.currentHealth; // Store the current health before dealing damage

        // Add the damage action
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        final AbstractCard card = this; // Capture the card instance in a final variable

        // Use a lambda function as a callback to execute after the actions are resolved
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                // Check if the monster's health decreased after the action phase
                if (m.currentHealth < originalHealth) {
                    addToBot(new RefundAction(card, ENERGY_GAIN)); // Use the captured card instance
                }
                this.isDone = true; // Mark the action as done
            }
        });
    }
    @Override
    public AbstractCard makeCopy() {
        return new SyphonStrike();
    }
}
