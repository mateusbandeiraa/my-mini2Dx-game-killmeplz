package dev.mateusbandeira.killmeplz.entity;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import com.badlogic.gdx.graphics.Texture;

/**
 * Classe mãe de todos os objetos desenháveis na tela.
 * 
 * @author Mateus Bandeira
 * 
 */
public abstract class GameObject {
	protected CollisionBox collisionBox;
	protected Sprite sprite;

	public GameObject() {
		sprite = new Sprite();
		collisionBox = new CollisionBox();
	}

	public GameObject(float positionX, float positionY, float width, float height, Texture texture) {
		collisionBox = new CollisionBox(positionX, positionY, width, height);
		sprite = new Sprite(texture);
		this.updateSpriteDimensions();
		this.updateSpritePosition();
	}

	public Texture getTexture() {
		return sprite.getTexture();
	}

	public void setTexture(Texture texture) {
		sprite.setTexture(texture);
	}

	public void setSize(float width, float height) {
		collisionBox.setSize(width, height);
		this.updateSpriteDimensions();
	}

	public float getWidth() {
		return collisionBox.getWidth();
	}

	public void setWidth(float width) {
		collisionBox.setWidth(width);
		this.updateSpriteDimensions();
	}

	public float getHeight() {
		return collisionBox.getHeight();
	}

	public void setHeight(float height) {
		collisionBox.setHeight(height);
		this.updateSpriteDimensions();
	}

	public void setPosition(float x, float y) {
		collisionBox.set(x, y);
		this.updateSpritePosition();
	}

	public float getX() {
		return collisionBox.getX();
	}

	public void setX(float x) {
		collisionBox.setX(x);
		this.updateSpritePosition();
	}

	public float getY() {
		return collisionBox.getY();
	}

	public void setY(float y) {
		collisionBox.setY(y);
		this.updateSpritePosition();
	}
	
	/**
	 * Atualiza as dimensões do sprite para coincidir com a da collisionBox.
	 */
	private void updateSpriteDimensions() {
		sprite.setSize(collisionBox.getWidth(), collisionBox.getHeight());
	}

	/**
	 * Atualiza a posição do sprite para coincidir com a da collisionBox.
	 */
	private void updateSpritePosition() {
		sprite.setPosition(collisionBox.getX(), collisionBox.getY());
	}

	public final void update(float delta) {
		collisionBox.preUpdate();
		this.behave(delta);
	}

	/**
	 * Análogo ao update(), mas é rodado após o update da collisionBox.
	 */
	public abstract void behave(float delta);

	public void interpolate(GameContainer gc, float alpha) {
		collisionBox.interpolate(gc, alpha);
	}

	public void render(Graphics g) {
		if(sprite.getX() != collisionBox.getRenderX()) {
			System.out.println("X: " + sprite.getX() + ", renderX: " + collisionBox.getRenderX());
		}
		g.drawSprite(sprite, collisionBox.getRenderX(), collisionBox.getRenderY());
	};

}
