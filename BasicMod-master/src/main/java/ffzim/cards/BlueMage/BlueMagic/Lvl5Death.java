package ffzim.cards.BlueMage.BlueMagic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.actions.watcher.JudgementAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GiantTextEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import com.badlogic.gdx.graphics.Color;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Lvl5Death extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Lvl5Death",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private AbstractMonster targetEnemy;
    public Lvl5Death() {
        super(cardInfo, true);
        exhaust = true;
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    @Override
    public void   use(AbstractPlayer p, AbstractMonster m) {
        if (isCurrentFloorDivisibleBy(5)) {
            if (!(m.type == AbstractMonster.EnemyType.ELITE || m.type == AbstractMonster.EnemyType.BOSS)) {
                targetEnemy = m;
                magicNumber = (targetEnemy.currentHealth);
                final int magicNumberCopy = magicNumber;
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if (m != null) {
                            addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, Color.GOLD.cpy())));
                            addToBot(new WaitAction(0.8F));
                            addToBot(new VFXAction(new GiantTextEffect(m.hb.cX, m.hb.cY)));
                        }
                        addToBot(new JudgementAction(m, magicNumberCopy));
                        isDone = true;
                    }

                });
            }
            }
    }
    private boolean isCurrentFloorDivisibleBy(int num) {
        return AbstractDungeon.floorNum % num == 0;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(10);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Lvl5Death();
    }
}
