package ffzim.cards.Generic.ItemCards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.ItemLorePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ItemLore extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "ItemLore",
            3,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public ItemLore() {
        super(cardInfo, true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            upgradeBaseCost(2);
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ItemLorePower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ItemLore();
    }
}
