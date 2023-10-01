package ffzim.cards.Chocobo.Healer;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.cards.Chocobo.ChocoGuard;
import ffzim.cards.Chocobo.FreeStance;
import ffzim.cards.WhiteMage.Cure;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import java.util.ArrayList;

import static ffzim.BasicMod.makeID;

public class ChocoHealer extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoHealer",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);
    private static final int TEMPHP = 3;
    private static final int UPG_TEMPHP = 3;

    public ChocoHealer() {
        super(cardInfo, true);
        setMagic(TEMPHP, UPG_TEMPHP);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeMagicNumber(UPG_TEMPHP);
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        //validCards.addToTop(new ChocoCure());
        validCards.addToTop(new FreeStance());

        validCards.addToTop(new ChocoSurge());
        AbstractCard chocoCure = new Cure();
        chocoCure.name = "Choco Cure";
        chocoCure.initializeDescription();
        validCards.addToTop(chocoCure);
        validCards.addToTop(new ChocoGuard());
        if (this.upgraded) {
            for (AbstractCard card : validCards.group) {
                card.upgrade();
            }
        }
        ArrayList<AbstractCard> validCardList = new ArrayList<>(validCards.group);
        addToBot(new SelectCardsAction(validCardList,1,"Choose 1 to Play",false,c -> true,cards -> {
            if (!cards.isEmpty()) {
                AbstractCard selectedCard = cards.get(0);
                if (selectedCard.cardID.equals(new FreeStance().cardID)) {
                    PurgeField.purge.set(this, true);
                }
                selectedCard.freeToPlayOnce = true;
                selectedCard.current_y = -200.0F * Settings.scale;
                selectedCard.target_x = Settings.WIDTH / 2.0F;
                selectedCard.target_y = Settings.HEIGHT / 2.0F;
                selectedCard.targetAngle = 0.0F;
                selectedCard.lighten(false);
                selectedCard.drawScale = 0.12F;
                selectedCard.targetDrawScale = 0.75F;
                selectedCard.calculateCardDamage(m);
                selectedCard.applyPowers();
                AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(selectedCard, m));
            }
        }));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoHealer();
    }
}