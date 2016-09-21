package jbowie;

public class Test {
    public static void main(String[] args) {
        /* Test the maze class. */
        Maze maze;
        char[][][] map = new char[3][5][5];
        int[] pos, offsets;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 0; k < map[0][0].length; k++) {
                    map[i][j][k] = '#';
                }
            }
        }

        map[0][2][1] = '+';
        map[0][2][2] = '.';
        map[0][2][3] = '@';

        map[2][2][1] = '.';
        map[2][2][2] = '.';
        map[2][2][3] = '*';

        /* #####  layer 0
         * #####
         * #+.@#
         * #####
         * #####
         */
        /* #####  layer 1
         * #####
         * #####
         * #####
         * #####
         */
        /* #####  layer 2
         * #####
         * #..*#
         * #####
         * #####
         */

        maze = new Maze(5, 5, 3, map);
        pos = maze.getEntrance();
        System.out.println(pos[0] + " " + pos[1] + " " + pos[2]);

        System.out.println("Tile at (3, 2, 0): " + maze.getTileAt(3, 2, 0));
        System.out.println("Tile at (3, 2, 100): " + maze.getTileAt(3, 2, 100));

        System.out.println("Following the through-tile at (1, 2, 0):");
        System.out.println("  Tile type: " + maze.getTileAt(1, 2, 0));
        System.out.print("  Leads to offsets: ");

        offsets = maze.findDisplacements(1, 2, 0);
        for (int i = 0; i < offsets.length; i++) {
            System.out.print(offsets[i]);
            if (i != offsets.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();

        Solver solver = new Solver(maze);

        pos = solver.next();
        while (pos != null) {
            System.out.println("Step");
            pos = solver.next();
        }

        if (solver.isSolved()) {
            System.out.println("Solved");
        } else {
            System.out.println("Impossible");
        }
    }
}
