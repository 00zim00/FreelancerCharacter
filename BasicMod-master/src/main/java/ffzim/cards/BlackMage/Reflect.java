package ffzim.cards.BlackMage;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Reflect extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Reflect",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);
    private static final int SLOW = 2;
    private static final int UPG_SLOW = 2;

    public Reflect() {
        super(cardInfo, false);

        setMagic(SLOW,UPG_SLOW);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFSLOW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // Reflects ONLY things enemy applies.

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Reflect();
    }
}