/* Interface for the maze:
 * maze.getEntrance() -> int[3]
 * maze.getTileAt(int x, int y, int z) -> char
 * maze.findDisplacement(int x, int y, int z) -> int  -- returns how much up or down you would go if you went "through"
 * maze.getWidth() -> int
 * maze.getHeight() -> int
 */
import java.util.Stack;

public class Solver
{
    private Maze maze;
    private int pos[3];
    private Stack<int[]> trail;
    boolean solved;
    
    private boolean isVictoryTile(int x, int y, int z) {
        return maze.getTileAt(x, y, z) == '*';
    }
    
    private boolean goNorth() {
        if (pos[1] > 0 && maze.getTileAt(pos[0], pos[1] - 1, pos[2]) != '#') {
            trail.push(pos);
            pos[1]--;
            return true;
        }
        
        return false;
    }
    
    private boolean goSouth() {
        if (pos[0] < maze.getHeight() - 1
            && maze.getTileAt(pos[0], pos[1] + 1, pos[2]) != '#') {
            trail.push(pos);
            pos[1]++;
            return true;
        }
        
        return false;
    }
    
    private boolean goEast() {
        if (pos[0] < maze.getWidth() - 1
            && maze.getTileAt(pos[0] + 1, pos[1], pos[2]) != '#') {
            trail.push(pos);
            pos[0]++;
            return true;
        }
    }
    
    private boolean goWest() {
        if (pos[0] > 0 && maze.getTileAt(pos[0] - 1, pos[1], pos[2]) != '#') {
            trail.push(pos);
            pos[0]--;
            return true;
        }
    }
    
    private boolean goThrough() {
        if (pos[2] > 0 && maze.findDisplacement(pos[0], pos[1], pos[2]) != '#') {
            trail.push(pos);
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
        int d, h, w;
        
        this.pos = maze.getEntrance();
        this.maze = maze;
        this.solved = false;
        this.trail = new Stack<int[]>();
        
        d = maze.getDepth();
        h = maze.getHeight();
        w = maze.getWidth();
        this.explored = new boolean[d][h][w];
    }
    
    private boolean isPathAvailable() {
        /* Return whether there is an unexplored, passable path from the current position. */
    }
    
    public int[] next() {
        /* Mark the current tile as explored. */
        explored[pos[2]][pos[1]][pos[0]] = true;

        if (isVictoryTile(pos[0], pos[1], pos[2])) {
            /* The maze is solved. */
            solved = true;
            return null;
        } else if (isPathAvailable()) {
            /* Follow one of the available paths and return that position. */
        } else if (!trail.empty()) {
            /* Go back to the previous position and return that. */
            pos = trail.pop();
            return pos;
        } else {
            /* The maze has been proven impossible to solve. */
            return null;
        }
    }
    
    public boolean isSolved() {
        return solved;
    }
}
