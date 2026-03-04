# Refactoring Backlog

This document tracks planned improvements and design pattern implementations for the engine. The project serves as a learning dojo for system design and software architecture.

## Phase 1: Production Ready (Current Priority)

### High Priority
- [ ] **Logging System** - Replace System.out with proper logging framework
  - Design Logger interface
  - Implement console logger
  - Add log levels (DEBUG, INFO, WARN, ERROR)
  - Replace all System.out/err calls
  - Pattern: Strategy Pattern, Dependency Injection

- [ ] **Unit Tests** - Add test coverage for core components
  - Test PlatformerPhysics methods
  - Test collision detection (AABB, tryMove)
  - Test Vector2D operations
  - Mock MovementValidator for isolated testing
  - Pattern: Test-Driven Development

- [ ] **Error Handling** - Improve exception handling
  - Replace printStackTrace with proper error reporting
  - Add custom exceptions (ResourceNotFoundException, etc.)
  - Graceful degradation for missing resources
  - Pattern: Exception Handling Best Practices

- [ ] **Documentation** - Design decision documentation
  - Document trade-offs in ARCHITECTURE.md
  - Add JavaDoc to public APIs
  - Create usage examples
  - Pattern: Documentation as Code

## Phase 2: Advanced Patterns (Next)

### Design Pattern Improvements
- [ ] **SoundManager Refactoring** - Remove static singleton
  - Convert to instance-based design
  - Add AudioSystem interface
  - Inject into scenes/entities
  - Pattern: Dependency Injection, Service Locator

- [ ] **PhysicsState Extraction** - Stateless physics design
  - Extract velocity and onGround to PhysicsState class
  - Make PlatformerPhysics stateless
  - Enable physics instance sharing
  - Pattern: Stateless Service, Value Object

- [ ] **DialogueSystem Interface** - Decouple NPC from DialogueBox
  - Extract DialogueSystem interface
  - Allow different dialogue implementations
  - Pattern: Dependency Inversion Principle

- [ ] **InteractionHelper** - Remove logic from Interactable interface
  - Extract isPlayerNear to helper class
  - Extract drawInteractionIndicator to helper
  - Keep interface as pure contract
  - Pattern: Helper/Utility Pattern, Interface Segregation

## Phase 3: Scalability (Future)

### Architecture Improvements
- [ ] **Entity Component System (ECS)** - Replace inheritance with composition
  - Separate data (components) from behavior (systems)
  - Improve performance for many entities
  - Pattern: Entity Component System

- [ ] **Event System** - Decouple game logic
  - Add EventBus for game events
  - Replace direct method calls with events
  - Pattern: Observer Pattern, Event-Driven Architecture

- [ ] **Resource Pooling** - Optimize memory usage
  - Pool sprites, sounds, particles
  - Reduce garbage collection pressure
  - Pattern: Object Pool Pattern

- [ ] **Plugin Architecture** - Extensible physics system
  - Split PlatformerPhysics into plugins
  - MovementPlugin, JumpPlugin, GravityPlugin
  - Allow runtime composition
  - Pattern: Plugin Architecture, Strategy Pattern

## Phase 4: Advanced Features (Later)

### Gameplay Systems
- [ ] **State Machine** - Complex player states
  - Idle, Running, Jumping, Falling, Dashing, WallSliding
  - Clean state transitions
  - Pattern: State Pattern, Finite State Machine

- [ ] **Behavior Trees** - AI system for NPCs
  - Composable AI behaviors
  - Reusable AI components
  - Pattern: Behavior Tree, Composite Pattern

- [ ] **Serialization System** - Save/Load game state
  - Save player progress, inventory, world state
  - JSON or binary format
  - Pattern: Memento Pattern, Serialization

- [ ] **Performance Profiling** - Optimization tools
  - FPS counter, memory usage
  - Hotspot detection
  - Performance metrics dashboard
  - Pattern: Instrumentation, Observer Pattern

## Phase 5: Advanced Physics (Optional)

### Physics Extensions
- [ ] **TopDownPhysics** - For RPG/roguelike games
  - 8-direction movement
  - No gravity
  - Pattern: Strategy Pattern

- [ ] **GridPhysics** - For Snake/puzzle games
  - Tile-based movement
  - Turn-based logic
  - Pattern: Strategy Pattern

- [ ] **Advanced Platformer Features**
  - Double jump
  - Wall sliding
  - Dash mechanics
  - Variable jump height
  - Pattern: State Pattern, Command Pattern

## Learning Goals

Each refactoring phase focuses on specific design patterns and principles:

**Phase 1:** SOLID principles, testing, documentation
**Phase 2:** Dependency Injection, stateless design, interface segregation
**Phase 3:** Scalability patterns, performance optimization
**Phase 4:** Complex behavioral patterns, AI systems
**Phase 5:** Domain-specific implementations

## Notes

- Each item should be a separate commit with clear message
- Document learnings in blog posts
- Add tests before refactoring (when possible)
- Keep game working after each refactoring
- Prioritize learning over perfection
