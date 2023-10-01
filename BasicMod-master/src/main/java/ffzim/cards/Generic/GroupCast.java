package ffzim.cards.Generic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.GroupCastPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class GroupCast extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GroupCast",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    public GroupCast() {
        super(cardInfo, false);
        setMagic(1);
        setExhaust(true);
        setCostUpgrade(1);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            this.upgradedCost = true;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot( new ApplyPowerAction(p,p,new GroupCastPower( p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new GroupCast();
    }
}

