//package ffzim.keywords;
//
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import ffzim.BasicMod;
//import ffzim.cards.BaseCard;
//import ffzim.util.CardInfo;
//
//public abstract class Revolving extends BaseCard {
//
//    public int revolvingCount = -2;
//    private int initialRevolvingCount = -2;
//    //private String cardToAdd;
//
//    //private AbstractCard cardToAdd;
//
//    //
//    public int cardToAddRevolvingCount = -2;
//    //
//    private boolean revUpgraded;
//    protected boolean baseRevolving = false;
//    protected boolean upgRevolving = false;
//    protected boolean selfRevolving = false;
//    //private final String modID = "ffzim";
//
//    //private AbstractCard myCard;
//    // Card name
//
//    private AbstractCard myAbstractCard;
//
//
//
//
//
//    //private boolean makeRevolingPreview = false;
//
////   public RevolvingKeyword(CardInfo cardInfo) {
////       this(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardRarity, cardInfo.cardTarget, cardInfo.cardColor);
////   }
//
//
////    public Revolving(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
////        super(cardID, cost, type, target, rarity, color);
////
////    }
////
////    public Revolving(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
////        this(cardID, cost, type, rarity, target, MyCharacter.Enums.FFcardColor);
////    }
//
//
//
// //   working
//    public Revolving(CardInfo cardInfo, boolean upgradesDescription) {
//        super(cardInfo.baseId, cardInfo.baseCost, cardInfo.cardType, cardInfo.cardTarget, cardInfo.cardRarity, cardInfo.cardColor, upgradesDescription);
//    }
////working
//
////
////
////   public RevolvingKeyword(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
////       super(new CardInfo(cardID, cost, type, target, rarity, color), "");
////   }
////
////   public RevolvingKeyword(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
////       this(new CardInfo(cardID, cost, type, target, rarity, MyCharacter.Enums.FFcardColor));
////   }
////
////
////
////
//    protected final void setRevolving(int maxRevolving) {
//        //String tempID = ":" + newCardName;
//        //myCard = makeID(newCardName);
//        //myCard = newCardName;
//        //myCard = "new " + newCardName + "()";
//        //String cardID = cardName;
//        //this.cardToAdd = CardLibrary.getCard(cardID);
//        //myCard = CardLibrary.getCard(cardName);
//        //String myCard = BasicMod.makeID("Undraw"); // Replace "Undraw" with the correct card ID
//        //myCard = card.cardID;
//        //this.cardToAdd = card.cardID;
//
//
//        System.out.println("Lib Card2: " + BasicMod.makeID("Undraw"));
//        //this.revUpgraded = UpgradeMe;
//        this.revolvingCount = maxRevolving;
//        this.initialRevolvingCount = maxRevolving;
//        if (revolvingCount == 0) {
//            //setCostForTurn(-2);
//        }
//    }
//
//    public int getRevolvingCount() {return this.revolvingCount;
//    }
//
//
//    //@Override
////    public void triggerOnCardPlayed(AbstractCard cardPlayed) {
////        //if (isRevolvingEnabled() && revolvingCount > 0) {
////            // Decrease the revolvingCount by 1
////            if (revolvingCount >= 1) {
////                revolvingCount -= 1;
////                PurgeField.purge.set(this, true);
////                System.out.println("revolvingCount: " + revolvingCount);
////                //Revolving cardToAdd = (Revolving) CardLibrary.getCard(cardID).makeStatEquivalentCopy();
////                System.out.println("Test1: " + revolvingCount);
////                System.out.println("Test2: " + myCard);
////                Revolving cardToAdd = (Revolving) CardLibrary.cards.get(myCard);
////                //myAbstractCard = myCard;
////                System.out.println("Test2.5: " + myCard);
////                System.out.println("Test3: " + cardToAdd);
////                System.out.println("Test4: " + this.revolvingCount);
////                System.out.println("Test4: " + cardToAdd.revolvingCount);
////                cardToAdd.revolvingCount = revolvingCount;
////                if (revUpgraded) {
////                    cardToAdd.upgrade();
////                }
////                System.out.println("Test5: " + cardToAdd);
////                //cardToAdd.renderCardPreview().add(delayedDescriptionModifier);
////                cardToAdd.initializeDescription();
////                //PurgeField.purge.set(cardToAdd, true);
////                System.out.println("Card Made Revole: " + (revolvingCount - 1));
////                addToBot(new MakeTempCardInHandAction(cardToAdd, 1, false));
////                return;
////
////            }
//////            addToBot(new AbstractGameAction() {
//////                @Override
//////                public void update() {
//////                    if (revolvingCount == 0) {
//////                        setCostForTurn(-2);
//////                        applyPowers();
//////                        isDone = true;
//////                    } else {
//////                        isDone = true;
//////                    }
//////                }
//////            });
////    }
//
//    @Override
//    public void triggerAtStartOfTurn() {
//        // DOES NOTHING, NEVR DID
//        // Reset the revolvingCount to its initial value
//        System.out.println("Turn Start1: " + revolvingCount);
//        System.out.println("Turn Start2: " + initialRevolvingCount);
//        revolvingCount = initialRevolvingCount;
//        System.out.println("Turn Start3: " + revolvingCount);
//        System.out.println("Turn Start4: " + initialRevolvingCount);
//        //isRevolvingEnabled();
//    }
//
//    @Override
//    public void triggerWhenCopied() {
//        //THis works, tho the des isnt updating
//        // Reset the revolvingCount to its initial value
//        System.out.println("initialize Des: " + revolvingCount);
//        setMagic(revolvingCount);
//        setSecondMagic(revolvingCount);
//        //this.initializeDescriptionCN();
//        //initializeDescriptionCN();
//        super.initializeDescription();
//    }
//
//    // BAD BREAKING!!
////    @Override
////    public void triggerOnEndOfTurnForPlayingCard() {
////        // Reset the revolvingCount to its initial value
////        System.out.println("TurnForPlayingCard " + revolvingCount);
////        System.out.println("TurnForPlayingCard: " + initialRevolvingCount);
////        revolvingCount = initialRevolvingCount;
////    THis?    System.out.println("TurnForPlayingCard: " + revolvingCount);
////        System.out.println("TurnForPlayingCard: " + initialRevolvingCount);
////        //isRevolvingEnabled();
////    }
//
//    @Override
//    public void atTurnStart() {
//        // Reset the revolvingCount to its initial value
//        // THIS IS CORRECT, WORKS OVER TRIGGER/
//        System.out.println("atTurnStart: " + revolvingCount);
//        System.out.println("atTurnStart: " + initialRevolvingCount);
//        revolvingCount = initialRevolvingCount;
//        System.out.println("atTurnStart: " + revolvingCount);
//        System.out.println("atTurnStart: " + initialRevolvingCount);
//        //isRevolvingEnabled();
//    }
//
//    @Override
//    public void atTurnStartPreDraw() {
//        // Reset the revolvingCount to its initial value
//        // THIS IS CORRECT, WORKS OVER TRIGGER!
//        System.out.println("atTurnStartPreDraw: " + revolvingCount);
//        System.out.println("atTurnStartPreDraw2: " + initialRevolvingCount);
//        revolvingCount = initialRevolvingCount;
//        setMagic(revolvingCount);
//        setSecondMagic(revolvingCount);
//        //this.initializeDescriptionCN();
//        //initializeDescriptionCN();
//        super.initializeDescription();
//        System.out.println("atTurnStartPreDraw: " + revolvingCount);
//        System.out.println("atTurnStartPreDraw: " + initialRevolvingCount);
//        //isRevolvingEnabled();
//    }
//
//    @Override
//    public void onRetained() {
//        // Reset the revolvingCount to its initial value
//        // THIS IS CORRECT, WORKS OVER TRIGGER!
//        System.out.println("nRetained(: " + revolvingCount);
//        System.out.println("nRetained(2: " + initialRevolvingCount);
//        revolvingCount = initialRevolvingCount;
//        System.out.println("nRetained(3: " + revolvingCount);
//        System.out.println("nRetained(4: " + initialRevolvingCount);
//        //isRevolvingEnabled();
//    }
//
//
//    @Override
//    public void triggerWhenDrawn() {
//        // DOES NOTHING
//        super.triggerWhenDrawn();
//        setSecondMagic(revolvingCount);
//        System.out.println("Trigger Count Update: " + revolvingCount);
//        initializeDescription();
//    }
//
////        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
////            if !(revolvingCount == 0) {
////                return false;
////            } else{
////                return true;
////            }
////        }
//
//
//    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
//        System.out.println("canUse method called: " + revolvingCount);
//        //return !(revolvingCount == 0 && super.canUse(p, m));
//        return !(revolvingCount == 0);
//    }
//
////
////   @Override
////   public boolean canUse(AbstractPlayer p, AbstractMonster m) {
////       if (this.type == AbstractCard.CardType.STATUS && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Medical Kit")) {
////           return false;
////       } else if (this.type == AbstractCard.CardType.CURSE && this.costForTurn < -1 && !AbstractDungeon.player.hasRelic("Blue Candle")) {
////           return false;
////       } else if (getRevolvingCount() == 0) {
////           return false;
////       } else {
////           return this.cardPlayable(m) && this.hasEnoughEnergy();
////       }
////   }
//
//    protected final void setSelfRevolving(boolean revolving) {
//        this.setSelfRevolving(revolving, revolving);
//    }
//
//    protected void setSelfRevolving(boolean baseRevolving, boolean upgRevolving) {
//        this.baseRevolving = baseRevolving;
//        this.upgRevolving = upgRevolving;
//        this.selfRevolving = baseRevolving;
//    }
//
//    @Override
//    public AbstractCard makeStatEquivalentCopy() {
//        AbstractCard card = super.makeStatEquivalentCopy();
//
//        if (card instanceof Revolving) {
//            ((Revolving) card).revolvingCount = this.revolvingCount;
//            ((Revolving) card).baseRevolving = this.baseRevolving;
//            ((Revolving) card).upgRevolving = this.upgRevolving;
//            ((Revolving) card).cardToAddRevolvingCount = this.cardToAddRevolvingCount;
//            //((Revolving) card).makeRevolingPreview = false;
//
//            if (this.baseRevolving ^ this.upgRevolving) {
//                ((Revolving) card).selfRevolving = this.upgRevolving;
//            }
//
//        }
//
//        return card;
//    }
//
//
//    @Override
//    public void upgrade() {
//        if (!upgraded) {
//            this.upgradeName();
//
//            if (this.upgradesDescription) {
//                if (cardStrings.UPGRADE_DESCRIPTION == null) {
//                    BasicMod.logger.error("Card " + cardID + " upgrades description and has null upgrade description.");
//                } else {
//                    this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//                }
//            }
//
//            if (upgradeCost) {
//                if (isCostModified && this.cost < this.baseCost && this.cost >= 0) {
//                    int diff = this.costUpgrade - this.baseCost; //how the upgrade alters cost
//                    this.upgradeBaseCost(this.cost + diff);
//                    if (this.cost < 0)
//                        this.cost = 0;
//                } else {
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
//}
//
//
//////    @Override
//////    public void renderCardTip(SpriteBatch sb) {
//////        if (this.cardsToPreview != null && this.cardsToPreview instanceof Revolving) {
//////            Revolving revolvingCard = (Revolving) this.cardsToPreview.makeCopy();
//////            // Handle any specific modifications or settings for previewing Revolving cards
//////            //revolvingCard.setRevolving(initialRevolvingCount);
//////            revolvingCard.makePreview = false; // Disable preview for the preview card itself
//////            revolvingCard.makeRevolingPreview = true; // Set the flag indicating it's being copied for preview
//////            revolvingCard.initializeDescription();
//////            TipHelper.renderTipForCard(revolvingCard, sb, revolvingCard.keywords);
//////        } else {
//////            super.renderCardTip(sb);
//////        }
//////    }
////
////
////}
