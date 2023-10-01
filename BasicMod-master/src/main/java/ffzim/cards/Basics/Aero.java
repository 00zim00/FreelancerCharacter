package ffzim.cards.Basics;

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

public class Aero extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Aero",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;

    public Aero() {
        super(cardInfo, true);

        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CustomTags.FFSPELL);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int halfDamage = (int) Math.floor(damage / 2.0);
        addToBot(new DamageAction(m, new DamageInfo(p, damage-halfDamage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        //halfDamage = (int) Math.floor(damage / 2.0);
        addToBot(new DamageAction(m, new DamageInfo(p, halfDamage, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Aero();
    }
}