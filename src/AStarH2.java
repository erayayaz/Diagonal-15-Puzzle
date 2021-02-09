import java.util.*;

public class AStarH2 {

    static int[][] solutionMatrix = {{1, 2, 3, 4},
            {12, 13, 14, 5},
            {11, 0, 15, 6},
            {10, 9, 8, 7}};

    private int maxNumberOfStoredInMemory = 0;

    private final Set<State> visited = new HashSet<>();
   private final Queue<State> frontier = new PriorityQueue<>(new Comparator<State>() {
        @Override
        public int compare(State state, State t1) {
            return (state.getCost() + state.getHeuristic()) - (t1.getCost() + t1.getHeuristic());
        }
    });


    // We apply the algorithm and print the solution path, cost, number of expanded nodes and number of nodes stored in memory
    public  void solve(State state) {
        frontier.clear();
        frontier.add(state);
        State currentState;
        while (!frontier.isEmpty()) {
            currentState = frontier.poll();
//            System.out.println(frontier.size());

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
    //If children nodes are in visited or frontier, we will not add to frontier. We check it then add it to frontier if there is no problem.
    public void addQueue(State state) {

        if (state != null) {
            if (searchVisited(state) && searchFrontier(state)) {
                int heuristicValue = calculateHeuristicH2(state);
                state.setHeuristic(heuristicValue);
                frontier.add(state);
            } else if (searchAtFrontier(state)) {
                int heuristicValue = calculateHeuristicH2(state);
                state.setHeuristic(heuristicValue);
                swapFrontier(state);
            }
        }
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
    //İf node to be added is already addded, then the lower cost must be added to frontier between these 2 same node. In this case,
    // this method look to these two puzzle and take the puzzle with the smallest cost and if it needs to swap them.
    public void swapFrontier(State state){

        for (State tempState: frontier) {
            if(Arrays.deepEquals(tempState.getMatrixPuzzle(), state.getMatrixPuzzle())){
                if((tempState.getCost() + tempState.getHeuristic()) > (state.getCost() + state.getHeuristic())){
                    frontier.remove(tempState);
                    frontier.add(state);
                    break;
                }
            }
        }

    }
    //Checking that the child node is already at the frontier.
    public boolean searchAtFrontier(State state){
        if (state == null){
            return false;
        }

        for (State tempState: frontier) {
            if(Arrays.deepEquals(tempState.getMatrixPuzzle(), state.getMatrixPuzzle())){
                return true;
            }
        }
        return false;

    }

    //Check each time if the visited node is the goal node.
    public  boolean isSolution(int[][] puzzle) {
        return Arrays.deepEquals(puzzle, solutionMatrix);
    }

    // Calculate the of the city-block distances of each misplaced tile from its current location to its goal location.
    public  int calculateHeuristicH2(State state) {
        int heuristicValueH2 = 0;
        int[][] stateMatrix = state.getMatrixPuzzle();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int currentIndex = stateMatrix[i][j];
                for (int k = 0; k < 4; k++)
                    for (int l = 0; l < 4; l++) {
                        if (solutionMatrix[k][l] == currentIndex) {
                            heuristicValueH2 = heuristicValueH2 + Math.abs(k - i) + Math.abs(l - j);
                        }
                    }
            }
        }

        return heuristicValueH2;
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
