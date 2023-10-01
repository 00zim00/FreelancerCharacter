package ffzim.monster;

import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateSlowAttackAction;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.SlimeAnimListener;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class FriendlySlime extends AbstractMonster {
    public static final String ID = "FriendlySlime";

    private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings("Apology Slime");

    public static final String NAME = monsterStrings.NAME;

    public static final String[] MOVES = monsterStrings.MOVES;

    public static final String[] DIALOG = monsterStrings.DIALOG;

    public static final int HP_MIN = 8;

    public static final int HP_MAX = 12;

    public static final int TACKLE_DAMAGE = 3;

    public static final int WEAK_TURNS = 1;

    private static final byte TACKLE = 1;

    private static final byte DEBUFF = 2;

    public FriendlySlime() {
        super(NAME, "Apology Slime", AbstractDungeon.monsterHpRng.random(8, 12), 0.0F, -4.0F, 130.0F, 100.0F, null);
        this.damage.add(new DamageInfo((AbstractCreature)this, 3));
        loadAnimation("images/monsters/theBottom/slimeS/skeleton.atlas", "images/monsters/theBottom/slimeS/skeleton.json", 1.0F);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.state.addListener((AnimationState.AnimationStateListener)new SlimeAnimListener());
    }

    public void usePreBattleAction() {
        AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, "No-hurties, please! Share your cards? shh-shh!", 4.0F, 4.0F));
    }

    public void takeTurn() {
        switch (this.nextMove) {
            case 1:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, "Gloop, gloop! Offer me your shiny trinket, thud-thud!", 4.0F, 4.0F));
                break;
            case 2:
                AbstractDungeon.actionManager.addToBottom((AbstractGameAction)new TalkAction((AbstractCreature)this, "Give me some Ore!!!", 4.0F, 4.0F));
                break;
        }
    }

    // on hit nnnOOO!

    // Cards (by rariry) "gimmi cat" (gimmy bird cultist) asks for rare relic
    // Use potion on them


    protected void getMove(int num) {
        if (AbstractDungeon.aiRng.randomBoolean()) {
            setMove((byte)1, Intent.UNKNOWN);
        } else {
            setMove((byte)2, Intent.MAGIC);
        }
    }
}
