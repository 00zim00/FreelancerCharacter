package ffzim.cards.Dragoon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class CherryBlossom extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Cherry Blossom",
            1,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int STR_BONUS = 2;
    private static final int UPG_STR_BONUS  = 1;

    public CherryBlossom() {
        super(cardInfo, true);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(STR_BONUS,UPG_STR_BONUS);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int StrBonusDamage;
        StrengthPower currentStrength = (StrengthPower) p.getPower(StrengthPower.POWER_ID);
        if (currentStrength != null) {
            StrBonusDamage = currentStrength.amount * magicNumber;
            addToBot(new DamageAction(m, new DamageInfo(p, damage + StrBonusDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }else{
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CherryBlossom();
    }
}