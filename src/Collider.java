/* 
 *  Lab 1: Particle Collision
 *  2-5-2017
 *  Authors: Keely Weisbeck
 *  		 Spencer Cornish
 * 	
 *  This file contains the main class and the Collider Class
 *  The collider class takes in data and runs the simulation
 *  The main class provides inputs to the collider class
 */
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import java.awt.Color;
import edu.princeton.cs.algs4.MinPQ;
import java.io.File;
import java.io.FileInputStream;

public class Collider {
	private Particle[] particle; // Main array of particles passed in from main
	// method
	private MinPQ<Event> eventPQ; // Queue of events
	private double tick = 0.0; // time ticker
	private double modifier = 5; // This somehow modifies timing for the
	// system...

	public Collider(Particle[] particle) {
		this.particle = particle;
		run(); // Run the simulation
	}

	public void run() {
		eventPQ = new MinPQ<Event>(); // Instantiate the Priority Queue
		eventPQ.insert(new Event(0, null, null)); // Insert first redraw event
		for (int i = 0; i < particle.length; i++)
			predict(particle[i]); // Iterate through all particles and predict
		// collisions
		while (!eventPQ.isEmpty()) // Main loop
		{
			Event currentEvent = eventPQ.delMin(); // removes the most current
			// event
			if (currentEvent.wasSuperveningEvent())
				continue; // Checks if the event is valid or not. If not, back
			// to start of while loop
			for (int i = 0; i < particle.length; i++)
				particle[i].move(currentEvent.getTime() - tick); // Move the particles
			tick = currentEvent.getTime(); // Increment the time to the current tick
			Particle a = currentEvent.getA(); //Gets Particles
			Particle b = currentEvent.getB();
			if (a == null && b == null) 
				redraw(); // Redraw event
			else if (a == null && b != null)
				b.bounceV(); // Vertical
			else if (a != null && b == null)
				a.bounceH(); // Horizontal
			else if (a != null && b != null)
				a.bounce(b); // Particle to particle collision

			if (a != null)
				predict(a);
			if (b != null)
				predict(b);

		}
	}

	public void redraw() {
		eventPQ.insert(new Event(tick + 1 / modifier, null, null)); // Inserts the new redraw event
		StdDraw.clear(); // Clears the canvas
		for (int i = 0; i < particle.length; i++)
			particle[i].draw(); // Redraws all particles
		StdDraw.text(0.9, 0.01, Double.toString(tick));
		StdDraw.pause(10); // 10ms pause to not kill computers
		StdDraw.show(); // Shows the canvas once drawing is complete
	}

	public void predict(Particle p) {
		// Add horizontal Event
		double hCollide = p.collidesH();
		if(hCollide >=0) eventPQ.insert(new Event(tick + hCollide, p, null));
		// Add vertical Event
		double vCollide = p.collidesV();
		if(vCollide >=0) eventPQ.insert(new Event(tick + vCollide, null, p));

		for (int i = 0; i < particle.length; i++) {
			double tPart = p.collides(particle[i]); // Check for P2P collisions, add if >= 0
			if (tPart >= 0) {
				// A valid time has been reported
				eventPQ.insert(new Event(tick + tPart, p, particle[i]));
			}
		}
	}

	public static void main(String[] args) {
		StdDraw.setCanvasSize(800, 800); // Sets window size
		StdDraw.enableDoubleBuffering();
		Particle[] partArray; // Array of particles
		// For testing purposes
		if (args.length == 0) {
			System.out.println("No StdIn, making generic particles");
			int n = 4; // Number of generic particles
			partArray = new Particle[n];
			for (int i = 0; i < n; i++) {
				partArray[i] = new Particle();
			}
		} else {
			try{ 
				FileInputStream is = new FileInputStream(new File(args[0]));
				System.setIn(is); // Change System.in to the file in args[0]
			}
			catch(Exception e) {
				
			}
			
			int count = StdIn.readInt(); // First integer of file is count of// total particles
			partArray = new Particle[count];
			for (int i = 0; i < count; i++) {
				double px = StdIn.readDouble(); // Position variables
				double py = StdIn.readDouble();
				
				double vx = StdIn.readDouble(); // Velocity variables
				double vy = StdIn.readDouble();
				
				double radius = StdIn.readDouble();
				double mass = StdIn.readDouble();
				int r = StdIn.readInt();
				int g = StdIn.readInt();
				int b = StdIn.readInt();
				
				partArray[i] = new Particle(px, py, vx, vy, radius, mass, r, g, b); // instantiate the particle
			}
		}
		new Collider(partArray); // Calls the collider with data
	}
}
