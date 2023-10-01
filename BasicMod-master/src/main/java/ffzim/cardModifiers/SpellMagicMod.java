package ffzim.cardModifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.powers.MagicPower;
import ffzim.util.CustomTags;

import static basemod.helpers.CardModifierManager.addModifier;
import static ffzim.BasicMod.makeID;

public class SpellMagicMod extends AbstractCardModifier {

    private int StartingDamage;
    private static final String MagicMod_ID = makeID("SpellMagicMod");

    public SpellMagicMod() {
        super();
    }


    // NOTE. maybe also check and chane exhasuted pile to. since they may not count as draw/
    // hm... what about cards that are copies. they are never drawn to.

    @Override
    public void onInitialApplication(AbstractCard card) {
        StartingDamage = card.baseDamage;
        card.baseDamage = Math.round(modifyDamage(card.baseDamage, card.damageTypeForTurn));
        card.applyPowers();
    }

    public float modifyDamage(float damage, DamageInfo.DamageType type) {
        AbstractPower magic = AbstractDungeon.player.getPower(MagicPower.POWER_ID);
        if (magic != null) {
            return damage + magic.amount;
        }
        return damage;
    }

    public void stackPower() {
        // ... existing code ...

        // Update card damage if there is a card in play
        if (AbstractDungeon.player != null) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = Math.round(modifyDamage(c.baseDamage, c.damageTypeForTurn));
                    c.applyPowers();
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = Math.round(modifyDamage(c.baseDamage, c.damageTypeForTurn));
                    c.applyPowers();
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = Math.round(modifyDamage(c.baseDamage, c.damageTypeForTurn));
                    c.applyPowers();
                }
            }
        }
    }

    public void reducePower() {
        // ... existing code ...

        // Update card damage if there is a card in play
        if (AbstractDungeon.player != null) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = Math.round(modifyDamage(c.baseDamage, c.damageTypeForTurn));
                    c.applyPowers();
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = Math.round(modifyDamage(c.baseDamage, c.damageTypeForTurn));
                    c.applyPowers();
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = Math.round(modifyDamage(c.baseDamage, c.damageTypeForTurn));
                    c.applyPowers();
                }
            }
        }
    }

    public void onRemove() {
        if (AbstractDungeon.player != null) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = StartingDamage;
                    c.applyPowers();
                }
            }
            for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = StartingDamage;
                    c.applyPowers();
                }
            }
            for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
                if (c.hasTag(CustomTags.FFSPELL)) {
                    c.damage = StartingDamage;
                    c.applyPowers();
                }
            }
        }
    }


    @Override
    public AbstractCardModifier makeCopy() {
            return new SpellMagicMod();
    }
}