package ffzim.cards.WhiteMage.Gems;

import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.BlackMage.Fira;
import ffzim.cards.BlackMage.Fire;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class FireMateriaBU extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FireMateriaBU",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            CardColor.COLORLESS);


    public static final String ID = makeID(cardInfo.baseId);

    private boolean isInPreview;

    public FireMateriaBU() {this(true);}
    public FireMateriaBU(boolean makePreview) {
        super(cardInfo, true);
        tags.add(CustomTags.FFGEM);
        if (makePreview) {
            cardsToPreview = new Fire(false);
        }
        this.isInPreview = makePreview;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            if (isInPreview ) {
                this.cardsToPreview = new Fira(false);
            }
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            tags.add(CustomTags.FFGEM);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new WaitAction(0.8F));
            AbstractCard CastCard;
        if (upgraded) {
            CastCard = new Fira(true);
        }else{
            CastCard = new Fire(true);
        }
            AbstractCard tmp = CastCard.makeSameInstanceOf();
            tmp.tags.add(CustomTags.FFCOPY);
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
        return new FireMateriaBU();
    }
}