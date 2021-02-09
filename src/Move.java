public interface Move {

    static State up(State state) {
        if (state.getBlankTileCoordinateX() - 1 >= 0) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX() - 1, state.getBlankTileCoordinateY()};

            swap(newMatrix, spaceLocation, tileLocation);
            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 1);
            return newState;
        } else {
            return null;
        }
    }

    static State down(State state) {
        if (state.getBlankTileCoordinateX() + 1 <= 3) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX() + 1, state.getBlankTileCoordinateY()};
            swap(newMatrix, spaceLocation, tileLocation);
            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 1);
            return newState;
        } else {
            return null;
        }
    }

    static State right(State state) {
        if (state.getBlankTileCoordinateY() + 1 <= 3) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY() + 1};
            swap(newMatrix, spaceLocation, tileLocation);
            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 1);
            return newState;
        } else {
            return null;
        }
    }

    static State left(State state) {
        if (state.getBlankTileCoordinateY() - 1 >= 0) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY() - 1};
            swap(newMatrix, spaceLocation, tileLocation);
            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 1);
            return newState;
        } else {
            return null;
        }
    }

    static State upAndRight(State state) {
        if (state.getBlankTileCoordinateX() - 1 >= 0 && state.getBlankTileCoordinateY() + 1 <= 3) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX() - 1, state.getBlankTileCoordinateY() + 1};
            swap(newMatrix, spaceLocation, tileLocation);

            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 3);
            return newState;
        } else {
            return null;
        }
    }

    static State upAndLeft(State state) {
        if (state.getBlankTileCoordinateX() - 1 >= 0 && state.getBlankTileCoordinateY() - 1 >= 0) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX() - 1, state.getBlankTileCoordinateY() - 1};
            swap(newMatrix, spaceLocation, tileLocation);

            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 3);
            return newState;
        } else {
            return null;
        }
    }

    public static State downAndRight(State state) {
        if (state.getBlankTileCoordinateX() + 1 <= 3 && state.getBlankTileCoordinateY() + 1 <= 3) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX() + 1, state.getBlankTileCoordinateY() + 1};
            swap(newMatrix, spaceLocation, tileLocation);

            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 3);
            return newState;
        } else {
            return null;
        }
    }

    static State downAndLeft(State state) {
        if (state.getBlankTileCoordinateX() + 1 <= 3 && state.getBlankTileCoordinateY() - 1 >= 0) {
            int[][] newMatrix = new int[4][4];
            newMatrix = copy(newMatrix, state.getMatrixPuzzle());
            int[] spaceLocation = {state.getBlankTileCoordinateX(), state.getBlankTileCoordinateY()};
            int[] tileLocation = {state.getBlankTileCoordinateX() + 1, state.getBlankTileCoordinateY() - 1};
            swap(newMatrix, spaceLocation, tileLocation);

            State newState = new State(newMatrix, state);
            newState.setCost(state.getCost() + 3);
            return newState;
        } else {
            return null;
        }
    }

    static void swap(int[][] puzzle, int[] space, int[] tile) {
        puzzle[space[0]][space[1]] = puzzle[tile[0]][tile[1]];
        puzzle[tile[0]][tile[1]] = 0;
    }

    static int[][] copy(int[][] old, int[][] current) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                old[i][j] = current[i][j];
        return old;
    }

}
