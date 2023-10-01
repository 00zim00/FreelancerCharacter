package ffzim.cards.WhiteMage.Gems;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.keywords.MateriaUpgradeAction;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.List;

import static ffzim.BasicMod.makeID;

public class PedometerMateria extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "PedometerMateria",
            -2,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.COMMON,
            CardColor.COLORLESS); 


    public static final String ID = makeID(cardInfo.baseId);
    public static final int baselevel1AP = 777;
    private boolean isInPreview;
    public PedometerMateria() {this(true);}
    public PedometerMateria(boolean makePreview) {
        super(cardInfo, true);
        this.misc = 0;
        secondMagicNumber = this.timesUpgraded;
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        setCardAp(true, this.misc, baselevel1AP,-2, -2, -2);
        tags.add(CustomTags.FFMATERIA);
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
        return new PedometerMateria();
    }
}