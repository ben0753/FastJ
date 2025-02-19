package tech.fastj.example.oldhelloworld;

import tech.fastj.engine.FastJEngine;

public class Main {
    public static void main(String[] args) {
        /* Initializes the game engine, with an instance of our game manager. */
        FastJEngine.init("Example Game", new GameManager());
        /* Runs the game engine. */
        FastJEngine.run();
    }
}
