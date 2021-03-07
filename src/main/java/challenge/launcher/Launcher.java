package challenge.launcher;

import challenge.exception.InvalidInputException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * This class contains the utilities to take user inputs and start rovers.
 */
public class Launcher {

    public Launcher() {

    }

    public static void startVoyage() {
        HashSet validDirections = new HashSet<>(Arrays.<Character>asList('N', 'S', 'W', 'E'));
        // create Mars object using singleton design pattern
        MarsPlateau marsPlateau = MarsPlateau.getMarsSingleton();
        Scanner readSpaceStationSignals = new Scanner(System.in);

        String[] plateauCoordinates;
        plateauCoordinates = readSpaceStationSignals.nextLine().split(" ");
        try {
            // validating user input for plateau coordinates
            if (plateauCoordinates.length != 2) {

                throw new InvalidInputException("Coordinates for plateau are invalid");
            }

            int marsPlateauX = Integer.parseInt(plateauCoordinates[0]);
            int marsPlateauY = Integer.parseInt(plateauCoordinates[1]);
            // initializing plateau coordinates
            marsPlateau.setPlateauX(marsPlateauX);
            marsPlateau.setPlateauY(marsPlateauY);
            // validating plateau coordinates
            marsPlateau.validateCoordinates(marsPlateauX, marsPlateauY);

            System.out.println("NASA Started launching rovers now");

            while (!readSpaceStationSignals.hasNext("exit")) {
                String[] roverInitializer = readSpaceStationSignals.nextLine().toUpperCase().split(" ");
                // validating user input for rover coordinates
                if (roverInitializer.length != 3) {
                    throw new InvalidInputException("Coordinates for rover are invalid");
                }
                // user input for rover facing direction should exist in ('N', 'S', 'W', 'E')
                if (!validDirections.contains(roverInitializer[2].charAt(0))) {
                    throw new InvalidInputException("Invalid direction for rover");
                }
                // initializing rover coordinates
                Rover nasaRover = new Rover(Integer.parseInt(roverInitializer[0]), Integer.parseInt(roverInitializer[1]), roverInitializer[2].charAt(0), validDirections);
                marsPlateau.validateCoordinates(Integer.parseInt(roverInitializer[0]), Integer.parseInt(roverInitializer[1]));
                // taking user inputs to move the rover
                String commandInputs = readSpaceStationSignals.nextLine().toUpperCase();
                for (char command : commandInputs.toCharArray()) {
                    switch (command) {
                        case 'L':
                            nasaRover.changeFacingDirection('L');
                            break;
                        case 'R':
                            nasaRover.changeFacingDirection('R');
                            break;
                        case 'M':
                            if (nasaRover.canMove(marsPlateauX, marsPlateauY)) nasaRover.moveForward();
                            break;
                        default:
                            throw new InvalidInputException("invalid input");
                    }
                }
                System.out.println(nasaRover.getOutput());
            }

        }
        // handling specific errors to output meaningful error messages for the user
        catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid input. Use numbers");
        } catch (InvalidInputException invalidInputException) {
            System.out.println(invalidInputException.getMessage());
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Invalid input. Unknown Error");
        } finally {
            // closing the scanner
            readSpaceStationSignals.close();
            System.out.println("Exiting...");
        }
    }
}
