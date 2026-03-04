package main.engine.physics;

/** 
 * Decouples physics calculations from game-specific collision logic.
 * Implementations should check tiles, entities, and game-specific rules (doors, bridges, etc).
 */
@FunctionalInterface
public interface MovementValidator {
    /** @return true if the entity can occupy the given position */
    boolean canMoveTo(Vector2D pos, int width, int height);
}
