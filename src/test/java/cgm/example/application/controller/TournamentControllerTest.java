package cgm.example.application.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TournamentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void getPathToMatch() {
        var result = this.restTemplate.getForObject("http://localhost:" + port + "/tournament/path?matchID={matchID}", List.class,1);
        var iterator = result.iterator();
        var m1 = iterator.next();
        var m2 = iterator.next();
        var m3 = iterator.next();
        var m4 = iterator.next();
        assertThat(m1.toString()).isEqualTo("{id=30, matchID=15, playerName=Player 14, gameWon=true, leftChildId=14, rightChildId=13}");
        assertThat(m2.toString()).isEqualTo("{id=27, matchID=14, playerName=Player 3, gameWon=true, leftChildId=9, rightChildId=10}");
        assertThat(m3.toString()).isEqualTo("{id=18, matchID=9, playerName=Player 3, gameWon=true, leftChildId=1, rightChildId=2}");
        assertThat(m4.toString()).isEqualTo("{id=1, matchID=1, playerName=Player 1, gameWon=true, leftChildId=0, rightChildId=0}");

    }


    @Test
    void getAllWinners() {
        var result = this.restTemplate.getForObject("http://localhost:" + port + "/tournament/winners", Set.class);
        assertThat(result.size()).isEqualTo(7);
        assertThat(result).containsAll(Set.of("Player 10","Player 8","Player 16","Player 3","Player 6","Player 14","Player 1"));
    }
}