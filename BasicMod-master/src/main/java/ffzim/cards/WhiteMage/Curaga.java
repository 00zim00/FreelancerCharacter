package ffzim.cards.WhiteMage;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Curaga extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Curaga",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int TEMPHP = 12;
    private static final int UPG_TEMPHP = 4;
    private boolean isInPreview;

    public Curaga() {this(true);}

    public Curaga(boolean makePreview) {
        super(cardInfo, true);
        setMagic(TEMPHP, UPG_TEMPHP);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFSPELL);
        this.isInPreview = makePreview;
        this.rarity = CardRarity.RARE;
        this.setCardLevel(3);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPG_TEMPHP);
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddTemporaryHPAction(p, p, magicNumber));
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new Curaga();
    }
}
