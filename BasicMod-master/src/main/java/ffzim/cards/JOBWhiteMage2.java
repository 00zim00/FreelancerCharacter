//package ffzim.cards;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.OnObtainCard;
//import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
//import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.CardGroup;
//import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
//import ffzim.util.CardInfo;
//
//import java.util.stream.Collectors;
//
//import static ffzim.BasicMod.makeID;
//
//public class JOBWhiteMage2 extends BaseCard implements OnObtainCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "JOBWhiteMage2",
//            0,
//            CardType.SKILL,
//            CardTarget.SELF,
//            CardRarity.SPECIAL,
//            MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//
//    public JOBWhiteMage2() {
//        super(cardInfo, true);
//        setInnate(true);
//
//    }
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
//
//    }
//    @Override
//    public void onObtainCard() {
//        CardCrawlGame.sound.playA("BELL", MathUtils.random(-0.2F, -0.3F));
//    }
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new JOBWhiteMage2();
//    }
//}
//
