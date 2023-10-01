package ffzim.cards.Chocobo;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.MultiUpgradeCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import ffzim.cardModifiers.MateriaMod;
import ffzim.cards.BaseCard;
import ffzim.cards.Chocobo.attacker.ChocoBeak;
import ffzim.cards.Chocobo.attacker.ChocoBlast;
import ffzim.cards.Chocobo.attacker.ChocoStrike;
import ffzim.cards.Chocobo.attacker.ChocoUnleash;
import ffzim.character.Freelancer;
import ffzim.helpers.MateriaUpdatePreview;
import ffzim.util.CardInfo;

import java.util.ArrayList;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;

public class ChocoAttacker extends BaseCard implements MultiUpgradeCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "ChocoAttacker",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            Freelancer.Enums.FFcardColor);



    public static final String ID = makeID(cardInfo.baseId);
    private boolean isInPreview;
    private static final int BASEAP = 10;
    private static final float AP_MULTI = 1.5F;
    private int CardLevel;
    public CardGroup ChocoAttackerCardsGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private final AbstractCard CardOption1 = new ChocoStrike();
    private final AbstractCard CardOption2 = new ChocoBeak();
    private final AbstractCard CardOption3 = new ChocoBlast();
    private final AbstractCard CardOption4 = new ChocoUnleash();
    private static boolean checkedInBattle = false;
    public ChocoAttacker() {
        this(true);
    }
    public ChocoAttacker(boolean makePreview) {
        super(cardInfo, true);
        this.misc = 0;
        setSecondMagic(getChocoboRankApLevel());
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = this.misc;
        this.setCardLevel(getChocoAttackLevel());
        setCardsOptions();
        //this.timesUpgraded = getChocoAttackLevel();
        this.setApUpgradeCards(CardOption1,CardOption2,CardOption3,CardOption4);
        APLevelUp(this);
        //MateriaUpdatePreview.setPreview(this);
        this.isInPreview = makePreview;
        exhaust = true;
        CardModifierManager.addModifier(this, new MateriaMod());
        super.initializeDescription();
    }

    // Could just have a custom pop up menu at camp fire where it lets u pick a card and sets a vaule on this card based on it.
    // can just do it with misc easy enough and split it.

    public void setCardsOptions(){
        ChocoAttackerCardsGroup.clear();
        ChocoAttackerCardsGroup.addToTop(CardOption1);
        ChocoAttackerCardsGroup.addToTop(CardOption2);
        ChocoAttackerCardsGroup.addToTop(CardOption3);
        ChocoAttackerCardsGroup.addToTop(CardOption4);
    }


    @Override
    public void upgrade() {
        processUpgrade();
        if (!this.upgraded && timesUpgraded >= 3) {
            this.upgradeName();
            exhaust = false;
            ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
            ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
            this.rawDescription = cardStrings.DESCRIPTION;
            this.initializeDescription();
        }
    }

    public void addUpgrades() {
        addUpgradeData(() -> {

        });
        addUpgradeData(() -> {

        });
        addUpgradeData(() -> {

        });
        addUpgradeData(() -> {

        });
        addUpgradeData(() -> {

        });
    }

    public void updateName() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int CardLevel = this.cardLevel;
        if (CardLevel >= 3){
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, CardLevel), CardLevel));
            addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, CardLevel), CardLevel));
        }

        CardGroup validCardList = ChocoAttackerCardsGroup;
        CardGroup gainedCardList = gainedChocoboCards;
        System.out.println("validCardList: " + validCardList);
        System.out.println("gainedCardList: " + gainedCardList);
        System.out.println("isEmpty: " + (!validCardList.isEmpty()));
        if (!validCardList.isEmpty() && gainedCardList != null) {
            if (gainedCardList.isEmpty()){
                validCardList.clear();
            }
            ArrayList<AbstractCard> cardsToRemove = new ArrayList<>();
            for (AbstractCard gainedCard : gainedCardList.group) {
                System.out.println("gainedCard " + validCardList);
                for (AbstractCard validCard : validCardList.group) {
                    if (!validCard.cardID.equals(gainedCard.cardID)) {
                        cardsToRemove.add(validCard);
                        System.out.println("Found matching card: " + gainedCard.cardID);
                    }
                }
            }
            validCardList.group.removeAll(cardsToRemove);
        }
        System.out.println("gainedChocoboCards.group: " + gainedChocoboCards);
        System.out.println("gainedChocoboCards.group: " + gainedChocoboCards);
        if (!validCardList.isEmpty()) {
        AbstractDungeon.gridSelectScreen.open(validCardList, 1, "Choose 1 to Play", false, false, true, false);
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                        AbstractCard selectedCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
//                        if (selectedCard.cardID.equals(new FreeStance().cardID)) {
//                            PurgeField.purge.set(this, true);
//                        }
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
                    this.isDone = true;
                }
        });
        }
    }

    public void applyPowers() {
        this.baseBlock = this.misc;
        super.applyPowers();
        MateriaUpdatePreview.setPreview(this);
        initializeDescription();
    }

    public void initializeDescription() {
        APRefreshVaules(this);
        checkInBattle();
        MateriaUpdatePreview.setPreview(this,true);
        super.initializeDescription();
    }

    public void checkInBattle() {
        if (AbstractDungeon.isPlayerInDungeon()) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !checkedInBattle) {
                for (AbstractCard c : GetAllInBattleInstances.get(this.uuid)) {
                    if (c != null){
                        horizontal.set(this, true);
                        checkedInBattle = true;
                    }
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ChocoAttacker();
    }
}