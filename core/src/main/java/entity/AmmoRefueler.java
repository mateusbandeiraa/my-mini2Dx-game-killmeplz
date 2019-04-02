package entity;

import org.mini2Dx.core.engine.geom.CollisionPoint;
import org.mini2Dx.core.geom.Point;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import com.badlogic.gdx.graphics.Texture;

public class AmmoRefueler {
	private CollisionPoint point;
	private Sprite sprite;
	
	private static final float DIMENSIONS = 32f;
	private static final float COLLISION_RADIUS = 24f;

	public AmmoRefueler() {
		point = new CollisionPoint(512f, 8f);
		sprite = new Sprite(new Texture("tanks_crateAmmo.png"));
		sprite.setSize(DIMENSIONS, DIMENSIONS);
	}

	public boolean checkCollision(Player p) {
		Point playerCollisionPoint = p.getPoint();

		return this.point.getDistanceTo(playerCollisionPoint) < COLLISION_RADIUS;
	}

	public void update() {
		// preUpdate() must be called before any changes are made to the CollisionPoint
		point.preUpdate();

	}

	public void interpolate(float alpha) {
		// This method uses the lerp (linear interpolate) method from LibGDX
		// to interpolate between the previous and current positions
		// and set the render coordinates correctly
		point.interpolate(null, alpha);
	}

	public void render(Graphics g) {
		// Use the point's render coordinates to draw the sprite
		g.drawSprite(sprite, point.getRenderX(), point.getRenderY());
	}

	public CollisionPoint getPoint() {
		return point;
	}
}