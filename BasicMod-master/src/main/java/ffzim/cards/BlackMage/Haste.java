package ffzim.cards.BlackMage;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.HastePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Haste extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "Haste",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public Haste() {
        super(cardInfo, false);
        this.baseMagicNumber = 1;
        this.magicNumber = 1;
        tags.add(CustomTags.FFSPELL);
        this.rarity = CardRarity.UNCOMMON;
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(this.cost - 1);
            this.rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new HastePower(p, this.magicNumber), this.magicNumber));
    }


    public AbstractCard makeCopy() {
        return new Haste();
    }
}
