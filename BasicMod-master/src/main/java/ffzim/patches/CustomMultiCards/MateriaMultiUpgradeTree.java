//package ffzim.patches.CustomMultiCards;
//
//import basemod.helpers.CardModifierManager;
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector2;
//import com.evacipated.cardcrawl.mod.stslib.util.*;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.core.Settings;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.helpers.ImageMaster;
//import com.megacrit.cardcrawl.helpers.MathHelper;
//import com.megacrit.cardcrawl.helpers.ShaderHelper;
//import com.megacrit.cardcrawl.helpers.input.InputHelper;
//
//import java.util.ArrayList;
//
//public class MateriaMultiUpgradeTree {
//    public static AbstractCard materiaMainCard;
//    public static boolean materiaManualMainCard;
//    public static ArrayList<AbstractCard> materiaCardList = new ArrayList<>();
//    public static ArrayList<AbstractCard> materiaTakenList = new ArrayList<>();
//    public static ArrayList<AbstractCard> materiaLockedList = new ArrayList<>();
//    public static CardGraph materiaCardGraph = new CardGraph();
//    public static float grabXmateria, grabYmateria;
//    public static float deltaXmateria, deltaYmateria;
//    public static float minXmateria, minYmateria;
//    public static float maxXmateria, maxYmateria;
//    public static boolean allowXmateria, allowYmateria;
//    public static boolean materiaDragging;
//    public static float materiaRenderScale;
//    private static final float DEFAULT_ZOOM = 1.0f;
//    private static final float MIN_ZOOM = 0.3f;
//    private static final float MAX_ZOOM = 1.1f;
//    private static final float X_PAD = 400F * Settings.scale;
//    private static final float Y_PAD = 220F * Settings.scale;
//    private static final float LINE_SPACING = 20F * Settings.scale;
//    public static Texture materiaUpgradeAndLine = ImageMaster.loadImage("images/stslib/ui/andLine.png");
//    public static Texture materiaExclusionLine = ImageMaster.loadImage("images/stslib/ui/exLine.png");
//
//    public static void open(AbstractCard c) throws Exception {
//        open(c, false);
//    }
//
//    public static void open(AbstractCard c, boolean manualMainCard) throws Exception {
//        if (manualMainCard) {
//            materiaMainCard = c.makeStatEquivalentCopy();
//        } else {
//            materiaMainCard = c;
//        }
//        MateriaMultiUpgradeTree.materiaManualMainCard = manualMainCard;
//        resetScrollState();
//        prepTree(materiaMainCard);
//    }
//
//    public static void render(SpriteBatch sb) {
//        renderArrows(sb);
//        renderCards(sb);
//    }
//
//    public static void update() {
//        updateScrolling();
//        updateCards();
//    }
//
//    public static void selectCard(AbstractCard card) {
//        if (card != materiaMainCard && materiaCardList.contains(card) && !materiaTakenList.contains(card) && !materiaLockedList.contains(card)) {
//            MateriaMultiUpgradePatches.MultiUpgradeFields.glowRed.set(card, false);
//            card.beginGlowing();
//            materiaCardList.forEach((c) -> {
//                if (c != card) {
//                    MateriaMultiUpgradePatches.MultiUpgradeFields.glowRed.set(c, false);
//                    c.stopGlowing();
//                }
//
//            });
//            CardVertex v = materiaCardGraph.getVertexByCard(card);
//            if (v != null) {
//                for (CardVertex exclude : v.exclusions) {
//                    markExclusions(exclude);
//                }
//
//            }
//
//            MateriaMultiUpgradePatches.MultiSelectFields.chosenIndex.set(AbstractDungeon.gridSelectScreen, MateriaMultiUpgradePatches.MultiUpgradeFields.upgradeIndex.get(card));
//            MateriaMultiUpgradePatches.MultiSelectFields.waitingForUpgradeSelection.set(AbstractDungeon.gridSelectScreen, false);
//        }
//    }
//
//    private static void markExclusions(CardVertex v) {
//        MateriaMultiUpgradePatches.MultiUpgradeFields.glowRed.set(v.card, true);
//        v.card.beginGlowing();
//        v.children.forEach(c -> {
//            if (c.strict) {
//                markExclusions(c);
//            }
//        });
//    }
//
//    private static void prepTree(AbstractCard c) throws Exception {
//        resetScrollState();
//        for (UpgradeData u : ((MateriaMultiUpgradeCard) c).getUpgrades()) {
//            for (int i : u.dependencies) {
//                if (i > u.index) {
//                    throw new Exception("Illegal forward dependency: Upgrade Index "+u.index+" requires Upgrade Index "+i);
//                }
//                if (i == u.index) {
//                    throw new Exception("Illegal self dependency: Upgrade Index "+u.index+" requires itself");
//                }
//            }
//        }
//        //Prep the cards into the array
//        materiaCardList.clear();
//        materiaTakenList.clear();
//        materiaLockedList.clear();
//        materiaCardGraph.clear();
//        if (materiaManualMainCard) {
//            materiaCardList.add(c);
//        }
//        CardVertex root = new CardVertex(c, -1);
//        root.move(-1, 0);
//        materiaCardGraph.addVertex(root);
//        for (UpgradeData u : ((MateriaMultiUpgradeCard) c).getUpgrades()) {
//            AbstractCard copy;
//            if (u.applied) {
//                copy = makeSimpleCopy(c);
//            } else {
//                copy = c.makeStatEquivalentCopy();
//            }
//            prepUpgradePreview(copy, u);
//
//            MateriaMultiUpgradePatches.MultiUpgradeFields.upgradeIndex.set(copy, u.index); //Gets set back to -1 when completed, so we need to set it again
//
//            materiaCardList.add(copy);
//
//            if (u.applied) {
//                materiaTakenList.add(copy);
//            } else if (!u.canUpgrade(((MateriaMultiUpgradeCard) c).getUpgrades())) {
//                materiaLockedList.add(copy);
//            }
//            CardVertex v = new CardVertex(copy, u.index, u.strict);
//            materiaCardGraph.addVertex(v);
//            if (u.dependencies.isEmpty()) {
//                materiaCardGraph.addDependence(v, root);
//            } else {
//                for (int i : u.dependencies) {
//                    //Dependency directed graphs
//                    materiaCardGraph.addDependence(v, materiaCardGraph.vertices.get(i + 1));
//                }
//            }
//
//        }
//        for (UpgradeData u : ((MateriaMultiUpgradeCard) c).getUpgrades()) {
//            if (u.exclusions.size() > 0) {
//                for (int i : u.exclusions) {
//                    materiaCardGraph.addExclusion(materiaCardGraph.vertices.get(u.index+1), materiaCardGraph.vertices.get(i+1));
//                }
//            }
//        }
//
//        c.current_x = c.target_x = (float)Settings.WIDTH * 1/3F;
//        c.current_y = c.target_y = (float)Settings.HEIGHT / 2F;
//
//        //Balance all cards in the X direction
//        for (CardVertex v : materiaCardGraph.vertices) {
//            for (CardVertex dependency : v.parents) {
//                if (dependency.x >= v.x) {
//                    v.move(dependency.x + 1, v.y);
//                }
//                v.card.current_x = v.card.target_x = Settings.WIDTH * 2/3F + (v.x * X_PAD);
//            }
//        }
//
//        for (int i = 0; i <= materiaCardGraph.depth() ; i++) {
//            int finalI = i;
//            final int[] yIndex = {(int) materiaCardGraph.vertices.stream().filter(v -> v.x == finalI).count() - 1};
//            materiaCardGraph.vertices.stream().filter(v -> v.x == finalI).forEach(v -> {
//                v.move(v.x, yIndex[0]);
//                v.card.current_y = v.card.target_y = Settings.HEIGHT / 2F + (v.y * Y_PAD);
//                if (materiaManualMainCard) {
//                    v.card.update();
//                }
//                yIndex[0] -= 2;
//            });
//        }
//
//        //Define the scroll limits
//        float left, right, up, down;
//        left = right = c.current_x;
//        up = down = c.current_y;
//        for (AbstractCard card : materiaCardList) {
//            if (card.current_x < left) {
//                left = card.current_x;
//            } else if (card.current_x > right) {
//                right = card.current_x;
//            }
//            if (card.current_y < down) {
//                down = card.current_y;
//            } else if (card.current_y > up) {
//                up = card.current_y;
//            }
//        }
//
//        //Add some padding
//        left -= 200F;
//        right += 200F;
//        up += 260F;
//        down -= 260F;
//
//        if (left < 0) {
//            maxXmateria = -left + 200F * Settings.scale;
//            allowXmateria = true;
//        }
//        if (right > Settings.WIDTH) {
//            minXmateria = Settings.WIDTH - right - 200F * Settings.scale;
//            allowXmateria = true;
//        }
//        if (down < 0) {
//            maxYmateria = -down + 260F * Settings.scale;
//            allowYmateria = true;
//        }
//        if (up > Settings.HEIGHT) {
//            minYmateria = Settings.HEIGHT - up - 260F * Settings.scale;
//            allowYmateria = true;
//        }
//
//        if (materiaCardGraph.height() > 2) {
//            materiaRenderScale = 0.8F;
//        }
//    }
//
//    private static void renderCards(SpriteBatch sb) {
//        AbstractCard c = materiaMainCard;
//        float dx = deltaXmateria;
//        float dy = deltaYmateria;
//
//        c.target_x = (float)Settings.WIDTH * 1/3F + dx;
//        c.target_y = (float)Settings.HEIGHT / 2F + dy;
//        c.drawScale = materiaRenderScale;
//        c.render(sb);
//
//        for (CardVertex v : materiaCardGraph.vertices) {
//            if (v.x != -1) {
//                if (v.card.hb.hovered) {
//                    v.card.drawScale = materiaRenderScale;
//                } else {
//                    v.card.drawScale = 0.9F * materiaRenderScale;
//                }
//                v.card.target_x = c.target_x + (Settings.WIDTH / 3F * materiaRenderScale) + (v.x * X_PAD * materiaRenderScale);
//                v.card.target_y = c.target_y + (v.y * Y_PAD * materiaRenderScale);
//
//                if (materiaLockedList.contains(v.card) || MateriaMultiUpgradePatches.MultiUpgradeFields.glowRed.get(v.card)) {
//                    sb.end();
//                    sb.setShader(Grayscale.program);
//                    sb.begin();
//                } else if (materiaTakenList.contains(v.card)) {
//                    sb.end();
//                    sb.setShader(Greenify.program);
//                    sb.begin();
//                }
//                v.card.render(sb);
//                ShaderHelper.setShader(sb, ShaderHelper.Shader.DEFAULT);
//                v.card.renderCardTip(sb);
//            }
//        }
//    }
//
//    private static void renderArrows(SpriteBatch sb) {
//        sb.setColor(Color.WHITE);
//        AbstractCard hovered = null;
//        AbstractCard selected = null;
//        for (CardVertex v : materiaCardGraph.vertices) {
//            if (v.card.hb.hovered) {
//                hovered = v.card;
//            }
//            if (AbstractDungeon.gridSelectScreen != null && v.index != -1 && v.index == MateriaMultiUpgradePatches.MultiSelectFields.chosenIndex.get(AbstractDungeon.gridSelectScreen)) {
//                selected = v.card;
//            }
//        }
//        if (selected != null && hovered == null) {
//            hovered = selected;
//        }
//        for (CardVertex v : materiaCardGraph.vertices) {
//            for (CardVertex child : v.children) {
//                if (hovered != null) {
//                    if (child.card == hovered) {
//                        if (v.index == -1 || materiaTakenList.contains(v.card)) {
//                            sb.setColor(Color.GREEN);
//                        } else if (!materiaLockedList.contains(v.card)) {
//                            sb.setColor(Color.ORANGE);
//                        } else {
//                            sb.setColor(Color.RED);
//                        }
//                    } else {
//                        sb.setColor(Settings.QUARTER_TRANSPARENT_WHITE_COLOR);
//                    }
//                }
//
//                Vector2 vec2 = (new Vector2((child.card.current_x),child.card.current_y)).sub(new Vector2((v.card.current_x), v.card.current_y));
//                float length = vec2.len();
//                for (float i = 0; i < length; i += LINE_SPACING) {
//                    vec2.clamp(length - i, length - i);
//                    Texture texture = child.strict ? materiaUpgradeAndLine : ImageMaster.MAP_DOT_1;
//                    float width = texture.getWidth();
//                    sb.draw(texture, (v.card.current_x) + vec2.x - width / 2, v.card.current_y + vec2.y - width / 2, width / 2, width / 2, width, width, Settings.scale, Settings.scale, (new Vector2((v.card.current_x) - (child.card.current_x), v.card.current_y - child.card.current_y)).nor().angle() + 90.0F, 0, 0, (int) width, (int) width, false, false);
//                }
//                sb.setColor(Color.WHITE);
//            }
//
//            sb.setColor(Color.RED);
//            for (CardVertex exclusion : v.exclusions) {
//                if (exclusion.index > v.index) {
//                    continue;
//                }
//                Vector2 vec2 = (new Vector2((exclusion.card.current_x), exclusion.card.current_y)).sub(new Vector2((v.card.current_x), v.card.current_y));
//                float length = vec2.len();
//                int mod = 0;
//                for (float i = 0; i < length; i += LINE_SPACING) {
//                    vec2.clamp(length - i, length - i);
//                    Texture texture = materiaExclusionLine;
//                    float width = texture.getWidth();
//                    sb.draw(materiaExclusionLine, (v.card.current_x) + vec2.x - width / 2, v.card.current_y + vec2.y - width / 2, width / 2, width / 2, width, width, Settings.scale, Settings.scale, (new Vector2((v.card.current_x) - (exclusion.card.current_x), v.card.current_y - exclusion.card.current_y)).nor().angle() + 90.0F, 0, 0, (int) width, (int) width, false, false);
//                }
//            }
//            sb.setColor(Color.WHITE);
//        }
//    }
//
//    private static AbstractCard makeSimpleCopy(AbstractCard c) {
//        AbstractCard copy = c.makeCopy();
//        CardModifierManager.copyModifiers(c, copy, false, false, false);
//        return copy;
//    }
//
//    private static void prepUpgradePreview(AbstractCard card, UpgradeData u) {
//        doUpgrade(card, u);
//        card.displayUpgrades();
//    }
//
//    private static void doUpgrade(AbstractCard card, UpgradeData u) {
//        if (u.strict) {
//            for (int i : u.dependencies) {
//                UpgradeData dep = ((MateriaMultiUpgradeCard)card).getUpgrades().get(i);
//                if (!dep.applied) {
//                    doUpgrade(card, dep);
//                }
//            }
//        }
//        MateriaMultiUpgradePatches.MultiUpgradeFields.upgradeIndex.set(card, u.index);
//        card.upgrade();
//    }
//
//    private static void updateScrolling() {
//        int x = InputHelper.mX;
//        int y = InputHelper.mY;
//        if (!materiaDragging) {
//            if (InputHelper.justClickedLeft) {
//                materiaDragging = true;
//                grabXmateria = x - deltaXmateria;
//                grabYmateria = y - deltaYmateria;
//            }
//        } else if (InputHelper.isMouseDown) {
//            if (allowXmateria) {
//                deltaXmateria = x - grabXmateria;
//            }
//            if (allowYmateria) {
//                deltaYmateria = y - grabYmateria;
//            }
//
//        } else {
//            materiaDragging = false;
//        }
//
//        if (deltaXmateria < minXmateria) {
//            deltaXmateria = MathHelper.scrollSnapLerpSpeed(deltaXmateria, minXmateria);
//        } else if (deltaXmateria > maxXmateria) {
//            deltaXmateria = MathHelper.scrollSnapLerpSpeed(deltaXmateria, maxXmateria);
//        }
//        if (deltaYmateria < minYmateria) {
//            deltaYmateria = MathHelper.scrollSnapLerpSpeed(deltaYmateria, minYmateria);
//        } else if (deltaYmateria > maxYmateria) {
//            deltaYmateria = MathHelper.scrollSnapLerpSpeed(deltaYmateria, maxYmateria);
//        }
//        if (InputHelper.scrolledDown && materiaRenderScale > MIN_ZOOM) {
//            materiaRenderScale -= 0.1f;
//        } else if (InputHelper.scrolledUp && materiaRenderScale < MAX_ZOOM) {
//            materiaRenderScale += 0.1f;
//        }
//    }
//
//    private static void updateCards() {
//        for (AbstractCard c : materiaCardList) {
//            if (c != null) {
//                c.update();
//                c.updateHoverLogic();
//            }
//        }
//    }
//
//    private static void resetScrollState() {
//        deltaXmateria = 0;
//        deltaYmateria = 0;
//        minXmateria = 0;
//        maxXmateria = 0;
//        minYmateria = 0;
//        maxYmateria = 0;
//        allowXmateria = false;
//        allowYmateria = false;
//        materiaRenderScale = DEFAULT_ZOOM;
//    }
//}