//package ffzim.patches;
//
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.rooms.AbstractRoom;
//import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
//import ffzim.powers.JumpDodgePower;
//
//@SpirePatch(clz = AbstractPlayer.class, method = "damage")
//public class JumpDodgePatchBU {
//    // ... (your existing imports and code)
//    public static SpireReturn Prefix(AbstractPlayer self, DamageInfo info) {
//        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && info.type == DamageType.NORMAL && info.output > 0 && self.hasPower(JumpDodgePower.POWER_ID)) {
//            JumpDodgePower DodgePower = (JumpDodgePower) self.getPower(JumpDodgePower.POWER_ID);
//            float modifiedDamage = DodgePower.atDamageReceive(info.output, info.type, info);
//
//            if (modifiedDamage == 0) {
//                DodgePower.flash();
//                AbstractDungeon.effectList.add(new BlockedWordEffect(self, self.hb.cX, self.hb.cY, JumpDodgePower.DESCRIPTIONS[0]));
//                return SpireReturn.Return(null);
//            }
//        }
//        return SpireReturn.Continue();
//    }
//}
