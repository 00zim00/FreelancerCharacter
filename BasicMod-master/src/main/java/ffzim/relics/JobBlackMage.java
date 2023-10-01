package ffzim.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.stances.TranceBlackMagePower;

import static ffzim.BasicMod.makeID;

public class JobBlackMage extends BaseRelic
{
    public static final String ID = makeID("Job:Blackmage");

    public JobBlackMage() {
        super(ID, "Job:Blackmage", RelicTier.SPECIAL, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public void onEquip() {
        AbstractDungeon.player.decreaseMaxHealth((int) (AbstractDungeon.player.maxHealth * 0.3F));

    }
    @Override
    public void onUnequip() {
        AbstractDungeon.player.increaseMaxHp((int) (AbstractDungeon.player.maxHealth * 0.3F), false);

    }

    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        flash();
        addToTop(new ApplyPowerAction(p, p,new TranceBlackMagePower(p, 0), 0));
    }

    public BaseRelic makeCopy() {
        return new JobBlackMage();
    }
}
