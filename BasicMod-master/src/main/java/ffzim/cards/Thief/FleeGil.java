package ffzim.cards.Thief;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import com.megacrit.cardcrawl.vfx.TextAboveCreatureEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

import ffzim.actions.CustomWaitAction;
import ffzim.cards.BaseCard;

import ffzim.character.Freelancer;
import ffzim.powers.EvasionPower;
import ffzim.util.CardInfo;



import static ffzim.BasicMod.makeID;

public class FleeGil extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FleeGil",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int FLEE_CHANCE = 20;
    private static final int UPG_FLEE_CHANCE = 30;
    private static final int GOLD_MIN = 5;
    private static final int GOLD_MAX = 10;

    private int EscapeChance;
    public FleeGil() {
        super(cardInfo, true);
        setMagic(GOLD_MIN, GOLD_MAX);
        setSecondMagic(FLEE_CHANCE,UPG_FLEE_CHANCE);
        setExhaust(true);
        //this.setBackgroundTexture("resources/ffzim/character/cardback/bg_attack_p.png", "resources/ffzim/character/cardback/bg_attack.png");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        float dur = Settings.FAST_MODE ? 0.3f : 1.0f;
        Random random = new Random();
        int randomNumber = random.random.nextInt(100 - 1 + 1) + 1;
        if (p.hasPower(EvasionPower.POWER_ID)) {
            AbstractPower evasionPower = AbstractDungeon.player.getPower(EvasionPower.POWER_ID);
            EscapeChance = evasionPower.amount + secondMagicNumber;
            //evasionPower.CheckJump();
        } else {
            EscapeChance = secondMagicNumber;
        }

        if (!AbstractDungeon.getCurrRoom().eliteTrigger &&
                AbstractDungeon.getCurrRoom().monsters.monsters.stream().noneMatch(monster ->
                        monster.type == AbstractMonster.EnemyType.BOSS)) {
            if ((EscapeChance) >= randomNumber) {
                if ((AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT) {
                    random = new Random(); // Use the default Random class
                    int goldToAdd = random.random.nextInt(GOLD_MAX - GOLD_MIN + 1) + GOLD_MIN;
                    p.gainGold(goldToAdd);
                    AbstractDungeon.effectList.add(new GainGoldTextEffect(goldToAdd));
                    addToBot(new CustomWaitAction(0.5f));
                    (AbstractDungeon.getCurrRoom()).smoked = true;
                    addToBot(new VFXAction(new SmokeBombEffect((p).hb.cX, (p).hb.cY)));
                    p.hideHealthBar();
                    p.isEscaping = true;
                    p.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
                    AbstractDungeon.overlayMenu.endTurnButton.disable();
                    p.escapeTimer = 2.5F;
                    return;
                }
            }

            p.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
            addToBot(new CustomWaitAction(dur));
            p.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
            AbstractDungeon.effectList.add(new TextAboveCreatureEffect(p.hb.cX, p.hb.cY, "Failed.", Color.WHITE));
            return;
        }
        p.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
        addToBot(new CustomWaitAction(dur));
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                p.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
                AbstractDungeon.effectList.add(new TextAboveCreatureEffect(p.hb.cX, p.hb.cY, "Can't Escape!", Color.WHITE));
                isDone = true; // Finish this custom action after the visual effect is added
            }
        });
    }

    public AbstractCard makeCopy() {
        return new FleeGil();
    }
}
