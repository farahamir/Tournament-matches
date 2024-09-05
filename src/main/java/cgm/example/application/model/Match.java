package cgm.example.application.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long matchID;
    private String playerName;
    private boolean gameWon;
    private Long leftChildId;  // ID of the left child match
    private Long rightChildId; // ID of the right child match

    public Long getMatchID() {
        return matchID;
    }

    public void setMatchID(Long matchID) {
        this.matchID = matchID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public Long getLeftChildId() {
        return leftChildId;
    }

    public void setLeftChildId(Long leftChildId) {
        this.leftChildId = leftChildId;
    }

    public Long getRightChildId() {
        return rightChildId;
    }

    public void setRightChildId(Long rightChildId) {
        this.rightChildId = rightChildId;
    }
}
