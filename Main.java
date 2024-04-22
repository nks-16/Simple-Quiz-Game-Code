import java.util.*;

class Player {
    private String name;
    private int points;

    public Player(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void deductPoints(int points) {
        this.points -= points;
    }
}

class QuizGame {
    private List<Player> players;
    private int currentRound;
    private int initialPoints;
    private int roundMultiplier;

    public QuizGame(int initialPoints, int roundMultiplier) {
        this.players = new ArrayList<>();
        this.currentRound = 2;
        this.initialPoints = initialPoints;
        this.roundMultiplier = roundMultiplier;
    }

    public void addPlayer(String name) {
        players.add(new Player(name, initialPoints));
    }

    public int calculatePoints(boolean isCorrect, int timeTaken) {
        int points = ((currentRound) * roundMultiplier * (timeTaken)) / 50;
        return points;
    }

    public void updatePoints(String playerName, int points, boolean isCorrect) {
        for (Player player : players) {
            if (player.getName().equals(playerName)) {
                if (isCorrect) {
                    player.addPoints(points);
                } else {
                    player.deductPoints(points);
                }
            }
            break;
        }
    }

    public void answerQuestion(String playerName, boolean isCorrect, int timeTaken) {
        int points = calculatePoints(isCorrect, timeTaken);
        updatePoints(playerName, points, isCorrect);
    }

    public void nextRound() {
        currentRound++;
    }

    public List<Player> getPlayers() {
        return players;
    }
}

public class Main {
    public static void main(String[] args) {
        QuizGame game = new QuizGame(0, 500);
        game.addPlayer("Player 1");
        game.addPlayer("Player 2");
        game.answerQuestion("Player 1", true, 40);
        game.answerQuestion("Player 2", false, 40);
        game.nextRound();
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + ": " + player.getPoints() + " points");
        }
    }
}
