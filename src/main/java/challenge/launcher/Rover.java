package challenge.launcher;

import java.util.HashSet;

public class Rover {

    private int roverX;
    private int roverY;
    private char facingDirection;
    private final HashSet validDirections;

    public Rover(int roverX, int roverY, char facingDirection, HashSet validDirections){
        this.roverX = roverX;
        this.roverY = roverY;
        this.facingDirection = facingDirection;
        this.validDirections = validDirections;
    }

    public void setFacingDirection(char direction){
        if(validDirections.contains(direction)){
            this.facingDirection = direction;
        }
    }

    public void changeFacingDirection(char rotateDegrees){
        if(rotateDegrees == 'L' || rotateDegrees == 'R'){
            switch(facingDirection){
                case 'N':
                    if(rotateDegrees == 'L'){
                        setFacingDirection('W');
                    } else {
                        setFacingDirection('E');
                    }
                    break;
                case 'W':
                    if(rotateDegrees == 'L'){
                        setFacingDirection('S');
                    } else {
                        setFacingDirection('N');
                    }
                    break;
                case 'E':
                    if(rotateDegrees == 'L'){
                        setFacingDirection('N');
                    } else {
                        setFacingDirection('S');
                    }
                    break;
                case 'S':
                    if(rotateDegrees == 'L'){
                        setFacingDirection('E');
                    } else {
                        setFacingDirection('W');
                    }
                    break;
            }
        }
    }

    public boolean canMove(int marsMaxX, int marsMaxY){
        switch(facingDirection){
            case 'N':
                return this.roverY < marsMaxY;
            case 'W':
                return this.roverX > 0;
            case 'E':
                return this.roverX < marsMaxX;
            case 'S':
                return this.roverY > 0;
        }
        return false;
    }

    public void moveForward(){
        switch(facingDirection){
            case 'N':
                this.roverY += 1;
                break;
            case 'W':
                this.roverX += -1;
                break;
            case 'E':
                this.roverX += 1;
                break;
            case 'S':
                this.roverY += -1;
                break;
        }
    }

    public String getOutput(){
        return this.roverX + " " + this.roverY + " " + this.facingDirection;
    }

}
