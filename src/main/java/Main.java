import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game2048 game = new Game2048();
        Scanner scanner = new Scanner(System.in);

        while (!game.isGameOverPublic()) {
            System.out.println("Score: " + game.getScore());
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(game.getValueAt(i, j) + "\t");
                }
                System.out.println();
            }
            System.out.println("Move (left, right, up, down):");
            String move = scanner.next();
            switch (move) {
                case "left":
                    game.move(Game2048.Direction.LEFT);
                    break;
                case "right":
                    game.move(Game2048.Direction.RIGHT);
                    break;
                case "up":
                    game.move(Game2048.Direction.UP);
                    break;
                case "down":
                    game.move(Game2048.Direction.DOWN);
                    break;
                default:
                    System.out.println("Invalid move!");
            }
        }
        System.out.println("Game over! Score: " + game.getScore());
    }
}