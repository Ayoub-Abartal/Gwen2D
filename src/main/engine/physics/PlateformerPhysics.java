package main.engine.physics;

/** 
 * Handles platformer-style physics with gravity, jumping, and collision resolution.
 * Manages horizontal/vertical movement, ground detection, and velocity state.
 */
public class PlateformerPhysics {
    
    private Vector2D velocity;
    private boolean onGround;

    private Vector2D gravity;
    private float jumpForce;
    private float maxFallSpeed;
    private float speed;

    private static final float GROUND_SNAP_STEP = 1.0f;


    /** 
     * @param gravity typically (0, 0.5) for downward pull
     * @param jumpForce negative value for upward movement (e.g., -14)
     * @param maxFallSpeed terminal velocity cap
     * @param speed horizontal movement speed in pixels per frame
     */
    public PlateformerPhysics(Vector2D gravity,float jumpForce, float maxFallSpeed,float speed){
        this.velocity = Vector2D.zero();
        this.onGround = false;
        this.gravity = gravity;
        this.jumpForce = jumpForce;
        this.maxFallSpeed = maxFallSpeed;       
        this.speed = speed; 
    }

    
    /** 
     * Calculates next position after applying physics and collision resolution.
     * Handles horizontal movement, gravity, and vertical movement in sequence.
     * 
     * @param moveInput movement direction (-1 left, 1 right, 0 stopped)
     * @param validator callback to check if a position is valid
     * @return new position after physics calculations
     */
    public Vector2D calculateNextPos(Vector2D current, Vector2D moveInput, int width, int height, MovementValidator validator){
        
        // 1. Update horizontal velocity based on input
        updateHorizontalVelocity(moveInput);

        // 2. Apply horizontal movement with collision
        Vector2D nextPosition = applyHorizontalMovement(current, width, height, validator);
       
        // 3. Apply gravity to vertical velocity
        applyGravity();

        // 4. Apply vertical movement with collision
        nextPosition = applyVerticalMovement(nextPosition, width, height, validator);

        // 5. Return final position
        return nextPosition;
    }


    private void updateHorizontalVelocity(Vector2D moveDirection){
        // 1. Create working copy to preserve input
        Vector2D copyMoveDirection = moveDirection.copy();
    
        // 2. Check if player is trying to move

        if (copyMoveDirection.length() > 0) 
            // 3. If moving:
        {
            //    - Normalize direction (make it unit vector)
            copyMoveDirection.normalize();
            //    - Apply speed multiplier
            copyMoveDirection.multiply(speed);
            //    - Update velocity.x with result
            velocity.x = copyMoveDirection.x;
        
        } else{
            // 4. If not moving:
            //    - Set velocity.x to 0 (stop)
            velocity.x = 0;
        }
    }


    private Vector2D applyHorizontalMovement(Vector2D position, int w, int h, MovementValidator v){
        
        Vector2D horizontalMovement = new Vector2D(velocity.x, 0);
        Vector2D nextPosition = tryMove(position, horizontalMovement, w, h, v);
        
        if(nextPosition.equals(position)){
            velocity.x = 0; // Hit wall
        }

        return nextPosition; // Return actual new position
    }

    private Vector2D applyVerticalMovement(Vector2D position, int w , int h, MovementValidator v){
        Vector2D movement = new Vector2D(0,velocity.y);

        if(velocity.y > 0){
            return handleFalling(position, movement, w, h, v);
        }else if(velocity.y < 0){
            return handleRising(position, movement, w, h, v);
        }

        return position;
    }

    private Vector2D snapToGround(Vector2D position, int w, int h, MovementValidator v){
        // create a copy 
        Vector2D newPosition = position.copy();

        // while we can move down one more pixel
            // move down by GROUND_SNAP_STEP
        while(v.canMoveTo(new Vector2D(newPosition.x, newPosition.y + GROUND_SNAP_STEP), w, h)){
            newPosition.y += GROUND_SNAP_STEP;
        }
        
        // return final snapped position 
        return newPosition;
    }


    private Vector2D tryMove(Vector2D from, Vector2D movement, int w, int h, MovementValidator v){
        // Calculate Where we want to move to 
        Vector2D nextPosition = from.copy();
        nextPosition.add(movement);

        // check if that position is valid 
        // Return nextPosition if valid,
        // otherwise return original
        return v.canMoveTo(nextPosition, w, h) ? nextPosition : from;

    }
    

    private Vector2D handleRising(Vector2D position, Vector2D movement, int w, int h, MovementValidator v){
        // Try to move up
        Vector2D newPosition = tryMove(position, movement, w, h, v) ;

        // If Blocked (position didnt change)
        if(newPosition.equals(position)){
            velocity.y = 0; // Hit Ceiling, stop
        }

        // Return new position
        return newPosition; 
    }

    private Vector2D handleFalling(Vector2D position, Vector2D movement, int w, int h, MovementValidator v){
        Vector2D newPosition = tryMove(position, movement, w, h, v);

        if(newPosition.equals(position)){
            newPosition = snapToGround(newPosition, w, h, v);
            velocity.y = 0;
            onGround = true;
        }else{
            onGround = false;
        }

        return newPosition;
    }


    private void applyGravity(){
        // === GRAVITY ===
    velocity.add(gravity);
    
        // Cap fall speed
        if (velocity.y > maxFallSpeed) {
            velocity.y = maxFallSpeed;
        }

    }


    /** Initiates a jump if on ground. Does nothing if already airborne. */
    public void jump(){
        if(onGround){
            velocity.y = jumpForce;
            onGround = false;
        }
    }


    public boolean isOnGround(){
        return onGround;
    }


    public Vector2D getVelocity(){
        return new Vector2D(velocity);
    }
}
