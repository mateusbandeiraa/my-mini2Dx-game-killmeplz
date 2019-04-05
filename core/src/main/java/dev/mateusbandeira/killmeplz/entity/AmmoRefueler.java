package dev.mateusbandeira.killmeplz.entity;

import com.badlogic.gdx.graphics.Texture;

public class AmmoRefueler extends GameObject {

	private static final float DIMENSIONS = 32f;
	private static final float COLLISION_RADIUS = 4f;
	private static final String TEXTURE_PATH = "tanks_crateAmmo.png";

	public AmmoRefueler(float positionX, float positionY) {
		super(positionX, positionY, DIMENSIONS, DIMENSIONS, new Texture(TEXTURE_PATH));
	}

	public boolean checkCollision(Player p) {
		System.out.println("Distance: " + this.collisionBox.getDistanceTo(p.getCenterX(), p.getCenterY()));
		return this.collisionBox.getDistanceTo(p.getCenterX(), p.getCenterY()) < COLLISION_RADIUS;
	}

	@Override
	public void behave(float delta) {
		// TODO Auto-generated method stub

	}
}