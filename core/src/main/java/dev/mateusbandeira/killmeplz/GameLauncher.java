package dev.mateusbandeira.killmeplz;

import org.mini2Dx.core.game.ScreenBasedGame;

public class GameLauncher extends ScreenBasedGame {
	public static final String GAME_IDENTIFIER = "dev.mateusbandeira.killmeplz";
	
	
	@Override
	public void initialise() {
		this.addScreen(new IngameScreen());
        this.addScreen(new MenuScreen());
	}

	@Override
	public int getInitialScreenId() {
		// TODO Auto-generated method stub
		return IngameScreen.ID;
	}
}
