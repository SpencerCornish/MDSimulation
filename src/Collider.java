
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.awt.Color;

import edu.princeton.cs.algs4.MinPQ;

public class Collider {
	private Particle[] particle; // Main array of particles passed in from main
									// method
	private MinPQ<Event> eventPQ; // Queue of events
	private double tick = 0.0; // time ticker
	private double modifier = 1; // This somehow modifies timing for the
									// system...

	public Collider(Particle[] particle) {
		this.particle = particle;
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
				particle[i].move(currentEvent.getTime() - tick); // Move the
																	// particles
																	// the
																	// distance
																	// since the
																	// last
																	// event
																	// (Tick
																	// Difference)
			tick = currentEvent.getTime(); // Increment the time
			Particle a = currentEvent.getA();
			Particle b = currentEvent.getB();
			if (a == null && b == null)
				redraw();
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
		StdDraw.clear(); // Clears the canvas
		for (int i = 0; i < particle.length; i++)
			particle[i].draw(); // Redraws all particles
		StdDraw.pause(20);
		StdDraw.show(); // Shows the canvas once drawing is complete
		eventPQ.insert(new Event(tick + 1 / modifier, null, null));
	}

	public void predict(Particle p) {
		// Add horizontal Event
		double hCollide = p.collidesH();
		eventPQ.insert(new Event(tick + hCollide, p, null));
		// Add vertical Event
		double vCollide = p.collidesV();
		eventPQ.insert(new Event(tick + vCollide, null, p));

		for (int i = 0; i < particle.length; i++) {
			double tPart = p.collides(particle[i]);
			if (tPart < 0)
				continue;
			else {
				// A valid time has been reported

				eventPQ.insert(new Event(tick + tPart, p, particle[i]));
			}
		}
	}

	public static void main(String[] args) {
		// StdDraw.setScale(-2, +2);
		StdDraw.enableDoubleBuffering();
		Particle[] partArray; // Array of particles
		if (args.length == 1) {
			int n = Integer.parseInt(args[0]);
			partArray = new Particle[n];
			for (int i = 0; i < n; i++)
				partArray[i] = new Particle();
		} else if (args.length == 0) {
			System.out.println("No StdIn, making generic particles :D");
			int n = 10; // Number of generic particles
			partArray = new Particle[n];
			for (int i = 0; i < n; i++) {
				partArray[i] = new Particle();
			}
		} else {
			int count = StdIn.readInt(); // First integer of file is count of
											// total particles
			partArray = new Particle[count];
			for (int i = 0; i < count; i++) {
				double rx = StdIn.readDouble();
				double ry = StdIn.readDouble();
				double vx = StdIn.readDouble();
				double vy = StdIn.readDouble();
				double radius = StdIn.readDouble();
				double mass = StdIn.readDouble();
				int r = StdIn.readInt();
				int g = StdIn.readInt();
				int b = StdIn.readInt();
				Color color = new Color(r, g, b);
				partArray[i] = new Particle(rx, ry, vx, vy, radius, mass, color); // instantiate
																					// particle
																					// in
																					// array
			}
		}
		Collider collider = new Collider(partArray);
		collider.run();
	}
}
