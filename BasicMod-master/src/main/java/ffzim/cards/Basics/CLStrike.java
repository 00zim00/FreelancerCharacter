package ffzim.cards.Basics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
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

public class CLStrike extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CLStrike",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static final int AOEDAMAGE = 1;
    private static final int UPG_AOEDAMAGE = 1;

    public CLStrike() {
        super(cardInfo, true);

        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(AOEDAMAGE,UPG_AOEDAMAGE);
        tags.add(CustomTags.FFSPELL);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CustomTags.FFJOBSTRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster != m) {
                addToBot(new DamageAction(monster, new DamageInfo(p, magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
            }
        }

    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CLStrike();
    }
}