package ffzim.cards.WhiteMage.Gems;

import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.MultiUpgradeCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.keywords.MateriaUpgradeAction;
import ffzim.cards.BaseCard;
import ffzim.cards.BlackMage.*;
import ffzim.cards.WhiteMage.Cura;
import ffzim.cards.WhiteMage.Curaga;
import ffzim.cards.WhiteMage.Cure;
import ffzim.util.CardInfo;

import java.util.ArrayList;
import java.util.List;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;

public class LightningMateria extends BaseCard  implements MultiUpgradeCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "LightningMateria",
            1,
            CardType.SKILL,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            CardColor.COLORLESS); 


    public static final String ID = makeID(cardInfo.baseId);
    private boolean isInPreview;
    private static final int baselevel1AP = 30;
    private static final int baselevel2AP = 90;

    private final AbstractCard Card1 = new Cure();
    private final AbstractCard Card2 = new Cura();
    private final AbstractCard Card3 = new Curaga();


    public LightningMateria() {this(true);}
    public LightningMateria(boolean makePreview) {
        super(cardInfo, true);
        this.misc = 0;
        secondMagicNumber = this.timesUpgraded;
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        setCardAp(true, this.misc, baselevel1AP, baselevel2AP, -2, -2);
        //tags.add(CustomTags.FFMATERIA);
        if (makePreview) {
            MultiCardPreview.add(this,new Thunder(),new Thundara(),new Thundaga());
            horizontal.set(this, true);
        }
        this.isInPreview = makePreview;
        super.initializeDescription();
    }

    public void addUpgrades() {
        //addUpgradeData(Fire::new);
        // Upgrade 1
        addUpgradeData(() -> {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
            name = Card1.name;
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            applyPowers();
            initializeDescription();
        });


        // Upgrade 2 depends on Upgrade 1
        addUpgradeData(() -> {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[2];
            name = Card2.name;
            //this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            //this.rawDescription = String.join("\n", cardStrings.EXTENDED_DESCRIPTION);
            updateName();
            processUpgrade();
            applyPowers();
            initializeDescription();
        }, true, 0);

        addUpgradeData(() -> {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[3];
            name = Card3.name;
            updateName();
            processUpgrade();
            applyPowers();
            initializeDescription();
        }, true, 1);
    }

    public void updateName() {

    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MateriaUpgradeAction(p, m,this));
    }

    public List<TooltipInfo> getCustomTooltipsTop() {
        if (addToToolTip == null) {
            addToToolTip = new ArrayList<>();
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("spell")), BaseMod.getKeywordDescription(makeID("spell"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("Ranged")), BaseMod.getKeywordDescription(makeID("Ranged"))));
        }
        return addToToolTip;
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        initializeDescription();
    }

    public void initializeDescription() {
       //APRefreshVaules(this);
        super.initializeDescription();
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new LightningMateria();
    }
}