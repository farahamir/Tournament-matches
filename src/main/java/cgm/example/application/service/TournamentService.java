package cgm.example.application.service;

import cgm.example.application.model.Match;
import cgm.example.application.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TournamentService {

    @Value("${root.match.id}")
    public long ROOT_MATCH_ID ;

    private final MatchRepository matchRepository;

    public TournamentService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public Match getMatchByMatchID(Long matchID, Boolean gamWon) {
        return matchRepository.findFirstByMatchIDAndGameWon(matchID,gamWon);
    }

    public List<Match> getPathToMatch(Long matchID) {
        Match root = getMatchByMatchID(ROOT_MATCH_ID, true); // Assuming root match has ID 15
        List<Match> path = new ArrayList<>();
        findMatchDFS(root, matchID, path);
        return path;
    }

    private boolean findMatchDFS(Match current, Long matchID, List<Match> path) {
        if (current == null) {
            return false;
        }
        path.add(current);
        if (current.getMatchID().equals(matchID)) {
            return true;
        }
        if (findMatchDFS(getMatchByMatchID(current.getLeftChildId(),true), matchID, path) ||
                findMatchDFS(getMatchByMatchID(current.getRightChildId(),true), matchID, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }

    public Set<String> getAllWinners() {
        Set<String> winners = new HashSet<>();
        Queue<Match> queue = new LinkedList<>();
        queue.add(getMatchByMatchID(ROOT_MATCH_ID,true)); // Assuming root match has ID 15
        while (!queue.isEmpty()) {
            Match current = queue.poll();
            if (current == null) continue;
            if (current.isGameWon()) {
                winners.add(current.getPlayerName());
            }
            queue.add(getMatchByMatchID(current.getLeftChildId(),true));
            queue.add(getMatchByMatchID(current.getRightChildId(),true));
        }
        return winners;
    }
}
