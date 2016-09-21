package jbowie;

import java.util.Stack;

public class Solver
{
    private Maze maze;
    private int pos[];
    private Stack<int[]> trail;
    private boolean explored[][][];
    boolean solved;

    private boolean tryNorth() {
        if (pos[1] > 0 &&
            !explored[pos[2]][pos[1] - 1][pos[0]] &&
            maze.getTileAt(pos[0], pos[1] - 1, pos[2]) != '#') {
            trail.push(pos);
            pos[1] -= 1;
            return true;
        }

        return false;
    }

    private boolean tryWest() {
        if (pos[0] > 0 &&
            !explored[pos[2]][pos[1]][pos[0] - 1] &&
            maze.getTileAt(pos[0] - 1, pos[1], pos[2]) != '#') {
            trail.push(pos);
            pos[0] -= 1;
            return true;
        }

        return false;
    }

    private boolean tryEast() {
        if (pos[0] < maze.getWidth() - 1 &&
            !explored[pos[2]][pos[1]][pos[0] + 1] &&
            maze.getTileAt(pos[0] + 1, pos[1], pos[2]) != '#') {
            trail.push(pos);
            pos[0] += 1;
            return true;
        }

        return false;
    }

    private boolean trySouth() {
        if (pos[0] < maze.getHeight() - 1 &&
            !explored[pos[2]][pos[1] + 1][pos[0]] &&
            maze.getTileAt(pos[0], pos[1] + 1, pos[2]) != '#') {
            trail.push(pos);
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

                for (int i = 0; i < offsets.length; i++) {
                    if (!explored[pos[2] + offsets[i]][pos[1]][pos[0]]) {
                        trail.push(pos);
                        pos[2] += offsets[i];
                        return pos;
                    }
                }

                /* None of the directions have worked. */

                if (!trail.empty()) {
                    /* Go back to the previous position and return that. */
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
    
    public boolean isSolved() {
        return solved;
    }
}
