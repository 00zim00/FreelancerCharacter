package ffzim.cards.Generic.ItemCards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class ExcaliburII extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "ExcaliburII",
            -2,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);

    @Override
    public boolean canUpgrade() {
        return false;
    }
    public ExcaliburII() {
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
        return new ExcaliburII();
    }
}
