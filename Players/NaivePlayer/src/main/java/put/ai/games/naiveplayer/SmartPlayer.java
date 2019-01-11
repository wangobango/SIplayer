/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.naiveplayer;

import java.util.List;
import java.util.Random;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;
import put.ai.games.game.moves.PlaceMove;

public class SmartPlayer extends Player {

    private Random random = new Random(0xdeadbeef);


    @Override
    public String getName() {
        return "Gracz Naiwny 84868";
    }


    @Override
    public Move nextMove(Board b) {
        List<Move> moves = b.getMovesFor(getColor());

        for(Move pom:moves){
            System.out.println(getHeuristicValue(pom,b));
        }

        return moves.get(random.nextInt(moves.size()));

    }

    public int getHeuristicValue(Move m, Board b){
        int value = 0;
        PlaceMove place = (PlaceMove)m;

        Color right = b.getState(place.getX()+1,place.getY());
        if(right == Color.EMPTY){
            value+=10;
        }
        Color bottom = b.getState(place.getX(),place.getY()-1);
        if(bottom== Color.EMPTY){
            value+=10;
        }
        Color left = b.getState(place.getX()-1,place.getY());
        if(left == Color.EMPTY){
            value+=10;
        }
        Color up = b.getState(place.getX(),place.getY()+1);
        if(up == Color.EMPTY){
            value+=10;
        }

        if( right == getColor() || left == getColor() || up == getColor() || bottom == getColor()){
            return 0;
        } else return value;
    }
}
