package ffzim.cards.WhiteMage;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class Holy extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Holy",
            -1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int TEMPHP = 4;
    private static final int UPG_TEMPHP = 4;
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 2;

    public Holy() {
        this(true);
    }

    public Holy(boolean makePreview) {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(TEMPHP, UPG_TEMPHP);
        setSecondMagic(DAMAGE, UPG_DAMAGE);
        tags.add(CustomTags.FFSPELL);
    }


//    @Override
//    public List<TooltipInfo> getCustomTooltipsTop() {
//        if (TrapTooltip == null)
//        {
//            TrapTooltip = new ArrayList<>();
//            TrapTooltip.add(new TooltipInfo(BaseMod.getKeywordTitle("moonworks:trap"), BaseMod.getKeywordDescription("moonworks:trap")));
//        }
//        List<TooltipInfo> compoundList = new ArrayList<>(TrapTooltip);
//        if (super.getCustomTooltipsTop() != null) compoundList.addAll(super.getCustomTooltipsTop());
//        return compoundList;
//    }

    // Half for minon
    // Damage is x2 for elite and x3 for bosses, x4 for Heart??
    // basically extra damage vs more evil?
    // Does x4 damage vs the exploding 600 hp guy.
    // Summon 3 summons (including carbunkle) and gain holy?

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!this.freeToPlayOnce)
            p.energy.use(EnergyPanel.totalCount);
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        //addToBot(new AddTemporaryHPAction(p, p, magicNumber));
    }

//    public void applyPowers() {
//        spellApplyPowers(this);
//        super.applyPowers();
//        spellApplyPowers2(this);
//    }

    public void calculateCardDamage(AbstractMonster mo) {
//        spellCalculateCardDamage(this,mo);
//        super.calculateCardDamage(mo);
//        spellCalculateCardDamage2(this,mo);

        int baseDamage = this.baseDamage; // Store the original base damage
        super.calculateCardDamage(mo); // This will calculate the damage normally
        if (mo.hasPower("Minion")) {
            this.damage = (int) (this.damage * 0.5f);
        } else if (mo.type == AbstractMonster.EnemyType.ELITE) {
            this.damage = (int) (this.damage * 1.5f);
        } else if (mo.id.equals("Transient")) {
            this.damage = (int) (this.damage * 1.5f);
        } else if (mo.type == AbstractMonster.EnemyType.BOSS) {
            if (mo.id.equals("CorruptHeart")) {
                this.damage = (int) (this.damage * 2.5f);
            } else {
                this.damage = (int) (this.damage * 2f);
            }
        }
        AbstractPlayer p = AbstractDungeon.player;
        int currentEnergy = EnergyPanel.totalCount;
        if (p.hasRelic("Chemical X")) {
            EnergyPanel.totalCount += 2;
        }
        this.damage = this.damage * currentEnergy;
        this.isDamageModified = this.damage != baseDamage; // Set isDamageModified flag
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new Holy();
    }
}