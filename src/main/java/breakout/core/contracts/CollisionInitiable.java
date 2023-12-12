package breakout.core.contracts;

import breakout.core.entities.Entity;

/**
 * Used to mark an entity that is not constrained by limited movements and can initiate collision with other entities
 */
public interface CollisionInitiable {
    /**
     * Checks if the entity is colliding with another entity.
     *
     * @param entity The entity to check collision with.
     * @return True if the entity is colliding with the other entity, false otherwise.
     */
    boolean collidesWith(Entity entity);
}
