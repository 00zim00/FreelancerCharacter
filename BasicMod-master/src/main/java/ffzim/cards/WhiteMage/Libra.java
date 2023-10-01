package ffzim.cards.WhiteMage;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Libra extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Libra",
            1,
            CardType.SKILL,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int Draw = 1;
    private static final int Vul = 1;
    private boolean seenItems = false;
    public Libra() {
        super(cardInfo, true);
        setMagic(Draw);
        exhaust = true;
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        setSecondMagic(Vul);
        tags.add(CustomTags.FFVULNERABLE);
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int numEnemies = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        addToBot(new DrawCardAction(p, numEnemies));
        if (upgraded){
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, Vul, false), Vul));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Libra();
    }
}