//package ffzim.patches;
//import com.evacipated.cardcrawl.mod.stslib.blockmods.AbstractBlockModifier;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.AbstractCreature;
//
//
//public class ArmorBreakBlockModifier extends AbstractBlockModifier {
//
//    public ArmorBreakBlockModifier() {
//        super(); // Call the superclass constructor
//    }
//
//    @Override
//    public String getName() {
//        return "Armor Break"; // You can provide a name for your block modifier
//    }
//
//    @Override
//    public String getDescription() {
//        return "Reduces incoming block by 50%"; // You can provide a description for your block modifier
//    }
//
//    @Override
//    public float onModifyBlock(float block, AbstractCard card, AbstractCreature source, AbstractCreature target) {
//        return block * 0.5f; // Reduce block amount by 50%
//    }
//
//    @Override
//    public AbstractBlockModifier makeCopy() {
//        return new ArmorBreakBlockModifier(); // Create a copy of the block modifier
//    }
//}