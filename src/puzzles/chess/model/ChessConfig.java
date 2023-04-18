package puzzles.chess.model;

import puzzles.clock.ClockConfig;
import puzzles.common.solver.Configuration;

import java.util.*;

public class ChessConfig implements Configuration {
    private String[][] chessBoard;
    private static int dim;
    public ChessConfig(String[][] chessBoard) {
        this.chessBoard = chessBoard;
        dim = chessBoard.length;
    }

    @Override
    public boolean isSolution() {
        int pieceCount = 0;
        for (int i = 0; i<dim; i++) {
            for (int j = 0; j<dim; j++) {
                if (!(this.chessBoard[i][j].equals("."))){
                    pieceCount+=1;
                }
            }
        }
        if (pieceCount == 1){
            return true;
        }
        return false;
    }

    @Override
    public Collection<Configuration> getNeighbors() {
        Set neighbors = new LinkedHashSet<ChessConfig>();
        for (int i = 0; i<dim; i++) {
            for (int j = 0; j<dim; j++) {
                if (this.chessBoard[i][j].equals("P")){
                    neighbors.addAll(getPawnNeighbors(i,j));
                } else if (this.chessBoard[i][j].equals("N")) {
                    neighbors.addAll(getKnightNeighbors(i,j));
                } else if (this.chessBoard[i][j].equals("K")) {
                    neighbors.addAll(getKingNeighbors(i,j));
                } else if (this.chessBoard[i][j].equals("R")) {
                    neighbors.addAll(getRookNeighbors(i,j));
                }
            }
        }
        return neighbors;
    }
    @Override
    public boolean equals(Object other){
        if(!(other instanceof ChessConfig)) {
            return false;
        }
        else
        {
            ChessConfig otherChess = (ChessConfig) other;
            for (int i = 0; i<dim; i++){
                for(int j = 0; j<dim; j++){
                    if (!(this.chessBoard[i][j].equals(otherChess.chessBoard[i][j]))){
                        return false;
                    }
                }
            }
            return true;
        }
    }
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(chessBoard);
    }
    @Override
    public String toString() {
        List<String> listOfStringRow = new ArrayList<>();
        String chessString = "";
        for (int i = 0; i<dim; i++) {
            String stringRow = "";
            for (int j = 0; j<dim; j++) {
                stringRow+=chessBoard[i][j] + " ";
            }
            listOfStringRow.add(stringRow);
        }
        for (String s: listOfStringRow){
            chessString+=(s+"\n");
        }
        return chessString;
    }
    public String[][] makeNeighbor(int startRow, int startCol, int endRow, int endCol, String piece){
        String[][] newChessBoard = new String[dim][dim];
        for (int i = 0; i<dim; i++) {
            for (int j = 0; j<dim; j++) {
                if (i == startRow && j == startCol){
                    newChessBoard[i][j] = ".";
                } else if (i == endRow && j == endCol) {
                    newChessBoard[i][j] = piece;
                } else {
                    newChessBoard[i][j] = this.chessBoard[i][j];
                }
            }
        }
        return newChessBoard;
    }
    public Collection<Configuration> getPawnNeighbors(int row, int col){
        Set pawnNeighbors = new LinkedHashSet<ChessConfig>();
        try{
            if (!(this.chessBoard[row-1][col+1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-1,col+1, "P"));
                pawnNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}

        try{
            if (!(this.chessBoard[row-1][col-1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-1,col-1, "P"));
                pawnNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        return pawnNeighbors;
    }
    public Collection<Configuration> getKnightNeighbors(int row, int col){
        Set knightNeighbors = new LinkedHashSet<ChessConfig>();
        try{
            if (!(this.chessBoard[row-2][col-1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-2,col-1, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row-2][col+1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-2,col+1, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row-1][col+2].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-1,col+2, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row+1][col+2].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row+1,col+2, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row+2][col+1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row+2,col+1, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row+2][col-1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row+2,col-1, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row+1][col-2].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row+1,col-2, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row-1][col-2].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-1,col-2, "N"));
                knightNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        return knightNeighbors;
    }
    public Collection<Configuration> getKingNeighbors(int row, int col){
        Set kingNeighbors = new LinkedHashSet<ChessConfig>();
        try{
            if (!(this.chessBoard[row-1][col].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-1,col, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row+1][col].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row+1,col, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row][col-1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row,col-1, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row][col+1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row,col+1, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row+1][col+1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row+1,col+1, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row-1][col-1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-1,col-1, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row+1][col-1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row+1,col-1, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        try{
            if (!(this.chessBoard[row-1][col+1].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row-1,col+1, "K"));
                kingNeighbors.add(neighbor);
            }
        } catch (IndexOutOfBoundsException e){}
        return kingNeighbors;
    }
    public Collection<Configuration> getRookNeighbors(int row, int col){
        Set rookNeighbors = new LinkedHashSet<ChessConfig>();
        //Moves up
        for(int i = row-1; i>=0; i-=1){
            if(!(this.chessBoard[i][col].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, i, col, "R"));
                rookNeighbors.add(neighbor);
                break;
            }
        }
        //Moves down
        for(int i=row+1; i<dim; i+=1){
            if(!(this.chessBoard[i][col].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, i, col, "R"));
                rookNeighbors.add(neighbor);
                break;
            }
        }
        //Moves left
        for(int i=col-1; i>=0; i-=1){
            if(!(this.chessBoard[row][i].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row, i, "R"));
                rookNeighbors.add(neighbor);
                break;
            }
        }
        //Moves right
        for(int i=col+1; i<dim; i++){
            if(!(this.chessBoard[row][i].equals("."))){
                ChessConfig neighbor = new ChessConfig(makeNeighbor(row, col, row, i, "R"));
                rookNeighbors.add(neighbor);
                break;
            }
        }
        return rookNeighbors;
    }

}
