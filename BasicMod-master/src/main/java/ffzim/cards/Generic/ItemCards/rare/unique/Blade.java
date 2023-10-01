package ffzim.cards.Generic.ItemCards.rare.unique;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;
import static ffzim.helpers.UniqueCard.ApplyUniqueCard;

public class Blade extends BaseCard implements OnObtainCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Blade",
            -2,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.RARE,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);
    private final CardRarity thisRarity = AbstractCard.CardRarity.RARE;
    @Override
    public boolean canUpgrade() {
        return false;
    }
    public Blade() {
        super(cardInfo, false);
        tags.add(CustomTags.FFITEM);
        tags.add(CustomTags.FFUNIQUE);
        tags.add(CustomTags.FFRARE);
        tags.add(CardTags.HEALING);
        ApplyUniqueCard(this,cardInfo.baseId);
        //setDisplayRarity(CardRarity.COMMON);
    }
    @Override
    public void onObtainCard() {
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;
        for (AbstractCard card : masterDeck) {

        }

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Blade();
    }
}
