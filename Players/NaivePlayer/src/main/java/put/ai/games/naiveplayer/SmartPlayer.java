/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package put.ai.games.naiveplayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import put.ai.games.game.Board;
import put.ai.games.game.Move;
import put.ai.games.game.Player;
import put.ai.games.game.moves.PlaceMove;

public class SmartPlayer extends Player {

    private Random random = new Random(0xdeadbeef);
    private ArrayList<PlaceMove> guaranteed = new ArrayList<>();


    @Override
    public String getName() {
        return "Ramon Dyzman 132213 Julia Dziuba 132217";
    }


    @Override
    public Move nextMove(Board b) {
        List<Move> moves = b.getMovesFor(getColor());

        moves.sort(Comparator.comparingInt(o -> getHeuristicValue(o, b)));

//        PlaceMove top = (PlaceMove)moves.get(moves.size()-1);
//        guaranteed.add()


        return moves.get(moves.size()-1);

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
        Color upup = b.getState(place.getX(),place.getY()+2);
        if(upup==Color.EMPTY){
            value +=10;
        }
        Color upright = b.getState(place.getX()+1, place.getY()+1);
        if(upright==Color.EMPTY){
            value +=10;
        }
        Color rightright = b.getState(place.getX()+2, place.getY());
        if(upright==Color.EMPTY){
            value +=10;
        }
        Color botright = b.getState(place.getX()+1, place.getY()-1);
        if(botright==Color.EMPTY){
            value +=10;
        }
        Color botbot = b.getState(place.getX()-2, place.getY()-2);
        if(botbot==Color.EMPTY){
            value +=10;
        }
        Color botleft = b.getState(place.getX()-1, place.getY()-1);
        if(botleft==Color.EMPTY){
            value +=10;
        }
        Color lefteft = b.getState(place.getX()-2, place.getY());
        if(lefteft==Color.EMPTY){
            value +=10;
        }
        Color upleft = b.getState(place.getX()-1, place.getY()+1);
        if(upleft==Color.EMPTY){
            value +=10;
        }

        if(place.getY() == b.getSize()-1 || place.getX() == b.getSize()-1 || place.getY() == 0 || place.getX()==0 && value>=20){
            value-=20;
        }

        if( right == getColor() || left == getColor() || up == getColor() || bottom == getColor()){
            return 0;
        } else return value;
    }
}
