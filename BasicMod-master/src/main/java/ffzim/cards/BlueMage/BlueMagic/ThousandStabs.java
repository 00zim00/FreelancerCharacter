package ffzim.cards.BlueMage.BlueMagic;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class ThousandStabs extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ThousandStabs",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);

    public ThousandStabs() {
        super(cardInfo, true);
        this.baseDamage = 1;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.baseDamage; i++) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_BOOK_STAB_" + MathUtils.random(0, 3)));
            int roll = AbstractDungeon.aiRng.random(2);
            if (roll == 0) {
                addToBot(new DamageAction(m, new DamageInfo(p, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }else{
                addToBot(new DamageAction(m, new DamageInfo(p, this.magicNumber, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
        addToBot(new ModifyDamageAction(this.uuid, +1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new ThousandStabs();
    }
}
