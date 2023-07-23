package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {
		return "N";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}
  
  @Override
	public boolean[][] possibleMoves() {
    int[][] possible = {
      { -1, -2 },
      { -2, -1 },
      { -2, 1 },
      { -1, 2 },
      { 1, 2 },
      { 2, 1 },
      { 2, -1 },
      { 1, -2 }
    };
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);
    
    for (int[] move : possible) {
      p.setValues(position.getRow() + move[0], position.getColumn() + move[1]);
      if (getBoard().positionExists(p) && canMove(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }
    }

		return mat;
	}
}
