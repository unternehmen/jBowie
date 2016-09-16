/* Interface for the maze:
 * maze.getEntrance() -> int[3]
 * maze.getTileAt(int x, int y, int z) -> char
 * maze.findDisplacement() -> int  -- returns how much up or down you would go if you went "through"
 * maze.getWidth() -> int
 * maze.getHeight() -> int
 */

public class MazeSolver
{
    private Maze maze;
    private int pos[3];
    boolean solved;
    
    private static boolean goNorth() {
        if (pos[1] > 0 && maze.getTileAt(pos[0], pos[1] - 1, pos[2]) != '#') {
            pos[1]--;
            return true;
        }
        
        return false;
    }
    
    private static boolean goSouth() {
        if (pos[0] < maze.getHeight() - 1
            && maze.getTileAt(pos[0], pos[1] + 1, pos[2]) != '#') {
            pos[1]++;
            return true;
        }
        
        return false;
    }
    
    private static boolean goEast() {
        if (pos[0] < maze.getWidth() - 1
            && maze.getTileAt(pos[0] + 1, pos[1], pos[2]) != '#') {
            pos[0]++;
            return true;
        }
    }
    
    private static boolean goWest() {
        if (pos[0] > 0 && maze.getTileAt(pos[0] - 1, pos[1], pos[2]) != '#') {
            pos[0]--;
            return true;
        }
    }
    
    private static boolean goThrough() {
        if (pos[2] > 0 && maze.findDisplacement(pos[0], pos[1], pos[2]) != '#') {
            pos[0]--;
            return true;
        }
    }
    
    /*
    private static int[] makePoint(int x, int y, int z) {
        int[] pt = new int[3];
        
        pt[0] = x;
        pt[1] = y;
        pt[2] = z;
        
        return pt;
    }
    */
    
    public Solver(Maze maze) {
        this.pos = maze.getEntrance();
        this.maze = maze;
        this.solved = false;
    }
    
    public int[] next() {
        /* If we are standing on the victory tile, the maze is solved.  Return null and set solved to true. */
        /* Else: If there are unexplored paths from the current position, follow one of them and return that position. */
        /* Else: If there are no unexplored paths, go back to the previous position and return that. */
        /* Else: If there is no previous position, the maze is impossible to solve.  Return null. */
    }
    
    public boolean isSolved() {
        return solved;
    }
}
