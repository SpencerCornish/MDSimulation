
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.MinPQ;

public class Main {

	public static void main(String[] args) {
		StdDraw.setScale(-2, +2);
		StdDraw.enableDoubleBuffering();
		MinPQ prq = new MinPQ();  // instantiates a new Minimum Priority Queue
		
		//TODO: Read in text file using stdin 
		//TODO: instantiate all particles read in as particles()
		
		//TODO: Add all elements to the priority queue
		
		Particle pTest = new Particle(0,0, .1, .1, );
		
		
		for (double t = 0.0; true; t += 0.02) {
			double x = Math.cos(t);
			double y = Math.cos(t);
			StdDraw.clear();
			StdDraw.setPenColor(StdDraw.BOOK_BLUE);
			StdDraw.filledCircle(x, y, 0.05);
			StdDraw.show();
			StdDraw.pause(15);
		}

	}
}
