//package ffzim.cards;
//
//import basemod.abstracts.CustomCard;
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.files.FileHandle;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.CardLibrary;
//import com.megacrit.cardcrawl.helpers.FontHelper;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
//
//public abstract class AbstractFFZimCard extends CustomCard {
//
//    protected final CardStrings cardStrings;
//
//    public int secondMagic;
//    public int baseSecondMagic;
//    public boolean upgradedSecondMagic;
//    public boolean isSecondMagicModified;
//
//    public int secondDamage;
//    public int baseSecondDamage = -1;
//    public boolean upgradedSecondDamage;
//    public boolean isSecondDamageModified;
//
//    private boolean needsArtRefresh = false;
//    private String bottomText;
//    private String frameFolder;
//    private String orbString;
//
//    public boolean isUnnate = false;
//}