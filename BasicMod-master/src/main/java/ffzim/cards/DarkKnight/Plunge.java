package ffzim.cards.DarkKnight;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ffzim.cards.BaseCard;
import ffzim.character.Freelancer;
import ffzim.powers.DiveReadyPower;
import ffzim.powers.JumpDodgePower;
import ffzim.util.CardInfo;

import static ffzim.BasicMod.makeID;

public class Plunge extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Plunge",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            Freelancer.Enums.FFcardColor);
    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE= 4;
    private static final int MULTIPLYER = 2;
    private static final int UPG_MULTIPLYER = 1;
    private static final int JUMP = 2;
    private static final int UPG_JUMP = 2;
    private static final int HPLOSS = 6;
    private static final int UPG_HPLOSS = -2;

    public static final String ID = makeID(cardInfo.baseId);
    public Plunge() {
        super(cardInfo, true);
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(JUMP,UPG_JUMP);
        setSecondMagic(HPLOSS);
        this.baseMagicNumber = this.magicNumber;
        this.baseSecondMagicNumber = this.secondMagicNumber;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
            setDamage(DAMAGE+UPG_DAMAGE);
            setMagic(JUMP+UPG_JUMP);
            setSecondMagic(HPLOSS+UPG_HPLOSS);
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new JumpDodgePower(p, magicNumber)));

        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
            int jumpAmount = 0;
            JumpDodgePower jumpStack = (JumpDodgePower) p.getPower(JumpDodgePower.POWER_ID);
            System.out.println("jumpStack " + jumpStack.amount);
            System.out.println("upgraded " + upgraded);
                if (upgraded) {
                    jumpAmount = (MULTIPLYER + UPG_MULTIPLYER) * jumpStack.amount;
                    System.out.println("Stack1 " + jumpAmount);
                } else if (jumpStack != null) {
                    jumpAmount = jumpStack.amount * MULTIPLYER;
                    System.out.println("Stack2 " + jumpAmount);
                }
            addToBot(new ApplyPowerAction(p, p, new DiveReadyPower(p, 0)));
            addToBot(new WaitAction(0.5F));
            addToBot(new DamageAction(m, new DamageInfo(p, damage + jumpAmount, DamageInfo.DamageType.NORMAL), AttackEffect.BLUNT_HEAVY));
            System.out.println("damage " + damage);
            addToBot(new DamageAction(p, new DamageInfo(p, secondMagicNumber, DamageInfo.DamageType.HP_LOSS), AttackEffect.SMASH));
            addToBot(new RemoveSpecificPowerAction(p, p, DiveReadyPower.POWER_ID));
            isDone = true; // Mark the action as done
            }
        });
    }

    public AbstractCard makeCopy() {
        return new Plunge();
    }
}
