package ffzim.cards.Basics;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class CombatCommand extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CombatCommand",
            1,
            CardType.ATTACK,
            SelfOrEnemyTargeting.SELF_OR_ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    public CombatCommand() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setBlock(BLOCK, UPG_BLOCK);
        this.tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = SelfOrEnemyTargeting.getTarget(this);

        if (target == null)
            target = AbstractDungeon.player;

        if (target == AbstractDungeon.player) {
            this.tags.remove(CardTags.STRIKE);
            addToBot(new GainBlockAction(p, p, block));
        } else if (target instanceof AbstractMonster) {
            addToBot(new DamageAction(target, new DamageInfo(target, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CombatCommand();
    }
}