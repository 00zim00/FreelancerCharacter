package ffzim.cards.BlackMage;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import ffzim.cards.BaseCard;
import ffzim.cards.variables.PlayerSaveable;
import ffzim.character.Freelancer;
import ffzim.powers.VenomPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Biora extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Biora",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    // TintEffect

    public static final String ID = makeID(cardInfo.baseId);

    private static final int POISON = 9;
    private static final int UPG_POISON = 3;
    private static final int VENOM_PERCENT = 40;
    private boolean isInPreview;

    public Biora() {
        this(true);
    }
    public Biora(boolean makePreview) {
        super(cardInfo, true);
        setMagic(POISON, UPG_POISON);
        setSecondMagic(Math.min(VENOM_PERCENT + PlayerSaveable.luckPlus,100));
        this.magicNumber = this.baseMagicNumber;
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFSPELL);
        tags.add(CustomTags.FFPOISON);
        tags.add(CustomTags.FFVENOM);
        this.rarity = CardRarity.UNCOMMON;
        this.isInPreview = makePreview;
        super.initializeDescription();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.DESCRIPTION;
            tags.add(CustomTags.FFSPELL);
            PurgeField.purge.set(this, true);
            setMagic(POISON+UPG_POISON);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        int tempVENOM_PERCENT = Math.min(VENOM_PERCENT + PlayerSaveable.luckPlus,100);
        if (MathUtils.randomBoolean(tempVENOM_PERCENT / 100f)) {
            addToBot(new ApplyPowerAction(m, p, new VenomPower(m, 0), 0, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Biora();
    }
}
