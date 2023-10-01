//package ffzim.cards.BlueMage;
//
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.RegenPower;
//import ffzim.cards.BaseCard;
//import ffzim.util.CardInfo;
//
//import static ffzim.BasicMod.makeID;
//
//public class AutoReganAutoplayset extends BaseCard {
//        private final static CardInfo cardInfo = new CardInfo(
//                "Auto-Regan2",
//                0,
//                CardType.POWER,
//                CardTarget.SELF,
//                CardRarity.COMMON,
//                MyCharacter.Enums.FFcardColor); 
//
//    public static final String ID = makeID(cardInfo.baseId);
//    private static final int REGEN = 2;
//    private static final int UPG_REGEN = 3;
//
//    public AutoReganAutoplayset() {
//        super(cardInfo, true);
//
//        setMagic(REGEN, UPG_REGEN);
//        PurgeField.purge.set(this, true);
//        AutoplayField.autoplay.set(this, true);
//    }
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, this.magicNumber)));
//    }
//
//    @Override
//    public AbstractCard makeCopy() {
//        return new AutoReganAutoplayset();
//    }
//
//    @Override
//    public void upgrade() {
//        if (!this.upgraded) {
//            upgradeName();
//            this.upgradeMagicNumber(UPG_REGEN);
//        }
//    }
//}
