package ffzim.cards.BlueMage;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import ffzim.actions.AutoPlayRemoverAction;
import ffzim.cards.BaseCard;
import ffzim.cards.Generic.MagicStone;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class AutoRegan extends BaseCard implements StartupCard
{
            private final static CardInfo cardInfo = new CardInfo(
                    "AutoRegan",
                    -2,
                    CardType.POWER,
                    CardTarget.SELF,
                    CardRarity.RARE,
                    Freelancer.Enums.FFcardColor);

    public static final String ID = makeID("AutoRegan");
    private static final int REGEN = 2;
    private static final int UPG_REGEN = 3;
    public AutoRegan() {
        super(cardInfo, true);
        setMagic(REGEN,UPG_REGEN);
        PurgeField.purge.set(this, true);
        cardsToPreview = new MagicStone();
        this.tags.add(CardTags.HEALING);

    }

        @Override
        public void upgrade() {
            if (!this.upgraded) {
                this.cardsToPreview.upgrade();
                this.upgradeName();
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                this.initializeDescription();
                this.magicNumber = baseMagicNumber = REGEN+UPG_REGEN;
            }
        }


    @Override
    public boolean atBattleStartPreDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber)));
        addToBot(new AutoPlayRemoverAction(p, p, this));
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    @Override
    public AbstractCard makeCopy() {
        return new AutoRegan();
    }
}
