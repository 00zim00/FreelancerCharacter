package ffzim.cards.BlueMage.BlueMagic;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.SelfDestructPower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class SelfDestruct extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SelfDestruct",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            Freelancer.Enums.FFcardColor); //changed from MyCharacter.Enums.CARD_COLOR

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 25;
    private static final int UPG_DAMAGE = 20;
    public SelfDestruct() {
        super(cardInfo, true);
        setDamage(DAMAGE, UPG_DAMAGE);
        tags.add(CustomTags.FFBLUEMAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new SelfDestructPower(p, this.damage)));
        addToBot(new PressEndTurnButtonAction());
        //addToBot(new ApplyPowerAction(p, p, new SelfDestructInvisPower(p, 0)));
    }


    public AbstractCard makeCopy() {
        return new SelfDestruct();
    }
}
