package ffzim.CustomEffect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ImpactSparkEffect;

public class SpearFallFX extends AbstractGameEffect {
    private float x;
    private float y;
    private float startY; // The starting y-coordinate off-screen
    private float targetY; // The target y-coordinate
    float movementSpeed = 4000.0f * Settings.scale;
    private Texture img = null;

    private float waitingTime = 0.5F;

    public SpearFallFX(float x, float y) {
        this.img = new Texture(Gdx.files.internal("ffzim/images/SpearFX.png"));
        this.x = x;
        this.startY = y + 800.0f * Settings.scale; // Adjust the value as needed
        this.targetY = y; // The original y-coordinate
        this.y = startY; // Initialize to the starting position
        this.color = Color.WHITE.cpy();
        this.duration = 16000.5F; // Total duration of the animation (falling + stay)
        this.startingDuration = 160.5F; // 2.5F (original duration) + 4.0F (extra stay duration)
    }

    public void update() {
        // Falling phase
        if (this.duration > 15000.0F) {
            this.y -= Gdx.graphics.getDeltaTime() * movementSpeed;
        }
        else if (this.duration > 0.0F) { // Stay on target phase
            this.duration += Gdx.graphics.getDeltaTime();// * 1.0F;// Decrease the remaining stay time
        }
//        else {
//            // Effect is done
//            this.isDone = true;
//        }

        // Calculate the alpha (opacity) based on the remaining stay time
        //this.color.a = Interpolation.bounceIn.apply(this.duration * 2.0F);
    }


    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(this.img, this.x - this.img.getWidth() / 2.0F, this.y, this.img.getWidth(), this.img.getHeight(), 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
        if (this.img != null) {
            this.img.dispose();
            this.img = null;
        }
    }
}
