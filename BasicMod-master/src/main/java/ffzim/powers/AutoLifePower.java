package ffzim.powers;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;

import static ffzim.BasicMod.makeID;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.UUID;

public class AutoLifePower extends BasePower implements OnPlayerDeathPower {
    public static final String POWER_ID = makeID("AutoLifePower");

    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;

//    private static final Texture tex84 = TextureLoader.getTexture(expansionContentMod.getModID() + "Resources/images/powers/AwakenDeath84.png");
//    private static final Texture tex32 = TextureLoader.getTexture(expansionContentMod.getModID() + "Resources/images/powers/AwakenDeath32.png");
    private final int HealValue;

    private AbstractCard thisCard;

    // private static final float healingPercentage = 0.3f;
    //public AutoLifePower(AbstractCreature owner, AbstractCreature source, int amount) {
    public AutoLifePower(AbstractCreature owner,AbstractCard thisCard, int uses, int healvalue, int stack) {
        super(POWER_ID, TYPE, TURN_BASED, owner, healvalue);;
        this.owner = owner;
        this.amount = stack;
        this.HealValue = healvalue;
        this.thisCard = thisCard;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;
        updateDescription();
        //loadRegion("regen");
//        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
//        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

    }

    @Override
    public boolean onPlayerDeath(AbstractPlayer abstractPlayer, DamageInfo damageInfo) {
        trigger(abstractPlayer);
        return false;
    }

    public void trigger(AbstractPlayer abstractPlayer) {
        addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));
        AbstractDungeon.actionManager.addToTop(new HealAction(abstractPlayer, abstractPlayer, this.HealValue));
        AbstractDungeon.actionManager.addToTop(new VFXAction(this.owner, new IntenseZoomEffect(this.owner.hb.cX, this.owner.hb.cY, true), 0.05F, true));
        AbstractDungeon.actionManager.addToTop(new SFXAction("VO_AWAKENEDONE_1"));
        System.out.println("thisCard: " + thisCard);
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.uuid.equals(thisCard.uuid)) {
                AbstractDungeon.player.masterDeck.removeCard(card);
                break;
            }
        }


//        UUID thisUUID = thisCard.uuid;
//        AbstractDungeon.player.masterDeck.removeCard(thisCard);
    }


//        String removeCardID = thisCard.cardID;
//        boolean thisUpgraded = thisCard.upgraded;
//        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
//            if (card.cardID.equals(removeCardID) && card.upgraded == thisUpgraded) {
//                AbstractDungeon.player.masterDeck.removeCard(card);
//                break;
//            } else {
//                AbstractDungeon.player.masterDeck.removeCard(removeCardID);
//                break;
//            }
 //       }
//    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8.0F;
        this.amount -= reduceAmount;
        if (this.amount <= 0)
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + (this.HealValue) + DESCRIPTIONS[1];
    }
}