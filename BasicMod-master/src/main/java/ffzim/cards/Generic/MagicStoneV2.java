package ffzim.cards.Generic;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class MagicStoneV2 extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MagicStoneV2",
            -2,
            CardType.CURSE,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);

// add a upgrade that when exhuasted at end of turn gain 1 bonus energy


    public static final String ID = makeID(cardInfo.baseId);

    private boolean canUseCard = false;

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return canUseCard;
    }

    private static final int ENERGYGAIN = 0;
    private static final int MAXENERGY = 1;
    private static final int UPG_MAXENERGY = 1;

    public MagicStoneV2() {
        super(cardInfo, true);
        setMagic(ENERGYGAIN);
        setSecondMagic(MAXENERGY,UPG_MAXENERGY);
        isEthereal = true;
        selfRetain = true;
    }

    public void onRetained() {
        int currentEnergy = AbstractDungeon.player.energy.energyMaster;
        currentEnergy = Math.min(currentEnergy, secondMagicNumber);
        if (currentEnergy >= 1) {
            upgradeMagicNumber(currentEnergy);
            addToTop(new LoseEnergyAction(currentEnergy));
            canUseCard = true;
            baseCost = 0;
            setExhaust(true);
        }
    }
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new GainEnergyAction(magicNumber));
        upgradeMagicNumber(0);
        canUseCard = false;
        baseCost = -2;
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new MagicStoneV2();
    }
}