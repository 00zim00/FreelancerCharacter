package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class RawIntuition extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "RawIntuition",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BLOCKnDAMAGE = 7;
    private static final int UPG_BLOCKnDAMAGE   = 3;

    public RawIntuition() {
        super(cardInfo, false);
        setDamage(BLOCKnDAMAGE,UPG_BLOCKnDAMAGE);
        setBlock(BLOCKnDAMAGE,UPG_BLOCKnDAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null && m.getIntentBaseDmg() >= 0) {
            addToBot(new GainBlockAction(p, p, block));
        } else {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RawIntuition();
    }
}
