//package ffzim.cards;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.animations.VFXAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.actions.utility.SFXAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.VulnerablePower;
//import com.megacrit.cardcrawl.powers.WeakPower;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
//import com.megacrit.cardcrawl.vfx.combat.ViceCrushEffect;
//import ffzim.util.CardInfo;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import static ffzim.BasicMod.makeID;
//
//    public class Dong2 extends BaseCard {
//
//    // Card properties
//    private final static CardInfo cardInfo = new CardInfo(
//            "Dong",
//            2,
//            CardType.ATTACK,
//            CardTarget.ENEMY,
//            CardRarity.UNCOMMON,
//            MyCharacter.Enums.FFcardColor);
//
//    private static final int DAMAGE = 12;
//    private static final int UPG_DAMAGE = 3;
//    // Constructor
//    public Dong2() {
//        super(cardInfo, false);
//        setDamage(DAMAGE, UPG_DAMAGE);
//    }
//    // Card usage
//        @Override
//        public void use(AbstractPlayer p, AbstractMonster m) {
//            addToBot(new GainBlockAction(p, p, block));
//            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
//        }
//    // Method triggered when the card is obtained as a reward or during events
//
//
//    // Card upgrades
//    @Override
//    public void upgrade() {
//        if (!upgraded) {
//            upgradeName();
//            upgradeDamage(UPG_DAMAGE);
//            upgradeMagicNumber(DAMAGE);
//        }
//    }
//
//    // Optional method to create a copy of the card
//    @Override
//    public AbstractCard makeCopy() {
//        return new Dong2();
//    }
//}
