package ffzim.util;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.powers.MagicPower;

import static ffzim.BasicMod.makeID;


public class SpellbuffingMagic {

    private static int magicStackAmount;
    private static int magicStackAmount2;

    public static void spellApplyPowers3(AbstractCard card) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null) {
                magicStackAmount = ((MagicPower) magic).magicStacks;
                ((MagicPower) magic).magicStacks = magic.amount;
            }
        }
    }

    public static void spellApplyPowers4(AbstractCard card) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null)
                ((MagicPower) magic).magicStacks = magicStackAmount;
        }
    }

    public static void spellCalculateCardDamage5(AbstractCard card, AbstractMonster mo) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null) {
                magicStackAmount2 = ((MagicPower) magic).magicStacks;
                ((MagicPower) magic).magicStacks = magic.amount;
            }
        }
    }

    public static void spellCalculateCardDamage6(AbstractCard card, AbstractMonster mo) {
        if (card.hasTag(CustomTags.FFSPELL) || card.hasTag(CustomTags.FFENSPELL)) {
            AbstractPower magic = AbstractDungeon.player.getPower(makeID("MagicPower"));
            if (magic != null)
                ((MagicPower) magic).magicStacks = magicStackAmount2;
        }
    }
}
