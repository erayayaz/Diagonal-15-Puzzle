public class State {

    private int [][] matrixPuzzle;
    private State previousState;
    private int cost;
    private int heuristic;
    private int blankTileCoordinateX;
    private int blankTileCoordinateY;
    private int depth;


    // Root node
    public State(int [][] matrixPuzzle){
        this.matrixPuzzle = matrixPuzzle;
        this.previousState= null;
        this.cost=0;
        this.depth=0;
        this.heuristic=0;
        int [] positions = findSpace(matrixPuzzle);
        this.blankTileCoordinateX= positions[0];
        this.blankTileCoordinateY=positions[1];

    }

    // Expanded node
    public State(int [][] matrixPuzzle, State previousState){
        this.matrixPuzzle = matrixPuzzle;
        this.previousState= previousState;
        this.cost=0;
        this.depth=0;
        this.heuristic=0;
        int [] positions = findSpace(matrixPuzzle);
        this.blankTileCoordinateX= positions[0];
        this.blankTileCoordinateY=positions[1];

    }

    public static int[] findSpace(int [][] puzzle){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(puzzle[i][j] == 0){
                    return new int[] {i,j};
                }
            }
        }
        return new int[] {0,0};
    }
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    public int[][] getMatrixPuzzle() {
        return matrixPuzzle;
    }

    public void setMatrixPuzzle(int[][] matrixPuzzle) {
        this.matrixPuzzle = matrixPuzzle;
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public int getBlankTileCoordinateX() {
        return blankTileCoordinateX;
    }

    public void setBlankTileCoordinateX(int blankTileCoordinateX) {
        this.blankTileCoordinateX = blankTileCoordinateX;
    }

    public int getBlankTileCoordinateY() {
        return blankTileCoordinateY;
    }

    public void setBlankTileCoordinateY(int blankTileCoordinateY) {
        this.blankTileCoordinateY = blankTileCoordinateY;
    }

}
