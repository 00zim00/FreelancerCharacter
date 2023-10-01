package ffzim.cards.variables;

import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static ffzim.BasicMod.makeID;

public class UniqueSaveable implements CustomSavable<Integer>
{
    private static UniqueSaveable instance;
    private AbstractPlayer player;
    public static int Hilt = 0;      // Bit 7
    public static int Blade = 0;      // Bit 6
    public static int Excalibur = 0;   // Bit 5
    public static int PowerPlus = 0;    // Bit 4
    public static int MindPlus = 0;      // Bit 3
    public static int SpeedPlus = 0;      // Bit 2
    public static int LuckPlus = 0;        // Bit 1
    public static int SomaDrop = 0;        // Bit 0

    public UniqueSaveable(AbstractPlayer player, int uniqueItemSeen) {
        this.player = player;
    }
    public AbstractPlayer getUniqueSaveablePlayer() {
        return player;
    }

    public static UniqueSaveable getInstance(AbstractPlayer player) {
        if (instance == null) {
            instance = new UniqueSaveable(player, 0);
        }
        return instance;
    }
    public static int getUniqueIsSeen(String itemName) {
        int itemFlag = 0;

        if (makeID("Hilt").equals(itemName)) {
            itemFlag = Hilt;
        } else if (makeID("Blade").equals(itemName)) {
            itemFlag = Blade;
        } else if (makeID("Excalibur").equals(itemName)) {
            itemFlag = Excalibur;
        } else if (makeID("PowerPlus").equals(itemName)) {
            itemFlag = PowerPlus;
        } else if (makeID("MindPlus").equals(itemName)) {
            itemFlag = MindPlus;
        } else if (makeID("SpeedPlus").equals(itemName)) {
            itemFlag = SpeedPlus;
        } else if (makeID("LuckPlus").equals(itemName)) {
            itemFlag = LuckPlus;
        } else if (makeID("SomaDrop").equals(itemName)) {
            itemFlag = SomaDrop;
        }
        return (itemFlag) != 0 ? 1 : 0;
    }

    public void setUniqueIsSeen(AbstractPlayer player, String itemName, int flagValue) {
        switch (itemName) {
            case "Hilt":
                Hilt = flagValue;
                break;
            case "Blade":
                Blade = flagValue;
                break;
            case "Excalibur":
                Excalibur = flagValue;
                break;
            case "PowerPlus":
                PowerPlus = flagValue;
                break;
            case "MindPlus":
                MindPlus = flagValue;
                break;
            case "SpeedPlus":
                SpeedPlus = flagValue;
                break;
            case "LuckPlus":
                LuckPlus = flagValue;
                break;
            case "SomaDrop":
                SomaDrop = flagValue;
                break;
            default:
                // Handle invalid item names or other cases if needed
                break;
        }
    }

    public void setAllItemQuantitiesToZero(AbstractPlayer player) {
        int flagValue = 0;
        setUniqueIsSeen(AbstractDungeon.player,"Hilt", flagValue);
        setUniqueIsSeen(AbstractDungeon.player,"Blade", flagValue);
        setUniqueIsSeen(AbstractDungeon.player,"Excalibur", flagValue);
        setUniqueIsSeen(AbstractDungeon.player,"PowerPlus", flagValue);
        setUniqueIsSeen(AbstractDungeon.player,"MindPlus", flagValue);
        setUniqueIsSeen(AbstractDungeon.player,"SpeedPlus", flagValue);
        setUniqueIsSeen(AbstractDungeon.player,"LuckPlus", flagValue);
        setUniqueIsSeen(AbstractDungeon.player,"SomaDrop", flagValue);
        this.player = player;
    }

    public Integer onSave() {
        if(AbstractDungeon.player != null){
            if (AbstractDungeon.player.saveFileExists()) {
                int savedValues;
                savedValues = (Hilt << 7) | (Blade << 6) | (Excalibur << 5) | (PowerPlus << 4) | (MindPlus << 3) | (SpeedPlus << 2) | (LuckPlus << 1) | SomaDrop;
                return savedValues;
            }else{
                setAllItemQuantitiesToZero(AbstractDungeon.player);
                return 0;
            }
        }
        return 0;
    }

    public void onLoad(Integer savedValues) {
        if (getUniqueSaveablePlayer() == null) {
            setAllItemQuantitiesToZero(AbstractDungeon.player);
            System.out.println("Hilt1: " + Hilt);
            setUniqueIsSeen(AbstractDungeon.player,"Hilt", 0);
            System.out.println("Hilt2: " + Hilt);
            System.out.println("Load1: ");
        }
        if (savedValues != null) {
            System.out.println("Load2: ");
            System.out.println("Hilt3: " + Hilt);
            Hilt = (savedValues >> 7) & 1;
            Blade = (savedValues >> 6) & 1;
            Excalibur = (savedValues >> 5) & 1;
            PowerPlus = (savedValues >> 4) & 1;
            MindPlus = (savedValues >> 3) & 1;
            SpeedPlus = (savedValues >> 2) & 1;
            LuckPlus = (savedValues >> 1) & 1;
            SomaDrop = savedValues & 1;
            System.out.println("Hilt4: " + Hilt);
            setUniqueIsSeen(AbstractDungeon.player,"Hilt", Hilt);
            setUniqueIsSeen(AbstractDungeon.player,"Blade", Blade);
            setUniqueIsSeen(AbstractDungeon.player,"Excalibur", Excalibur);
            setUniqueIsSeen(AbstractDungeon.player,"PowerPlus", PowerPlus);
            setUniqueIsSeen(AbstractDungeon.player,"MindPlus", MindPlus);
            setUniqueIsSeen(AbstractDungeon.player,"SpeedPlus", SpeedPlus);
            setUniqueIsSeen(AbstractDungeon.player,"LuckPlus", LuckPlus);
            setUniqueIsSeen(AbstractDungeon.player,"SomaDrop", SomaDrop);
            System.out.println("Hilt5: " + Hilt);
        }else {
            System.out.println("Load3: ");
            setUniqueIsSeen(AbstractDungeon.player,"Hilt", 0);
            setAllItemQuantitiesToZero(AbstractDungeon.player);
        }
    }
}
