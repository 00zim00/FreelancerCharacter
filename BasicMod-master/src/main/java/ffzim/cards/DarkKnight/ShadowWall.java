package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;
import ffzim.cardModifiers.ShadowMod;

import static basemod.helpers.CardModifierManager.addModifier;
import static ffzim.BasicMod.makeID;
import static ffzim.util.CustomTags.FFSHADOW;

public class ShadowWall extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ShadowWall",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 4;

    public ShadowWall() { this(true);
    }
    public ShadowWall(boolean makePreview) {
        super(cardInfo, false);
        setBlock(BLOCK, UPG_BLOCK);
        tags.add(CustomTags.FFSPELL);
        if (!this.hasTag(FFSHADOW)) {
            if (makePreview) {
                cardsToPreview = new ShadowWall(false);
                cardsToPreview.name = "[#c639de]" + cardsToPreview.name;
            }
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (cardsToPreview != null && !this.hasTag(FFSHADOW)) {
                this.cardsToPreview.upgrade();
            }
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if (!this.hasTag(FFSHADOW)) {
            AbstractCard cardToAdd = new ShadowWall();
            if (upgraded) {cardToAdd.upgrade();}
            addModifier(cardToAdd, new ShadowMod());
            AbstractDungeon.player.hand.addToTop(cardToAdd);
            cardToAdd.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShadowWall();
    }
}