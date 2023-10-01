package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Shirk extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Shirk",
            0,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BUFF = 2;
    private static final int UPG_BUFF   = 1;
    private static final int DEBUFF = -2;
    private static final int UPG_DEBUFF   = -1;
    public Shirk() {
        super(cardInfo, true);
        setMagic(BUFF);
        setSecondMagic(DEBUFF);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPG_BUFF);
            setSecondMagic(UPG_DEBUFF);
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.secondMagicNumber), this.secondMagicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Shirk();
    }
}
