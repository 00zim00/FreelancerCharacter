package ffzim.cards.Generic.ItemCards.rare;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;

public class Remedy extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Remedy",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public Remedy() {
        super(cardInfo, false);
        FleetingField.fleeting.set(this, true);
        setSelfRetain(true);
        tags.add(CustomTags.FFITEM);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractPower> debuffsToRemove = new ArrayList<>();
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                debuffsToRemove.add(power);
            }
        }
        for (AbstractPower debuff : debuffsToRemove) {
            addToBot(new ReducePowerAction(p, p, debuff, 1));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Remedy();
    }
}
