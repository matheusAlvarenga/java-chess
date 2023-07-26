package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece{

  private ChessMatch chessMatch;

  public Pawn(Board board, Color color, ChessMatch chessMatch) {
    super(board, color);
    this.chessMatch = chessMatch;
  }
  
  @Override
  public String toString() {
    return "P";
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    Position p = new Position(0, 0);

    int rowsCalc = getColor() == Color.WHITE ? -1 : 1;

    p.setValues(position.getRow() + rowsCalc, position.getColumn());
    if(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValues(position.getRow() + (2 * rowsCalc), position.getColumn());
    Position p2 = new Position(position.getRow() + rowsCalc, position.getColumn());
    if (
      getBoard().positionExists(p)
      && !getBoard().thereIsAPiece(p)
      && getBoard().positionExists(p2)
      && !getBoard().thereIsAPiece(p2)
      && getMoveCount() == 0
    ) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValues(position.getRow() + rowsCalc, position.getColumn() - 1);
    if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    p.setValues(position.getRow() + rowsCalc, position.getColumn() + 1);
    if(getBoard().positionExists(p) && isThereOpponentPiece(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }
    
    // CHECKING EN PASSANT
    if (position.getRow() == 3 || position.getRow() == 4) {
      Position left = new Position(position.getRow(), position.getColumn() - 1);
      if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
        mat[left.getRow() + rowsCalc][left.getColumn()] = true;
      }

      Position right = new Position(position.getRow(), position.getColumn() + 1);
      if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
        mat[right.getRow() + rowsCalc][right.getColumn()] = true;
      }
    }

    return mat;
  }
}
