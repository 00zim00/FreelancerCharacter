package ffzim.helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import ffzim.BasicMod;
import ffzim.util.CustomTags;
import ffzim.cards.variables.PlayerSaveable;

import java.util.ArrayList;
import java.util.Collections;

public class ChestItemRewards {
    private static int CommonChance;
    private static int UncommonChance;
    private static int RareChance;

    public static void getChestItemRewards(String chest) {
        AbstractPlayer p = AbstractDungeon.player;
        int counterNlothsMask = -2;
        if (p.hasRelic("NlothsMask") && !"BossChest".equals(BasicMod.cachedChest)) {
            AbstractRelic rel = AbstractDungeon.player.getRelic("NlothsMask");
            counterNlothsMask = rel.counter;
            System.out.println("HJmm: " + counterNlothsMask);
        }

        System.out.println("NlothsMask: " + counterNlothsMask);
        System.out.println("NlothsMask: " + !p.hasRelic("Busted Crown"));
        System.out.println("NlothsMask: " + (!p.hasRelic("Busted Crown") && counterNlothsMask <= 0) );

        if (!p.hasRelic("Busted Crown") && counterNlothsMask <= 0) {
            int luck = PlayerSaveable.luckPlus;
            //boolean hasQuestionCard = p.hasRelic("Question Card");
            //boolean hasSingingBowl = p.hasRelic("Singing Bowl");

            //boolean hasNlothsGift = p.hasRelic("N'loth's Gift");
            //boolean hasPrayerWheel = p.hasRelic("Prayer Wheel");

            int rngRoll = (int) (Math.random() * 100) + 1;
            int luckRoll = (int) (Math.random() * 100) + 1;
            ArrayList<AbstractCard> matchingCards = new ArrayList<>();
            RewardItem cardRewardItem = new RewardItem();
            cardRewardItem.cards.clear();


            if ("SmallChest".equals(BasicMod.cachedChest)) {
                CommonChance = 60;
                UncommonChance = 37;
                RareChance = 3;
            } else if ("MediumChest".equals(BasicMod.cachedChest)) {
                CommonChance = 50;
                UncommonChance = 40;
                RareChance = 10;
            } else if ("LargeChest".equals(BasicMod.cachedChest)) {
                CommonChance = 0;
                UncommonChance = 75;
                RareChance = 25;
            } else if ("BossChest".equals(BasicMod.cachedChest)) {
                CommonChance = 0;
                UncommonChance = 0;
                RareChance = 100;
            }

            if (p.hasRelic("Nloth's Gift")) {
                RareChance = RareChance * 3;
            }

            String thisRarity;
            if (rngRoll <= RareChance) {
                thisRarity = "Rare";
            } else if (rngRoll <= UncommonChance + RareChance) {
                thisRarity = "Uncommon";
                if (luck >= luckRoll) {
                    thisRarity = "Rare";
                }
            } else {
                thisRarity = "Common";
                if (luck >= luckRoll) {
                    thisRarity = "Uncommon";
                }
                int tmpLuck = (luck - 100);
                if (tmpLuck >= luckRoll) {
                    thisRarity = "Rare";
                }
            }

            for (AbstractCard card : CardLibrary.getAllCards()) {
                boolean isMatch = false;
                if (thisRarity.equals("Rare")) {
                    if ((card.hasTag(CustomTags.FFITEM) || card.hasTag(CustomTags.FFMATERIA)) && card.rarity == AbstractCard.CardRarity.RARE) {
                        isMatch = true;
                    }
                } else if (thisRarity.equals("Uncommon")) {
                    if ((card.hasTag(CustomTags.FFITEM) || card.hasTag(CustomTags.FFMATERIA)) && card.rarity == AbstractCard.CardRarity.UNCOMMON) {
                        isMatch = true;
                    }
                } else {
                    if ((card.hasTag(CustomTags.FFITEM) || card.hasTag(CustomTags.FFMATERIA)) && card.rarity == AbstractCard.CardRarity.COMMON) {
                        isMatch = true;
                    }
                }
                if (!matchingCards.isEmpty()) {
                    if (p.hasRelic("Sozu") && card.hasTag(CustomTags.FFPOTION)) {
                        isMatch = false;
                    }
                }
                if (!matchingCards.isEmpty()) {
                    if (card.isSeen && (card.hasTag(CustomTags.FFUNIQUE))) {
                        isMatch = false;
                    }
                }
                if (isMatch) {
                    matchingCards.add(card);
                }
            }

            int actNum = AbstractDungeon.actNum;
            int ascensionLevel = AbstractDungeon.ascensionLevel;
            float upgradeChance;
            float rngUpgrade = (float) (Math.random() * (100f - 0f + 1f));
            if (actNum == 1) {
                upgradeChance = 0f;
            } else if (actNum == 2) {
                upgradeChance = 25f;
                if (ascensionLevel >= 12) {
                    upgradeChance = 12.5f;
                    ;
                }
            } else {
                upgradeChance = 50f;
                if (ascensionLevel >= 12) {
                    upgradeChance = 25f;
                    ;
                }
            }

            if (!matchingCards.isEmpty()) {
                luckRoll = (int) (Math.random() * 100) + 1;
                if (p.hasRelic("Question Card") && matchingCards.size() >= 2) {
                    Collections.shuffle(matchingCards, AbstractDungeon.cardRng.random);
                    AbstractCard chosenCard1 = matchingCards.get(0);
                    AbstractCard chosenCard2 = matchingCards.get(1);
                    if (rngUpgrade >= upgradeChance) {
                        chosenCard1.upgrade();

                        rngUpgrade = (float) (Math.random() * (100f - 0f + 1f));
                        if (rngUpgrade >= upgradeChance) {
                            chosenCard2.upgrade();
                        } else if (luck >= luckRoll) {
                            chosenCard2.upgrade();
                        }
                    } else {
                        if (luck >= luckRoll) {
                            chosenCard1.upgrade();
                        }
                        luckRoll = (int) (Math.random() * 100) + 1;
                        if (luck >= luckRoll) {
                            chosenCard2.upgrade();
                        }
                    }
                    cardRewardItem.cards.add(chosenCard1.makeCopy());
                    cardRewardItem.cards.add(chosenCard2.makeCopy());
                } else {
                    AbstractCard chosenCard3 = matchingCards.get(AbstractDungeon.cardRng.random(matchingCards.size() - 1));
                    if (rngUpgrade >= upgradeChance) {
                        chosenCard3.upgrade();
                    } else if (luck >= luckRoll) {
                        chosenCard3.upgrade();
                    }
                    cardRewardItem.cards.add(chosenCard3.makeCopy());
                }


                AbstractDungeon.getCurrRoom().rewards.add(cardRewardItem);
            }
        }
    }
}