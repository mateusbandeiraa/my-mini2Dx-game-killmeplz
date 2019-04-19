package dev.mateusbandeira.killmeplz.entity;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;

import com.badlogic.gdx.graphics.Color;

public class Health extends GameObject {
	private float maxHealth;
	private float curHealth;
	private GameObject target;

	public Health(float maxHealth, float curHealth, GameObject target) {
		super();
		this.maxHealth = maxHealth;
		this.curHealth = curHealth;
		this.target = target;
	}

	@Override
	public void behave(GameContainer gc, float delta) {
		float x = this.target.getX();
		float y = this.target.getY() + 32f + 8f;

		this.setPosition(x, y);
	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect(getX(), getY(), 32f, 5f);
		g.setColor(getPercentage() > 0.4f ? Color.GREEN : Color.RED);
		g.fillRect(getX(), getY(), (32f * getPercentage()), 5f);
	}

	private float getPercentage() {
		return curHealth / maxHealth;
	}

	public float getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(float maxHealth) {
		this.maxHealth = maxHealth;
	}

	public float getCurHealth() {
		return curHealth;
	}

	public void setCurHealth(float curHealth) {
		if(curHealth > maxHealth || curHealth < 0) {
			return;
		}
		this.curHealth = curHealth;
		System.out.println("curHealth: " + curHealth);
	}
	
	public void changeHealth(float amount) {
		if(curHealth + amount > maxHealth || curHealth + amount < 0) {
			return;
		}
		curHealth += amount;
	}

	public GameObject getTarget() {
		return target;
	}

}
