package ffzim.cards.BlueMage;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Hyperbeam;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import ffzim.actions.CarveAction;
import ffzim.cards.BaseCard;
import ffzim.cards.BlueMage.BlueMagic.*;
import ffzim.cards.Dragoon.Luna;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;

public class CarvingKnife extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "CarvingKnife",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public final static String ID = makeID("CarvingKnife");

    public static final int DAMAGE = 6;
    public static final int UPG_DAMAGE = 3;
    private final ArrayList<AbstractCard> cardToPreview = new ArrayList<>();
    private float rotationTimer;
    private int previewIndex = 0;

    protected float getRotationTimeNeeded() {
        return 1.0F;
    }

    @Override
    public void update() {
        super.update();
        if (!cardToPreview.isEmpty() && AbstractDungeon.actionManager.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    if (previewIndex == cardToPreview.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                    if (previewIndex >= cardToPreview.size()) {
                        previewIndex = cardToPreview.size() - 1;
                    }
                    cardsToPreview = cardToPreview.get(previewIndex);
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }

    public CarvingKnife() {
        this(true);
    }

    public CarvingKnife(boolean makePreview) {
        super(cardInfo, true);
        baseDamage = damage = DAMAGE;
        this.exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        // Do check IF preview card is in deck, then return nothing or do nothing?

        addToBot(new CarveAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL), this));
        this.cardToPreview.clear();
        this.previewIndex = 0;
    }

    public void applyPowers() {
        super.applyPowers();
        this.cardToPreview.clear();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && (m.type == AbstractMonster.EnemyType.ELITE || m.type == AbstractMonster.EnemyType.BOSS)) {
                if (!this.cardToPreview.contains(MonsterWeapon(m.id))) {
                    this.cardToPreview.add(MonsterWeapon(m.id));
                }
            }
            if (!m.isDeadOrEscaped() && AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                if (m.id.equals("AcidSlime_L") || m.id.equals("SpikeSlime_L")) {
                    if (!this.cardToPreview.contains(MonsterWeapon(m.id))) {
                        this.cardToPreview.add(MonsterWeapon(m.id));
                    }
                }
            }
            if (!m.isDeadOrEscaped()) {
                if (m.id.equals("FuzzyLouseDefensive") || m.id.equals("FuzzyLouseNormal") || m.id.equals("WrithingMass") || m.id.equals("SnakePlant") || m.id.equals("Cultist") || m.id.equals("Exploder")) {
                    if (!this.cardToPreview.contains(MonsterWeapon(m.id))) {
                        this.cardToPreview.add(MonsterWeapon(m.id));
                    }
                }
            }
        }
    }

    public AbstractCard MonsterWeapon(String id) {
        if (id == null) {
            return new Hyperbeam();
        }
        switch (id) {
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
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                    return new Lick();
                }
            case "AcidSlime_L":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                    return new Lick();
                }
            case "SpikeSlime_M":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
                    return new Lick();
                }
            case "AcidSlime_M":
                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {
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
                return new Luna();
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
            case "CorruptHeart":
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
                cardID = "SelfDestruct";
                if (AbstractDungeon.player.masterDeck.findCardById(cardID) != null) {
                    return new SelfDestruct();
                } else {return null;}
            default:
                return new Hyperbeam();
        }
    }


    public void SetFleeting(boolean set){
        FleetingField.fleeting.set(this, set);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}