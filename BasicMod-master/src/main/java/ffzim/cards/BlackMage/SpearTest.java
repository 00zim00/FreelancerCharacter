package ffzim.cards.BlackMage;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.SpearTestAction;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class SpearTest extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SpearTest",
            0,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 1;
    private static final int UPG_DAMAGE = 0;

    public SpearTest() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFSPELL);
        cardsToPreview = new Thundara();
    }
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.cardsToPreview.upgrade();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            setDamage(DAMAGE+UPG_DAMAGE);
            PurgeField.purge.set(this, true);
            tags.add(CustomTags.FFSPELL);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SpearTestAction(this));
        addToBot((AbstractGameAction)new SpearTestAction(this));

    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new SpearTest();
    }
}