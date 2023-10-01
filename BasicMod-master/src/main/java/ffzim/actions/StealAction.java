package ffzim.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ObtainPotionAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import ffzim.powers.EvasionPower;

import java.util.ArrayList;
import java.util.Arrays;

import static ffzim.BasicMod.makeID;

public class StealAction extends AbstractGameAction {
    private static final float DUR = 0.25F;



    public StealAction(AbstractCreature target, AbstractCreature source) {
        setValues(target, source);
        this.target = target;
        this.source = source;
        this.actionType = ActionType.BLOCK;
        this.duration = 0.25F;
    }

    //!this.target.hasPower("Minion")) {

    // Ad a hiden power or tag to enemies who have been robed once. so that cant keep doing it.
    // need a a way to store what items "will" be given so it can be accesed later if need be. eg scan

    public void update() {
        if (this.target.hasPower(makeID("StealTaggingPower"))){

        }

        int roll = AbstractDungeon.aiRng.random(2);
        if (roll == 0) {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_MUGGER_1A"));
        } else {
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_MUGGER_1B"));
        }

        roll = 39;
        //roll = AbstractDungeon.aiRng.random(99) + 1;
        int roll2 = AbstractDungeon.aiRng.random(99) + 1;

        if (!this.target.hasPower("Minion")){
            if (roll >= 40){
                stealGold();
            }else if (roll >= 60){
                addToBot(new ObtainPotionAction(AbstractDungeon.returnRandomPotion(true)));
            }else if (roll >= 80){
                stealCard();
            }else if (roll >= 90){
                //Relic
            }else if (roll >= 95){
                //Relic
            }else if (roll >= 100){
                //Blue Magic
        }

        }
        this.isDone = true;
    }

    public void stealGold()  {
        AbstractPlayer p = (AbstractPlayer) source;
        AbstractMonster m = (AbstractMonster) target;
        int increaseGold;
        if (!(m.type == AbstractMonster.EnemyType.ELITE || m.type == AbstractMonster.EnemyType.BOSS)) {
            increaseGold = AbstractDungeon.aiRng.random(4)+1;
        }else if(!(m.type == AbstractMonster.EnemyType.BOSS)) {
            increaseGold = AbstractDungeon.aiRng.random(4)+6;
        }else{
            increaseGold = AbstractDungeon.aiRng.random(9)+11;
        }
        //AbstractDungeon.effectList.add(new GainGoldTextEffect(increaseGold));
        AbstractDungeon.player.gainGold(increaseGold);
        for (int i = 0; i < increaseGold; i++)
            AbstractDungeon.effectList.add(new GainPennyEffect(this.source, this.target.hb.cX, this.target.hb.cY, this.source.hb.cX, this.source.hb.cY, true));
    }

    public void stealCard() {
        ArrayList<AbstractCard> rewardCards = AbstractDungeon.getRewardCards();
        if (rewardCards != null && !rewardCards.isEmpty()) {
            int randomIndex = AbstractDungeon.cardRng.random(rewardCards.size());
            ArrayList<AbstractCard> chosenCardList = new ArrayList<>(Arrays.asList(rewardCards.get(randomIndex)));
            AbstractDungeon.cardRewardScreen.open(chosenCardList, null, "Steal Success!");
        }
    }
}


