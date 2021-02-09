import java.util.*;

public class ILS {
    static int[][] solution = {{1, 2, 3, 4},
            {12, 13, 14, 5},
            {11, 0, 15, 6},
            {10, 9, 8, 7}};

    private int maxNumberOfStoredInMemory = 0;
    private int totalVisitedNode = 0;

    private boolean solvedFlag = false;

    public static HashSet<State> visited = new HashSet<>();
    public static final PriorityQueue<State> frontier = new PriorityQueue<>(new Comparator<State>() {
        @Override
        public int compare(State state, State t1) {
            return state.getCost() - t1.getCost();
        }
    });

    // We apply the algorithm and print the solution path, cost, number of expanded nodes and number of nodes stored in memory
    public void solve(State state) {

        int maxCost = 100000;

        for (int currentCoast = 0; currentCoast < maxCost; currentCoast++) {

            visited.clear();
            frontier.clear();
            frontier.add(state);
            State currentState;
            while (!frontier.isEmpty()) {
                currentState = frontier.poll();

                if (isSolution(currentState.getMatrixPuzzle())) {
                    State printState = currentState;

                    List<State> list = new ArrayList<>();
                    while (currentState != null) {
                        list.add(currentState);
                        currentState = currentState.getPreviousState();
                    }

                    Collections.reverse(list);

                    for (State reverseState : list
                    ) {
                        printPuzzle(reverseState.getMatrixPuzzle());
                        System.out.println("----------- cost of move is " + reverseState.getCost());

                    }

                    solvedFlag = true;
                    //System.out.println("Solved at coast level: " + printState.getCost());
                    System.out.println("Total number of expanded node: " + totalVisitedNode);
                    System.out.println("Maximum number of nodes stored in the memory: " + maxNumberOfStoredInMemory);

                    break;
                }


                visited.add(currentState);

                addQueue(Move.up(currentState), currentCoast);
                addQueue(Move.down(currentState), currentCoast);
                addQueue(Move.left(currentState), currentCoast);
                addQueue(Move.right(currentState), currentCoast);
                addQueue(Move.downAndLeft(currentState), currentCoast);
                addQueue(Move.upAndLeft(currentState), currentCoast);
                addQueue(Move.downAndRight(currentState), currentCoast);
                addQueue(Move.upAndRight(currentState), currentCoast);
                maxNumberOfStoredInMemory = Math.max(maxNumberOfStoredInMemory, frontier.size() + visited.size());


            }

            totalVisitedNode = totalVisitedNode + visited.size();

            if (solvedFlag){
                break;
            }

        }
    }
    //Check each time if the visited node is the goal node.
    public static boolean isSolution(int[][] puzzle) {
        return Arrays.deepEquals(puzzle, solution);
    }
    //If children nodes are in visited or frontier, we will not add to frontier. We check it then add it to frontier if there is no problem.
    public void addQueue(State state, int currentCoast) {
        if(state != null){
            if(state.getCost() <= currentCoast) {
                if (searchVisited(state) && searchFrontier(state)) {
                    frontier.add(state);
                } else if (searchAtFrontier(state)) {
                    swapFrontier(state);
                }
            }
        }
    }
    public void swapFrontier(State state){

        for (State tempState: frontier) {
            if(Arrays.deepEquals(tempState.getMatrixPuzzle(), state.getMatrixPuzzle())){
                if(tempState.getCost() > state.getCost()){
                    frontier.remove(tempState);
                    frontier.add(state);
                    break;
                }
            }
        }

    }
    //Checking that the child node is already at the frontier.
    public boolean searchAtFrontier(State state){

        for (State tempState: frontier) {
            if(Arrays.deepEquals(tempState.getMatrixPuzzle(), state.getMatrixPuzzle())){
                return true;
            }
        }
        return false;
    }
    //Checking whether the child node is already visited or not.
    public boolean searchVisited(State state){
        for (State tempState: visited) {
            if(Arrays.deepEquals(tempState.getMatrixPuzzle(), state.getMatrixPuzzle())){
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
