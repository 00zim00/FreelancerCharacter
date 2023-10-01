//package ffzim.cardModifiers;
//
//import basemod.abstracts.AbstractCardModifier;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import ffzim.cards.BlueMage.BlueMagic.AutoLife;
//
//import static basemod.helpers.CardModifierManager.addModifier;
//import static ffzim.BasicMod.makeID;
//
//public class AutoCardMod extends AbstractCardModifier {
//
//    private static final String AutoCard_ID = makeID("AutoCardMod");
//    private int activeNeeded;
//    public final int uses;
//    public final int upg_Uses;
//    public AbstractCard origCard;
//    public boolean isActive = false;
//    public AutoCardMod(AbstractCard origCard,int USES,int UPG_USES,int ACTIVE_NEEDED) {
//        super();
//        this.origCard = origCard;
//        this.activeNeeded = ACTIVE_NEEDED;
//        this.uses = USES;
//        this.upg_Uses = UPG_USES;
//    }
//
//    @Override
//    public boolean isInherent(AbstractCard card) {
//        return true;
//    }
//
////    @Override
////    public String modifyName(String rawDescription, AbstractCard card) {
////        return "[#c639de]" + card.name;
////    }
//
//    @Override
//    public String modifyDescription(String rawDescription, AbstractCard card) {
//        if (!isActive) {
//            return makeID("Support_Ability") + ": " + activeNeeded + ". NL " + rawDescription;
//        }
//        return rawDescription;
//    }
//
//    @Override
//    public void onInitialApplication(AbstractCard card) {
////            card.purgeOnUse = true;
////            card.isEthereal = true;
////            card.tags.add(CustomTags.FFSHADOW);
//        card.initializeDescription();
////            card.applyPowers();
//    }
//
//    @Override
//    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
//
//    }
//
//    public int getActiveNeeded() {
//        return activeNeeded;
//    }
//
//    @Override
//    public AbstractCardModifier makeCopy() {
//        return new AutoCardMod(origCard,uses,upg_Uses,activeNeeded);
//    }
//}