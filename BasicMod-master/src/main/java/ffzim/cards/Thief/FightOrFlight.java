package ffzim.cards.Thief;

import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class FightOrFlight extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FightOrFlight",
            1,
            CardType.ATTACK,
            SelfOrEnemyTargeting.SELF_OR_ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BUFF = 1;
    private static final int UPG_BUFF   = 1;
    private static final int DAM = 6;
    private static final int UPG_DAM   = 3;
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK   = 3;

    public FightOrFlight() {
        super(cardInfo, true);
        setDamage(DAM,UPG_DAM);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(BUFF ,UPG_BUFF );
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = SelfOrEnemyTargeting.getTarget(this);

        if (target == null)
            target = AbstractDungeon.player;

        if (target == AbstractDungeon.player) {
            addToBot(new GainBlockAction(target, p, this.block));
            addToBot(new ApplyPowerAction(target, p, new DexterityPower(target, this.magicNumber), this.magicNumber));
        } else if (target instanceof AbstractMonster) {
            addToBot(new DamageAction(target, new DamageInfo(target, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(target, this.magicNumber), this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FightOrFlight();
    }
}
