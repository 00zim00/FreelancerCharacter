//package ffzim.cards.WhiteMage.Gems;
//
//import basemod.BaseMod;
//import basemod.helpers.TooltipInfo;
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.MultiUpgradeCard;
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
//import com.evacipated.cardcrawl.mod.stslib.patches.cardInterfaces.MultiUpgradePatches;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.actions.keywords.MateriaUpgradeAction;
//import ffzim.cards.BaseCard;
//import ffzim.cards.WhiteMage.Cura;
//import ffzim.cards.WhiteMage.Curaga;
//import ffzim.cards.WhiteMage.Cure;
//import ffzim.helpers.MateriaFakeUpgrade;
//import ffzim.helpers.MateriaUpdatePreview;
//import ffzim.util.CardInfo;
//import ffzim.util.CustomTags;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static ffzim.BasicMod.makeID;
//
//public class HealingMateriaBU extends BaseCard implements MultiUpgradeCard, OnObtainCard
//{
//    private final static CardInfo cardInfo = new CardInfo(
//            "HealingMateriaBU",
//            1,
//            CardType.SKILL,
//            CardTarget.SELF,
//            CardRarity.UNCOMMON,
//            CardColor.COLORLESS); 
//
//
//    public static final String ID = makeID(cardInfo.baseId);
//
//    public boolean isInPreview;
//    private static final int baselevel1AP = 35;
//    private static final int baselevel2AP = 105;
//    private final AbstractCard Card1 = new Cure(false);
//    private final AbstractCard Card2 = new Cura(false);
//    private final AbstractCard Card3 = new Curaga(false);
//
//    public HealingMateriaBU() {this(true);}
//    public HealingMateriaBU(boolean makePreview) {
//        super(cardInfo, false);
//        setExhaust(true);
//        this.misc = 0;
//        secondMagicNumber = this.timesUpgraded;
//        this.baseMagicNumber = this.misc;
//        this.magicNumber = this.baseMagicNumber;
//        setMateria(true, this.misc, baselevel1AP, baselevel2AP, -2, -2);
//        this.setApUpgradeCards(Card1,Card2,Card3);
//        APLevelUp(this);
//        tags.add(CustomTags.FFMATERIA);
//        MateriaUpdatePreview.setPreview(this);
//        this.timesUpgraded = Math.max(1,this.timesUpgraded);
//        if (this.name.equals(Card2.name)){
//            this.setRenderInGrayscale(this, true);
//        }
//        this.setMateriaLevel(0);
//        //MultiUpgradePatches.MultiUpgradeFields.upgradeIndex.set(this, -1);
//        //takenList.add(getAPLevel1Card(Card1));
//        //MultiUpgradePatches.MultiUpgradeFields.upgradeIndex.set(this, -1);
//        //this.timesUpgraded = 1;
//        //this.isInPreview = makePreview;
//        //this.setCardPreview(true);
//        //addUpgrades();
//        MultiUpgradePatches.MultiUpgradeFields.upgradeIndex.set(this, 0);
//        super.initializeDescription();
//    }
//
////    @Override
////    public boolean canUpgrade() {
////        return false;
////    }
//
//    @Override
//    public void onObtainCard() {
//        this.setCardPreview(false);
//        this.timesUpgraded = Math.max(1,this.timesUpgraded);
//        updateMultiUpgradeLists(this);
//        //this.setRenderInFullColor(this,true);
////        if (isInPreview){
////            this.setCardPreview(false);
////        }
//    }
//
//
//    @Override
//    public void upgrade() {
//        //processUpgrade();
//
//        if (isCardPreview(this) && isInPreview){
//            updateMultiUpgradeLists(this);
//            MateriaUpdatePreview.setPreview(this);
//            //MultiUpgradePatches.MultiUpgradeFields.upgradeIndex.set(this, -1);
//            //takenList.add(Card1);
//            //MultiUpgradeTree.manualMainCard = true;
//            //mainCard = this;
//            //manualMainCard = true;
//        }else {
//            //this.setRenderInFullColor(this,true);
//            MateriaUpdatePreview.setPreview(this);
//            processUpgrade();
//        }
//    }
//    public void addUpgrades() {
//        isInPreview = true;
//        // setRenderInGrayscale(this,true);
//        //this.setRenderInFullColor(this,false);
//        addUpgradeData(() -> {
//            // setRenderInGrayscale(this,false);
//           // setRenderInFullColor(this,true);
//            MateriaFakeUpgrade materiaFakeUpgrade = new MateriaFakeUpgrade();
//            materiaFakeUpgrade.setCustomUpgradeDataMethod(this,1,false);
//        });
//        addUpgradeData(() -> {
//            MateriaFakeUpgrade materiaFakeUpgrade = new MateriaFakeUpgrade();
//            materiaFakeUpgrade.setCustomUpgradeDataMethod(this,2,false);
//            //this.misc = baselevel1AP;
//        }, true, 0);
//        addUpgradeData(() -> {
//            MateriaFakeUpgrade materiaFakeUpgrade = new MateriaFakeUpgrade();
//            materiaFakeUpgrade.setCustomUpgradeDataMethod(this,3,false);
//            //this.misc = baselevel2AP;
//        }, true, 1);
//        // setRenderInGrayscale(this,false);
//        updateMultiUpgradeLists(this);
//        isInPreview = false;
//    }
//
//    //@Override
//    public void updateName() {
//        isInPreview = true;
//        //this.setRenderInFullColor(this,false);
//        int upgradeIndex = MultiUpgradePatches.MultiUpgradeFields.upgradeIndex.get(this);
//        if (upgradeIndex == 0) {
//            MateriaFakeUpgrade materiaFakeUpgrade = new MateriaFakeUpgrade();
//            materiaFakeUpgrade.setCustomUpgradeDataMethod(this,1,true);
//        }else if (upgradeIndex == 1) {
//            MateriaFakeUpgrade materiaFakeUpgrade = new MateriaFakeUpgrade();
//            materiaFakeUpgrade.setCustomUpgradeDataMethod(this, 2, true);
//        }else if (upgradeIndex == 2) {
//            MateriaFakeUpgrade materiaFakeUpgrade = new MateriaFakeUpgrade();
//            materiaFakeUpgrade.setCustomUpgradeDataMethod(this,3,true);
//        }
//        //this.setRenderInFullColor(this,true);
//        updateMultiUpgradeLists(this);
//        isInPreview = false;
//}
//
//
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new MateriaUpgradeAction(p, m, this, Card1,Card2,Card3,Card3));
//    }
//
//    public List<TooltipInfo> getCustomTooltipsTop() {
//        if (addToToolTip == null) {
//            addToToolTip = new ArrayList<>();
//            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("spell")), BaseMod.getKeywordDescription(makeID("spell"))));
//            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle("temporary_hp"), BaseMod.getKeywordDescription("temporary_hp")));
//        }
//        return addToToolTip;
//    }
//
//    public void applyPowers() {
//        this.baseBlock = this.misc;
//        MateriaUpdatePreview.setPreview(this);
//        super.applyPowers();
//        initializeDescription();
//    }
//
//    public void initializeDescription() {
//        APRefreshVaules(this);
//        //MateriaUpdatePreview.setPreview(this);
//        super.initializeDescription();
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        BaseCard copy = new HealingMateriaBU();
//        //copy.materiaLevel = this.materiaLevel;
//
//        return copy;
//    }
//}