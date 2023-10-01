package ffzim.cards.WhiteMage;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.ShellPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Shell extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Shell",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BUFF = 1;
    public Shell() {
        super(cardInfo, true);
        setMagic(BUFF);
        exhaust = true;
        tags.add(CustomTags.FFSPELL);
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        exhaust = false;
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ShellPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Shell();
    }
}