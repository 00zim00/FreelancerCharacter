//package ffzim.powers;
//
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.actions.utility.UseCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import ffzim.cards.BlackMage.Haste;
//import ffzim.cards.BlackMage.Slow;
//import ffzim.cards.BlueMage.BlueMagic.AutoLife;
//import ffzim.cards.Generic.MagicStone;
//
//import java.util.ArrayList;
//
//import static basemod.BaseModInit.DESCRIPTION;
//import static ffzim.BasicMod.makeID;
//
//public class MagicStonePower extends BasePower {
//
//    public static final String POWER_ID = makeID("MagicStonePower");
//    private int magicStones;
//    private static int MAGIC_STONES_COUNTER;
//    private static final boolean AutoLife = false;
//    private static final boolean AutoFloat = false;
//    private static final boolean AutoRegan = false;
//    private static final boolean AutoCounter = false;
//    private boolean chosen = false;
//    private final AbstractCard FirstCard;
//    public MagicStonePower(AbstractCreature owner,AbstractCard card, int Amount, int MagicStones) {
//        super(POWER_ID, PowerType.BUFF,false, owner, Amount);
//        this.owner = owner;
//        this.amount = Amount;
//        this.magicStones = MagicStones;
//        this.FirstCard = card;
//        updateDescription();
//    }
//
//    @Override
//    public void onInitialApplication() {
//        MAGIC_STONES_COUNTER += magicStones;
//        AbstractCard cardToAdd = new MagicStone();
//        if (FirstCard.upgraded) {cardToAdd.upgrade();}
//        addToBot(new MakeTempCardInDrawPileAction(cardToAdd, this.magicStones, false, true));
//
//        if (owner.hasPower(AstralIcePower.POWER_ID)) {
//            addToBot( new RemoveSpecificPowerAction(this.owner, this.owner, AstralIcePower.POWER_ID));
//        }
//    }
//
//    public void onUseCard(AbstractCard card, UseCardAction action) {
//        if (card.cardID == "MagicStone") {
//            flash();
//            MAGIC_STONES_COUNTER += 1;
//            addToTop( new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
//
//            if (MAGIC_STONES_COUNTER == 0){
//
//            }
//            ArrayList<AbstractCard> cards = new ArrayList<>();
//            if (AutoLife){
//                cards.add(new AutoLife());
//            }
//                AbstractDungeon.cardRewardScreen.customCombatOpen(cards, DESCRIPTION, false);                tickDuration();
//            } else if (!chosen) {
//                AbstractCard selectedCard = AbstractDungeon.cardRewardScreen.discoveryCard;
//                if (selectedCard != null) {
//                    chosen = true;
//                    addToTop(new MakeTempCardInHandAction(selectedCard));
//                }
//
//        }
//    }
//
//    public void stackPower(AbstractCard newcard, int stackAmount, int MagicStones) {
//        //this.fontScale = 8.0F;
//        //this.amount += stackAmount;
//
//        MagicStonePower newPower = new MagicStonePower(owner, newcard, stackAmount, MagicStones);
//
//        addToBot(new ApplyPowerAction(owner, owner,newPower));
//    }
//
//
//
//
//    public void updateDescription() {
//        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
//    }
//}