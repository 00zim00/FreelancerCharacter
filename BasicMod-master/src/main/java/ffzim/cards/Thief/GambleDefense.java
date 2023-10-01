package ffzim.cards.Thief;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.LoseBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class GambleDefense extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GambleDefense",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    public GambleDefense() {
        super(cardInfo, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    if (!upgraded){
        if (AbstractDungeon.cardRandomRng.random(1) == 1) {
            addToBot(new LoseBlockAction(p, p, p.currentBlock));
        } else {
            addToBot(new GainBlockAction(p, p, p.currentBlock));
        }
    } else if (AbstractDungeon.cardRandomRng.random(1) == 1) {
            addToBot(new LoseBlockAction(p, p, p.currentBlock));
        } else {
            addToBot(new GainBlockAction(p, p, p.currentBlock * 2));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GambleDefense();
    }
}
