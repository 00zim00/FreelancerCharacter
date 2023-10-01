package ffzim.cards.Generic.ItemCards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.SoulboundField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.powers.MagicPower;
import ffzim.powers.NullFrailPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class RuinStaff extends BaseCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "RuinStaff",
            -1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 3;
    private static final int MagicUp = 2;
    private static final int UPG_MagicUp  = 1;

    @Override
    public boolean canUpgrade() {
        return false;
    }
    public RuinStaff() {
        super(cardInfo, false);
        setDamage(DAMAGE);
        setMagic(MagicUp);
        SoulboundField.soulbound.set(this, true);
        tags.add(CustomTags.FFITEM);
        tags.add(CustomTags.FFUNIQUE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MagicPower( p, magicNumber), magicNumber));
        // Casts Ultima

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new RuinStaff();
    }
}
