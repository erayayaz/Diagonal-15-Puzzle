import java.util.*;

public class IDS {

    static int[][] solution = {{1, 2, 3, 4},
            {12, 13, 14, 5},
            {11, 0, 15, 6},
            {10, 9, 8, 7}};
    private int maxNumberOfStoredInMemory = 0;
    private final HashSet<State> visited = new HashSet<>();
    private final Stack<State> frontier = new Stack<>();
    private boolean solvedFlag = false;

    // We apply the algorithm and print the solution path, cost, number of expanded nodes and number of nodes stored in memory
    public void solve(State state, int depthLimit) {

        for (int currentDepth = 0; currentDepth <= depthLimit; currentDepth++) {

            visited.clear();
            frontier.clear();
            frontier.add(state);
            State currentState;

            while (!frontier.isEmpty()) {
                currentState = frontier.pop();

                if (isSolution(currentState.getMatrixPuzzle())) {
                    State printState = currentState;
                    List<State> list = new ArrayList<>();
                    while (currentState != null) {
                        list.add(currentState);
                        currentState = currentState.getPreviousState();
                    }
                    Collections.reverse(list);

                    for (State reverseState: list
                    ) {
                        printPuzzle(reverseState.getMatrixPuzzle());
                        System.out.println("----------- cost of move is " + reverseState.getCost());

                    }
                    System.out.println("Total number of expanded node: " + visited.size());
                    System.out.println("Maximum number of nodes stored in the memory: " + maxNumberOfStoredInMemory);
                    solvedFlag = true;
                    break;
                }


                    visited.add(currentState);

                    addQueue(Move.upAndRight(currentState),currentDepth,currentState.getDepth());
                    addQueue(Move.upAndLeft(currentState),currentDepth,currentState.getDepth());
                    addQueue(Move.left(currentState),currentDepth,currentState.getDepth());
                    addQueue(Move.down(currentState),currentDepth,currentState.getDepth());
                    addQueue(Move.up(currentState),currentDepth, currentState.getDepth());
                    addQueue(Move.downAndLeft(currentState),currentDepth,currentState.getDepth());
                    addQueue(Move.downAndRight(currentState),currentDepth,currentState.getDepth());
                    addQueue(Move.right(currentState),currentDepth,currentState.getDepth());

                    maxNumberOfStoredInMemory = Math.max(maxNumberOfStoredInMemory, frontier.size() + visited.size());


            }

            if (solvedFlag){
                break;
            }

        }
    }
    //Check each time if the visited node is the goal node.
    public boolean isSolution(int[][] puzzle) {
        return Arrays.deepEquals(puzzle, solution);
    }

    //If children nodes are in visited or frontier, we will not add to frontier. We check it then add it to frontier if there is no problem.
    public void addQueue(State state, int currentDepth, int pastStateDepth) {

        if (state != null) {
            state.setDepth(pastStateDepth + 1);
            if(state.getDepth() <= currentDepth) {
                if (searchVisited(state) && searchFrontier(state)) {
                    frontier.push(state);
                }
            }
        }
    }
    //Checking whether the child node is already visited or not.
    public boolean searchVisited(State state) {
        for (State tempState : visited) {
            if (Arrays.deepEquals(tempState.getMatrixPuzzle(), state.getMatrixPuzzle())) {
                return false;
            }
        }
        return true;
    }

    //Checking whether the child node is already at frontier or not.
    public boolean searchFrontier(State state) {
        for (State tempState : frontier) {
            if (Arrays.deepEquals(tempState.getMatrixPuzzle(), state.getMatrixPuzzle())) {
                return false;
            }
        }
        return true;
    }
    // Print the solution path.
    public void printPuzzle(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.printf("%4d", anInt);
            }
            System.out.println();
        }
    }


}
