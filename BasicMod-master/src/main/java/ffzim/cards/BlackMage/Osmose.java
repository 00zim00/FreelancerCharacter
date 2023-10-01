package ffzim.cards.BlackMage;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.OsmoseAction;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Osmose extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Osmose",
            -1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public Osmose() {
        super(cardInfo, false);
        PurgeField.purge.set(this, true);
        //tags.add(CustomTags.FFSPELL);
        //Maybe allow spell so that when upgraded get 1 energy for free.
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new OsmoseAction(p, m,this, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Osmose();
    }
}