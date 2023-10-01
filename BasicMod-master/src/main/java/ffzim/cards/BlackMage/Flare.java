package ffzim.cards.BlackMage;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.HastePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Flare extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "Flare",
            4,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 28;
    private static final int ASTRAL_FIRE= 12;
    public Flare() {
        super(cardInfo, true);
        this.baseMagicNumber = 1;
        this.magicNumber = 1;
    }

    // Cost 1 less for every spell De-levelved spell.
    // Cost 1 less for ever leveled spell?

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new HastePower((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(this.cost - 1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


    public AbstractCard makeCopy() {
        return new Flare();
    }
}
