package ffzim.cards.BlueMage.BlueMagic;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Lick extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Lick",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DEBUFF_MIN = 0;
    private static final int UPG_DEBUFF_MIN   = 1;

    private static final int DEBUFF_MAX = 2;
    private static final int UPG_DEBUFF_MAX   = 1;

    public Lick() {
        super(cardInfo, true);
        setMagic(DEBUFF_MIN,UPG_DEBUFF_MIN);
        setSecondMagic(DEBUFF_MAX,UPG_DEBUFF_MAX);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFWEAK);
        tags.add(CustomTags.FFBLUEMAGIC);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int roll;
        if (!this.upgraded) {
            roll = MathUtils.random(DEBUFF_MAX);
        }else {
            roll = MathUtils.random(DEBUFF_MAX + 1);
        }
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, roll, false), roll));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Lick();
    }
}