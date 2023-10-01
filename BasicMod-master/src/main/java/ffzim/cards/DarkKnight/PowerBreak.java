package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class PowerBreak extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "PowerBreak",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);


    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;
    private static final int DEBUFF = 2;
    private static final int UPG_DEBUFF = 1;


    public PowerBreak() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(DEBUFF,UPG_DEBUFF);
        tags.add(CustomTags.FFVULNERABLE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot((AbstractGameAction)new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));


    }



    @Override
    public AbstractCard makeCopy() { //Optional
        return new PowerBreak();
    }
}