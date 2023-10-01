package ffzim.cards.BlueMage.BlueMagic;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import ffzim.CustomEffect.SlimeBounceEffect;
import ffzim.actions.BouncingSlimeAction;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class GoopSpray extends BaseCard {

    private final static CardInfo cardInfo = new CardInfo(
            "GoopSpray",
            1,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private final int DEBUFF= 1;
    public GoopSpray() {
        super(cardInfo, false);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
        tags.add(CustomTags.FFBLUEMAGIC);
    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        if (randomMonster != null)
            addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new SlimeBounceEffect(p.hb.cX, p.hb.cY, randomMonster.hb.cX, this.hb.cY), 0.4F));
        addToBot((AbstractGameAction)new BouncingSlimeAction((AbstractCreature)randomMonster, DEBUFF, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new GoopSpray();
    }
}
