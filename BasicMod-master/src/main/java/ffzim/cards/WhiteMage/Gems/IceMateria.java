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

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;

public class IceMateria extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "IceMateria",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            CardColor.COLORLESS); 


    public static final String ID = makeID(cardInfo.baseId);
    private static final int baselevel1AP = 30;
    private static final int baselevel2AP = 90;
    private boolean isInPreview;
    public IceMateria() {this(true);}
    public IceMateria(boolean makePreview) {
        super(cardInfo, true);
        this.misc = 0;
        secondMagicNumber = this.timesUpgraded;
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        setCardAp(true, this.misc, baselevel1AP, baselevel2AP, -2, -2);
        tags.add(CustomTags.FFMATERIA);
        if (makePreview) {
            MultiCardPreview.add(this, new Blizzard(false), new Blizzara(false), new Blizzaga(false));
            horizontal.set(this, true);
        }
        this.isInPreview = makePreview;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MateriaUpgradeAction(p, m, this));
    }

    public List<TooltipInfo> getCustomTooltipsTop() {
        if (addToToolTip == null) {
            addToToolTip = new ArrayList<>();
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("spell")), BaseMod.getKeywordDescription(makeID("spell"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("Ranged")), BaseMod.getKeywordDescription(makeID("Ranged"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("umbral_ice")), BaseMod.getKeywordDescription(makeID("umbral_ice"))));
        }
        //List<TooltipInfo> compoundList = new ArrayList<>(spellToolTip);
        //if (super.getCustomTooltipsTop() != null) compoundList.addAll(super.getCustomTooltipsTop());
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
    public AbstractCard makeCopy() { //Optional
        return new IceMateria();
    }
}