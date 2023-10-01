package ffzim.cards.variables;

import basemod.abstracts.CustomSavable;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import ffzim.cards.variables.IsSavedSavable;

import java.util.HashMap;

public class YourCustomSavable implements CustomSavable<Integer> {
    public static int powerPlus = 0;
    public static int speedPlus = 0;
    public static boolean isSaved;
    public YourCustomSavable() {
        // Constructor if needed
    }

    @Override
    public Integer onSave() {
        System.out.println("powerPlus Saved: " + powerPlus);
        return powerPlus;
    }

    @Override
    public void onLoad(Integer saved) {
        if (saved != null) {
            powerPlus = saved;
            System.out.println("powerPlus Loaded: " + powerPlus);

        }

    }

    public static int getpowerPlus() {
        return powerPlus;
    }

}
