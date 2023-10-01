//package ffzim.cards;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
//
//import static ffzim.BasicMod.makeID;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//
//import ffzim.cards.BaseCard;
//import ffzim.util.CardInfo;
//
//public class Dong extends BaseCard implements OnObtainCard {
//
//    private final static CardInfo cardInfo = new CardInfo(
//            "Fira",
//            2,
//            CardType.ATTACK,
//            CardTarget.ENEMY,
//            CardRarity.COMMON,
//            MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//    private static final int DAMAGE = 12;
//
//    public Dong() {
//        super(CardInfo, true);
//        baseDamage = 20;
//        baseMagicNumber = magicNumber = 2;
//        cardsToPreview = new CurseOfTheBell();
//    }
//
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
//    }
//
//    @Override
//    public void onObtainCard() {
//        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
//        AbstractDungeon.effectsQueue.add(new ShowCardAndObtainEffect(new CurseOfTheBell(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
//    }
//
//    public void upp() {
//        upgradeDamage(4);
//        upgradeMagicNumber(1);
//    }
//}