package ffzim.cards.WhiteMage.Gems;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.BlackMage.Demi;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class FatChoco extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FatChoco",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            CardColor.COLORLESS);


    public static final String ID = makeID(cardInfo.baseId);

    public FatChoco() {
        super(cardInfo, true);
        cardsToPreview = new Demi();
        tags.add(CustomTags.FFGEM);
    }

    /// Gains bonus based on number of food items used this run?
    // has a chance to show up every time u use a food item (since they have little use without needing ap for choco



    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            tags.add(CustomTags.FFGEM);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new WaitAction(0.8F));
            AbstractCard CastCard;
            CastCard = new Demi();
            if (upgraded) {
                CastCard.upgrade();
            }
            AbstractCard tmp = CastCard.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = CastCard.current_x;
            tmp.current_y = CastCard.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, CastCard.energyOnUse, true, true), true);
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FatChoco();
    }
}