package ffzim.util;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import static ffzim.BasicMod.modID;

public class ApReward extends CustomReward {
    private static final Texture ICON = new Texture(Gdx.files.internal(modID + "/icons/ApReward.png"));

    public static final String ICON_LOCATION = modID + "/icons/ApReward.png";

    public int amount;

    public ApReward(int amount) {
        super(ICON, "Gained " + amount + " AP this Combat.", ApRewardTypePatch.FF_AP_REWARD);
        this.amount = amount;
    }

    @Override
    public boolean claimReward() {
        return false;
    }
}