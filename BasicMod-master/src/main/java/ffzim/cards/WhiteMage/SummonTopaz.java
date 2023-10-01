package ffzim.cards.WhiteMage;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.WhiteMage.Gems.LightningMateria;
import ffzim.character.Freelancer;
import ffzim.powers.HastePower;
import ffzim.powers.ProtectPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class SummonTopaz extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SummonTopaz",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

   // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BUFF = 1;
    private boolean isInPreview;
    public SummonTopaz() {
        this(true);
    }
    public SummonTopaz(boolean makePreview) {
        super(cardInfo, true);
        setMagic(BUFF);
        setLevelUp(true);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFLEVELUP);
        if (makePreview) {
            cardsToPreview = new LightningMateria(false);
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
        addToBot(new ApplyPowerAction(p, p, new ProtectPower(p, magicNumber)));
        if (upgraded){
            addToBot(new ApplyPowerAction(p, p, new HastePower(p, magicNumber)));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SummonTopaz();
    }
}