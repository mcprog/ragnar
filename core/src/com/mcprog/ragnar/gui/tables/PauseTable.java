package com.mcprog.ragnar.gui.tables;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mcprog.ragnar.gui.GuiStyles;
import com.mcprog.ragnar.gui.buttons.PlayButton;
import com.mcprog.ragnar.gui.buttons.QuitGame;
import com.mcprog.ragnar.screens.GameScreen;

public class PauseTable extends RagnarTable {

	private TextButton pauseResume;
    private QuitGame quitButton;
    private PlayButton restart;
    private GameScreen screen;
	
	public PauseTable(final GameScreen screen) {
        this.screen = screen;
		setFillParent(true);
		GuiStyles.init();
		pauseResume = new TextButton("Pause", GuiStyles.smallButtonStyleLight);
        quitButton = new QuitGame(GuiStyles.smallButtonStyleLight);
        restart = new PlayButton(GuiStyles.smallButtonStyleLight);
        restart.setText("Restart");
		
		pauseResume.addListener(new ClickListener () {
			@Override
			public void clicked(InputEvent event, float x, float y) {
                screen.setGameToPaused(!screen.isPaused());
                screen.setJustPaused(true);
			}
		});
	}

    public void show() {
        clear();
        add(pauseResume);
        pauseResume.setText("Pause");
        pad(60);
        right();
        bottom();
        System.out.println("Pause table show called");
    }

    public void showPaused() {
        clear();
        add(pauseResume).pad(30);
        pauseResume.setText("Resume");
        row();
        add(restart).pad(30);
        row();
        add(quitButton).pad(30);
        center();

    }
}
