//package ffzim.cards;
//
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
//import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
//import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
//import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
//import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.core.CardCrawlGame;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import ffzim.util.CardInfo;
//
//import static ffzim.BasicMod.makeID;
//
//public class Lancer extends AbstractCard {
//    private final static CardInfo cardInfo = new CardInfo(
//            "Lancer",
//            1,
//            CardType.ATTACK,
//            CardTarget.ENEMY,
//            CardRarity.BASIC,
//            MyCharacter.Enums.FFcardColor);
//
//    public static final String ID = makeID(cardInfo.baseId);
//    private static final int DAMAGE = 7;
//    private static final int UPGRADED_DAMAGE = 11;
//    private static final int ENERGY_GAIN = 1;
//
//    public Lancer() {
//        super(cardInfo, false);
//        baseDamage = damage = DAMAGE;
//        baseMagicNumber = magicNumber = ENERGY_GAIN;
//        GraveField.grave.set(this, true);
//        AutoplayField.autoplay.set(this, true);
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new DrawCardAction(p, magicNumber));
//
//        // Check if the last card played was an attack
//        if (p.hand.group.size() > 0) {
//            AbstractCard lastCardPlayed = p.hand.group.get(p.hand.group.size() - 1);
//            if (lastCardPlayed.type == CardType.ATTACK) {
//                // Modify damage for double damage
//                damage *= 2;
//            }
//        }
//
//        // Deal the modified damage
//        addToBot(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
//                new DamageInfo(p, damage, damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//    }
//
//    @Override
//    public void upgrade() {
//        if (!upgraded) {
//            upgradeName();
//            upgradeDamage(UPGRADED_DAMAGE);
//        }
//    }
//
//    @Override
//    public AbstractCard makeCopy() {
//        return new Lancer();
//    }
//}
