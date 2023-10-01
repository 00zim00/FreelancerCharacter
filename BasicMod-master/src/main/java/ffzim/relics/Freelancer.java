package ffzim.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import com.megacrit.cardcrawl.ui.campfire.LiftOption;
import ffzim.BasicMod;
import ffzim.cards.Generic.ItemCards.Excalibur;
import ffzim.stances.LimitGaugePower;
import ffzim.helpers.ChestItemRewards;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;

public class Freelancer extends BaseRelic
{
    public static final String ID = makeID("Freelancer");

    private static int TRANCE_COUNTER = 0;

    private boolean jobBlackMage;

    public Freelancer() {
        super(ID, "Freelancer", RelicTier.SPECIAL, BaseRelic.LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStart() {
        TRANCE_COUNTER = this.counter;
        AbstractPlayer p = AbstractDungeon.player;
        flash();
        addToTop(new ApplyPowerAction(p, p,new LimitGaugePower(p, TRANCE_COUNTER), TRANCE_COUNTER));

//        if (jobBlackMage = true) {
//            addToTop(new ApplyPowerAction(p, p,new TranceBlackMagePower(p, 0), 0));
//        }
    }

    public void onVictory() {
        LimitGaugePower limitGaugePower = (LimitGaugePower) AbstractDungeon.player.getPower(LimitGaugePower.POWER_ID);
        if (limitGaugePower != null) {
            TRANCE_COUNTER = limitGaugePower.amount;
            this.counter = TRANCE_COUNTER;
        }
    }

    public void addCampfireOption(ArrayList<AbstractCampfireOption> options) {
        options.add(new LiftOption((this.counter < 3)));
    }
    public void onObtainCard(AbstractCard card) {
        if (card.cardID.equals(makeID("BlackMage"))) {
            imageName = "BlackMage";
            jobBlackMage = true;
        }
    }

    public void onEnterRoom(AbstractRoom room) {
        if (AbstractDungeon.player.masterDeck.contains(new Excalibur()) && room instanceof com.megacrit.cardcrawl.rooms.RestRoom && AbstractDungeon.actNum == 4 && CardCrawlGame.playtime <= 720) {

        }else if (room instanceof com.megacrit.cardcrawl.rooms.RestRoom && AbstractDungeon.actNum == 4 && CardCrawlGame.playtime <= 720) {

        }
    }

    public void onChestOpen(boolean bossChest) {
        //String className;
        String className = getClass().getSimpleName();
        System.out.println("Chest Class Name: " + className);
        System.out.println("result1: " + BasicMod.cachedChest);
        flash();

        ChestItemRewards.getChestItemRewards(className);

    }
//        if ("SmallChest".equals(BasicMod.cachedChest)) {
//            // Your code for SmallChest
//        } else if ("MediumChest".equals(BasicMod.cachedChest)) {
//            // Your code for LargeChest
//        } else if ("LargeChest".equals(BasicMod.cachedChest)) {
//            // Your code for AnotherChestType
//        } else if ("BossChest".equals(BasicMod.cachedChest)) {
//            // Code for other cases or default behavior
//        }
//
//
//        System.out.println("Open Chest: " );
//        AbstractPlayer p = AbstractDungeon.player;
//        ArrayList<AbstractCard> matchingCards = new ArrayList<>();
//        RewardItem cardRewardItem = new RewardItem();
//        cardRewardItem.cards.clear();
//        System.out.println("Open Chest: " + !bossChest);
//        if (!bossChest) {
//            flash();
//            addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//            //float chance = Math.max(0.75F- BaseCard.LuckPlus,0.0F);
//            if (AbstractDungeon.cardRng.randomBoolean(0.75F)) {
//                for (AbstractCard card : CardLibrary.getAllCards()) {
//                    if ((card.hasTag(CustomTags.FFITEM) || card.hasTag(CustomTags.FFMATERIA)) && card.rarity == AbstractCard.CardRarity.COMMON) {
//                        matchingCards.add(card);
//                    }
//                    if (p.hasRelic("Sozu") && card.hasTag(CustomTags.FFPOTION)) {
//                        matchingCards.remove(card);
//                    }
//                }
//            } else {
//                for (AbstractCard card : CardLibrary.getAllCards()) {
//                    if ((card.hasTag(CustomTags.FFITEM) || card.hasTag(CustomTags.FFMATERIA)) && card.rarity == AbstractCard.CardRarity.UNCOMMON) {
//                        matchingCards.add(card);
//                    }
//                    if (p.hasRelic("Sozu") && card.hasTag(CustomTags.FFPOTION)) {
//                        matchingCards.remove(card);
//                    }
//                }
//            }
//            } else {
//                for (AbstractCard card : CardLibrary.getAllCards()) {
//                    if ((card.hasTag(CustomTags.FFITEM) || card.hasTag(CustomTags.FFMATERIA)) && card.rarity == AbstractCard.CardRarity.RARE || card.rarity == AbstractCard.CardRarity.SPECIAL ) {
//                        if (card.rarity == AbstractCard.CardRarity.SPECIAL){
//                            card.rarity = AbstractCard.CardRarity.RARE;
//                        }
//                        matchingCards.add(card);
//                        if (card.isSeen && (card.hasTag(CustomTags.FFUNIQUE))){
//                            matchingCards.remove(card);
//                        }
//                        if (p.hasRelic("Sozu") && card.hasTag(CustomTags.FFPOTION)) {
//                            matchingCards.remove(card);
//                        }
//                    }
//                }
//            }
//        System.out.println("Open Chest: " + matchingCards);
//        if (!matchingCards.isEmpty()) {
//            AbstractCard chosenCard = matchingCards.get(AbstractDungeon.cardRng.random(matchingCards.size() - 1));
//            cardRewardItem.cards.add(chosenCard.makeCopy());
//            AbstractDungeon.getCurrRoom().rewards.add(cardRewardItem);
//        }
//    }


    public BaseRelic makeCopy() {
        return new Freelancer();
    }
}
