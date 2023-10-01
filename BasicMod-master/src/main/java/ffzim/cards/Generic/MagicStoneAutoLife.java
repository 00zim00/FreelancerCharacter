package ffzim.cards.Generic;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.actions.CustomWaitAction;
import ffzim.actions.MagicStoneAdditionAction;
import ffzim.actions.MagicStoneActivateAction;
import ffzim.cards.BaseCard;
import ffzim.cards.BlueMage.BlueMagic.AutoLife;
import ffzim.character.Freelancer;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class MagicStoneAutoLife extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MagicStoneAutoLife",
            1,
            CardType.SKILL,
            CardTarget.NONE,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);

    public int Equip_Vaule = -1;
    private static final int MAX_Equip = ((AutoLife) new AutoLife()).MAX_Equip;;
    private boolean isInPreview;
    public MagicStoneAutoLife() {this(true);}
    public MagicStoneAutoLife(boolean makePreview) {
        super(cardInfo, true);
        //this.misc = 0;
        setSecondMagic(MAX_Equip);
        this.misc = secondMagicNumber;
        this.baseMagicNumber = this.misc;
        System.out.println("this.misc69: " +  isInPreview);
        if (makePreview) {
            cardsToPreview = new AutoLife(false,0);
        }
        this.isInPreview = makePreview;
        //PersistFields.setBaseValue(this, 6);
        setExhaust(true);
        //CardModifierManager.addModifier(this,new AutoCardMod(ACTIVE_NEEDED));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            if (isInPreview) {
                this.cardsToPreview.upgrade();
            }
            //this.upgradedSecondMagicNumber = true;
            //this.misc = misc + UPG_MAX_Equip;
            this.upgradedCost = true;
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction(modID + ":Equip"));
        AbstractCard cardToAdd =new AutoLife(false,1);
        AbstractCard cardToPlay = cardToAdd.makeSameInstanceOf();
        ((AutoLife) cardToAdd).isActive = true;
        //cardToAdd.misc += 1;
        if (this.upgraded) {
            cardToAdd.upgrade();
            Equip_Vaule = -2;
        }
        addToBot(new MagicStoneAdditionAction(this.Equip_Vaule, this.uuid));
        addToBot(new CustomWaitAction(0.5f));
        addToBot(new MagicStoneActivateAction(p,p,cardToAdd,this,this.secondMagicNumber, cardToPlay));

    }

    public void applyPowers() {
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        initializeDescription();
    }



    @Override
    public AbstractCard makeCopy() { //Optional
        return new MagicStoneAutoLife();
    }
}