package cgm.example.application.repository;

import cgm.example.application.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MatchRepository extends JpaRepository<Match, Long> {
    Match findFirstByMatchIDAndGameWon(Long matchID, boolean gameWon);
}
