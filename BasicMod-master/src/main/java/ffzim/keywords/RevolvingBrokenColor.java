//package ffzim.keywords;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.helpers.CardLibrary;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.BasicMod;
//import ffzim.cards.BaseCard;
//import ffzim.util.CardInfo;
//
//public abstract class RevolvingBrokenColor extends BaseCard {
//
//    protected int revolvingCount = -2;
//    private int initialRevolvingCount = -2;
//    private String cardToAdd;
//    private String cardInfo;
//    public int cardToAddRevolvingCount = 0;
//    private boolean revUpgraded;
//    private boolean cardInfo2;
//    protected boolean baseRevolving = false;
//    protected boolean upgRevolving = false;
//    protected boolean selfRevolving = false;
//
//    private AbstractCard myPrivateCard;
//
//    //private boolean makeRevolingPreview = false;
//
//
//    public RevolvingBrokenColor(CardInfo cardInfo, boolean upgradesDescription) {
//        super(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType,  cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor, upgradesDescription);
//        this.cardToAddRevolvingCount = -2;
//        //this.makeRevolingPreview = makePreview;
//    }
//
////    public Revolving() {
////        super(makeID("Draw"), -1, CardType.SKILL, CardTarget.SELF, CardRarity.BASIC, CardColor.RED, false);
////    }
//
////    public Revolving(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
////        super(cardID, cost, type, target,rarity, MyCharacter.Enums.FFcardColor);
//
//
//
////    public RevolvingKeyword(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
////        super(new CardInfo(cardID, cost, type, target, rarity, color), "");
////    }
//
////    public RevolvingKeyword(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
////        this(new CardInfo(cardID, cost, type, target, rarity, MyCharacter.Enums.FFcardColor));
////    }
//
//
//
//
//    protected final void setRevolving(AbstractCard card, int maxRevolving) {
//        this.cardToAdd = card.cardID;
//        this.revUpgraded= card.upgraded;
//        //this.cardInfo2 = card.cardsToPreview;
//        //this.cardInfo = card;
//        //this.cardInfo = card.cardsToPreview.toString();
//        //this.cardInfo2 = card.cardsToPreview.
//        this.revolvingCount = maxRevolving;
//        this.initialRevolvingCount = maxRevolving;
//        if (revolvingCount == 0) {setCostForTurn(-2);}
//
//    }
//    public int getRevolvingCount() {
//        return revolvingCount;
//    }
//    @Override
//    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
//        //if (isRevolvingEnabled() && revolvingCount > 0) {
//        if (revolvingCount > 0) {
//            // Decrease the revolvingCount by 1
//            revolvingCount -= 1;
//            System.out.println("revolvingCount: " + revolvingCount);
//            if (revolvingCount > 0) {
//                RevolvingBrokenColor cardToAdd = (RevolvingBrokenColor) CardLibrary.getCard(cardID).makeStatEquivalentCopy();
//                cardToAdd.revolvingCount = revolvingCount - 1;
//                if (revUpgraded) {cardToAdd.upgrade();}
//                cardToAdd.initializeDescription();
//                //PurgeField.purge.set(cardToAdd, true);
//                System.out.println("Card Made Revole" + (revolvingCount-1));
//
//                //addToBot(new MakeTempCardInHandAction(cardToAdd, 1, false));
//
//            }
//            addToBot(new AbstractGameAction() {
//                @Override
//                public void update() {
//                    if (revolvingCount == 0) {
//                        setCostForTurn(-2);
//                        applyPowers();
//                        isDone = true;
//                    }else {
//                        isDone = true;
//                    }
//                }
//            });
//
//        }
//    }
//
//
//
//    @Override
//    public void triggerAtStartOfTurn() {
//        // Reset the revolvingCount to its initial value
//        revolvingCount = initialRevolvingCount;
//        //isRevolvingEnabled();
//    }
//
//
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        System.out.println("canUse method called");
//        return !(revolvingCount == 0 && super.canUse(p, m));
//    }
//
////    @Override
////    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
////        if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
////            return false;
////        } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
////            return false;
////        } else if (getRevolvingCount() == 0) {
////            return false;
////        } else {
////            return this.cardPlayable(m) && this.hasEnoughEnergy();
////        }
////    }
//
//
//
//
//
//    protected final void setSelfRevolving(boolean revolving) {this.setSelfRevolving(revolving, revolving); }
//
//    protected void setSelfRevolving(boolean baseRevolving, boolean upgRevolving) {
//        this.baseRevolving = baseRevolving;
//        this.upgRevolving = upgRevolving;
//        this.selfRevolving = baseRevolving;
//    }
//
//
//    @Override
//    public AbstractCard makeStatEquivalentCopy() {
//        AbstractCard card = super.makeStatEquivalentCopy();
//
//        if (card instanceof RevolvingBrokenColor)
//        {
//            ((RevolvingBrokenColor) card).revolvingCount = this.revolvingCount;
//            ((RevolvingBrokenColor) card).baseRevolving = this.baseRevolving;
//            ((RevolvingBrokenColor) card).upgRevolving = this.upgRevolving;
//            ((RevolvingBrokenColor) card).cardToAddRevolvingCount = this.cardToAddRevolvingCount;
//            //((Revolving) card).makeRevolingPreview = false;
//
//            if (this.baseRevolving ^ this.upgRevolving) {
//                ((RevolvingBrokenColor) card).selfRevolving = this.upgRevolving;
//            }
//
//        }
//
//        return card;
//    }
//
//
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
//            if (baseRevolving ^ upgRevolving)
//                this.selfRevolving = upgRevolving;
//
//
//            this.initializeDescription();
//        }
//    }
//
//
////    @Override
////    public void renderCardTip(SpriteBatch sb) {
////        if (this.cardsToPreview != null && this.cardsToPreview instanceof Revolving) {
////            Revolving revolvingCard = (Revolving) this.cardsToPreview.makeCopy();
////            // Handle any specific modifications or settings for previewing Revolving cards
////            //revolvingCard.setRevolving(initialRevolvingCount);
////            revolvingCard.makePreview = false; // Disable preview for the preview card itself
////            revolvingCard.makeRevolingPreview = true; // Set the flag indicating it's being copied for preview
////            revolvingCard.initializeDescription();
////            TipHelper.renderTipForCard(revolvingCard, sb, revolvingCard.keywords);
////        } else {
////            super.renderCardTip(sb);
////        }
////    }
//
//
//}
