package ffzim.cards.BlueMage.BlueMagic;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RitualPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Ritual extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Ritual",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int Ritual = 1;
    private static final int UPG_Trance = 0;

    public Ritual() {
        super(cardInfo, true);
        setMagic(1);
        tags.add(CustomTags.FFSPELL);
        this.tags.add(CardTags.HEALING);
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    // its cost 2 + X, where X is what you gain as rit
    //  Upgrade, base cost is 2

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int temp = MathUtils.random(1, 10);
        if (temp < 4) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(p, "CAW! CAAAW!!", 1.0F, 2.0F));

        } else if (temp < 7) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(p, "My Power Is Unmatched!", 1.0F, 2.0F));
        }
        playSfx();
        addToBot(new ApplyPowerAction(p, p, new RitualPower(p, magicNumber,true)));
    }



    private void playSfx() {
        int roll = MathUtils.random(2);
        if (roll == 0) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_CULTIST_1A"));
        } else if (roll == 1) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_CULTIST_1B"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_CULTIST_1C"));
        }
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new Ritual();
    }
}