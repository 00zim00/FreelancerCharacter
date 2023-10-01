package ffzim.cards.WhiteMage.Summons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.AstralFirePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class AlexanderDivineJudgment extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AlexanderDivineJudgment",
            3,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAM = 16;
    private static final int UPG_DAM = 8;
    private static final int AstralFireBASE = 4;
    private static final int UPG_AstralFireBASE = 4;
    private static final int astralFireMULTI = 2;
    private int currentMateria = 0;
    public AlexanderDivineJudgment() {
        super(cardInfo, true);
        setDamage(DAM,UPG_DAM);
        setMagic(AstralFireBASE,UPG_AstralFireBASE);
        tags.add(CustomTags.FFSUMMON);
        //Boost power lets Magic power affect this card.
    }

//    @Override
//    public void upgrade() {
//        this.upgradeName();
//        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//        this.initializeDescription();
//    }

    // Damage is x2 for elite and x3 for bosses, x4 for Heart??
    // basically extra damage vs more evil?
    // Does x4 damage vs the exploding 600 hp guy.
    // Summon 3 summons (including carbunkle) and gain holy?


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        int currentAstralFire = 0;
        checkMateria();
//        AstralFirePower astralFirePower = (AstralFirePower) p.getPower(AstralFirePower.POWER_ID);
//        if (astralFirePower != null) {
//            currentAstralFire = astralFirePower.amount;
//        }
        addToBot(new VFXAction(new ScreenOnFireEffect(), 0.2F));
        addToBot(new WaitAction(0.1F));
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));

        addToBot(new ApplyPowerAction(p,p, new AstralFirePower(p, (magicNumber + currentMateria)), (magicNumber + currentMateria), true));
    }

    public void applyPowers() {
        checkMateria();
        AbstractPower astralFire = AbstractDungeon.player.getPower(AstralFirePower.POWER_ID);
        if (astralFire != null)
            astralFire.amount *= (astralFireMULTI);
        super.applyPowers();
        if (astralFire != null)
            astralFire.amount /= (astralFireMULTI);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        checkMateria();
        AbstractPower astralFire = AbstractDungeon.player.getPower(AstralFirePower.POWER_ID);
        if (astralFire != null)
            astralFire.amount *= (astralFireMULTI);
        super.calculateCardDamage(mo);
        if (astralFire != null)
            astralFire.amount /= (astralFireMULTI);
    }

    public void checkMateria() {
        currentMateria = 0;
        if (AbstractDungeon.isPlayerInDungeon()) {
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card.hasTag(CustomTags.FFMATERIA) || card.hasTag(CustomTags.FFSUMMON)) {
                    currentMateria++;
                }
            }
            for (AbstractCard card : AbstractDungeon.player.discardPile.group) {
                if (card.hasTag(CustomTags.FFMATERIA) || card.hasTag(CustomTags.FFSUMMON)) {
                    currentMateria++;
                }
            }
            for (AbstractCard card : AbstractDungeon.player.drawPile.group) {
                if (card.hasTag(CustomTags.FFMATERIA) || card.hasTag(CustomTags.FFSUMMON)) {
                    currentMateria++;
                }
                currentMateria -= 1;
            }
        }
    }




    @Override
    public AbstractCard makeCopy() { //Optional
        return new AlexanderDivineJudgment();
    }
}