package ffzim.cards.BlueMage.BlueMagic;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.CurlUpPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class CurlUp extends BaseCard implements StartupCard
{

    public static final String POWER_ID = makeID("CurlUp");
    private final static CardInfo cardInfo = new CardInfo(
            "CurlUp",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    private static final int BUFF = 8;
    private static final int UPG_BUFF   = 4;

    public CurlUp() {
        super(cardInfo, true);
        setMagic(BUFF,UPG_BUFF);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        if (upgraded) {
            AbstractPlayer p = AbstractDungeon.player;
            addToBot(new ApplyPowerAction(p, p, new CurlUpPower(p, magicNumber), magicNumber));
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CurlUpPower(p, magicNumber),magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CurlUp();
    }
}