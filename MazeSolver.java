/* Interface for the maze:
 * maze.enterMaze() -> int[2]
 * maze.tryNorth(int[3]) -> boolean
 * maze.trySouth(int[3]) -> boolean
 * maze.tryEast(int[3]) -> boolean
 * maze.tryWest(int[3]) -> boolean
 * maze.tryThrough(int[3]) -> boolean
 * maze.findDisplacement() -> int  -- returns how much up or down you would go if you went "through"
 * maze.isSolved() -> boolean
 * maze.getWidth() -> int
 * maze.getHeight() -> int
 */

public class MazeSolver
{
    Maze maze;
    
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
        if (pos[2] > 0 && maze.getTileAt(pos[0] - 1, pos[1], pos[2]) != '#') {
            pos[0]--;
            return true;
        }
    }
    
    public Solver(Maze maze) {
        this.maze.enterMaze();
    }
    
    public int[] next() {
        
    }
}
