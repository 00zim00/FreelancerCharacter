package ffzim.cards.Basics;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class BLKStrike extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BLKStrike",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 3;

    private static final int DOUBLEPERCENT = 20;
    private static final int UPG_DOUBLEPERCENT = 10;
    private static final int TRANCE = 1;
    private static final int UPG_TRANCE = 2;

    //potency When a spell De-levels gives 100% chance to trigger this turn.

    public BLKStrike() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DOUBLEPERCENT);
        tags.add(CustomTags.FFSPELL);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CustomTags.FFJOBSTRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (MathUtils.randomBoolean(DOUBLEPERCENT / 100f)) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage * 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }else{
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BLKStrike();
    }
}