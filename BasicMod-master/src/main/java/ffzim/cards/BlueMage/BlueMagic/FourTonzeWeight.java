package ffzim.cards.BlueMage.BlueMagic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class FourTonzeWeight extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FourTonzeWeight",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor); //changed from MyCharacter.Enums.CARD_COLOR

    public static final String ID = makeID(cardInfo.baseId);

    // Drops a 4-tonze weight dealing physical damage at a designated location with a potency of 200
    // for the first enemy, and 50% less for all remaining enemies.

    // Does X damage for each turn that has pased since combat start. Ending at a max after 4 turns.

    private static final int DAMAGE = 0;
    private static final int UPG_DAMAGE = 5;
    private static final int TURN_DAMAGE = 5;
    private static final int MAXDAMAGE = 30;
    private static final int UPG_MAXDAMAGE = 10;
    public FourTonzeWeight() {
        super(cardInfo, true);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(MAXDAMAGE,UPG_MAXDAMAGE);
        setExhaust(true);
        //setSelfRetain(true);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    //Set the words above being base don the damage it does.
//   AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new ShoutAction((AbstractCreature)this, "#r~" +

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (m != null) {
            playSfx();
            addToBot(new WaitAction(0.5F));
            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.BLUE.cpy())));
            addToBot(new WaitAction(0.8F));
            addToBot(new VFXAction(new GiantTextEffect(m.hb.cX, m.hb.cY)));
        }

        int currentTurn = AbstractDungeon.actionManager.turn;
        damage = Math.min((damage +(TURN_DAMAGE * currentTurn)), magicNumber);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }


    private void playSfx() {
        int roll = MathUtils.random(2);
        if (roll == 0) {
            AbstractDungeon.actionManager.addToBottom( new SFXAction("VO_GIANTHEAD_1A"));
        } else if (roll == 1) {
            AbstractDungeon.actionManager.addToBottom( new SFXAction("VO_GIANTHEAD_1B"));
        } else {
            AbstractDungeon.actionManager.addToBottom( new SFXAction("VO_GIANTHEAD_1C"));
        }
    }

        public AbstractCard makeCopy() {
        return new FourTonzeWeight();
    }
}
