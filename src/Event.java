
public class Event implements Comparable<Event> {
	private double time;
	private Particle a;
	private Particle b;
	private int countA, countB;

	public Event(double time, Particle a, Particle b) {
		this.time = time;
		this.a = a;
		this.b = b;
		if (a != null)
			countA = a.getCollisionCount();
		if (b != null)
			countB = b.getCollisionCount();
	}

	@Override
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
