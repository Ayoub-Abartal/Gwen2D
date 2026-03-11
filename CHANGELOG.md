# Changelog

All notable changes to Gwen2D will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Scene switching support in SceneManager

### Changed
- Nothing yet

### Fixed
- Nothing yet

## [0.1.0] - 2026-03-11

### Added
- GameApplication base class with Template Method pattern
- SceneManager for scene lifecycle management
- GameWindow for Swing window management
- GameLoop with thread-safe pause/resume
- Scene system for entity management
- Entity base class with interface segregation (Updatable, Drawable, Collidable, Interactable, Positioned)
- PlatformerPhysics with gravity, jumping, and collision resolution
- TileManager for CSV-based tile maps
- SpriteSheet and TileSpriteSheet for sprite management
- KeyHandler for input management
- SoundManager for audio playback
- DialogueBox for text dialogue
- Log facade over java.util.logging with file rotation
- CollisionChecker for tile-based collision
- AABB and Vector2D for physics calculations
- MovementValidator functional interface
- GameSettings builder for configuration
- Complete usage example with demo scene
- Build scripts for framework

### Documentation
- README with architecture overview
- REFACTORING_BACKLOG with improvement roadmap

[Unreleased]: https://github.com/Ayoub-Abartal/Gwen2D/compare/v0.1.0...HEAD
[0.1.0]: https://github.com/Ayoub-Abartal/Gwen2D/releases/tag/v0.1.0
