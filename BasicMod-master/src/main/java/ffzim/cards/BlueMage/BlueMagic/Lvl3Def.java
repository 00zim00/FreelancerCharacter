package ffzim.cards.BlueMage.BlueMagic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Lvl3Def extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Lvl3Def",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int VUL_WEAK_AMOUNT = 3;

    private static final int UPG_VUL_WEAK_AMOUNT = 3;

    public Lvl3Def() {
        super(cardInfo, true);
        setMagic(VUL_WEAK_AMOUNT, UPG_VUL_WEAK_AMOUNT);
        exhaust = true;
        tags.add(CustomTags.FFWEAK);
        tags.add(CustomTags.FFVULNERABLE);
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isCurrentFloorDivisibleBy(3)) {
            addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, VUL_WEAK_AMOUNT, false), VUL_WEAK_AMOUNT));
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, VUL_WEAK_AMOUNT, false), VUL_WEAK_AMOUNT));
        }
    }

    private boolean isCurrentFloorDivisibleBy(int num) {
        return AbstractDungeon.floorNum % num == 0;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Lvl3Def();
    }
}
