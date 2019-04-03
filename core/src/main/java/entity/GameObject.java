package entity;

import org.mini2Dx.core.engine.geom.CollisionBox;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;

import com.badlogic.gdx.graphics.Texture;

/**
 * Classe mãe de todos os objetos desenháveis na tela.
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
		sprite = new Sprite(texture);
		sprite.setSize(width, height);
		sprite.setPosition(positionX, positionY);
		collisionBox = new CollisionBox(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
	}

	public Texture getTexture() {
		return sprite.getTexture();
	}

	public void setTexture(Texture texture) {
		sprite.setTexture(texture);
	}

	public void setSize(float width, float height) {
		sprite.setSize(width, height);
		this.updateCollisionBoxDimensions();
	}
	
	public float getWidth() {
		return sprite.getWidth();
	}

	public void setWidth(float width) {
		sprite.setSize(width, sprite.getHeight());
		this.updateCollisionBoxDimensions();
	}
	
	public float getHeight() {
		return sprite.getHeight();
	}
	
	public void setHeight(float height) {
		sprite.setSize(sprite.getWidth(), height);
		this.updateCollisionBoxDimensions();
	}

	public void setPosition(float x, float y) {
		sprite.setX(x);
		sprite.setY(y);
		this.updateCollisionBoxPosition();
	}
	
	public float getX() {
		return sprite.getX();
	}

	public void setX(float x) {
		sprite.setX(x);
		this.updateCollisionBoxPosition();
	}

	public float getY() {
		return sprite.getY();
	}
	
	public void setY(float y) {
		sprite.setY(y);
		this.updateCollisionBoxPosition();
	}

	/**
	 * Atualiza as dimensões da collisionBox para coincidir com a do Sprite.
	 */
	private void updateCollisionBoxDimensions() {
		collisionBox.setSize(sprite.getWidth(), sprite.getHeight());
	}
	
	/**
	 * Atualiza a posição da collisionBox para coincidir com a do Sprite.
	 */
	private void updateCollisionBoxPosition() {
		collisionBox.set(sprite.getX(), sprite.getY());
	}

	public final void update() {
		collisionBox.preUpdate();
		this.behave();
	}

	/**
	 * Análogo ao update(), mas é rodado após o update da collisionBox.
	 */
	public abstract void behave();

	public void interpolate(float alpha){
		collisionBox.interpolate(null, alpha);
	}

	public abstract void render(Graphics g);

}
