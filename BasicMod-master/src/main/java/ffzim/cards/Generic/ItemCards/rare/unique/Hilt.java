package ffzim.cards.Generic.ItemCards.rare.unique;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import ffzim.cards.BaseCard;
import ffzim.cards.Generic.ItemCards.Excalibur;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;
import static ffzim.helpers.UniqueCard.ApplyUniqueCard;

public class Hilt extends BaseCard implements OnObtainCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "Hilt",
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

    public Hilt() {
        super(cardInfo, false);
        this.misc = 0;
        tags.add(CustomTags.FFITEM);
        tags.add(CustomTags.FFUNIQUE);
        tags.add(CustomTags.FFRARE);
        tags.add(CardTags.HEALING);
        //setCardUnique(true);
        ApplyUniqueCard(this,cardInfo.baseId);
        //setDisplayRarity(thisRarity);

//        if(AbstractDungeon.player != null) {
//            UniqueSaveable uniqueSaveable = UniqueSaveable.getInstance(AbstractDungeon.player);
//            if (UniqueSaveable.Hilt == 1 && this.misc == 0) {
//                this.rarity = CardRarity.SPECIAL;
//            } else {
//                this.misc = 1;
//                setMagic(this.misc);
//                this.rarity = thisRarity;
//                uniqueSaveable.setUniqueIsSeen(AbstractDungeon.player, cardInfo.baseId, 1);
//            }
//        }
    }

    @Override
    public void onObtainCard() {
        ArrayList<AbstractCard> masterDeck = AbstractDungeon.player.masterDeck.group;
        for (AbstractCard bladeCard : masterDeck) {
            if (bladeCard.cardID.equals(makeID("Blade"))) {
                AbstractDungeon.player.masterDeck.removeCard(bladeCard);

                AbstractCard cardToObtain = new Excalibur();
                AbstractDungeon.effectList.add(new BorderLongFlashEffect(Color.LIGHT_GRAY));
                ShowCardAndObtainEffect s = new ShowCardAndObtainEffect(cardToObtain, (float) Settings.WIDTH * 3.0f / 4.0F, (float)Settings.HEIGHT / 2.0F);
                s.duration = 1.4f;
                s.startingDuration = 1.4f;
                AbstractDungeon.effectList.add(s);
                AbstractDungeon.effectList.add(new UpgradeShineEffect( (float) Settings.WIDTH * 3.0f / 4.0F, (float)Settings.HEIGHT / 2.0F));

                for (AbstractCard thisCard : masterDeck) {
                    if (thisCard instanceof Hilt) {
                        AbstractDungeon.player.masterDeck.removeCard(thisCard);
                        break;
                    }
                }
            }
        }
    }



    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        System.out.println("UseMisc: " + this.misc);
    }

    public void applyPowers() {
        //this.baseBlock = this.misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() { //Optional

        return new Hilt();
    }

    @Override
    public void initializeDescription() {
        // Modify the card's name, description, or other attributes here.
//        if (this.misc == 1) {
//            this.rarity = thisRarity;
//        }
        super.initializeDescription();
    }
}
