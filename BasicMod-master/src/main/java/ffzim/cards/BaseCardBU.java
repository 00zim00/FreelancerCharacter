//package ffzim.cards;
//
//import basemod.abstracts.CustomCard;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import ffzim.BasicMod;
//import ffzim.util.CardInfo;
//
//import static ffzim.BasicMod.makeID;
//import static ffzim.util.TextureLoader.getCardTextureString;
//
//
//public abstract class BaseCardBU extends CustomCard {
//    protected CardStrings cardStrings;
//
//    protected boolean upgradesDescription;
//
//    protected int baseCost;
//
//    protected boolean upgradeCost;
//    protected boolean upgradeDamage;
//    protected boolean upgradeBlock;
//    protected boolean upgradeMagic;
//
//    protected int costUpgrade;
//    protected int damageUpgrade;
//    protected int blockUpgrade;
//    protected int magicUpgrade;
//
//    protected boolean baseExhaust = false;
//    protected boolean upgExhaust = false;
//    protected boolean baseEthereal = false;
//    protected boolean upgEthereal = false;
//    protected boolean baseInnate = false;
//    protected boolean upgInnate = false;
//    protected boolean baseRetain = false;
//    protected boolean upgRetain = false;
//
//    public int baseSecondMagicNumber;
//    public int secondMagicNumber;
//    public int secondMagicUpgrade;
//    public boolean isSecondMagicNumberModified;
//    public boolean isDefaultSecondMagicNumberModified;
//    public boolean upgradedSecondMagicNumber;
//
//    public BaseCardBU(CardInfo cardInfo) {
//        this(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor);
//    }
//    public BaseCardBU(CardInfo cardInfo, boolean upgradesDescription)
//    {
//        this(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor, upgradesDescription);
//    }
//
//    public BaseCardBU(String baseID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color)
//    {
//        super(makeID(baseID), "", getCardTextureString(baseID, cardType), cost, "", cardType, color, rarity, target);
//
//        loadStrings();
//
//        this.baseCost = cost;
//
//        this.upgradesDescription = cardStrings.UPGRADE_DESCRIPTION != null;
//        this.upgradeCost = false;
//        this.upgradeDamage = false;
//        this.upgradeBlock = false;
//        this.upgradeMagic = false;
//        this.upgradedSecondMagicNumber = false;
//
//        this.costUpgrade = cost;
//        this.damageUpgrade = 0;
//        this.blockUpgrade = 0;
//        this.magicUpgrade = 0;
//        this.secondMagicUpgrade = 0;
//
//        initializeTitle();
//        initializeDescription();
//    }
//
//    public BaseCardBU(String cardName, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, boolean upgradesDescription)
//    {
//        super(makeID(cardName), "", getCardTextureString(cardName, cardType), cost, "", cardType, color, rarity, target);
//
//        loadStrings();
//
//        this.baseCost = cost;
//
//        this.upgradesDescription = upgradesDescription;
//        this.upgradeCost = false;
//        this.upgradeDamage = false;
//        this.upgradeBlock = false;
//        this.upgradeMagic = false;
//        this.upgradedSecondMagicNumber = false;
//
//        this.costUpgrade = cost;
//        this.damageUpgrade = 0;
//        this.blockUpgrade = 0;
//        this.magicUpgrade = 0;
//        this.secondMagicUpgrade = 0;
//
//        initializeTitle();
//        initializeDescription();
//    }
//
//    private void loadStrings() {
//        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);
//
//        this.rawDescription = cardStrings.DESCRIPTION;
//        this.originalName = cardStrings.NAME;
//        this.name = originalName;
//    }
//
//    //Methods meant for constructor use
//    protected final void setDamage(int damage)
//    {
//        this.setDamage(damage, 0);
//    }
//    protected final void setBlock(int block)
//    {
//        this.setBlock(block, 0);
//    }
//    protected final void setMagic(int magic)
//    {
//        this.setMagic(magic, 0);
//    }
//    protected final void setSecondMagic(int secondMagic) {this.setSecondMagic(secondMagic, 0);}
//    protected final void setCostUpgrade(int costUpgrade)
//    {
//        this.costUpgrade = costUpgrade;
//        this.upgradeCost = true;
//    }
//    protected final void setExhaust(boolean exhaust) { this.setExhaust(exhaust, exhaust); }
//    protected final void setEthereal(boolean ethereal) { this.setEthereal(ethereal, ethereal); }
//    protected final void setInnate(boolean innate) {this.setInnate(innate, innate); }
//    protected final void setSelfRetain(boolean retain) {this.setSelfRetain(retain, retain); }
//
//    protected final void setDamage(int damage, int damageUpgrade)
//    {
//        this.baseDamage = this.damage = damage;
//        if (damageUpgrade != 0)
//        {
//            this.upgradeDamage = true;
//            this.damageUpgrade = damageUpgrade;
//        }
//    }
//    protected final void setBlock(int block, int blockUpgrade)
//    {
//        this.baseBlock = this.block = block;
//        if (blockUpgrade != 0)
//        {
//            this.upgradeBlock = true;
//            this.blockUpgrade = blockUpgrade;
//        }
//    }
//    protected final void setMagic(int magic, int magicUpgrade)
//    {
//        this.baseMagicNumber = this.magicNumber = magic;
//        if (magicUpgrade != 0)
//        {
//            this.upgradeMagic = true;
//            this.magicUpgrade = magicUpgrade;
//        }
//    }
//
//    protected final void setSecondMagic(int secondMagic, int secondMagicUpgrade) {
//        this.baseSecondMagicNumber = this.secondMagicNumber = secondMagic;
//        if (secondMagicUpgrade != 0) {
//            this.upgradedSecondMagicNumber = true;
//            this.secondMagicUpgrade = secondMagicUpgrade;
//        }
//    }
//
//    protected final void setExhaust(boolean baseExhaust, boolean upgExhaust)
//    {
//        this.baseExhaust = baseExhaust;
//        this.upgExhaust = upgExhaust;
//        this.exhaust = baseExhaust;
//    }
//    protected final void setEthereal(boolean baseEthereal, boolean upgEthereal)
//    {
//        this.baseEthereal = baseEthereal;
//        this.upgEthereal = upgEthereal;
//        this.isEthereal = baseEthereal;
//    }
//    protected void setInnate(boolean baseInnate, boolean upgInnate)
//    {
//        this.baseInnate = baseInnate;
//        this.upgInnate = upgInnate;
//        this.isInnate = baseInnate;
//    }
//    protected void setSelfRetain(boolean baseRetain, boolean upgRetain)
//    {
//        this.baseRetain = baseRetain;
//        this.upgRetain = upgRetain;
//        this.selfRetain = baseRetain;
//    }
//
//
//    @Override
//    public AbstractCard makeStatEquivalentCopy() {
//        AbstractCard card = super.makeStatEquivalentCopy();
//
//        if (card instanceof BaseCardBU)
//        {
//            card.rawDescription = this.rawDescription;
//            ((BaseCardBU) card).upgradesDescription = this.upgradesDescription;
//
//            ((BaseCardBU) card).baseCost = this.baseCost;
//
//            ((BaseCardBU) card).upgradeCost = this.upgradeCost;
//            ((BaseCardBU) card).upgradeDamage = this.upgradeDamage;
//            ((BaseCardBU) card).upgradeBlock = this.upgradeBlock;
//            ((BaseCardBU) card).upgradeMagic = this.upgradeMagic;
//
//            ((BaseCardBU) card).upgradedSecondMagicNumber= this.upgradedSecondMagicNumber;
//
//            ((BaseCardBU) card).costUpgrade = this.costUpgrade;
//            ((BaseCardBU) card).damageUpgrade = this.damageUpgrade;
//            ((BaseCardBU) card).blockUpgrade = this.blockUpgrade;
//            ((BaseCardBU) card).magicUpgrade = this.magicUpgrade;
//
//            ((BaseCardBU) card).secondMagicUpgrade = this.secondMagicUpgrade;
//
//            ((BaseCardBU) card).baseExhaust = this.baseExhaust;
//            ((BaseCardBU) card).upgExhaust = this.upgExhaust;
//            ((BaseCardBU) card).baseEthereal = this.baseEthereal;
//            ((BaseCardBU) card).upgEthereal = this.upgEthereal;
//            ((BaseCardBU) card).baseInnate = this.baseInnate;
//            ((BaseCardBU) card).upgInnate = this.upgInnate;
//            ((BaseCardBU) card).baseRetain = this.baseRetain;
//            ((BaseCardBU) card).upgRetain = this.upgRetain;
//        }
//
//        return card;
//    }
//
//    @Override
//    public void upgrade()
//    {
//        if (!upgraded)
//        {
//            this.upgradeName();
//
//            if (this.upgradesDescription)
//            {
//                if (cardStrings.UPGRADE_DESCRIPTION == null)
//                {
//                    BasicMod.logger.error("Card " + cardID + " upgrades description and has null upgrade description.");
//                }
//                else
//                {
//                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//                }
//            }
//
//            if (upgradeCost)
//            {
//                if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
//                    int diff = this.costUpgrade - this.baseCost; //how the upgrade alters cost
//                    this.upgradeBaseCost(this.cost + diff);
//                    if (this.cost < 0)
//                        this.cost = 0;
//                }
//                else {
//                    upgradeBaseCost(costUpgrade);
//                }
//            }
//
//            if (upgradeDamage)
//                this.upgradeDamage(damageUpgrade);
//
//            if (upgradeBlock)
//                this.upgradeBlock(blockUpgrade);
//
//            if (upgradeMagic)
//                this.upgradeMagicNumber(magicUpgrade);
//
//            if (upgradedSecondMagicNumber)
//                this.upgradeMagicNumber(secondMagicUpgrade);
//
//            if (baseExhaust ^ upgExhaust)
//                this.exhaust = upgExhaust;
//
//            if (baseInnate ^ upgInnate)
//                this.isInnate = upgInnate;
//
//            if (baseEthereal ^ upgEthereal)
//                this.isEthereal = upgEthereal;
//
//            if (baseRetain ^ upgRetain)
//                this.selfRetain = upgRetain;
//
//
//            this.initializeDescription();
//        }
//    }
//}