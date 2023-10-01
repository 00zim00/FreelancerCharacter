package ffzim.cards.WhiteMage.Gems;

import basemod.BaseMod;
import basemod.helpers.CardModifierManager;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.MultiUpgradeCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import ffzim.actions.keywords.MateriaUpgradeAction;
import ffzim.cardModifiers.MateriaMod;
import ffzim.cards.BaseCard;
import ffzim.cards.BlackMage.Fira;
import ffzim.cards.BlackMage.Firaga;
import ffzim.cards.BlackMage.Fire;
import ffzim.helpers.MateriaUpdatePreview;
import ffzim.util.CardInfo;
import ffzim.util.CustomTags;

import java.util.ArrayList;
import java.util.List;

import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
import static ffzim.BasicMod.makeID;

public class FireMateria extends BaseCard implements MultiUpgradeCard
{
    private final static CardInfo cardInfo = new CardInfo(
            "FireMateria",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            CardColor.COLORLESS); 


    public static final String ID = makeID(cardInfo.baseId);
    private boolean isInPreview;
    public int baseCurrentAP;
    private static final int baseLevel1AP = 30;
    private static final int baseLevel2AP = 90;
    private static boolean checkedInBattle = false;

    public FireMateria() {
        this(true);
    }

    public FireMateria(boolean makePreview) {
        super(cardInfo, false);
        this.misc = 0;
        this.baseMagicNumber = this.misc;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = this.misc;
        AbstractCard card1 = new Fire(false);
        AbstractCard card2 = new Fira(false);
        AbstractCard card3 = new Firaga(false);
        setCardAp(true, this.misc, baseLevel1AP, baseLevel2AP, -2, -2);
        setSecondMagic(this.getNextApLevel(this));
        APLevelUp(this);
        tags.add(CustomTags.FFMATERIA);
        this.setApUpgradeCards(card1, card2, card3);
        MateriaUpdatePreview.setPreview(this);
        this.isInPreview = makePreview;
        this.setCardLevel(0);
        CardModifierManager.addModifier(this, new MateriaMod());
        super.initializeDescription();
    }


    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
        processUpgrade();
        if (this.timesUpgraded == 1) {
            setSecondMagic(this.getNextApLevel(this));
            this.initializeDescription();
        }
        MateriaUpdatePreview.setPreview(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MateriaUpgradeAction(p, m,this));
    }

    public void addUpgrades() {
        addUpgradeData(() -> {
            if (this.cardLevel == 1){
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            }else{
                this.timesUpgraded = 1;
                this.cardLevel = 1;
                this.misc = baseLevel1AP;
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            }
            this.initializeDescription();
        });
        addUpgradeData(() -> {
            if (this.cardLevel == 2){
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            }else{
                this.timesUpgraded = 2;
                this.cardLevel = 2;
                this.misc = baseLevel2AP;
                this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0];
            }
            this.initializeDescription();
        }, true, 0);
    }

    //@Override
    public void updateName() {

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
        if (AbstractDungeon.isPlayerInDungeon() && AbstractDungeon.getCurrRoom() != null) {
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



    //@Override
    public List<TooltipInfo> getCustomTooltipsTop() {
        if (addToToolTip == null) {
            addToToolTip = new ArrayList<>();
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("materia")), BaseMod.getKeywordDescription(makeID("materia"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("ap")), BaseMod.getKeywordDescription(makeID("ap"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("spell")), BaseMod.getKeywordDescription(makeID("spell"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("Ranged")), BaseMod.getKeywordDescription(makeID("Ranged"))));
            addToToolTip.add(new TooltipInfo(BaseMod.getKeywordTitle(makeID("astral_fire")), BaseMod.getKeywordDescription(makeID("astral_fire"))));
        }
        return addToToolTip;
    }




    @Override
    public AbstractCard makeCopy() { //Optional
        return new FireMateria();
    }
}