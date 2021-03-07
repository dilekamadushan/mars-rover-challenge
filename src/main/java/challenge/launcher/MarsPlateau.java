package challenge.launcher;

import challenge.exception.InvalidInputException;

public class MarsPlateau {

    private static final MarsPlateau MARS_PLATEAU = new MarsPlateau();

    public MarsPlateau() {
    }

    private int plateauX;
    private int plateauY;

    public static MarsPlateau getMarsSingleton(){
       return MARS_PLATEAU;
    }

    public int getPlateauX() {
        return plateauX;
    }

    public int getPlateauY() {
        return plateauY;
    }

    public void setPlateauX(int plateauX) {
        this.plateauX = plateauX;
    }

    public void setPlateauY(int plateauY) {
        this.plateauY = plateauY;
    }

    public void validateCoordinates (int coordinateX, int coordinateY) throws InvalidInputException{
        //check whether the given coordinates are positive
        if(coordinateX < 0 || coordinateY < 0){
            throw new InvalidInputException("Coordinates should be positive!");
        }
        //check whether the given coordinates are exceed the plateau
        if(coordinateX > plateauX || coordinateY > plateauY){
            throw new InvalidInputException("Coordinates should stay with in the plateau!");
        }
    }


}
