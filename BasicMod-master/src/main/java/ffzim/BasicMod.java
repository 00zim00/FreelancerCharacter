package ffzim;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.DynamicTextBlocks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.mod.stslib.icons.CustomIconHelper;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.Patcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rewards.RewardSave;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import ffzim.actions.keywords.APLevelUpAction;
import ffzim.cards.BaseCard;
//import ffzim.cards.variables.SecondMagicNumber;
import ffzim.cards.RevolvingFields;
import ffzim.cards.WhiteMage.Gems.APUP;
import ffzim.cards.variables.PlayerSaveable;
import ffzim.cards.variables.SecondMagicNumber;
import ffzim.cards.variables.UniqueSaveable;
import ffzim.character.Freelancer;
import ffzim.icons.*;
import ffzim.potions.Tent;
import ffzim.powers.CardTrackerPower;
import ffzim.powers.MagicPower;
import ffzim.util.*;
//import ffzim.cards.variables.MagicNumber2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scannotation.AnnotationDB;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;
import static ffzim.util.ApRewardTypePatch.FF_AP_REWARD;

@SpireInitializer
public class BasicMod implements
        EditCharactersSubscriber, //new
        StartGameSubscriber,
        OnStartBattleSubscriber,
        PostBattleSubscriber,
        EditRelicsSubscriber,
        AddAudioSubscriber,
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber {

    //Start new
    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Freelancer(),
                CHAR_SELECT_BUTTON, CHAR_SELECT_PORTRAIT, Freelancer.Enums.FREELANCER);
    }

    public void receivePostBattle(AbstractRoom battleRoom) {
        boolean haveMateria = false;
        boolean haveApMateria = false;
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.hasTag(CustomTags.FFMATERIA)) {
                haveMateria = true;
                break;
            }
        }
        String ApUpCardID = (new APUP()).cardID;
        for (AbstractCard card : AbstractDungeon.player.masterDeck.group) {
            if (card.cardID.equals(ApUpCardID)) {
                haveApMateria = true;
                break;
            }
        }


            if (haveMateria && AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().rewardAllowed && !getCurrRoom().smoked) {

                int AP = 0;
                int actNum = AbstractDungeon.actNum;
                int ascensionLevel = AbstractDungeon.ascensionLevel;

                if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss) {

                    if (actNum == 1) {
                        AP = 6;
                    }
                    if (actNum == 2) {
                        AP = 9;
                    }
                    if (actNum == 3) {
                        AP = 12;
                    }
                    if (actNum == 4) {
                        AP = 36;
                    }

                } else if (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) {
                    if (actNum == 1) {
                        AP = 3;
                    }
                    if (actNum == 2) {
                        AP = 5;
                    }
                    if (actNum == 3) {
                        AP = 7;
                    }
                    if (actNum == 4) {
                        AP = 14;
                    }
                } else {
                    if (actNum == 1) {
                        AP = 1;
                    }
                    if (actNum == 2) {
                        AP = 2;
                    }
                    if (actNum == 3) {
                        AP = 3;
                    }
                    if (actNum == 4) {
                        AP = 4;
                    }

                }

                double ascensionPercent;
                if (ascensionLevel == 1) {
                    ascensionPercent = 100.0;
                } else if (ascensionLevel == 19) {
                    ascensionPercent = 50.0;
                } else {
                    ascensionPercent = 100.0 - ((ascensionLevel - 1) / 18.0) * 50.0;
                }

                AP = (int) Math.round(AP * (ascensionPercent / 100.0));

                AbstractPlayer p = AbstractDungeon.player;
                CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                for (
                        AbstractCard card : p.masterDeck.group) {
                    if (card instanceof BaseCard && ((BaseCard) card).getUsesAp()) {
                        validCards.addToTop(card);
                    }
                }
                if (!validCards.isEmpty()) {
                    for (int i = 0; i < validCards.size(); i++) {
                        AbstractCard card = validCards.group.get(i);
                        ((BaseCard) card).addAP(card, AP);
                        ((BaseCard) card).applyAP(card);
                        //Need to add it as a action or wait action?

                        //APLevelUp(card);
                        AbstractDungeon.actionManager.addToBottom(new APLevelUpAction(card));
                        card.applyPowers();
                    }
                }

                int escapedMonsterCount = 0;
                int nonMinionMonsterCount = 0;
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (m.escaped && !m.hasPower("Minion")) {
                        escapedMonsterCount++;
                    }
                }

                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (m.escaped && !m.hasPower("Minion")) {
                        nonMinionMonsterCount++;
                    }
                }

                if (escapedMonsterCount >= 1) {
                    int killedMonsters = nonMinionMonsterCount - escapedMonsterCount;
                    float APPercentChange = ((float) nonMinionMonsterCount / 100.0f) * killedMonsters;
                    AP = (int) (AP * APPercentChange);
                }

                if (AP > 0) {
                    battleRoom.rewards.add(new ApReward(AP));
                }
            }
        }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        AbstractPlayer p= player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new CardTrackerPower(player, 1), 1));
        int powerAmount = PlayerSaveable.powerPlus;
        if (powerAmount > 0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,new StrengthPower(player, powerAmount), powerAmount));
        }
        powerAmount = PlayerSaveable.mindPlus;
        if (powerAmount > 0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,new MagicPower(player, powerAmount), powerAmount));
        }
        powerAmount = PlayerSaveable.speedPlus;
        if (powerAmount > 0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,new MagicPower(player, powerAmount), powerAmount));
        }
        powerAmount = PlayerSaveable.luckPlus;
        if (powerAmount > 0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,new MagicPower(player, powerAmount), powerAmount));
        }
        powerAmount = PlayerSaveable.somaDrop;
        if (powerAmount > 0){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,new MagicPower(player, powerAmount), powerAmount));
        }


        for (AbstractCard card : p.drawPile.group) {
            if ((card.hasTag(CustomTags.FFUNIQUE) && (card.hasTag(CustomTags.FFCOMMON) && card.misc == 1))){
                card.rarity = AbstractCard.CardRarity.COMMON;
            }
            if ((card.hasTag(CustomTags.FFUNIQUE) && (card.hasTag(CustomTags.FFUNCOMMON) && card.misc == 1))){
                card.rarity = AbstractCard.CardRarity.UNCOMMON;
            }
            if ((card.hasTag(CustomTags.FFUNIQUE) && (card.hasTag(CustomTags.FFRARE) && card.misc == 1))){
                card.rarity = AbstractCard.CardRarity.RARE;
            }
        }

    }




    @Override
    public void receiveEditRelics() {
        // Apply a patch to modify an existing relic
        //BaseMod.addRelic(new CursedKeyPatch(), RelicType.SHARED);
        BaseMod.addRelic(new ffzim.relics.Freelancer(), RelicType.SHARED);
        //BaseMod.addRelic(new JobBlackMage(), RelicType.SHARED);
    }

    @Override
    public void receiveStartGame() {
        if (BaseCard.gainedChocoboCards == null){
            BaseCard.gainedChocoboCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        }
    }



    @Override
    public void receiveAddAudio() {
        //BaseMod.addAudio(modID + ":Jewel", modID + "/resources/audio/Jewel.mp3");
        BaseMod.addAudio(modID + ":Jewel", modID +"/sounds/Jewel.ogg");
        BaseMod.addAudio(modID + ":Jump", modID +"/sounds/Jump.ogg");
        BaseMod.addAudio(modID + ":SpellLevelUp", modID +"/sounds/SpellLevelUp.ogg");
        BaseMod.addAudio(modID + ":Equip", modID +"/sounds/Equip.ogg");
        BaseMod.addAudio(modID + ":Select", modID +"/sounds/Select.ogg");
        BaseMod.addAudio(modID + ":Confirm", modID +"/sounds/Confirm.ogg");
        //BaseMod.addAudio(modID + ":Jewel", modID + "audio/Jewel.mp3");
    }
    private static final String BG_ATTACK = characterPath("cardback/bg_attack.png");
    private static final String BG_ATTACK_P = characterPath("cardback/bg_attack_p.png");
    private static final String BG_SKILL = characterPath("cardback/bg_skill.png");
    private static final String BG_SKILL_P = characterPath("cardback/bg_skill_p.png");
    private static final String BG_POWER = characterPath("cardback/bg_power.png");
    private static final String BG_POWER_P = characterPath("cardback/bg_power_p.png");
    private static final String ENERGY_ORB = characterPath("cardback/energy_orb.png");
    private static final String ENERGY_ORB_P = characterPath("cardback/energy_orb_p.png");
    private static final String SMALL_ORB = characterPath("cardback/small_orb.png");
    private static final Color ffcardColor = new Color(249f/255f, 188f/255f, 8f/255f, 1f);

    private static final String CHAR_SELECT_BUTTON = characterPath("select/button.png");
    private static final String CHAR_SELECT_PORTRAIT = characterPath("select/portrait.png");
    // End New
    public static ModInfo info;
    public static String modID; //Edit your pom.xml to change this
    static { loadModInfo(); }
    public static final Logger logger = LogManager.getLogger(modID); //Used to output to the console.
    private static final String resourcesFolder = "ffzim";

    //This is used to prefix the IDs of various objects like cards and relics,
    //to avoid conflicts between different mods using the same name for things.
    public static String makeID(String id) {
        return modID + ":" + id;
    }

    //This will be called by ModTheSpire because of the @SpireInitializer annotation at the top of the class.

    public static SpireField<Integer> newPowerPlus = new SpireField<>(() -> 0);

    public static AbstractChest cachedChest0;
    public static String cachedChest = "";
    public static void initialize() {
        new BasicMod();

       //Start new
        //BaseMod.addSaveField(makeID("SharedCardData"), new YourCustomSavable());


        BaseMod.addColor(Freelancer.Enums.FFcardColor, ffcardColor,
                BG_ATTACK, BG_SKILL, BG_POWER, ENERGY_ORB,
                BG_ATTACK_P, BG_SKILL_P, BG_POWER_P, ENERGY_ORB_P,
                SMALL_ORB);
        //End new
    }

    public BasicMod() {
        BaseMod.subscribe(this); //This will make BaseMod trigger all the subscribers at their appropriate times.
        logger.info(modID + " subscribed to BaseMod.");
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SecondMagicNumber());

        CustomIconHelper.addCustomIcon(ApBarEmptyIcon.get());
        CustomIconHelper.addCustomIcon(ApBar20Icon.get());
        CustomIconHelper.addCustomIcon(ApBar40Icon.get());
        CustomIconHelper.addCustomIcon(ApBar50Icon.get());
        CustomIconHelper.addCustomIcon(ApBar60Icon.get());
        CustomIconHelper.addCustomIcon(ApBar80Icon.get());
        CustomIconHelper.addCustomIcon(ApBarFullIcon.get());
        CustomIconHelper.addCustomIcon(CardLevelIcon.get());
        CustomIconHelper.addCustomIcon(ApRewardIcon.get());
        new AutoAdd(modID) // Loads files from this mod
                .packageFilter(BaseCard.class) // In the same package as this class
                .setDefaultSeen(true) // And marks them as seen in the compendium
                .cards(); // Adds the cards

//        new AutoAdd(modID)
//                .packageFilter(Revolving.class) // Make sure to include RevolvingKeyword class in the package filter
//                .setDefaultSeen(true)
//                .cards();
    }
    @Override
    public void receivePostInitialize() {
        DynamicTextBlocks.registerCustomCheck("ffzim:M2", card -> {
            return ((BaseCard) card).secondMagicNumber;
        });

        DynamicTextBlocks.registerCustomCheck("ffzim:R", card -> {
            return RevolvingFields.revolving.get(card);
        });
        AbstractPlayer p= player;
        //int intPowerPlus = BasicMod.newPowerPlus.get(AbstractDungeon.player);
        PlayerSaveable playerSaveable = new PlayerSaveable(null, 0);
        BaseMod.addSaveField("PlayerSaveable", playerSaveable);
        UniqueSaveable uniqueSaveable = new UniqueSaveable(null, 0);
        BaseMod.addSaveField("UniqueSaveable", uniqueSaveable);


        BaseMod.addPotion(Tent.class, Color.WHITE, Color.RED, Color.BLACK, "ffzim:Tent");
        //    BaseMod.addPotion(Tent.class, Color.WHITE, Color.RED, Color.BLACK, "ffzim:Tent");
        //This loads the image used as an icon in the in-game mods menu.
        Texture badgeTexture = TextureLoader.getTexture(resourcePath("badge.png"));
        //Set up the mod information displayed in the in-game mods menu.
        //The information used is taken from your pom.xml file.
        BaseMod.registerModBadge(badgeTexture, info.Name, GeneralUtils.arrToString(info.Authors), info.Description, null);

        BaseMod.registerCustomReward(
                FF_AP_REWARD,
                (rewardSave) -> { // this handles what to do when this quest type is loaded.
                    return new ApReward(rewardSave.amount);
                },
                (customReward) -> { // this handles what to do when this quest type is saved.
                    return new RewardSave(customReward.type.toString(), null, ((ApReward)customReward).amount, 0);
                });

    }

    /*----------Localization----------*/

    //This is used to load the appropriate localization files based on language.
    private static String getLangString()
    {
        return Settings.language.name().toLowerCase();
    }
    private static final String defaultLanguage = "eng";



    @Override
    public void receiveEditStrings() {
        /*
            First, load the default localization.
            Then, if the current language is different, attempt to load localization for that language.
            This results in the default localization being used for anything that might be missing.
            The same process is used to load keywords slightly below.
        */
        loadLocalization(defaultLanguage); //no exception catching for default localization; you better have at least one that works.
        if (!defaultLanguage.equals(getLangString())) {
            try {
                loadLocalization(getLangString());
            }
            catch (GdxRuntimeException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadLocalization(String lang) {
        //While this does load every type of localization, most of these files are just outlines so that you can see how they're formatted.
        //Feel free to comment out/delete any that you don't end up using.
        BaseMod.loadCustomStringsFile(CardStrings.class,
                localizationPath(lang, "CardStrings.json"));
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                localizationPath(lang, "CharacterStrings.json"));
        BaseMod.loadCustomStringsFile(EventStrings.class,
                localizationPath(lang, "EventStrings.json"));
        BaseMod.loadCustomStringsFile(OrbStrings.class,
                localizationPath(lang, "OrbStrings.json"));
        BaseMod.loadCustomStringsFile(PotionStrings.class,
                localizationPath(lang, "PotionStrings.json"));
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                localizationPath(lang, "PowerStrings.json"));
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                localizationPath(lang, "RelicStrings.json"));
        BaseMod.loadCustomStringsFile(UIStrings.class,
                localizationPath(lang, "UIStrings.json"));
    }

    @Override
    public void receiveEditKeywords()
    {
        Gson gson = new Gson();
        String json = Gdx.files.internal(localizationPath(defaultLanguage, "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
        KeywordInfo[] keywords = gson.fromJson(json, KeywordInfo[].class);
        for (KeywordInfo keyword : keywords) {
            registerKeyword(keyword);
        }

        if (!defaultLanguage.equals(getLangString())) {
            try
            {
                json = Gdx.files.internal(localizationPath(getLangString(), "Keywords.json")).readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, KeywordInfo[].class);
                for (KeywordInfo keyword : keywords) {
                    registerKeyword(keyword);
                }
            }
            catch (Exception e)
            {
                logger.warn(modID + " does not support " + getLangString() + " keywords.");
            }
        }
    }

    private void registerKeyword(KeywordInfo info) {
        BaseMod.addKeyword(modID.toLowerCase(), info.PROPER_NAME, info.NAMES, info.DESCRIPTION);
    }

    //These methods are used to generate the correct filepaths to various parts of the resources folder.

    public static String localizationPath(String lang, String file) {
        return resourcesFolder + "/localization/" + lang + "/" + file;
    }

    public static String resourcePath(String file) {
        return resourcesFolder + "/" + file;
    }
    public static String characterPath(String file) {
        return resourcesFolder + "/character/" + file;
    }
    public static String powerPath(String file) {
        return resourcesFolder + "/powers/" + file;
    }
    public static String relicPath(String file) {
        return resourcesFolder + "/relics/" + file;
    }


    //This determines the mod's ID based on information stored by ModTheSpire.
    private static void loadModInfo() {
        Optional<ModInfo> infos = Arrays.stream(Loader.MODINFOS).filter((modInfo)->{
            AnnotationDB annotationDB = Patcher.annotationDBMap.get(modInfo.jarURL);
            if (annotationDB == null)
                return false;
            Set<String> initializers = annotationDB.getAnnotationIndex().getOrDefault(SpireInitializer.class.getName(), Collections.emptySet());
            return initializers.contains(BasicMod.class.getName());
        }).findFirst();
        if (infos.isPresent()) {
            info = infos.get();
            modID = info.ID;
        }
        else {
            throw new RuntimeException("Failed to determine mod info/ID based on initializer.");
        }
    }
}
