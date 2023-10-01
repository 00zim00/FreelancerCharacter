package ffzim.cards.BlueMage.BlueMagic;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import ffzim.actions.AutoPlayRemoverAction;
import ffzim.cards.BaseCard;
import ffzim.cards.Generic.MagicStoneAutoLife;
import ffzim.character.Freelancer;
import ffzim.powers.AutoLifePower;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import static ffzim.BasicMod.makeID;

public class AutoLife extends BaseCard implements StartupCard, OnObtainCard
{
            private final static CardInfo cardInfo = new CardInfo(
                    "AutoLife",
                    1,
                    CardType.POWER,
                    CardTarget.SELF,
                    CardRarity.RARE,
                    Freelancer.Enums.FFcardColor);

    public static final String ID = makeID(cardInfo.baseId);
    private static final int HPPRECENT = 20;
    private static final int Equip_Vaule = -1;
    private static final int UPG_Equip_Vaule = -2;
    private static final int UPG_HPPRECENT= 10;
    public int MAX_Equip = 9;
    private static final int UPG_MAX_Equip = 1;
    public boolean isActive;
    public static final int ACTIVE_NEEDED = 4;
    private boolean isInPreview;
    public AutoLife() {this(true,0);}
    public AutoLife(boolean makePreview, int miscValue) {
        super(cardInfo, true);
        this.misc = miscValue;
        if (this.misc > 0) isActive = true;
        setMagic(HPPRECENT);
        setSecondMagic(MAX_Equip);
        PurgeField.purge.set(this, true);
        if (this.misc == 0 && makePreview) {
                cardsToPreview = new MagicStoneAutoLife(false);
            }
        this.tags.add(CardTags.HEALING);
        this.costUpgrade = 2;
        tags.add(CustomTags.FFBLUEMAGIC);
        //CardModifierManager.addModifier(this,new AutoCardMod(ACTIVE_NEEDED));
        initializeDescription();
        this.isInPreview = makePreview;
    }

        @Override
        public void upgrade() {
            if (!this.upgraded) {
//                if (AbstractDungeon.isPlayerInDungeon()) {
                if (this.misc == 0 && isInPreview) {
                    this.cardsToPreview.upgrade();
                }
//                }
                this.upgradedCost = true;
                this.upgradeName();
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                this.initializeDescription();
            }
        }


       // Upgrade Idea. if misc is 1 or lower, then remove on one trigger. if 2 then two triggers
    // on the power. If upgraded then dont remove.. but then i need to track it.
    // oh if expented +1 misc. since it cant be in play anyways until misc is 1. so can then track if its 2 then remove it


    @Override
    public void onObtainCard() {
        if (!isActive){
            //if (!this.purgeOnUse) {
                AbstractCard cardToAdd = new MagicStoneAutoLife();
                if (upgraded) {cardToAdd.upgrade();}
                float cardX = Settings.WIDTH / 2.0F;
                float cardY = Settings.HEIGHT / 2.0F;
                AbstractDungeon.topLevelEffectsQueue.add(new ShowCardAndObtainEffect(cardToAdd, cardX, cardY));
                CardCrawlGame.sound.playA("CARD_OBTAIN", MathUtils.random(-0.2F, -0.3F));

            //AbstractDungeon.actionManager.addToBottom(new CustomWaitAction(0.5F));
                AbstractDungeon.player.masterDeck.removeCard(this);
                //ShowCardAndObtainEffect s = new ShowCardAndObtainEffect(cardToAdd, (float) Settings.WIDTH * 3.0f / 4.0F, (float) Settings.HEIGHT / 2.0F);
                //s.duration = 1.4f;
                //s.startingDuration = 1.4f;
                //AbstractDungeon.effectList.add(s);


                //CardModifierManager.addModifier(cardToAdd, new AutoCardMod(this,USES,UPG_USES,ACTIVE_NEEDED));
                //AbstractDungeon.player.masterDeck.addToBottom(cardToAdd.makeCopy());
            //}
        }
    }
    @Override
    public boolean atBattleStartPreDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(new ApplyPowerAction(p, p, new AutoLifePower(p,this,this.secondMagicNumber, this.magicNumber,1), 1));
        addToBot(new AutoPlayRemoverAction(p, p, this));
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AutoLifePower(p,this,this.secondMagicNumber, this.magicNumber,1), 1));
        //addToBot(new AutoPlayRemoverAction(p, p, this));
    }

//    public void initializeDescription(){
//        if (!isActive){
//        this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
//            }
//        super.initializeDescription();
//        }

    @Override
    public AbstractCard makeCopy() {
        return new AutoLife();
    }
}
