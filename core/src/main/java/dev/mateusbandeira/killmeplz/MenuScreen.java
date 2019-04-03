package dev.mateusbandeira.killmeplz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import entity.TankColor;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;

import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

public class MenuScreen extends BasicGameScreen {
	public static final int ID = 2;
	private Sprite[] tanks = new Sprite[3];
	private TankColor[] tankColors = { TankColor.GREEN, TankColor.YELLOW, TankColor.GREY };
	private int selectedTank = -1;
	private float tick = 0.125f;

	@Override
	public void initialise(GameContainer gc) {
		for (int i = 0; i < tanks.length; i++) {
			tanks[i] = new Sprite(new Texture(tankColors[i].sprite));
			Sprite tank = tanks[i];
			tank.setSize(60, 60);
			tank.setY(64);
			if (i == 0) {
				tank.setX(64);
			} else {
				tank.setX(tanks[i - 1].getX() + 64 + 16);
			}
		}
	}

	@Override
	public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
		IngameScreen telaIngame = ((IngameScreen) screenManager.getGameScreen(IngameScreen.ID));

		if (selectedTank == -1) {
			switch (telaIngame.getPlayer().getColor()) {
			case GREEN:
				selectedTank = 0;
				break;
			case YELLOW:
				selectedTank = 1;
				break;
			case GREY:
				selectedTank = 2;
				break;
			}
		}

		tick -= delta;
		if (tick < 0) {
			if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
				screenManager.enterGameScreen(1, new FadeOutTransition(), new FadeInTransition());
			}

			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				selectedTank = (selectedTank - 1 + 3) % 3;
				
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				selectedTank = (selectedTank + 1) % 3;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
				telaIngame.getPlayer().changeColor(tankColors[selectedTank]);
				screenManager.enterGameScreen(1, new FadeOutTransition(), new FadeInTransition());
			}
			tick = 0.125f;
		}
	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawString("Escolha seu tanque.", 32f, 32f);

		for (Sprite s : tanks) {
			g.drawSprite(s);
		}

		if (selectedTank >= 0)
			g.drawRect(tanks[selectedTank].getX(), tanks[selectedTank].getY(), 64, 64);
	}

	@Override
	public int getId() {
		return MenuScreen.ID;
	}

}
