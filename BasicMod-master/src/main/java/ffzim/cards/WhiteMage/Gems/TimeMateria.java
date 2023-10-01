package ffzim.cards.WhiteMage.Gems;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.keywords.MateriaUpgradeAction;
import ffzim.cards.BaseCard;
import ffzim.cards.BlackMage.*;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.List;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.add;
import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;
public class TimeMateria extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "TimeMateria",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    private boolean isInPreview;
    public static final int baselevel1AP = 40;
    private static final int baselevel2AP = 120;

    public TimeMateria() {
        this(true);
    }
    public TimeMateria(boolean makePreview) {
        super(cardInfo, false);
        setExhaust(true);
        this.misc = 0;
        secondMagicNumber = this.timesUpgraded;
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        setCardAp(true, this.misc, baselevel1AP, baselevel2AP, -2, -2);
        tags.add(CustomTags.FFMATERIA);
        if (makePreview) {
            MultiCardPreview.add(this, new Slow(), new Haste(), new Stop());
            horizontal.set(this, true);
        }
        this.isInPreview = makePreview;
        super.initializeDescription();
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MateriaUpgradeAction(p, m,this));


//        if (this.upgraded) {
//                AbstractCard cardToAdd = new Haste();
//                cardToAdd.upgrade();
//                addToBot(new MakeTempCardInHandAction(cardToAdd, 1));
//                cardToAdd = new Slow();
//                cardToAdd.upgrade();
//                addToBot(new MakeTempCardInHandAction(cardToAdd, 1));
//        }else{
//            AbstractDungeon.actionManager.addToBottom(new ChooseCardAction(p,p));
//        }
    }

    public List<TooltipInfo> getCustomTooltipsTop() {
        if (addToToolTip == null) {
            addToToolTip = new ArrayList<>();
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("spell")), BaseMod.getKeywordDescription(makeID("spell"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("Haste")), BaseMod.getKeywordDescription(makeID("Haste"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("Slow")), BaseMod.getKeywordDescription(makeID("Slow"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("Stop")), BaseMod.getKeywordDescription(makeID("Stop"))));
        }
        return addToToolTip;
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        initializeDescription();
    }

    public void initializeDescription() {
        APRefreshVaules(this);
        super.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new TimeMateria();
    }
}