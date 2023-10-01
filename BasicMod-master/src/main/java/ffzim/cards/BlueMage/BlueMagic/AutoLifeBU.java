package ffzim.cards.BlueMage.BlueMagic;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.AutoPlayRemoverAction;
import ffzim.cards.BaseCard;
import ffzim.cards.Generic.MagicStone;
import ffzim.character.Freelancer;
import ffzim.powers.AutoLifePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class AutoLifeBU extends BaseCard implements StartupCard
{
            private final static CardInfo cardInfo = new CardInfo(
                    "AutoLifeBU",
                    1,
                    CardType.POWER,
                    CardTarget.SELF,
                    CardRarity.RARE,
                    Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int HPPRECENT = 20;
    private static final int USES = 1;
    private static final int UPG_USES = 1;
    private static final int UPG_HPPRECENT= 10;
    public AutoLifeBU() {
        super(cardInfo, true);
        setMagic(HPPRECENT);
        setSecondMagic(USES,UPG_USES);
        PurgeField.purge.set(this, true);
        cardsToPreview = new MagicStone();
        this.tags.add(CardTags.HEALING);
        this.costUpgrade = 2;
        tags.add(CustomTags.FFBLUEMAGIC);

    }

//        @Override
//        public void upgrade() {
//            if (!this.upgraded) {
//                this.cardsToPreview.upgrade();
//                this.upgradeName();
//                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//                this.initializeDescription();
//                this.magicNumber = baseMagicNumber = REGEN+UPG_REGEN;
//            }
//        }


    @Override
    public boolean atBattleStartPreDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new AutoLifePower(p,this,this.secondMagicNumber, this.magicNumber,1), 1));
        addToBot(new AutoPlayRemoverAction(p, p, this));
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AutoLifePower(p,this,this.secondMagicNumber, this.magicNumber,1), 1));
        //addToBot(new AutoPlayRemoverAction(p, p, this));
    }

    @Override
    public AbstractCard makeCopy() {
        return new AutoLifeBU();
    }
}
