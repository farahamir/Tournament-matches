# Tournament Matches

A Java Spring Boot application for managing and analyzing tournament match trees.

## Features

- Retrieve match details by ID
- Trace the path from the tournament root to any match (shows the progression of matches)
- Get a list of all winners in the tournament
- Persistent match data via JPA repositories

## Main Logic (Service Layer)

The core logic resides in the `TournamentService` class:

- **getMatchById(id):**  
  Returns match details for a given database ID.

- **getMatchByMatchID(matchID, gameWon):**  
  Fetches the first match with a specific tournament match ID and win status.

- **getPathToMatch(matchID):**  
  Returns the sequence of matches from the root to the specified match using depth-first search (DFS). This is useful for visualizing how a player advanced through the tournament.

- **getAllWinners():**  
  Traverses the match tree and returns the set of all players who won their respective matches.

## REST API Endpoints

- `GET /tournament/match/{id}`  
  Get match details by its database ID.

- `GET /tournament/path?matchID={matchID}`  
  Get the path from the root to a given match ID.

- `GET /tournament/winners`  
  Get a set of all winning players.

## Data Model

Matches are stored as entities with the following key fields:
- `id`: Database ID (auto-generated)
- `matchID`: Tournament match identifier
- `playerName`: Name of the player in the match
- `gameWon`: Boolean indicating if the match was won
- `leftChildId`, `rightChildId`: Child match references, allowing matches to be structured as a binary tree

## Example Usage

```sh
# Get match details
curl http://localhost:8080/tournament/match/1

# Get path to a match
curl http://localhost:8080/tournament/path?matchID=9

# Get all winners
curl http://localhost:8080/tournament/winners
```

## Running Locally

1. **Clone the repository**
   ```
   git clone https://github.com/farahamir/Tournament-matches.git
   cd Tournament-matches
   ```

2. **Build and run**
   ```
   ./mvnw spring-boot:run
   ```

3. **Access endpoints**
   - Default port: `8080`
   - Use the example `curl` commands above or a tool like Postman.

## Testing

JUnit and Spring Boot tests are available for controller and service logic:
- See `src/test/java/cgm/example/application/controller/TournamentControllerTest.java`
- See `src/test/java/cgm/example/application/service/TournamentServiceTest.java`

## Extending

- Add new queries or analytics by extending `TournamentService`.
- Frontend/UI integration can be done using the provided REST endpoints.

## License

MIT
