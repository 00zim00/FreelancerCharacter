//package ffzim.cards.variables;
//
//import basemod.abstracts.CustomSavable;
//
//public class YourCustomSavableBU implements CustomSavable<Integer> {
//    public static int powerPlus = 0;
//    public static int speedPlus = 0;
//    public static boolean isSaved;
//    public YourCustomSavableBU() {
//        // Constructor if needed
//    }
//
//    @Override
//    public Integer onSave() {
//        System.out.println("powerPlus Saved: " + powerPlus);
//        return powerPlus;
//    }
//
//    @Override
//    public void onLoad(Integer saved) {
//        if (saved != null) {
//            powerPlus = saved;
//            System.out.println("powerPlus Loaded: " + powerPlus);
//
//        }
//
//    }
//
//    public int getpowerPlus() {
//        return powerPlus;
//    }
//
//}
