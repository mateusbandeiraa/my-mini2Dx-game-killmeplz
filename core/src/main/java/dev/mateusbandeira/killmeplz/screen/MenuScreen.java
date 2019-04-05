package dev.mateusbandeira.killmeplz.screen;

import org.mini2Dx.core.game.GameContainer;
import org.mini2Dx.core.graphics.Graphics;
import org.mini2Dx.core.graphics.Sprite;
import org.mini2Dx.core.screen.BasicGameScreen;
import org.mini2Dx.core.screen.GameScreen;
import org.mini2Dx.core.screen.ScreenManager;
import org.mini2Dx.core.screen.transition.FadeInTransition;
import org.mini2Dx.core.screen.transition.FadeOutTransition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import dev.mateusbandeira.killmeplz.entity.TankColor;

public class MenuScreen extends BasicGameScreen {
	public static final int ID = 2;
	/**
	 * Os sprites com os 3 tanques possíveis.
	 * As texturas dos sprites seguem a ordem de {@linkplain MenuScreen#tankColors}
	 */
	private Sprite[] tanks = new Sprite[3];
	/**
	 * As texturas dos 3 tanques possíveis.
	 */
	private TankColor[] tankColors = { TankColor.GREEN, TankColor.YELLOW, TankColor.GREY };
	/**
	 * Índice do tanque selecionado em {@linkplain MenuScreen#tanks}
	 */
	private int selectedTank = -1;
	/**
	 * Período mínimo (em segundos) entre ativações de uma input.
	 */
	private float inputCooldownTime = 0.125f;

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
		inputCooldownTime -= delta;

		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			screenManager.enterGameScreen(1, new FadeOutTransition(), new FadeInTransition());
		}

		if (shouldRespondToInput(Input.Keys.LEFT)) {
			// Move a seleção do tanque à esquerda. Troca de lado caso alcance o final.
			selectedTank = (selectedTank - 1 + 3) % 3;
		} else if (shouldRespondToInput(Input.Keys.RIGHT)) {
			// Move a seleção do tanque à direita. Troca de lado caso alcance o final.
			selectedTank = (selectedTank + 1) % 3;
		}

		// Troca a cor do tanque e fecha o menu
		if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
			telaIngame.getPlayer().changeColor(tankColors[selectedTank]);
			screenManager.enterGameScreen(1, new FadeOutTransition(), new FadeInTransition());
		}

		// Atualiza o selectedTank baseado no tanque atual do objeto Player na
		// TelaIngame (somente se for a primeira chamada do update()).
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

	}

	/**
	 * Retorna true se o programa deve responder à input passada por parâmetro.
	 * 
	 * Isso é útil quando não é desejável registrar uma input a cada ciclo do
	 * update(). A função possui um cooldown de ativação para teclas pressionadas
	 * por longos períodos, mas sempre retorna true caso a tecla acaba de ser
	 * pressionada.
	 * 
	 * @param key
	 *            Um inteiro estático de {@link Input.Keys}
	 * @return
	 */
	private boolean shouldRespondToInput(int key) {
		if (inputCooldownTime > 0) {
			return Gdx.input.isKeyJustPressed(key);

		} else if (Gdx.input.isKeyPressed(key)) {
			inputCooldownTime = 0.125f;
			return true;
		}
		return false;
	}

	@Override
	public void interpolate(GameContainer gc, float alpha) {

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		g.drawString("Escolha seu tanque.", 32f, 32f);

		for (Sprite s : tanks) {
			// Desenha os 3 tanques.
			g.drawSprite(s);
		}

		if (selectedTank >= 0) {
			// Desenha um retângulo no tanque selecionado.
			g.drawRect(tanks[selectedTank].getX(), tanks[selectedTank].getY(), 64, 64);
		}
	}

	@Override
	public int getId() {
		return MenuScreen.ID;
	}

}
