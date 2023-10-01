package ffzim.cards.variables;

import basemod.abstracts.CustomSavable;

public class IsSavedSavable implements CustomSavable<Integer> {
    public static int isSaved = 0;
    public IsSavedSavable() {
        // Constructor if needed
    }

    @Override
    public Integer onSave() {
        isSaved = 1;
        return isSaved;
    }

    @Override
    public void onLoad(Integer saved) {
        if (saved != null) {
            isSaved = saved;
        }
    }

    public int getIsSaved() {
        return isSaved;
    }

}
