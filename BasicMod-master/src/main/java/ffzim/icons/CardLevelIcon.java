package ffzim.icons;

import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import ffzim.util.TextureLoader;

import static ffzim.BasicMod.makeID;
import static ffzim.BasicMod.modID;

public class CardLevelIcon extends AbstractCustomIcon {
    public static final String ID = makeID("CardLevel");

    public static final String ICON_LOCATION = modID + "/icons/CardLevel.png";
    private static CardLevelIcon singleton;

    public CardLevelIcon() {
        super(ID, TextureLoader.getTexture("ICON_LOCATION"));
    }

    public static CardLevelIcon get()
    {
        if (singleton == null) {
            singleton = new CardLevelIcon();
        }
        return singleton;
    }
}