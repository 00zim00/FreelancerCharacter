package ffzim.cards.WhiteMage.Summons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.UmbralIcePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class ShivaDiamondDust extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ShivaDiamondDust",
            1,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAM = 14;
    private static final int UPG_DAM = 6;
    private static final int UmbralIceBASE = 8;
    private static final int UPG_UmbralIceBASE = 4;
    private int currentMateria = 0;
    public ShivaDiamondDust() {
        super(cardInfo, true);
        setDamage(DAM,UPG_DAM);
        setMagic(UmbralIceBASE,UPG_UmbralIceBASE);
        tags.add(CustomTags.FFSUMMON);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        checkMateria();
        UmbralIcePower umbralIcePower = (UmbralIcePower) p.getPower(UmbralIcePower.POWER_ID);
        if (umbralIcePower != null) {
            addToBot(new GainBlockAction(p, p, umbralIcePower.amount));
            addToTop(new RemoveSpecificPowerAction(p, p, UmbralIcePower.POWER_ID));
        }

        //addToBot(new VFXAction(new ScreenOnFireEffect(), 0.2F));
        //addToBot(new WaitAction(0.1F));
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        addToBot(new ApplyPowerAction(p,p, new UmbralIcePower(p, (magicNumber + currentMateria)), (magicNumber + currentMateria), true));
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
        return new ShivaDiamondDust();
    }
}