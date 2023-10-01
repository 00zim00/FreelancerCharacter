package ffzim.cards.WhiteMage;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.BlackMage.Fira;
import ffzim.character.Freelancer;
import ffzim.powers.ProtectPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class SummonRuby extends BaseCard {
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
    public SummonRuby() {
        this(true);
    }
    public SummonRuby(boolean makePreview) {
        super(cardInfo, true);
        setMagic(BUFF);
        setLevelUp(true);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFLEVELUP);
        if (makePreview) {
            cardsToPreview = new Fira(false);
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
        // Reflect and temp Hp?
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SummonRuby();
    }
}