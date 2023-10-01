package ffzim.cards.Generic.startercards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.BackRowPower;
import ffzim.powers.FrontRowPower;
import ffzim.util.CardInfo;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;

public class Swap extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Swap",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public Swap() {this(true);}

    public Swap(boolean makePreview) {
        super(cardInfo, true);
        setLevelUp(true);
        PurgeField.purge.set(this, true);
        MultiCardPreview.add(this, new FrontRow(false), new BackRow(false));
        horizontal.set(this, true);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower frontRowPower = AbstractDungeon.player.getPower(FrontRowPower.POWER_ID);
        if (frontRowPower instanceof FrontRowPower) {
            addToBot(new ApplyPowerAction(p, p, new BackRowPower(p, 0,upgraded), 0));
        }else{
            addToBot(new ApplyPowerAction(p, p, new FrontRowPower(p, 0,upgraded), 0));
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Swap();
    }
}
