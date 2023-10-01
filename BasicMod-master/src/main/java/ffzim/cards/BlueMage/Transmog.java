package ffzim.cards.BlueMage;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Transmog extends BaseCard {

    private static final CardInfo cardInfo = new CardInfo(
            "Transmog",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    public Transmog() {
        super(cardInfo, true);
        this.baseMagicNumber = 1;
        this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard transmogCard = this; // Store the current instance of Transmog card
        AbstractCard cardToTransmute = null;
        for (AbstractCard card : p.hand.group) {
            if (card != transmogCard) {
                cardToTransmute = card;
                break; // Stop searching after finding the first valid card
            }
        }



        //String cardName = "ffzim:AutoLife2";
        //addToBot(new TransmuteAction());

//        AbstractCard cardName = new AutoLife2();
//        TransmuteAction2 transmuteAction = new TransmuteAction2(cardName);
//        AbstractDungeon.actionManager.addToBottom(transmuteAction);

        //TransmuteAction2 transmuteAction = new TransmuteAction2(cardName);
        //AbstractDungeon.actionManager.addToBottom(transmuteAction);
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.upgradeBaseCost(this.cost - 1);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    public AbstractCard makeCopy() {
        return new Transmog();
    }
}
