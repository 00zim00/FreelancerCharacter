package ffzim.util;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.powers.MagicPower;

import static ffzim.BasicMod.makeID;


public class SpellUnbuffingStrength {

    private static int originalStackAmount;
    private static int originalStackAmount2;

    private static int magicStackAmount;
    private static int magicStackAmount2;

    private static int enSpellOriginalStackAmount;
    private static int enSpellOriginalStackAmount2;

    public static void spellApplyPowers(AbstractCard card) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower strength = AbstractDungeon.player.getPower("Strength");
            originalStackAmount = (strength != null) ? strength.amount : 0;
            if (strength != null && !card.hasTag(CustomTags.FFENSPELL)) {
                strength.amount = 0;
            }
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null) {
                magicStackAmount = ((MagicPower) magic).magicStacks;
                ((MagicPower) magic).magicStacks = magic.amount;
            }
            AbstractPower enSpell = AbstractDungeon.player.getPower(makeID("EnSpellInvisPower"));
            enSpellOriginalStackAmount = (enSpell != null) ? enSpell.amount : 0;
            if (enSpell != null) {
                enSpell.amount = 0;
            }
        }
    }

    public static void spellApplyPowers2(AbstractCard card) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower strength = AbstractDungeon.player.getPower("Strength");
            if (strength != null && !card.hasTag(CustomTags.FFENSPELL)) {
                strength.amount = originalStackAmount;
            }
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null) {
                ((MagicPower) magic).magicStacks = magicStackAmount;
            }
            AbstractPower enSpell = AbstractDungeon.player.getPower(makeID("EnSpellInvisPower"));
            if (enSpell != null) {
                enSpell.amount = enSpellOriginalStackAmount;
            }
        }
    }

    public static void spellCalculateCardDamage(AbstractCard card, AbstractMonster mo) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower strength = AbstractDungeon.player.getPower("Strength");
            originalStackAmount2 = (strength != null) ? strength.amount : 0;
            if (strength != null && !card.hasTag(CustomTags.FFENSPELL)) {
                strength.amount = 0;
            }
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null) {
                magicStackAmount2 = ((MagicPower) magic).magicStacks;
                ((MagicPower) magic).magicStacks = magic.amount;
            }
            AbstractPower enSpell = AbstractDungeon.player.getPower(makeID("EnSpellInvisPower"));
            enSpellOriginalStackAmount2 = (enSpell != null) ? enSpell.amount : 0;
            if (enSpell != null) {
                enSpell.amount = 0;
            }
        }
    }

    public static void spellCalculateCardDamage2(AbstractCard card, AbstractMonster mo) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower strength = AbstractDungeon.player.getPower("Strength");
            if (strength != null && !card.hasTag(CustomTags.FFENSPELL)) {
                strength.amount = originalStackAmount2; // Reset base damage to original value
            }
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null) {
                ((MagicPower) magic).magicStacks = magicStackAmount2;
            }
            AbstractPower enSpell = AbstractDungeon.player.getPower(makeID("EnSpellInvisPower"));
            if (enSpell != null) {
                enSpell.amount = enSpellOriginalStackAmount2; // Reset base damage to original value
            }
        }
    }
}
