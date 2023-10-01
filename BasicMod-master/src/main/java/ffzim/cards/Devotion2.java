package ffzim.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.DevotionPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DevotionEffect;

import static ffzim.BasicMod.makeID;

import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
public class Devotion2 extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Devotion2",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    public Devotion2() {
        super(cardInfo, true);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
        }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new SFXAction("HEAL_2", -0.4F, true));
        float doop = 0.8F;
        if (Settings.FAST_MODE)
            doop = 0.0F;
        addToBot((AbstractGameAction)new VFXAction((AbstractGameEffect)new DevotionEffect(), doop));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new DevotionPower((AbstractCreature)p, this.magicNumber), this.magicNumber));
    }

    public AbstractCard makeCopy() {
        return new Devotion2();
    }

    public void initializeDescription() {
        super.initializeDescription();
        this.keywords.add(GameDictionary.ENLIGHTENMENT.NAMES[0].toLowerCase());
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }
}
