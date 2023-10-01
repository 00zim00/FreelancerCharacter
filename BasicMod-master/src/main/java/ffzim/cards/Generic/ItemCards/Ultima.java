package ffzim.cards.Generic.ItemCards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Ultima extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Ultima",
            -2,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    public Ultima() {
        super(cardInfo, false);
        FleetingField.fleeting.set(this, true);
        tags.add(CustomTags.FFITEM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // if throw double damage.
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Ultima();
    }
}
