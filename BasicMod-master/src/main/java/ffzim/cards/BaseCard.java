package ffzim.cards;

import basemod.BaseMod;
import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.BasicMod;
import ffzim.helpers.MateriaUpdatePreview;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.List;

import static ffzim.BasicMod.makeID;
import static ffzim.util.SpellUnbuffingStrength.*;
import static ffzim.util.SpellUnbuffingStrength.spellApplyPowers2;
import static ffzim.util.TextureLoader.getCardTextureString;


public abstract class BaseCard extends CustomCard {
    protected CardStrings cardStrings;

    protected boolean upgradesDescription;

    protected int baseCost;

    protected boolean upgradeCost;
    protected boolean upgradeDamage;
    protected boolean upgradeBlock;
    protected boolean upgradeMagic;

    protected int costUpgrade;
    protected int damageUpgrade;
    protected int blockUpgrade;
    protected int magicUpgrade;

    protected boolean baseExhaust = false;
    protected boolean upgExhaust = false;
    protected boolean baseEthereal = false;
    protected boolean upgEthereal = false;
    protected boolean baseInnate = false;
    protected boolean upgInnate = false;
    protected boolean baseRetain = false;
    protected boolean upgRetain = false;
    //protected int revolvingCount = -2;
    //private int initialRevolvingCount = -2;
    //protected boolean baseRevolving = false;
    //protected boolean upgRevolving = false;

    //protected boolean selfRevolving = false;
    public int baseSecondMagicNumber;
    public int secondMagicNumber;
    public int secondMagicUpgrade;
    public boolean isSecondMagicNumberModified;
    public boolean isDefaultSecondMagicNumberModified;
    public boolean upgradedSecondMagicNumber;

    public int RegenCount;

    protected boolean levelUp = false; // Default value

    // private ArrayList<TooltipInfo> addToToolTip;
    public ArrayList<TooltipInfo> addToToolTip;

    // Other card properties and methods
    protected final void setLevelUp(boolean LevelUp) {
        this.levelUp = LevelUp;
    }

    public boolean isLevelUp() {
        return levelUp;
    }

    protected boolean renderInGrayscale = false;
    protected boolean renderInFullColor = false;

    public void setRenderInGrayscale(AbstractCard card, boolean renderInGrayscale) {
        if (card instanceof BaseCard) {
            ((BaseCard) card).renderInGrayscale = renderInGrayscale;
        }
    }

    public void setRenderInFullColor(AbstractCard card, boolean renderInFullColor) {
        if (card instanceof BaseCard) {
            ((BaseCard) card).renderInFullColor = renderInFullColor;

//            for (AbstractCard card : takenList) {
//                // Check if the card is not null and has timesUpgraded equal to 1
//                if (card != null && card == this && card.timesUpgraded == 1) {
//                }
//                if (card != null && card == this && card.timesUpgraded == 2) {
//                    // Perform your actions here for cards meeting the condition
//                }
//                if (card != null && card == this && card.timesUpgraded == 3) {
//                    // Perform your actions here for cards meeting the condition
//                }
//                if (card != null && card == this && card.timesUpgraded == 4) {
//                    // Perform your actions here for cards meeting the condition
//                }
//            }


            //updateMultiUpgradeLists(card);
        }
    }


    public void updateMultiUpgradeLists(AbstractCard targetCard) {
        if (targetCard instanceof BaseCard) {
//            for (AbstractCard cardInList : takenList) {
//                if (cardInList.uuid.equals(targetCard.uuid)) {
//                    int index = takenList.indexOf(cardInList);
//                    System.out.println("TakenList: " + index);
//
//                    if (index != -1) {
//                        BaseCard tempCard = (BaseCard) takenList.get(index);
//                        if (targetCard.timesUpgraded >= tempCard.timesUpgraded){
//                            System.out.println("tempCard: " + tempCard);
//                            tempCard.setRenderInGrayscale(targetCard,false);
//                            tempCard.setRenderInFullColor(targetCard,true);
//                            takenList.set(index, tempCard);
//                        }else{
//                            System.out.println("tempCard2: " + tempCard);
//                            tempCard.setRenderInGrayscale(targetCard,true);
//                            tempCard.setRenderInFullColor(targetCard,false);
//                            takenList.set(index, tempCard);
//                        }
//                    }
//                }
//            }
        }
    }


    public boolean isRenderInGrayscale() {
        return renderInGrayscale;
    }

    public boolean isRenderInFullColor() {
        return renderInFullColor;
    }

    public boolean getUsesAp() {
        return usesAp;
    }

    protected boolean usesAp = false;
    public int currentAP;
    public int cardLevel = -2;
    protected int maxCardLevel = -2;
    protected int level1AP = -2;
    protected int level2AP;
    protected int level3AP;
    protected int level4AP;
    protected int maxApLevel = 0;
    protected int nextApLevel = -2;
    protected AbstractCard APLevel1Card;
    protected AbstractCard APLevel2Card;
    protected AbstractCard APLevel3Card;
    protected AbstractCard APLevel4Card;
    protected boolean CardPreview = true;
    public static CardGroup gainedChocoboCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    protected static int ChocoLevelStoring = 0x01010101;

    protected final void setChocoboCards(AbstractCard... apCards) {
        int numCards = apCards.length;
        if (gainedChocoboCards != null) {
            if (numCards > 0 && this.APLevel1Card == null) {
                this.APLevel1Card = apCards[0];
            }
            if (numCards > 1 && this.APLevel2Card == null) {
                this.APLevel2Card = apCards[1];
            }
            if (numCards > 2 && this.APLevel3Card == null) {
                this.APLevel3Card = apCards[2];
            }
            if (numCards > 3 && this.APLevel4Card == null) {
                this.APLevel4Card = apCards[3];
            }
        }
    }

    protected final void setCardAp(boolean UsesAp, int CurrentAP, int Level1AP, int Level2AP, int Level3AP, int Level4AP) {
        this.currentAP = CurrentAP;
        this.level1AP = Level1AP;
        this.level2AP = Level2AP;
        this.level3AP = Level3AP;
        this.level4AP = Level4AP;
        this.usesAp = UsesAp;

        if (this.level2AP < this.level1AP) {
            maxApLevel = level1AP;
        } else if (this.level3AP < this.level2AP) {
            maxApLevel = level2AP;
        } else if (this.level4AP < this.level3AP) {
            maxApLevel = level3AP;
        } else {
            maxApLevel = level4AP;
        }

        this.setNextApLevel(this);

    }

    protected final void setNextApLevel(AbstractCard Card) {
        if (this.cardLevel == 0 && this.level1AP >= 0) {
            this.nextApLevel = this.level1AP;
        } else if (this.cardLevel == 1 && this.level2AP != 0) {
            this.nextApLevel = this.level2AP;
        } else if (this.cardLevel == 2 && this.level3AP != 0) {
            this.nextApLevel = this.level3AP;
        } else if (this.cardLevel == 3 && this.level4AP != 0) {
            this.nextApLevel = this.level4AP;
        }
    }

    protected final void setApUpgradeCards(AbstractCard... apCards) {
        int numCards = apCards.length;
        if (numCards > 0 && this.APLevel1Card == null) {
            this.APLevel1Card = apCards[0];
        }
        if (numCards > 1 && this.APLevel2Card == null) {
            this.APLevel2Card = apCards[1];
        }
        if (numCards > 2 && this.APLevel3Card == null) {
            this.APLevel3Card = apCards[2];
        }
        if (numCards > 3 && this.APLevel4Card == null) {
            this.APLevel4Card = apCards[3];
        }
    }

    public void incrementTotalChocoRank(int incrementAmount, boolean override) {
        int existingLevels = ChocoLevelStoring & 0x00FFFFFF;

        if (override) {
            // Set the rank to the specified value
            ChocoLevelStoring = existingLevels | ((incrementAmount & 0xFF) << 24);
        } else {
            // Increment or decrement the rank
            int newTotalChocoLevel = Math.min((getTotalChocoRank() + incrementAmount) & 0xFF, 20);
            ChocoLevelStoring = existingLevels | (newTotalChocoLevel << 24);
        }
    }

    public void incrementTotalChocoAttackLevel(int incrementAmount, boolean override) {
        int existingLevels = ChocoLevelStoring & 0xFF00FFFF; // Clear the second byte
        if (override) {
            // Set the Choco Attack Level to the specified value
            ChocoLevelStoring = existingLevels | ((incrementAmount & 0xFF) << 16);
        } else {
            // Increment or decrement the Choco Attack Level
            int newTotalChocoAttackLevel = Math.min((getChocoAttackLevel() + incrementAmount) & 0xFF, 5);
            ChocoLevelStoring = existingLevels | (newTotalChocoAttackLevel << 16);
        }
    }

    public void incrementTotalChocoHealerLevel(int incrementAmount, boolean override) {
        int existingLevels = ChocoLevelStoring & 0xFFFF00FF; // Clear the third byte
        if (override) {
            // Set the Choco Healer Level to the specified value
            ChocoLevelStoring = existingLevels | ((incrementAmount & 0xFF) << 8);
        } else {
            // Increment or decrement the Choco Healer Level
            int newTotalChocoHealerLevel = Math.min((getChocoHealerLevel() + incrementAmount) & 0xFF, 5);
            ChocoLevelStoring = existingLevels | (newTotalChocoHealerLevel << 8);
        }
    }

    public void incrementTotalChocoDefenderLevel(int incrementAmount, boolean override) {
        int existingLevels = ChocoLevelStoring & 0xFFFFFF00; // Clear the last byte
        if (override) {
            // Set the Choco Defender Level to the specified value
            ChocoLevelStoring = existingLevels | (incrementAmount & 0xFF);
        } else {
            // Increment or decrement the Choco Defender Level
            int newTotalChocoDefenderLevel = Math.min(getChocoDefenderLevel() + incrementAmount, 5);
            ChocoLevelStoring = existingLevels | newTotalChocoDefenderLevel;
        }
    }

    public int getTotalChocoRank() {
        if (ChocoLevelStoring != 0){
            return (ChocoLevelStoring >> 24) & 0xFF;
        }
        return 0;
    }

    public int getChocoboRankApLevel() {
        return (getTotalChocoRank() * 15);
    }
    public int getChocoAttackLevel() {
        if (ChocoLevelStoring != 0){
            return (ChocoLevelStoring >> 16) & 0xFF;
        }
        return 0;
    }
    public int getChocoHealerLevel() {
        if (ChocoLevelStoring != 0){
            return (ChocoLevelStoring >> 8) & 0xFF;
        }
        return 0;
    }
    public int getChocoDefenderLevel() {
        if (ChocoLevelStoring != 0){
            return ChocoLevelStoring & 0xFF;
        }
        return 0;
    }

    public int getNextApLevel(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            setNextApLevel(Card);
            return nextApLevel;
        }
        return -2;
    }

    public int getLevel1AP(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return level1AP;
        }
        return -2;
    }
    public int getMaxApLevel (AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return maxApLevel ;
        }
        return -2;
    }
    public int getLevel2AP(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return level2AP;
        }
        return -2;
    }
    public int getLevel3AP(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return level3AP;
        }
        return -2;
    }
    public int getLevel4AP(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return level4AP;
        }
        return -2;
    }

    public void setCardLevel(int level) {
            this.cardLevel = level;
    }

    public int getCardLevel(AbstractCard card) {
        if (card instanceof BaseCard) {
            return this.cardLevel;
        }
        return -2;
    }

    public AbstractCard getAPLevel1Card(AbstractCard card) {
        if (card instanceof BaseCard) {
            return APLevel1Card;
        }
        return null;
    }

    public boolean isCardPreview(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return CardPreview;
        }
        return false;
    }

    public void setCardPreview(Boolean CardPreview) {
        this.CardPreview = CardPreview;
    }

    public AbstractCard getAPLevel2Card(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return APLevel2Card;
        }
        return null;
    }

    public AbstractCard getAPLevel3Card(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return APLevel3Card;
        }
        return null;
    }

    public AbstractCard getAPLevel4Card(AbstractCard Card) {
        if (Card instanceof BaseCard) {
            return APLevel4Card;
        }
        return null;
    }

    public void setAP(AbstractCard card, int setAP) {
        card.misc = setAP;
    }

    public void addAP(AbstractCard card, int addAP) {
        int tempAP = card.misc + addAP;
        if (tempAP >= this.level4AP && this.level4AP > 0) {
            int excessAP = tempAP - this.level4AP;
            addAP = addAP - excessAP;
        }else if (tempAP >= this.level3AP && this.level3AP > 0 && this.level4AP < this.level3AP) {
            int excessAP = tempAP - this.level3AP;
            addAP = addAP - excessAP;
        }else if (tempAP >= this.level2AP && this.level2AP > 0 && this.level3AP < this.level2AP) {
            int excessAP = tempAP - this.level2AP;
            addAP = addAP - excessAP;
        }else if (tempAP >= this.level1AP && this.level1AP > 0 && this.level2AP < this.level1AP) {
            int excessAP = tempAP - this.level1AP;
            addAP = addAP - excessAP;
        }
        card.misc += addAP;
    }

    public void applyAP(AbstractCard card) {
        this.currentAP = card.misc;
    }

    public void APLevelUp(AbstractCard card) {
        if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
            this.currentAP = card.misc;
            if (card.timesUpgraded == 0 && this.currentAP >= this.level1AP && this.level1AP > 0) {
                card.timesUpgraded = 1;
                ((BaseCard)card).cardLevel = 1;
                if (card.misc != ((BaseCard) card).maxApLevel){
                    this.setNextApLevel(this);
                    card.misc = card.misc - this.level1AP;
                    this.secondMagicNumber = this.getNextApLevel(this);
                }
            }
            if (card.timesUpgraded == 1 && this.currentAP >= this.level2AP && this.level2AP > 0 && card.misc != ((BaseCard) card).maxApLevel) {
                card.timesUpgraded = 2;
                ((BaseCard)card).cardLevel = 2;
                if (card.misc != ((BaseCard) card).maxApLevel){
                    this.setNextApLevel(this);
                    card.misc = card.misc - this.level2AP;
                    this.secondMagicNumber = this.getNextApLevel(this);
                }
            }
            if (card.timesUpgraded == 2 && this.currentAP >= this.level3AP && this.level3AP > 0) {
                card.timesUpgraded = 3;
                ((BaseCard)card).cardLevel = 3;
                if (card.misc != ((BaseCard) card).maxApLevel){
                    this.setNextApLevel(this);
                    card.misc = card.misc - this.level3AP;
                    this.secondMagicNumber = this.getNextApLevel(this);
                }
            }
            if (card.timesUpgraded == 3 && this.currentAP >= this.level4AP && this.level4AP > 0 && card.misc != ((BaseCard) card).maxApLevel) {
                card.timesUpgraded = 4;
                ((BaseCard)card).cardLevel = 4;
                if (card.misc != ((BaseCard) card).maxApLevel){
                    this.setNextApLevel(this);
                    card.misc = card.misc - this.level4AP;
                    this.secondMagicNumber = this.getNextApLevel(this);
                }
            }

            if (card.misc == ((BaseCard) card).maxApLevel && maxCardLevel != ((BaseCard)card).cardLevel){
                card.timesUpgraded += 1;
                ((BaseCard)card).cardLevel += 1;
                maxCardLevel = card.timesUpgraded;
            }

            APRefreshVaules(this);
            MateriaUpdatePreview.setPreview(card);
            card.applyPowers();
            card.initializeDescription();
        }
    }

    public void APRefreshVaules(AbstractCard card) {
        if (card instanceof BaseCard){
            this.currentAP = card.misc;
            card.baseMagicNumber = card.misc;
            card.magicNumber = card.baseMagicNumber;
            card.baseDamage = card.misc;
            if (maxCardLevel != ((BaseCard)card).cardLevel){
                ((BaseCard) card).secondMagicNumber = getNextApLevel(this);
            }else{
                ((BaseCard) card).secondMagicNumber = getMaxApLevel(this);
            }
            ((BaseCard) card).baseSecondMagicNumber = ((BaseCard) card).secondMagicNumber;
        }


//        if (currentAP == this.level4AP && this.level4AP > 0) {
//            card.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//        }else if (currentAP == this.level3AP && this.level3AP > 0 && this.level3AP > this.level4AP) {
//            card.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//        }else if (currentAP == this.level2AP && this.level2AP > 0 && this.level2AP > this.level1AP) {
//            card.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//        }else if (currentAP == this.level1AP && this.level1AP > 0 && this.level1AP > this.level2AP) {
//            card.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//        }


//        if (card.timesUpgraded >= 3 && this.level4AP > this.level3AP) {
//            ((BaseCard) card).setSecondMagic(this.level4AP);
//        }else if (card.timesUpgraded >= 2 && this.level3AP > this.level2AP) {
//            ((BaseCard) card).setSecondMagic(this.level3AP);
//        }else if (card.timesUpgraded >= 1 && this.level2AP > this.level1AP) {
//            ((BaseCard) card).setSecondMagic(this.level2AP);
//        }else {
//            ((BaseCard) card).setSecondMagic(this.level1AP);
//        }
    }

//    protected boolean unique = false; // Default value
//    protected final void setCardUnique(boolean Unique) {
//        this.unique = Unique;
//    }
//    public boolean isCardUnique() {
//        return unique;
//    }

//    protected final void setRevolving(int count) {
//        if (count == -1) {
//            this.revolvingCount = 0;
//        } else if (count >= 0) {
//            this.revolvingCount = count;
//        } else {
//            this.revolvingCount = count;
//        }
//    }
//    public int getRevolvingCount() {
//        return revolvingCount;
//    }
//
//    public boolean isRevolvingEnabled() {
//        return revolvingCount >= 0;
//    }
//
//    @Override
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
//            return false;
//        } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
//            return false;
//        } else if (isRevolvingEnabled() && initialRevolvingCount == 0) {
//            return false;
//        } else {
//            return this.cardPlayable(m) && this.hasEnoughEnergy();
//        }
//    }
//
//    @Override
//    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
//        if (isRevolvingEnabled() && revolvingCount >= 0) {
//            // Decrease the revolvingCount by 1
//            revolvingCount -= 1;
//            System.out.println("revolvingCount: " + revolvingCount);
//        }
//    }
//
//    @Override
//    public void triggerAtStartOfTurn() {
//        // Reset the revolvingCount to its initial value
//        revolvingCount = initialRevolvingCount;
//    }


    //@Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        if (addToToolTip == null) {
            addToToolTip = new ArrayList<>();
            if (this.hasTag(CustomTags.FFSPELL) && !this.rawDescription.toLowerCase().contains(makeID("spell"))) {
                addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("spell")), BaseMod.getKeywordDescription(makeID("spell"))));
            }
            if (this.hasTag(CustomTags.FFITEM) && !this.rawDescription.toLowerCase().contains(makeID("item"))) {
                addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("item")), BaseMod.getKeywordDescription(makeID("item"))));
            }
        }
        //List<TooltipInfo> compoundList = new ArrayList<>(spellToolTip);
        //if (super.getCustomTooltipsTop() != null) compoundList.addAll(super.getCustomTooltipsTop());
        return addToToolTip;
    }

    @Override
    public void applyPowers() {
        if (this.hasTag(CustomTags.FFSPELL) || this.hasTag(CustomTags.FFENSPELL)) {
            spellApplyPowers(this);
            //super.applyPowers();
            spellApplyPowers2(this);
        }
        //super.applyPowers();
    }
    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (this.hasTag(CustomTags.FFSPELL) || this.hasTag(CustomTags.FFENSPELL)) {
            spellCalculateCardDamage(this,mo);
            if (mo != null){super.calculateCardDamage(mo);}
            spellCalculateCardDamage2(this,mo);
        }
        super.calculateCardDamage(mo);
    }

    public void refreshPreviewCards(AbstractCard card) {

    }


    @Override
    public List<String> getCardDescriptors() {
        ArrayList<String> retVal = new ArrayList<>();
        if (this.hasTag(CustomTags.FFSPELL)) {
            retVal.add("Spell");
        }
        if (this.hasTag(CustomTags.FFITEM)) {
            retVal.add("Item");
        }
        return retVal;
    }

    public BaseCard(CardInfo cardInfo) {
        this(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor);
    }
    public BaseCard(CardInfo cardInfo, boolean upgradesDescription)
    {
        this(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor, upgradesDescription);
    }

    public BaseCard(String baseID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color)
    {
        super(makeID(baseID), "", getCardTextureString(baseID, cardType), cost, "", cardType, color, rarity, target);

        loadStrings();

        this.baseCost = cost;

        this.upgradesDescription = cardStrings.UPGRADE_DESCRIPTION != null;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;
        this.upgradedSecondMagicNumber = false;

        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;
        this.secondMagicUpgrade = 0;

        initializeTitle();
        initializeDescription();
    }

    public BaseCard(String cardName, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, boolean upgradesDescription)
    {
        super(makeID(cardName), "", getCardTextureString(cardName, cardType), cost, "", cardType, color, rarity, target);

        loadStrings();

        this.baseCost = cost;

        this.upgradesDescription = upgradesDescription;
        this.upgradeCost = false;
        this.upgradeDamage = false;
        this.upgradeBlock = false;
        this.upgradeMagic = false;
        this.upgradedSecondMagicNumber = false;

        this.costUpgrade = cost;
        this.damageUpgrade = 0;
        this.blockUpgrade = 0;
        this.magicUpgrade = 0;
        this.secondMagicUpgrade = 0;

        initializeTitle();
        initializeDescription();
    }

    private void loadStrings() {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(cardID);

        this.rawDescription = cardStrings.DESCRIPTION;
        this.originalName = cardStrings.NAME;
        this.name = originalName;
    }

    //Methods meant for constructor use
    protected final void setDamage(int damage)
    {
        this.setDamage(damage, 0);
    }
    protected final void setBlock(int block)
    {
        this.setBlock(block, 0);
    }
    protected final void setMagic(int magic)
    {
        this.setMagic(magic, 0);
    }
    protected final void setSecondMagic(int secondMagic) {this.setSecondMagic(secondMagic, 0);}
    protected final void setCostUpgrade(int costUpgrade)
    {
        this.costUpgrade = costUpgrade;
        this.upgradeCost = true;
    }
    protected final void setExhaust(boolean exhaust) { this.setExhaust(exhaust, exhaust); }
    protected final void setEthereal(boolean ethereal) { this.setEthereal(ethereal, ethereal); }
    protected final void setInnate(boolean innate) {this.setInnate(innate, innate); }
    protected final void setSelfRetain(boolean retain) {this.setSelfRetain(retain, retain); }
    //protected final void setSelfRevolving(boolean revolving) {this.setSelfRevolving(revolving, revolving); }

    protected final void setDamage(int damage, int damageUpgrade)
    {
        this.baseDamage = this.damage = damage;
        if (damageUpgrade != 0)
        {
            this.upgradeDamage = true;
            this.damageUpgrade = damageUpgrade;
        }
    }
    protected final void setBlock(int block, int blockUpgrade)
    {
        this.baseBlock = this.block = block;
        if (blockUpgrade != 0)
        {
            this.upgradeBlock = true;
            this.blockUpgrade = blockUpgrade;
        }
    }
    protected final void setMagic(int magic, int magicUpgrade)
    {
        this.baseMagicNumber = this.magicNumber = magic;
        if (magicUpgrade != 0)
        {
            this.upgradeMagic = true;
            this.magicUpgrade = magicUpgrade;
        }
    }

    protected final void setSecondMagic(int secondMagic, int secondMagicUpgrade) {
        this.baseSecondMagicNumber = this.secondMagicNumber = secondMagic;
        if (secondMagicUpgrade != 0) {
            this.upgradedSecondMagicNumber = true;
            this.secondMagicUpgrade = secondMagicUpgrade;
        }
    }

    protected final void setExhaust(boolean baseExhaust, boolean upgExhaust)
    {
        this.baseExhaust = baseExhaust;
        this.upgExhaust = upgExhaust;
        this.exhaust = baseExhaust;
    }
    protected final void setEthereal(boolean baseEthereal, boolean upgEthereal)
    {
        this.baseEthereal = baseEthereal;
        this.upgEthereal = upgEthereal;
        this.isEthereal = baseEthereal;
    }
    protected void setInnate(boolean baseInnate, boolean upgInnate)
    {
        this.baseInnate = baseInnate;
        this.upgInnate = upgInnate;
        this.isInnate = baseInnate;
    }
    protected void setSelfRetain(boolean baseRetain, boolean upgRetain)
    {
        this.baseRetain = baseRetain;
        this.upgRetain = upgRetain;
        this.selfRetain = baseRetain;
    }

//    protected void setSelfRevolving(boolean baseRevolving, boolean upgRevolving) {
//        this.baseRevolving = baseRevolving;
//        this.upgRevolving = upgRevolving;
//        this.selfRevolving = baseRevolving;
//    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        AbstractCard card = super.makeStatEquivalentCopy();

        if (card instanceof BaseCard)
        {
            card.rawDescription = this.rawDescription;
            ((BaseCard) card).upgradesDescription = this.upgradesDescription;

            ((BaseCard) card).baseCost = this.baseCost;

            ((BaseCard) card).upgradeCost = this.upgradeCost;
            ((BaseCard) card).upgradeDamage = this.upgradeDamage;
            ((BaseCard) card).upgradeBlock = this.upgradeBlock;
            ((BaseCard) card).upgradeMagic = this.upgradeMagic;

            ((BaseCard) card).upgradedSecondMagicNumber= this.upgradedSecondMagicNumber;

            ((BaseCard) card).costUpgrade = this.costUpgrade;
            ((BaseCard) card).damageUpgrade = this.damageUpgrade;
            ((BaseCard) card).blockUpgrade = this.blockUpgrade;
            ((BaseCard) card).magicUpgrade = this.magicUpgrade;

            ((BaseCard) card).secondMagicUpgrade = this.secondMagicUpgrade;

            ((BaseCard) card).baseExhaust = this.baseExhaust;
            ((BaseCard) card).upgExhaust = this.upgExhaust;
            ((BaseCard) card).baseEthereal = this.baseEthereal;
            ((BaseCard) card).upgEthereal = this.upgEthereal;
            ((BaseCard) card).baseInnate = this.baseInnate;
            ((BaseCard) card).upgInnate = this.upgInnate;
            ((BaseCard) card).baseRetain = this.baseRetain;
            ((BaseCard) card).upgRetain = this.upgRetain;
            ((BaseCard) card).renderInFullColor = this.renderInFullColor;
            ((BaseCard) card).cardLevel = this.cardLevel;
            //((BaseCard) card).setCardPreview(this.CardPreview);

//            ((BaseCard) card).revolvingCount = this.revolvingCount;
//            ((BaseCard) card).baseRevolving = this.baseRevolving;
//            ((BaseCard) card).upgRevolving = this.upgRevolving;
//
//            if (this.baseRevolving ^ this.upgRevolving) {
//                ((BaseCard) card).selfRevolving = this.upgRevolving;
//            }

        }

        return card;
    }


    public void downgrade() {
        if (upgraded) {
            upgraded = false;
            name = cardStrings.NAME;
            rawDescription = cardStrings.DESCRIPTION;
            initializeTitle();
            initializeDescription();
        }
    }


    @Override
    public void upgrade()
    {
        if (!upgraded)
        {
            this.upgradeName();

            if (this.upgradesDescription)
            {
                if (cardStrings.UPGRADE_DESCRIPTION == null)
                {
                    BasicMod.logger.error("Card " + cardID + " upgrades description and has null upgrade description.");
                }
                else
                {
                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                }
            }

            if (upgradeCost)
            {
                if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
                    int diff = this.costUpgrade - this.baseCost; //how the upgrade alters cost
                    this.upgradeBaseCost(this.cost + diff);
                    if (this.cost < 0)
                        this.cost = 0;
                }
                else {
                    upgradeBaseCost(costUpgrade);
                }
            }

            if (upgradeDamage)
                this.upgradeDamage(damageUpgrade);

            if (upgradeBlock)
                this.upgradeBlock(blockUpgrade);

            if (upgradeMagic)
                this.upgradeMagicNumber(magicUpgrade);

            if (upgradedSecondMagicNumber)
                this.upgradeMagicNumber(secondMagicUpgrade);

            if (baseExhaust ^ upgExhaust)
                this.exhaust = upgExhaust;

            if (baseInnate ^ upgInnate)
                this.isInnate = upgInnate;

            if (baseEthereal ^ upgEthereal)
                this.isEthereal = upgEthereal;

            if (baseRetain ^ upgRetain)
                this.selfRetain = upgRetain;

//            if (baseRevolving ^ upgRevolving)
//                this.selfRevolving = upgRevolving;


            this.initializeDescription();
        }
    }
}