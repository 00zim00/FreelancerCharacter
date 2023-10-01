package ffzim.cards.Dragoon;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.DiveReadyPower;
import ffzim.powers.JumpDodgePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class MirageDive extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MirageDive",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 4;
    private static final int BLOCK_MULTIPLYER= 1;
    private static final int UPG_BLOCK_MULTIPLYER = 1;
    public MirageDive() {
        super(cardInfo, true);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(BLOCK_MULTIPLYER,UPG_BLOCK_MULTIPLYER);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasPower(DiveReadyPower.POWER_ID) && p.hasPower(JumpDodgePower.POWER_ID)){
            int jumptotal = p.getPower(JumpDodgePower.POWER_ID).amount;
            jumptotal = jumptotal * magicNumber;
            addToBot(new GainBlockAction(p, p, this.block + jumptotal));
        }else {
            addToBot(new GainBlockAction(p, p, this.block));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MirageDive();
    }
}