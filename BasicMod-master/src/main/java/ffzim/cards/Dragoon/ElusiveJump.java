package ffzim.cards.Dragoon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.JumpDodgePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class ElusiveJump extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ElusiveJump",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;
    private static final int BLOCK2 = 3;
    private static final int UPG_BLOCK2 = 2;

    private static final int JUMP = 2;
    private static final int UPG_JUMP = 2;

    public static final String ID = makeID(cardInfo.baseId);
    public ElusiveJump() {
        super(cardInfo, true);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(BLOCK2,UPG_BLOCK2);
        setSecondMagic(JUMP,UPG_JUMP);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, this.block));
        addToBot(new ApplyPowerAction(p, p, new NextTurnBlockPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new JumpDodgePower(p, secondMagicNumber)));
        //addToBot(new ApplyPowerAction(p, p, new DiveReadyPower(p, 0)));

    }

//    public void upgrade() {
//        if (!this.upgraded) {
//            upgradeName();
//            upgradeBlock(2);
//        }
//    }

    public AbstractCard makeCopy() {
        return new ElusiveJump();
    }
}
