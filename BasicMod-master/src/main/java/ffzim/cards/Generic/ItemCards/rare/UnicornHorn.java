package ffzim.cards.Generic.ItemCards.rare;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import ffzim.CustomEffect.NoHealHealEffect;
import ffzim.cards.BaseCard;
import ffzim.powers.NullVulnerablePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;

public class UnicornHorn extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "UnicornHorn",
            1,
            CardType.SKILL,
            CardTarget.ALL_ENEMY,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    public UnicornHorn() {
        super(cardInfo, false);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
    }

    // Power Exultations
    // "Time Warp";
    // WAY to many. instead add what CAN be removed.. anything on player. specific on enemy
    // Or just remove card
    // Removes, most, basic debuffs.


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        ArrayList<AbstractPower> powerToRemove = new ArrayList<>();
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF || power.type == AbstractPower.PowerType.BUFF ) {
                powerToRemove.add(power);
            }
        }
        for (AbstractPower power : powerToRemove) {
            addToBot(new RemoveSpecificPowerAction(p, p, power));
        }

        ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;

        for (AbstractMonster monster : monsters) {
            ArrayList<AbstractPower> powerToRemove2 = new ArrayList<>();
            for (AbstractPower power : monster.powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF || power.type == AbstractPower.PowerType.BUFF) {
                    powerToRemove2.add(power);
                }
            }
            for (AbstractPower power : powerToRemove2) {
                addToBot(new RemoveSpecificPowerAction(monster, monster, power));
            }
            float targetX = monster.hb.cX;
            float targetY = monster.hb.cY;
            AbstractDungeon.effectsQueue.add(new  NoHealHealEffect(targetX, targetY));
        }

        float targetX = p.hb.cX;
        float targetY = p.hb.cY;
        AbstractDungeon.effectsQueue.add(new NoHealHealEffect(targetX, targetY));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new UnicornHorn();
    }
}
