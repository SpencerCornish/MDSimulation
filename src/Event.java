/* 
 *  Lab 1: Particle Collision
 *  2-5-2017
 *  Authors: Keely Weisbeck
 *  		 Spencer Cornish
 * 	
 *  This file contains the event class
 *  Events hold data about collision times and redraw events
 */
public class Event implements Comparable<Event> {
	private double time; // Time of collision
	private Particle a;  // Particles
	private Particle b;
	private int countA, countB; // Number of collisions

	public Event(double time, Particle a, Particle b) {
		this.time = time;
		this.a = a;
		this.b = b;
		if (a != null)
			countA = a.getCollisionCount();
		if (b != null)
			countB = b.getCollisionCount();
	}

	// Automatically added compareTo (Thanks, Eclipse!)
	public int compareTo(Event other) {
		return Double.compare(time, other.getTime());
	}

	public boolean wasSuperveningEvent() {
		if (a != null && a.getCollisionCount() != countA)
			return true;
		else if (b != null && b.getCollisionCount() != countB)
			return true;
		else
			return false;
	}

	// @return the time
	public double getTime() {
		return time;
	}

	// @return the "A" Particle
	public Particle getA() {
		return a;
	}

	/// @return the "B" Particle
	public Particle getB() {
		return b;
	}
}
