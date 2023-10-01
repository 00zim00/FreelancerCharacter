package ffzim.cards.BlackMage;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.MagicPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Transpose extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Transpose",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static int CurrentStrPower = 0;
    private static int CurrentMagPower = 0;
    public Transpose() {
        super(cardInfo, true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            this.upgradeBaseCost(0);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CurrentMagPower = 0;
        CurrentStrPower = 0;
        AbstractPower magicPower = AbstractDungeon.player.getPower(MagicPower.POWER_ID);
        AbstractPower strengthPower = AbstractDungeon.player.getPower(StrengthPower.POWER_ID);
        if (magicPower != null) {
            CurrentMagPower = magicPower.amount;
            addToTop(new RemoveSpecificPowerAction(p, p, MagicPower.POWER_ID));
        }
        if (strengthPower != null) {
            CurrentStrPower = strengthPower.amount;
            addToTop(new RemoveSpecificPowerAction(p, p, StrengthPower.POWER_ID));
        }
        if (CurrentMagPower != 0) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, CurrentMagPower), CurrentMagPower));
        }
        if (CurrentStrPower != 0) {
            addToBot(new ApplyPowerAction(p, p, new MagicPower(p, CurrentStrPower), CurrentStrPower));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Transpose();
    }
}