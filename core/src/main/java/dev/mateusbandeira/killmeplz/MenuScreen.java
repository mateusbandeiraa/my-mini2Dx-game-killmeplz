package dev.mateusbandeira.killmeplz;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;


import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

public class MenuScreen extends BasicGameScreen {
	public static final int ID = 2;
	
	
	@Override
	public void initialise(GameContainer gc) {
            

	}

	@Override
	public void update(GameContainer gc, ScreenManager<? extends GameScreen> screenManager, float delta) {
		if (Gdx.input.isKeyPressed(Input.Keys.TAB)) {
                    screenManager.enterGameScreen(2, new FadeInTransition(), new FadeOutTransition());
                }

	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {


	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawString("Isso é um menu.", 32f, 32f);
	}

	@Override
	public int getId() {
		return this.ID;
	}

}
