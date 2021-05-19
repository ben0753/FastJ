package io.github.lucasstarsz.fastj.example.bullethell;

import io.github.lucasstarsz.fastj.engine.FastJEngine;
import io.github.lucasstarsz.fastj.graphics.Display;

import io.github.lucasstarsz.fastj.systems.control.LogicManager;

import java.awt.Color;

import io.github.lucasstarsz.fastj.example.bullethell.scenes.GameScene;

public class GameManager extends LogicManager {

    @Override
    public void setup(Display display) {
        GameScene gameScene = new GameScene();
        this.addScene(gameScene);
        this.setCurrentScene(gameScene);
        this.loadCurrentScene();

        display.setBackgroundColor(Color.lightGray);
        display.showFPSInTitle(true);
        display.getJFrame().setResizable(false);
    }

    public static void main(String[] args) {
        FastJEngine.init("Simple Bullet Hell", new GameManager());
        FastJEngine.run();
    }
}