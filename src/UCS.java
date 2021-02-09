import java.util.*;

public class UCS {
    private final static int[][] solution = {{1, 2, 3, 4},
            {12, 13, 14, 5},
            {11, 0, 15, 6},
            {10, 9, 8, 7}};

    private static int counter = 0;
    private int maxNumberOfStoredInMemory = 0;


    private final HashSet<State> visited = new HashSet<>();
   private final PriorityQueue<State> frontier = new PriorityQueue<>(new Comparator<State>() {
        @Override
        public int compare(State state, State t1) {
            return state.getCost() - t1.getCost();
        }
    });

    // We apply the algorithm and print the solution path, cost, number of expanded nodes and number of nodes stored in memory
    public void solve(State state) {
        frontier.clear();
        frontier.add(state);
        State currentState;
        while (!frontier.isEmpty()) {
            currentState = frontier.poll();

            if (isSolution(currentState.getMatrixPuzzle())) {
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

                break;
            }


            visited.add(currentState);

            addQueue(Move.up(currentState));
            addQueue(Move.down(currentState));
            addQueue(Move.left(currentState));
            addQueue(Move.right(currentState));
            addQueue(Move.downAndLeft(currentState));
            addQueue(Move.upAndLeft(currentState));
            addQueue(Move.downAndRight(currentState));
            addQueue(Move.upAndRight(currentState));

            maxNumberOfStoredInMemory = Math.max(maxNumberOfStoredInMemory, frontier.size() + visited.size());

        }
    }
    //Check each time if the visited node is the goal node.
    public boolean isSolution(int[][] puzzle) {
        return Arrays.deepEquals(puzzle, solution);
    }


    //If children nodes are in visited or frontier, we will not add to frontier. We check it then add it to frontier if there is no problem.
    public void addQueue(State state) {
        if(state != null){
            if ( searchVisited(state) && searchFrontier(state) ) {
                frontier.add(state);
            }else if(searchAtFrontier(state)){
                swapFrontier(state);
            }
        }
    }

    //İf node to be added is already addded, then the lower cost must be added to frontier between these 2 same node. In this case,
    // this method look to these two puzzle and take the puzzle with the smallest cost and if it needs to swap them.
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
