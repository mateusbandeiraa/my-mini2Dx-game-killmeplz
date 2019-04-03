package entity;

import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;

public class Player extends GameObject{
	private static final float DIMENSIONS = 32;
	private static final TankColor INITIAL_COLOR = TankColor.YELLOW;
	private TankColor color = INITIAL_COLOR;

	public Player() {
		super(32f, 8f, DIMENSIONS, DIMENSIONS, new Texture(INITIAL_COLOR.sprite));
	}

	public void behave(float delta) {
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			if (sprite.isFlipX())
				sprite.flip(true, false);
			this.setPosition(this.getX() + 4f, this.getY());
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			if (!sprite.isFlipX())
				sprite.flip(true, false);
			this.setPosition(this.getX() - 4f, this.getY());
		}
	}

	public void render(Graphics g) {
		g.drawSprite(this.sprite);
	}
	
	public void changeColor(TankColor color) {
		this.color = color;
		this.sprite.setTexture(new Texture(this.color.sprite));
	}
	
	public TankColor getColor() {
		return this.color;
	}
	
}
