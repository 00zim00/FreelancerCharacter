//package ffzim.character;
//
//import basemod.abstracts.CustomSavable;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.SaveHelper;
//import java.io.*;
//
//import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.player;
//import static ffzim.character.MyCharacter.saveableBasic;
//
//public class PowerPlusSavable implements CustomSavable<Integer> {
//    private int powerPlusValue;
//
//    @Override
//    public Integer onSave() {
//        return powerPlusValue;
//    }
//
//    @Override
//    public void onLoad(Integer savedValue) {
//        saveableBasic = true;
//        if (savedValue != null) {
//            powerPlusValue = savedValue;
//        }
//    }
//
//    public void setPowerPlusValue(int value) {
//        powerPlusValue = value;
//    }
//
//    public int getPowerPlusValue(AbstractPlayer player) {
//        int powerPlusSavable = MyCharacter.powerPlusSavable.get(player);
//        if (powerPlusSavable == 0) {
//            MyCharacter.powerPlusSavable.set(player,0);
//        }
//        powerPlusSavable = MyCharacter.powerPlusSavable.get(player);
//        return powerPlusSavable;
//    }
//}
