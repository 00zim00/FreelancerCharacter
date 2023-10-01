package ffzim.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.MultiUpgradeCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.WhiteMage.Cura;
import ffzim.cards.WhiteMage.Curaga;
import ffzim.cards.WhiteMage.Cure;
import ffzim.character.Freelancer;
import ffzim.powers.FloatPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class TestMulti extends BaseCard  implements MultiUpgradeCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "TestMulti",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private final AbstractCard Card1 = new Cure();
    private final AbstractCard Card2 = new Cura();
    private final AbstractCard Card3 = new Curaga();


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 0, MAGIC = 3, UP_MAGIC = 2;
    public TestMulti() {
        super(cardInfo, false);
        setMagic(1);
    }

    @Override
    public void upgrade() {
        processUpgrade();
    }
    public void addUpgrades() {
        addUpgradeData(() -> {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[1];
            name = Card1.name;
            initializeDescription();
        });
        addUpgradeData(() -> {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[2];
            name = Card2.name;
            initializeDescription();
        }, true, 0);
        addUpgradeData(() -> {
            rawDescription = cardStrings.EXTENDED_DESCRIPTION[3];
            name = Card3.name;
            initializeDescription();
        }, true, 1);
    }

    public void updateName() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FloatPower(p, magicNumber),1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new TestMulti();
    }
}