 

import java.util.LinkedList;

class Maze {
    private int width;
    private int height;
    private int depth;
    private char[][][] data;

    public Maze(int width, int height, int depth, char[][][] data) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.data = data;
    }

    private static int[] makePosition(int x, int y, int z) {
        int[] position = new int[3];

        position[0] = x;
        position[1] = y;
        position[2] = z;

        return position;
    }

    /* Returns either an int[3] of the entrance's position,
     * or null if no entrance can be found. */
    public int[] getEntrance() {
        int i, j, k;

        for (i = 0; i < depth; i++) {
            for (j = 0; j < height; j++) {
                for (k = 0; k < width; k++) {
                    if (data[i][j][k] == '@') {
                        return makePosition(k, j, i);
                    }
                }
            }
        }

        return null;
    }

    /**
     * Returns possible up-or-down movements from the selected tile.
     * On a stairs or portal tile, it returns a list of offsets
     * from the current depth level which are accessible to an
     * explorer.  For example, a stairs tile with stairs above
     * it and below it would return the array {-1, 1}.
     * If the given position is not a stairs or portal tile, an
     * empty list is returned.
     * @param  x the column of the tile
     * @param  y the row of the tile
     * @param  z the depth of the tile
     * @return a list of offsets or an empty list
     */
    public int[] findDisplacements(int x, int y, int z) {
        int i;
        LinkedList<Object> offsets;
        Object[] offsetsAsObjects = new Integer[1];
        int[] offsetsAsInts = new int[1];

        offsets = new LinkedList<Object>();

        if (data[z][y][x] == '+') {
            /* portals */

            for (i = z - 1; i >= 0; i--) {
                if (data[i][y][x] == '+') {
                    offsets.add(new Integer(i - z));
                }
            }

            for (i = z + 1; i < depth; i++) {
                if (data[i][y][x] == '+') {
                    offsets.add(new Integer(i - z));
                }
            }
        } else if (data[z][y][x] == '=') {
            /* stairs */

            if (z != 0) {
                if (data[z - 1][y][x] == '=') {
                    offsets.add(new Integer(-1));
                }
            }

            if (z != depth - 1) {
                if (data[z + 1][y][x] == '=') {
                    offsets.add(new Integer(1));
                }
            }
        }

        offsetsAsObjects = offsets.toArray();
        offsetsAsInts = new int[offsetsAsObjects.length];

        for (i = 0; i < offsetsAsObjects.length; i++) {
            offsetsAsInts[i] = ((Integer)offsetsAsObjects[i]).intValue();
        }

        return offsetsAsInts;
    }

    /**
     * Returns whether the selected tile is a victory tile.
     * @param x the column of the tile
     * @param y the row of the tile
     * @param z the depth of the tile
     * @return whether the tile is a victory tile
     */
    public boolean isVictoryTile(int x, int y, int z) {
        return data[z][y][x] == '*';
    }

    /* If the referenced tile exists, it is returned as a char.
     * If it doesn't, the character '?' is returned. */
    public char getTileAt(int x, int y, int z) {
        if (x < 0 || x >= width ||
            y < 0 || y >= height ||
            z < 0 || z >= depth) {
            return '?';
        } else {
            return data[z][y][x];
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }
}
