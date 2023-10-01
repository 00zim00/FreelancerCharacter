package ffzim.cards.Dragoon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.LunaPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Luna extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Luna",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);
    private static final int LUNA = 1;
    private static final int UPG_LUNA = 1;

    public Luna() {
        super(cardInfo, true);
        setMagic(LUNA,UPG_LUNA);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LunaPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Luna();
    }
}