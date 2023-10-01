package ffzim.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.character.Freelancer;
import ffzim.powers.BladeBarrierPower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class BladeBarrier extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BladeBarrier",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int THORNS = 3;
    private static final int UPG_THORNS= 1;
    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 2;

    public BladeBarrier() {
        super(cardInfo, true);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(THORNS,UPG_THORNS);
        //setSecondMagic(0);


    }
//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            setMagic(0);
//            setSecondMagic(THORNS,UPG_THORNS);
//        }
//    }

        @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println("Block value: " + this.block);
        System.out.println("Magic number value: " + this.magicNumber);
        System.out.println("2Magic number value: " + this.secondMagicNumber);
        //addToBot(new ApplyPowerAction(p, p, new BladeBarrierPower(p,this.block, this.magicNumber), this.secondMagicNumber));
        //addToBot(new ApplyPowerAction(p, p, new BladeBarrierPower(p, this.block, this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new BladeBarrierPower(p, this.block,  this.magicNumber),this.block));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new BladeBarrier();
    }
}