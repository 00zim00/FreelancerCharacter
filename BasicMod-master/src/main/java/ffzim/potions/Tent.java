package ffzim.potions;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Tent extends AbstractPotion {
    public static final String POTION_ID = "BloodPotion";

    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString("BloodPotion");

    public Tent() {
        super(potionStrings.NAME, "BloodPotion", AbstractPotion.PotionRarity.COMMON, AbstractPotion.PotionSize.H, AbstractPotion.PotionColor.WHITE);
        this.labOutlineColor = Settings.RED_RELIC_COLOR;
        this.isThrown = false;
    }

    public void initializeData() {
        this.potency = getPotency();
        this.description = potionStrings.DESCRIPTIONS[0] + this.potency + potionStrings.DESCRIPTIONS[1];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
    }

    public void use(AbstractCreature target) {
        (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Colosseum Slavers");
        (AbstractDungeon.getCurrRoom()).rewards.clear();
        (AbstractDungeon.getCurrRoom()).rewardAllowed = false;
        AbstractDungeon.lastCombatMetricKey = "Colosseum Slavers";
    }

    public boolean canUse() {
        (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Colosseum Slavers");
        (AbstractDungeon.getCurrRoom()).rewards.clear();
        (AbstractDungeon.getCurrRoom()).rewardAllowed = false;
        AbstractDungeon.lastCombatMetricKey = "Colosseum Slavers";
        return true;
    }

    public int getPotency(int ascensionLevel) {
        return 20;
    }


//    public void use(AbstractCreature target) {
//        (AbstractDungeon.getCurrRoom()).monsters = MonsterHelper.getEncounter("Colosseum Slavers");
//        (AbstractDungeon.getCurrRoom()).rewards.clear();
//        (AbstractDungeon.getCurrRoom()).rewardAllowed = false;
//        AbstractDungeon.lastCombatMetricKey = "Colosseum Slavers";
        //break;



        //AbstractDungeon.getCurrRoom().event = new CampfireUI();

        //        if (BaseMod.isOnMapScreen) {
//            // If on the map screen, trigger the CampfireAction
//            AbstractDungeon.actionManager.addToBottom(new CampfireUI());
//        } else {
//            // If not on the map screen, use the potion as usual
//            // (You can add additional effects or actions here)
//            super.use(target);
//        }
//    }


    public AbstractPotion makeCopy() {
        return new Tent();
    }
}
