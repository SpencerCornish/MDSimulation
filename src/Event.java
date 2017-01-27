
public class Event {
	private double time;
	private Particle a;
	private Particle b;
	public Event(double time, Particle a, Particle b)
	{
		this.time = time;
		this.a = a;
		this.b = b;
	}

	// returns time of the event
	public double getTime() {
		return 0;
	}

	// returns the first particle
	public Particle getParticel1() {
		return new Particle();
	}

	// returns the second particle
	public Particle getParticel2() {
		return Particle2;
	}

	// compares time associated with event x
	// returns a positive number(greater than), negative number(less than), or
	// zero(equal)
	public int compareTo(Object x) {
		return 0;
	}

	public boolean wasSuperveningEvent()
	{
		if(true)
		{
		//return true if event has been validated since creation
		return true;
		}
		else
		{
		//return false if the event has been invalidated 
		return false;
		}	
	}

}
