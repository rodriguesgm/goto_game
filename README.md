# GO_TO Game Deck challenge

## Description

Implementation of a Game deck based on requirements provided.

## Backend 

This project uses the following technologies in the backend:
- Java 21 with Spring Boot for building RESTful APIs
- Maven for dependency management and build automation
- H2 in-memory database for development and testing
- MapStruct for object mapping to not expose entities in the rest api
- Lombok to reduce boilerplate code
- JPA (Jakarta Persistence API) for ORM and database access


## BD Diagram

![Game Logo](game_erd.png)

## Checklist

 - [ ] API backend
   - [x] Requirements implementation: Tried to follow a package structure that separates principal domains (game, deck, player) and maybe apply Spring Monolith to avoid exposing unnecessary classes to the whole project, with rules validating each package but I'm not sure I liked the way I did. Would need a little more time to rethink about it and handle it better.
   - [x] Security: For the purpose of this challenge, just added a api key header that need to be sent to call this endpoint to avoid making them public. But ideally, Spring Security could play a role in here where we could add token validation and different permissions for each endpoint
   - [ ] Error handling
   - [ ] Rule Validations
   - [ ] Unit tests
   - [ ] Integration tests
 - [ ] Frontend
   - [ ] Views
   - [ ] Tests
