package ffzim.cards.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ffzim.cards.BaseCard;

public class PlayerSaveable implements CustomSavable<Integer>
{
    private static PlayerSaveable instance;
    private AbstractPlayer player;
    public static int powerPlus = 0;
    public static int mindPlus = 0;
    public static int speedPlus = 0;
    public static int luckPlus = 0;
    public static int somaDrop = 0;


    public PlayerSaveable(AbstractPlayer player, int powerPlus) {
        this.player = player;
        //this.powerPlus = powerPlus;
    }

    public AbstractPlayer getSaveablePlayer() {
        return player;
    }

    public static PlayerSaveable getInstance(AbstractPlayer player) {
        if (instance == null) {
            instance = new PlayerSaveable(player, 0);
        }
        return instance;
    }
    public int getPowerPlus(AbstractPlayer player) {
        return powerPlus;
    }

    public void setPowerPlus(AbstractPlayer player, Integer savedPowerPlus) {
        powerPlus = savedPowerPlus;
        this.player = player;
    }

    public void setMindPlus(AbstractPlayer player, Integer savedMindPlus) {
        mindPlus = savedMindPlus;
        this.player = player;
    }
    public void setSpeedPlus(AbstractPlayer player, Integer savedSpeedPlus) {
        speedPlus = savedSpeedPlus;
        this.player = player;
    }
    public void setLuckPlus(AbstractPlayer player, Integer savedLuckPlus) {
        luckPlus = savedLuckPlus;
        this.player = player;
    }
    public void setSomaDrop(AbstractPlayer player, Integer savedSomaDrop) {
        somaDrop = savedSomaDrop;
        this.player = player;
    }

    public void increasePowerPlus(int value) {
        powerPlus += value;
    }

    public void increaseMindPlus(int value) {
        mindPlus += value;
    }

    public void increaseSpeedPlus(int value) {
        speedPlus += value;
    }

    public void increaseLuckPlus(int value) {
        luckPlus += value;
    }

    public void increaseSomaDrop(int value) {
        somaDrop += value;
    }

    public Integer onSave() {
        if(AbstractDungeon.player != null){
            if (AbstractDungeon.player.saveFileExists()) {
                int savedValues;
                savedValues = (powerPlus << 24) | (mindPlus << 16) | (speedPlus << 8) | (luckPlus << 4) | somaDrop;                //return getPowerPlus(AbstractDungeon.player);
                return savedValues;
            }else{
                setPowerPlus(AbstractDungeon.player, 0);
                setMindPlus(AbstractDungeon.player, 0);
                setSpeedPlus(AbstractDungeon.player, 0);
                setLuckPlus(AbstractDungeon.player, 0);
                setSomaDrop(AbstractDungeon.player, 0);
                return 0;
            }
        }
        return 0;
    }

    public void onLoad(Integer savedValues) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
                validCards.addToTop(card);
            }
        }
        if (!validCards.isEmpty()) {
            for (int i = 0; i < validCards.size(); i++) {
                AbstractCard card = validCards.group.get(i);
                ((BaseCard) card). APLevelUp(card);
                card.applyPowers();
            }
        }

        if (getSaveablePlayer() == null) {
            System.out.println("Reaload1: ");
            System.out.println("Load1 test 1: " +powerPlus);
            setPowerPlus(AbstractDungeon.player, 0);
            setMindPlus(AbstractDungeon.player, 0);
            setSpeedPlus(AbstractDungeon.player, 0);
            setLuckPlus(AbstractDungeon.player, 0);
            setSomaDrop(AbstractDungeon.player, 0);
        }

        if (savedValues != null) {
            System.out.println("Reaload2: ");
            System.out.println("Load1 test 2: " +powerPlus);
            powerPlus = (savedValues >> 24) & 0xFF;
            mindPlus = (savedValues >> 16) & 0xFF;
            speedPlus = (savedValues >> 8) & 0xFF;
            luckPlus = (savedValues >> 4) & 0xF; // Extract the luckPlus value (4 bits)
            somaDrop = savedValues & 0xF; // Extract the SomaDrop value (4 bits)
            setPowerPlus(AbstractDungeon.player, Math.min(powerPlus, 99));
            setMindPlus(AbstractDungeon.player, Math.min(mindPlus, 99));
            setSpeedPlus(AbstractDungeon.player, Math.min(speedPlus, 99));
            setLuckPlus(AbstractDungeon.player, Math.min(luckPlus, 99));
            setSomaDrop(AbstractDungeon.player, Math.min(somaDrop, 99));
            System.out.println("Load3 test 3: " +powerPlus);
        }else {
            System.out.println("Reaload1: ");
            System.out.println("Load1 test 3: ");
            setPowerPlus(AbstractDungeon.player, 0);
            setMindPlus(AbstractDungeon.player, 0);
            setSpeedPlus(AbstractDungeon.player, 0);
            setLuckPlus(AbstractDungeon.player, 0);
            setSomaDrop(AbstractDungeon.player, 0);
        }
    }

}
