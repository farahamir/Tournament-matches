package cgm.example.application.controller;

import cgm.example.application.model.Match;
import cgm.example.application.service.TournamentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/match/{id}")
    public Match getMatchById(@PathVariable Long id) {
        return tournamentService.getMatchById(id);
    }


    @GetMapping("/path")
    public List<Match> getPathToMatch(@RequestParam Long matchID) {
        return tournamentService.getPathToMatch(matchID);
    }

    @GetMapping("/winners")
    public Set<String> getAllWinners() {
        return tournamentService.getAllWinners();
    }
}
