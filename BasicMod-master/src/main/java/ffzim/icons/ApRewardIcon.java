package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class ApRewardIcon extends AbstractCustomIcon {
    public static final String ID = makeID("ApReward");

    public static final String ICON_LOCATION = modID + "/icons/ApReward.png";
    private static ApRewardIcon singleton;

    public ApRewardIcon() {
        super(ID, TextureLoader.getTexture(ICON_LOCATION));
    }

    public static ApRewardIcon get()
    {
        if (singleton == null) {
            singleton = new ApRewardIcon();
        }
        return singleton;
    }
}