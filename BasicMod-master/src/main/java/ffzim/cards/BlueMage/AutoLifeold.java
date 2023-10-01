//package ffzim.cards.BlueMage;
//
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.AbstractPower;
//import com.megacrit.cardcrawl.powers.RegenPower;
//import ffzim.cards.BaseCard;
//import ffzim.powers.AutoLifePower;
//import ffzim.powers.ShellPower;
//import ffzim.util.CardInfo;
//
//import static ffzim.BasicMod.makeID;
//
//public class AutoLife extends BaseCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "AutoLife",
//            0,
//            CardType.POWER,
//            CardTarget.SELF,
//            CardRarity.COMMON,
//            MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//
//
//    private static final int Trance = 1;
//    private static final int UPG_Trance = 0;
//    private static final int MAGIC = 20;
//    private static final int UPGRADE_MAGIC = 10;
//    public AutoLife() {
//        super(cardInfo, true);
//        baseMagicNumber = magicNumber = MAGIC;
//        tags.add(CardTags.HEALING);
//
//    }
//
//
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        //addToBot(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber)));
//        addToBot(new ApplyPowerAction(p, p, new AutoLifePower(p, p, this.magicNumber), this.magicNumber));
//    }
//
//    @Override
//    public AbstractCard makeCopy() { //Optional
//        return new AutoLife();
//    }
//}