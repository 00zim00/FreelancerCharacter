package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cardModifiers.ShadowMod;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static basemod.helpers.CardModifierManager.addModifier;
import static ffzim.BasicMod.makeID;

public class LivingShadow extends BaseCard {


    private final static CardInfo cardInfo = new CardInfo(
            "LivingShadow",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public LivingShadow() {
        super(cardInfo, false);
        setMagic(1); // Number of copies to create
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard card : p.hand.group) {
            if (card != this) { // Skip the currently played card
                AbstractCard copy = card.makeSameInstanceOf();

                addModifier(copy, new ShadowMod());

//                copy.purgeOnUse = true;
//                copy.tags.add(CustomTags.FFCOPY);
//                copy.name = "[#c639de]" + copy.name;
//                copy.keywords.add(modID + ":Shadow");
//                copy.keywords.add(modID + "Shadow");
//                copy.rawDescription += " NL Shadow.";
//                setEthereal(true);

//                copy.transparency = 0.75f;
//                copy.targetTransparency = 0.75f;
//                card.cardColor = Color.PURPLE.cpy();
                copy.initializeDescription();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(copy));
                 // Add the copy to the hand

            }
        }
        exhaust = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new LivingShadow();
    }
}
