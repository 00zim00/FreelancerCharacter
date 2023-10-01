//package ffzim.cards.BlackMage;
//
//import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.animations.VFXAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
//import ffzim.actions.keywords.LevelUpAction;
//import ffzim.cards.BaseCard;
//import ffzim.cards.WhiteMage.Gems.FireMateria;
//import ffzim.powers.AstralFirePower;
//import ffzim.util.CardInfo;
//import ffzim.util.CustomTags;
//
//import static basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview.horizontal;
//import static ffzim.BasicMod.makeID;
//
//public class FiragaBU extends BaseCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "FiragaBU",
//            3,
//            CardType.ATTACK,
//            CardTarget.ENEMY,
//            CardRarity.SPECIAL,
//            CardColor.COLORLESS); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//    private static final int DAMAGE = 17;
//    private static final int UPG_DAMAGE = 5;
//    private static final int ASTRAL_FIRE = 3;
//    private static final int UPG_ASTRAL_FIRE  = 3;
//    private boolean isInPreview;
//
//    public FiragaBU() {
//        this(true);
//    }
//
//    public FiragaBU(boolean makePreview) {
//        super(cardInfo, false);
//        setDamage(DAMAGE, UPG_DAMAGE);
//        setMagic(ASTRAL_FIRE,UPG_ASTRAL_FIRE);
//        setLevelUp(true);
//        PurgeField.purge.set(this, true);
//        tags.add(CustomTags.FFSPELL);
//        tags.add(CustomTags.FFLEVELUP);
//
//
//
//        //this.setBackgroundTexture("ffzim/images/bg_power.png","ffzim/images/bg_power_p.png");
//
//        tags.add(CustomTags.FFSPELL);
//        tags.add(CustomTags.FFLEVELUP);
////        this.portrait = previewCard.portrait;
////        this.jokePortrait = previewCard.jokePortrait;
//        if (makePreview) {
//            MultiCardPreview.add(this, new Fire(), new FireMateria());
//            horizontal.set(this, true);
//        }
//        this.isInPreview = makePreview;
//        this.rarity = CardRarity.RARE;
//        super.initializeDescription();
//    }
//
//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            this.upgradeName();
//            upgradeDamage(UPG_DAMAGE);
//            upgradeMagicNumber(UPG_ASTRAL_FIRE);
//            if (isInPreview ) {
//                MultiCardPreview.multiCardPreview.get(this).forEach(AbstractCard::upgrade);
//            }
//            this.rawDescription = cardStrings.DESCRIPTION;
//            this.initializeDescription();
//        }
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new VFXAction(new ScreenOnFireEffect(), 0.2F));
//        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
//        addToBot(new LevelUpAction(p,p,isLevelUp(),new Fira(),new Fire(),true,false,false, this.upgraded, this.purgeOnUse));
//        addToBot(new ApplyPowerAction(p,p, new AstralFirePower(p, this.magicNumber), this.magicNumber));
//        if (!this.purgeOnUse) {
//            AbstractCard cardToAdd = new FireMateria();
//            if (upgraded) {cardToAdd.upgrade();}
//            addToBot(new MakeTempCardInDiscardAction(cardToAdd, 1));
//        }
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new FiragaBU();
//    }
//}