import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Dimension;

public class Test {
    public static void main(String[] args) {
        /* Test the maze class. */
        Maze maze;
        int[] pos, offsets;

/* Size: w: 25, h: 25, d: 10 */



       
        /*

        pos = solver.next();
        while (pos != null) {
            System.out.println("Step (" + pos[0] + " " + pos[1] + " " + pos[2] + ")");
            pos = solver.next();
        }

        if (solver.isSolved()) {
            System.out.println("Solved");
        } else {
            System.out.println("Impossible");
        }
        
        */
       
       SwingUtilities.invokeLater(new Runnable() {
           public void run() {
               GUI gui = new GUI();
               JFrame frame = new JFrame();
               
               frame.getContentPane().setPreferredSize(new Dimension(640, 280));
               frame.add(gui);
               frame.pack();
               frame.setVisible(true);
           }
       });
    }
}
