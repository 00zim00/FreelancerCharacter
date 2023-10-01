package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
import static ffzim.BasicMod.makeID;

public class Darkside extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Darkside",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 0;
    private static final int UPG_BLOCK = 0;
    private static final int BLOCK_PERCENT = 25;
    public Darkside() {
        super(cardInfo, true);
        setBlock(magicNumber);
        setExhaust(true);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(1); // Set the base cost to 1 when upgraded
            rawDescription = cardStrings.DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int calculatedBlock = (int) Math.ceil(player.maxHealth * BLOCK_PERCENT / 100.0);;
        addToBot(new GainBlockAction(p, p, calculatedBlock));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Darkside();
    }
}