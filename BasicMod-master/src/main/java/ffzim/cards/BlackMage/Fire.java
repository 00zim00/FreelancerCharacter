package ffzim.cards.BlackMage;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.AstralFirePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Fire extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Fire",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            //MyCharacter.Enums.FFcardColor); 
            Freelancer.Enums.FFcardColor);
// StaffFireEffec

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    private static final int ASTRAL_FIRE = 1;
    private static final int UPG_ASTRAL_FIRE  = 1;
    private boolean isInPreview;

    public Fire() {this(true);}

    public Fire(boolean makePreview) {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(ASTRAL_FIRE,UPG_ASTRAL_FIRE);
        PurgeField.purge.set(this, true);
        tags.add(CustomTags.FFSPELL);
        this.isInPreview = makePreview;

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeDamage(UPG_DAMAGE);
            upgradeMagicNumber(UPG_ASTRAL_FIRE);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        addToBot(new ApplyPowerAction(p,p, new AstralFirePower(p, this.magicNumber), this.magicNumber));
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new Fire();
    }
}
