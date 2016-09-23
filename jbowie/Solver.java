 

import java.util.Stack;

public class Solver
{
    private Maze maze;
    private int pos[];
    private Stack<int[]> trail;
    private boolean explored[][][];
    boolean solved;

    private boolean tryNorth() {
        System.out.println("Trying north...");

        if (pos[1] > 0 &&
            !explored[pos[2]][pos[1] - 1][pos[0]] &&
            maze.getTileAt(pos[0], pos[1] - 1, pos[2]) != '#') {
            trail.push(pos.clone());
            pos[1] -= 1;
            return true;
        }

        return false;
    }

    private boolean tryWest() {
        System.out.println("Trying west...");

        if (pos[0] > 0 &&
            !explored[pos[2]][pos[1]][pos[0] - 1] &&
            maze.getTileAt(pos[0] - 1, pos[1], pos[2]) != '#') {
            trail.push(pos.clone());
            pos[0] -= 1;
            return true;
        }

        return false;
    }

    private boolean tryEast() {
        System.out.println("Trying east...");

        if (pos[0] < maze.getWidth() - 1 &&
            !explored[pos[2]][pos[1]][pos[0] + 1] &&
            maze.getTileAt(pos[0] + 1, pos[1], pos[2]) != '#') {
            trail.push(pos.clone());
            pos[0] += 1;
            return true;
        }

        return false;
    }

    private boolean trySouth() {
        System.out.println("Trying south...");

        if (pos[0] < maze.getHeight() - 1 &&
            !explored[pos[2]][pos[1] + 1][pos[0]] &&
            maze.getTileAt(pos[0], pos[1] + 1, pos[2]) != '#') {
            trail.push(pos.clone());
            pos[1] += 1;
            return true;
        }

        return false;
    }

    public Solver(Maze maze) {
        int d, h, w;
        int i, j, k;
        
        this.pos = maze.getEntrance();
        this.maze = maze;
        this.solved = false;
        this.trail = new Stack<int[]>();
        
        d = maze.getDepth();
        h = maze.getHeight();
        w = maze.getWidth();
        this.explored = new boolean[d][h][w];

        for (i = 0; i < this.explored.length; i++) {
            for (j = 0; j < this.explored[0].length; j++) {
                for (k = 0; k < this.explored[0][0].length; k++) {
                    this.explored[i][j][k] = false;
                }
            }
        }
    }
    
    /**
     * Steps forward in the maze and returns the new position.
     * If there is no next position, null is returned and the
     * maze is considered either solved or impossible to solve.
     * @return the new position or null
     * @see    isSolved
     */
    public int[] next() {
        int[] offsets;

        /* Mark the current tile as explored. */
        explored[pos[2]][pos[1]][pos[0]] = true;

        if (maze.isVictoryTile(pos[0], pos[1], pos[2])) {
            /* The maze is solved. */
            solved = true;
            return null;
        } else {
            /* Follow one of the available paths and return that position. */
            if (!tryNorth() &&
                !tryEast() &&
                !tryWest() &&
                !trySouth()) {
                /* The cardinal directions didn't work, so try up and down. */

                offsets = maze.findDisplacements(pos[0], pos[1], pos[2]);

                System.out.println("There are " + offsets.length + " z-ways.");
                for (int i = 0; i < offsets.length; i++) {
                    System.out.println("  Trying one...");
                    if (!explored[pos[2] + offsets[i]][pos[1]][pos[0]]) {
                        trail.push(pos.clone());
                        pos[2] += offsets[i];
                        return pos;
                    }
                }

                /* None of the directions have worked. */

                if (!trail.empty()) {
                    /* Go back to the previous position and return that. */
                    System.out.println("Rewinding...");
                    pos = trail.pop();
                    return pos;
                } else {
                    /* The maze has been proven impossible to solve. */
                    return null;
                }
            }

            return pos;
        }
    }

    /**
     * Returns whether the maze has been solved.
     * If the solver has been completed the maze and found the victory
     * tile, true is returned.  Otherwise, false is returned.
     * @return whether the maze has been solved.
     * @see    next
     */
    public boolean isSolved() {
        return solved;
    }
}
