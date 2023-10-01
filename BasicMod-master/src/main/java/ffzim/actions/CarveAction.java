package ffzim.actions;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import ffzim.cards.BlueMage.BlueMagic.*;
import ffzim.cards.Dragoon.Luna;

import java.util.UUID;

import static basemod.BaseMod.logger;

public class CarveAction extends AbstractGameAction {
    private DamageInfo info;
    private UUID uuid;
    private AbstractMonster monsterTarget;
    private AbstractCard thisKnife;

    public CarveAction(AbstractCreature target, DamageInfo info, AbstractCard knife) {
        this.info = info;
        this.setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        monsterTarget = (AbstractMonster) target;
        this.duration = 0.1F;
        thisKnife = knife;
    }

    public void update() {
        if (this.target != null && this.target instanceof AbstractMonster) {
            AbstractMonster monster = (AbstractMonster) this.target;
            String monsterID = monster.id;
            logger.info("Attacking monster with ID: " + monsterID);
        }
        if (this.duration == 0.1F && this.target != null) {
            addToBot(new VFXAction(new BorderLongFlashEffect(Color.LIGHT_GRAY)));
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.SLASH_HORIZONTAL));
            this.target.damage(this.info);
            if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion") && (this.monsterTarget.type == AbstractMonster.EnemyType.BOSS || this.monsterTarget.type == AbstractMonster.EnemyType.ELITE)) {
                addToTop(new QuestCompleteAction(MonsterWeapon(this.target.id), thisKnife));
            }
            else if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion") && (this.target.id.equals("SpikeSlime_L") || this.target.id.equals("AcidSlime_L") || this.target.id.equals("SpikeSlime_M") || this.target.id.equals("AcidSlime_M"))){
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                    addToTop(new QuestCompleteAction(MonsterWeapon(this.target.id), thisKnife));
                }
            }
            else if ((this.target.isDying || this.target.currentHealth <= 0) && !this.target.halfDead
                    && (this.target.id.equals("FuzzyLouseDefensive") || this.target.id.equals("FuzzyLouseNormal") || this.target.id.equals("WrithingMass") || this.target.id.equals("SnakePlant") || this.target.id.equals("Cultist") || this.target.id.equals("Exploder"))){
                    addToTop(new QuestCompleteAction(MonsterWeapon(this.target.id), thisKnife));
            }
        }
        this.tickDuration();
    }

    public AbstractCard MonsterWeapon(String id){
        if (id == null){
            return new Hyperbeam();
        }
        switch (id){
            case "GremlinNob":
                return new Luna();
            case "Sentry":
                return new Luna();
            case "Lagavulin":
                return new Luna();
            case "TheGuardian":
                return new Luna();
            case "Hexaghost":
                return new Luna();
            case "SlimeBoss":
                return new GoopSpray();
            case "SpikeSlime_L":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new Lick();
                }
            case "AcidSlime_L":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new Lick();
                }
            case "SpikeSlime_M":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new Lick();
                }
            case "AcidSlime_M":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss){
                    return new Lick();
                }
            case "BookOfStabbing":
                return new ThousandStabs();
            case "SlaverBoss":
                return new Luna();
            case "GremlinLeader":
                return new Luna();
            case "Champ":
                return new Luna();
            case "TheCollector":
                return new Luna();
            case "BronzeAutomaton":
                return new Luna();
            case "Nemesis":
                return new Intangible();
            case "GiantHead":
                return new FourTonzeWeight();
            case "TimeEater":
                return new Luna();
            case "AwakenedOne":
                return new AutoLife();
            case "Donu":
                return new Luna();
            case "Deca":
                return new Luna();
            case "SpireShield":
                return new Luna();
            case "SpireSpear":
                return new Luna();
            case "Reptomancer":
                return new Luna();
            case "FuzzyLouseNormal":
                    return new CurlUp();
            case "FuzzyLouseDefensive":
                    return new CurlUp();
            case "WrithingMass":
                return new Malleable();
            case "SnakePlant":
                return new Malleable();
            case "Cultist":
                return new Ritual();
            case "Exploder":
                return new SelfDestruct();
            default:
                return new Hyperbeam();
        }
    }
}