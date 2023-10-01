package ffzim.cards.WhiteMage;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Intervention extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Intervention",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BUFF = 2;
    private static final int UPG_BUFF = 1;
    public Intervention() {
        super(cardInfo, true);
        setMagic(BUFF,UPG_BUFF);
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // When you take damage, gain 2/3 TempHP
        // Half on unused block goes to temp hp?
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Intervention();
    }
}