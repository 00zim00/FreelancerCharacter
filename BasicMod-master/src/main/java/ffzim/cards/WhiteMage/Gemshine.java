package ffzim.cards.WhiteMage;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.keywords.LevelUpAction;
import ffzim.cards.BaseCard;
import ffzim.cards.WhiteMage.Gems.FireMateria;
import ffzim.cards.WhiteMage.Gems.HolyMateria;
import ffzim.cards.WhiteMage.Gems.LightningMateria;
import ffzim.cards.WhiteMage.Gems.PoisonMateria;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;

public class Gemshine extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Protect",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BUFF = 1;

    private boolean isInPreview;
    public Gemshine() {
        this(true);
    }
    public Gemshine(boolean makePreview) {
        super(cardInfo, true);
        setMagic(BUFF);
        setLevelUp(true);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFLEVELUP);
        if (makePreview) {
            MultiCardPreview.add(this, new SummonTopaz(), new SummonEmerald(),new SummonRuby(),new SummonMoonstone());
            horizontal.set(this, true);
        }
        this.isInPreview = makePreview;
        super.initializeDescription();
    }

    @Override
    public void upgrade() {
        this.upgradeName();
        if (isInPreview ) {
            MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
        }
        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int rng = AbstractDungeon.cardRandomRng.random(4);
        if ( rng == 1) {
            addToBot(new LevelUpAction(p,p,isLevelUp(),new LightningMateria(),new SummonTopaz(),true,false,false, this.upgraded, this.purgeOnUse));
        }else if ( rng == 2) {
            addToBot(new LevelUpAction(p,p,isLevelUp(),new PoisonMateria(),new SummonEmerald(),true,false,false, this.upgraded, this.purgeOnUse));
        }else if ( rng == 3) {
            addToBot(new LevelUpAction(p,p,isLevelUp(),new FireMateria(),new SummonRuby(),true,false,false, this.upgraded, this.purgeOnUse));
        }else if ( rng == 4) {
            addToBot(new LevelUpAction(p,p,isLevelUp(),new HolyMateria(),new SummonMoonstone(),true,false,false, this.upgraded, this.purgeOnUse));
        }


    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Gemshine();
    }
}