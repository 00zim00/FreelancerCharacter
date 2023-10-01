//package ffzim.cards.variables;
//
//import basemod.abstracts.CustomSavable;
//import com.evacipated.cardcrawl.modthespire.lib.SpireField;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//public class SharedCardDataSavable implements CustomSavable<Map<String, Integer>> {
//
//    public static final SharedCardData sharedData = new SharedCardData();
//
//    public static final class SharedCardData {
//        public SpireField<Integer> PowerPlus = new SpireField<>(() -> 0);
//        public SpireField<Integer> MindPlus = new SpireField<>(() -> 0);
//        public SpireField<Integer> SpeedPlus = new SpireField<>(() -> 0);
//        public SpireField<Integer> LuckPlus = new SpireField<>(() -> 0);
//        public SpireField<Integer> SomaDrop = new SpireField<>(() -> 0);
//    }
//
//    public boolean setSaveable;
//
//    @Override
//    public Map<String, Integer> onSave() {
//        // Create a map to store the values
//        Map<String, Integer> savedData = new HashMap<>();
//        savedData.put("PowerPlus", sharedData.PowerPlus.get(AbstractDungeon.player));
//        savedData.put("MindPlus", sharedData.MindPlus.get(AbstractDungeon.player));
//        savedData.put("SpeedPlus", sharedData.SpeedPlus.get(AbstractDungeon.player));
//        savedData.put("LuckPlus", sharedData.LuckPlus.get(AbstractDungeon.player));
//        savedData.put("SomaDrop", sharedData.SomaDrop.get(AbstractDungeon.player));
//        return savedData;
//    }
//
//    @Override
//    public void onLoad(Map<String, Integer> savedData) {
//        // Load your shared data from the saved map and set it to the SpireFields
//        if (AbstractDungeon.player != null) {
//            setSaveable = true;
//            sharedData.PowerPlus.set(AbstractDungeon.player, savedData.getOrDefault("PowerPlus", 0));
//            sharedData.MindPlus.set(AbstractDungeon.player, savedData.getOrDefault("MindPlus", 0));
//            sharedData.SpeedPlus.set(AbstractDungeon.player, savedData.getOrDefault("SpeedPlus", 0));
//            sharedData.LuckPlus.set(AbstractDungeon.player, savedData.getOrDefault("LuckPlus", 0));
//            sharedData.SomaDrop.set(AbstractDungeon.player, savedData.getOrDefault("SomaDrop", 0));
//        }
//    }
//}