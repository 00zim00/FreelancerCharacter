//package ffzim.cards.variables;
//
//import basemod.abstracts.CustomSavable;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//
//public class PlayerSaveableBU implements CustomSavable<Integer>
//{
//    private static PlayerSaveableBU instance;
//    private AbstractPlayer player;
//    public static int powerPlus = 0;
//    public static int mindPlus = 0;
//    public static int speedPlus = 0;
//    public static int SomaDrop = 0;
//
//
//    public PlayerSaveableBU(AbstractPlayer player, int powerPlus) {
//        this.player = player;
//        //this.powerPlus = powerPlus;
//    }
//
//    public AbstractPlayer getSaveablePlayer() {
//        return player;
//    }
//
//    public static PlayerSaveableBU getInstance(AbstractPlayer player) {
//        if (instance == null) {
//            instance = new PlayerSaveableBU(player, 0);
//        }
//        return instance;
//    }
//    public int getPowerPlus(AbstractPlayer player) {
//        return powerPlus;
//    }
//
//    public void setPowerPlus(AbstractPlayer player, Integer savedPowerPlus) {
//        powerPlus = savedPowerPlus;
//        this.player = player;
//    }
//
//    public void increasePowerPlus(Integer savedPowerPlus) {
//        powerPlus += savedPowerPlus;
//    }
//    public Integer onSave() {
//        if(AbstractDungeon.player != null){
//            if (AbstractDungeon.player.saveFileExists()) {
//                return getPowerPlus(AbstractDungeon.player);
//            }else{
//                setPowerPlus(AbstractDungeon.player, 0);
//                return 0;
//            }
//        }
//        return 0; // Return the powerPlus value as an int
//    }
//
//    public void onLoad(Integer savedPowerPlus) {
//        if (getSaveablePlayer() == null) {
//            setPowerPlus(AbstractDungeon.player, 0);
//        }
//
//        if (savedPowerPlus != null) {
//            setPowerPlus(AbstractDungeon.player, savedPowerPlus);
//            powerPlus = savedPowerPlus; // Load the saved powerPlus value
//        }else { setPowerPlus(AbstractDungeon.player, 0); }
//    }
//
//}
