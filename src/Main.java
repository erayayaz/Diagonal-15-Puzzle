import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        int[][] puzzle = null;
        int depth = 0;
        String input;
        //1. puzzle
        int[][] defaultPuzzle1 = {{0, 1, 3, 4},
                {12, 13, 2, 5},
                {11, 14, 15, 6},
                {10, 9, 8, 7}};
        // 2. puzzle
        int[][] defaultPuzzle2 = {{1, 3, 5, 4},
                {2, 13, 14, 15},
                {11, 12, 9, 6},
                {0, 10, 8, 7}};

        //3. puzzle
        int[][] defaultPuzzle3 = {{1, 13, 3, 4},
                {12, 11, 2, 5},
                {9, 8, 15, 7},
                {10, 6, 14, 0}};

        System.out.println("Welcome to the Diagonal 15-Puzzle Solver");
        System.out.println("Do you want a use default puzzles or generate a puzzle");
        Scanner sc = new Scanner(System.in);
        int puzzleType;
        int puzzleChoice;
        int algorithmChoice;

        BFS bfs = new BFS();
        DFS dfs = new DFS();
        IDS ıds = new IDS();
        UCS ucs = new UCS();
        ILS ıls = new ILS();
        AStarH1 aStarH1 = new AStarH1();
        AStarH2 aStarH2 = new AStarH2();

        while(true){
            while(true){
                System.out.println("Type '0' to use default puzzles, or '1' to generate a puzzle");
                input= sc.nextLine();
                if(isNumeric(input)){
                    puzzleType = Integer.parseInt(input);
                    if(puzzleType == 0){
                        System.out.println("First Puzzle:");
                        printPuzzle(defaultPuzzle1);
                        System.out.println("Second Puzzle:");
                        printPuzzle(defaultPuzzle2);
                        System.out.println("Third Puzzle:");
                        printPuzzle(defaultPuzzle3);
                        System.out.println("Which puzzle do you want to use, 1 or 2 or 3?");

                        while(true){
                            input=sc.nextLine();
                            if(isNumeric(input)){
                                puzzleChoice = Integer.parseInt(input);
                                if(puzzleChoice == 1){
                                    puzzle = defaultPuzzle1;
                                    break;
                                }else if(puzzleChoice == 2){
                                    puzzle= defaultPuzzle2;
                                    break;
                                }else if(puzzleChoice == 3){
                                    puzzle = defaultPuzzle3;
                                    break;
                                }else{
                                    System.out.println("Please enter a correct type input");
                                }
                            }else{
                                System.out.println("Please enter an integer.");
                            }
                        }
                        break;
                    }else if(puzzleType == 1){
                        System.out.println("Please enter an depth size between 1-24");
                        input = sc.nextLine();
                        if(isNumeric(input)){
                            depth = Integer.parseInt(input);
                            while(true)
                                if(1<=depth && depth<=24){
                                    puzzle = PuzzleGenerator.generatePuzzle(depth);
                                    break;
                                }else{
                                    System.out.println("Please enter an input between 1-24");
                                }
                        }else{
                            System.out.println("Please enter an integer.");
                        }
                        break;
                    }else{
                        System.out.println("Please enter an input between 0-1");
                    }
                }else{
                    System.out.println("Please enter an integer.");
                }
            }

            if(puzzle != null){
                State initialState = new State(puzzle);
                while(true){
                    System.out.println("Which Algorithm do you want to use or you can exit the system or change the puzzle?");
                    System.out.println("1. BFS");
                    System.out.println("2. DFS");
                    System.out.println("3. IDS");
                    System.out.println("4. UCS");
                    System.out.println("5. ILS");
                    System.out.println("6. A*(h1)");
                    System.out.println("7. A*(h2)");
                    System.out.println("8. Change The Puzzle");
                    System.out.println("9. Exit");
                    input = sc.nextLine();
                    if(isNumeric(input)){
                        algorithmChoice = Integer.parseInt(input);
                        if(algorithmChoice == 1){
                            bfs.solve(initialState);
                        }else if(algorithmChoice == 2){
                            dfs.solve(initialState);
                        }else if(algorithmChoice == 3){
                            ıds.solve(initialState,depth);
                        }else if(algorithmChoice == 4){
                            ucs.solve(initialState);
                        }else if(algorithmChoice == 5){
                            ıls.solve(initialState);
                        }else if(algorithmChoice == 6){
                            aStarH1.solve(initialState);
                        }else if(algorithmChoice == 7){
                            aStarH2.solve(initialState);
                        }else if(algorithmChoice == 8){
                            break;
                        }else if(algorithmChoice == 9){
                            System.out.println("Bye.");
                            exit(1);
                        }else{
                            System.out.println("Please enter an input between 1-7");
                        }
                    }else{
                        System.out.println("Please enter an correct input");
                    }
                }
            }
        }

    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void printPuzzle(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%4d", anInt);
            }
            System.out.println();
        }
    }
}
