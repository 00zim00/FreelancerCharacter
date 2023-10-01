package ffzim.cards.Chocobo.Healer;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import java.util.ArrayList;
import java.util.Collections;

import static ffzim.BasicMod.makeID;

public class ChocoMedica extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoHealer",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int REMOVE = 1;
    private static final int UPG_REMOVE = 1;
    public ChocoMedica() {
        super(cardInfo, true);
        setMagic(REMOVE,UPG_REMOVE);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPG_REMOVE);
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractPower> debuffsToRemove = new ArrayList<>();
        for (AbstractPower power : p.powers) {
            if (power.type == AbstractPower.PowerType.DEBUFF) {
                debuffsToRemove.add(power);
            }
        }
        Collections.shuffle(debuffsToRemove);
        AbstractPower selectedPower = debuffsToRemove.get(0);
        addToBot(new ReducePowerAction(p, p, selectedPower, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoMedica();
    }
}