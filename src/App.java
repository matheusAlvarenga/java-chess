import java.util.InputMismatchException;
import java.util.Scanner;

import application.UI;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        
        while(true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
            } catch (ChessException e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.print("Press enter for continue...");
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println();
                System.out.println(e.getMessage());
                System.out.print("Press enter for continue...");
                sc.nextLine();
            }
        }
    }
}
