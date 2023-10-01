package ffzim.cards.Generic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.DoubleCastPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class DoubleCast extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "DoubleCast",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int USE = 1;
    private static final int UPG_USE = 1;
    public DoubleCast() {
        super(cardInfo, false);
        setMagic(USE,UPG_USE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DoubleCastPower( p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new DoubleCast();
    }
}

