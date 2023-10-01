package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.TroublePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Dyne extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Annoy",
            -1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);
    public static final String ID = makeID(cardInfo.baseId);
    private static final int DEBUFF = 1;
    private static final int UPG_DEBUFF   = 1;

    public Dyne() {
        super(cardInfo, true);
        setMagic(DEBUFF ,UPG_DEBUFF );
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFTROUBLE);
    }

    // X cost card that is for limit break? you keep the card even out of limit?


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new TroublePower(m, this.magicNumber), this.magicNumber));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Dyne();
    }
}
