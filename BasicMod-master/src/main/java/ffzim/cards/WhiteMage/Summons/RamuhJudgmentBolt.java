package ffzim.cards.WhiteMage.Summons;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.FFThunderAction;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class RamuhJudgmentBolt extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "RamuhJudgmentBolt",
            1,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAM = 2;
    private static final int UPG_DAM = 1;
    private static final int Times = 4;
    private static final int UPG_Times = 4;
    private int currentMateria = 0;
    public RamuhJudgmentBolt() {
        super(cardInfo, true);
        setDamage(DAM,UPG_DAM);
        checkMateria();
        setMagic(Times+currentMateria);
        setSecondMagic(damage*Times);
        tags.add(CustomTags.FFSUMMON);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        checkMateria();
        magicNumber = Times+currentMateria;
        secondMagicNumber = damage*Times;
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new FFThunderAction(this));
        }
        //addToBot(new VFXAction(new ScreenOnFireEffect(), 0.2F));
        //addToBot(new WaitAction(0.1F));
        addToBot(new DamageAction(m, new DamageInfo(p, secondMagicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
    }

    public void applyPowers() {
        checkMateria();
        setMagic(Times+currentMateria);
        setSecondMagic(damage*Times);
        super.applyPowers();
    }
    public void calculateCardDamage(AbstractMonster mo) {
        checkMateria();
        setMagic(Times+currentMateria);
        setSecondMagic(damage*Times);
        super.calculateCardDamage(mo);
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
        return new RamuhJudgmentBolt();
    }
}