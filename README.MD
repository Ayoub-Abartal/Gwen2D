# Java 2D Game Framework

A lightweight framework for building 2D games on top of Java Swing. Provides structure and utilities for rapid game development while maintaining flexibility.

## Overview

This framework offers an opinionated architecture for 2D game development, handling the game loop, window management, scene organization, and entity lifecycle. Built entirely with Java standard library and Swing, requiring no external dependencies.

## Core Architecture

### Application Entry Point

Games extend `GameApplication`, the framework's base class that manages the application lifecycle using the Template Method pattern.

```java
public class MyGame extends GameApplication {
    
    public MyGame() {
        super(GameSettings.builder()
            .title("My Game")
            .screenSize(800, 600)
            .fps(60)
            .build());
    }
    
    @Override
    protected Scene createInitialScene() {
        return new MenuScene(getSettings());
    }
    
    @Override
    protected void onStart() {
        // Optional: Custom initialization
    }
    
    public static void main(String[] args) {
        new MyGame().start();
    }
}
```

### Scene Management

Scenes contain and manage game entities. Each scene represents a distinct game state (menu, level, game over).

```java
public class Level1Scene extends Scene {
    
    public Level1Scene(GameSettings settings) {
        super(settings);
        
        // Add entities to scene
        Player player = new Player();
        addGameObject(player);
        
        Enemy enemy = new Enemy();
        addGameObject(enemy);
    }
    
    @Override
    public void update(double delta) {
        // Custom scene logic
        super.update(delta);
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // Custom rendering
        super.draw(g2d);
    }
}
```

### Entity System

Entities implement interfaces based on required behavior. The framework automatically manages update and render cycles.

```java
public class Player extends Entity implements Updatable, Drawable, Collidable {
    
    @Override
    public void update(double delta) {
        // Update logic
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // Render logic
    }
    
    @Override
    public boolean checkCollision(int x, int y, int w, int h) {
        // Collision logic
    }
}
```

## Framework Components

### Core
- `GameApplication` - Application base class with lifecycle hooks
- `GameLoop` - Fixed timestep game loop with pause/resume
- `Scene` - Entity container and game state manager
- `SceneManager` - Scene lifecycle and transitions
- `GameSettings` - Configuration builder

### Window
- `GameWindow` - Swing window management and display

### Entity
- `Entity` - Base class for game objects
- `NPC` - Non-player character base (game-specific, consider moving to game code)

### Interfaces
- `Updatable` - Objects that update each frame
- `Drawable` - Objects that render to screen
- `Collidable` - Objects with collision detection
- `Interactable` - Objects that respond to player interaction
- `Positioned` - Objects with position and dimensions

### Physics
- `PlatformerPhysics` - Gravity, jumping, collision resolution for platformers
- `AABB` - Axis-aligned bounding box collision
- `Vector2D` - 2D vector math utilities
- `CollisionChecker` - Tile-based collision detection
- `MovementValidator` - Functional interface for collision validation

### World
- `TileManager` - CSV-based tile map loading and rendering
- `TileLoader` - Tile definition and spritesheet loading
- `CollisionChecker` - Tile collision detection

### Graphics
- `SpriteSheet` - Sprite sheet loading and frame extraction
- `TileSpriteSheet` - Tile-specific sprite sheet handling

### Input
- `KeyHandler` - Keyboard input management

### Audio
- `SoundManager` - Sound effect and music playback (static singleton, refactoring planned)

### Dialogue
- `DialogueBox` - Text dialogue with typewriter effect

### Utilities
- `Log` - Logging facade over java.util.logging with file rotation
- `UIUtils` - UI rendering helpers

## Lifecycle Hooks

The framework provides hooks for custom logic at specific lifecycle points:

```java
protected abstract Scene createInitialScene();  // Required: Provide starting scene
protected void onStart() {}                     // Optional: After window shows
protected void onStop() {}                      // Optional: Before exit
protected void onPause() {}                     // Optional: Framework pause
protected void onResume() {}                    // Optional: Framework resume
```

## Building Games

### Project Structure
```
my-game/
├── src/
│   └── main/
│       ├── MyGame.java              (extends GameApplication)
│       ├── scenes/
│       │   ├── MenuScene.java
│       │   └── Level1Scene.java
│       └── entities/
│           ├── Player.java
│           └── Enemy.java
├── lib/
│   └── engine.jar
└── resources/
    ├── sprites/
    ├── audio/
    └── maps/
```

### Compilation
```bash
javac -d out -cp lib/engine.jar src/**/*.java
```

### Execution
```bash
java -cp "out;lib/engine.jar" main.MyGame
```

## Multi-Genre Support

The framework supports multiple game genres through modular physics:

- **Platformers** - PlatformerPhysics (gravity, jumping, collision)
- **Grid-based** - GridPhysics (planned: match-3, puzzle games)
- **Top-down** - TopDownPhysics (planned: RPG, roguelike)

## Requirements

- Java 17 or higher
- No external dependencies (pure Java + Swing)


## License

MIT
