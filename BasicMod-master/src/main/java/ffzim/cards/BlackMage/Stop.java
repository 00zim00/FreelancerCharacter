package ffzim.cards.BlackMage;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Stop extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Stop",
            2,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);
    private static final int STOP = 1;
    public Stop() {
        super(cardInfo, false);
        setMagic(STOP);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFSPELL);
        this.rarity = CardRarity.RARE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new StunMonsterAction(m, p, STOP));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Stop();
    }
}