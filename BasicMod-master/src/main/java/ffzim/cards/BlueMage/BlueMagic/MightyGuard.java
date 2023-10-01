package ffzim.cards.BlueMage.BlueMagic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.ProtectPower;
import ffzim.powers.ShellPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class MightyGuard extends BaseCard
{

    public static final String POWER_ID = makeID("MightyGuard");
    private final static CardInfo cardInfo = new CardInfo(
            "MightyGuard",
            3,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    private static final int BUFF = 1;

    public MightyGuard() {
        super(cardInfo, false);
        setMagic(BUFF);
        setExhaust(true);
        setCostUpgrade(2);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            this.upgradedCost = true;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ProtectPower(p, magicNumber),magicNumber));
        addToBot(new ApplyPowerAction(p, p, new ShellPower(p, magicNumber),magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MightyGuard();
    }
}