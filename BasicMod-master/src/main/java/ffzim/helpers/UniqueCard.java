package ffzim.helpers;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.BasicMod;
import ffzim.cards.BaseCard;
import ffzim.cards.variables.UniqueSaveable;
import ffzim.util.CustomTags;
import basemod.abstracts.CustomCard;
public class UniqueCard {

    public static void ApplyUniqueCard(AbstractCard card,String baseId) {
        if (AbstractDungeon.player != null) {
            card.tags.add(CustomTags.FFUNIQUE);
            UniqueSaveable uniqueSaveable = UniqueSaveable.getInstance(AbstractDungeon.player);

            if (UniqueSaveable.Hilt == 0 && card.misc == 0) {
                card.misc = 1;
                card.magicNumber = card.misc;
                //card.rarity = rarity;
                uniqueSaveable.setUniqueIsSeen(AbstractDungeon.player, baseId, 1);
            }


//            if (UniqueSaveable.Hilt == 1 && card.misc == 0) {
//                card.rarity = AbstractCard.CardRarity.SPECIAL;
//                //ReflectionHacks.setPrivate(card, AbstractCard.class, "displayRarity", rarity);
//            } else {
//                card.misc = 1;
//                card.magicNumber = card.misc;
//                card.rarity = rarity;
//                uniqueSaveable.setUniqueIsSeen(AbstractDungeon.player, baseId, 1);
//            }
        }
    }
}
