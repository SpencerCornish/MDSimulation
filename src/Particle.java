
public class Particle {
	private double posX; // X Position of the center of the sphere
	private double posY; // Y Position of the center of the sphere
	private double velX; // X Velocity of the particle
	private double velY; // Y Velocity of the particle
	private double mass; // Mass of the particle
	private double radius; // Radius of the particle (in pixels?)
	
	public Particle(int posX, int posY, double velX, double velY, double mass, double radius)
	{
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.mass = mass;
		this.radius = radius;		
	}
	public double collidesX()
	{
		
		return 0;
	}
	public double collidesY()
	{
		return 0;
	}
	public double collides(Particle b)
	{
		
		return 0;
	}
	public void bounceX()
	{
		
	}
	public void bounceY()
	{
		
	}
	public void bounce(Particle b)
	{
		
	}
	public int getCollisionCount()
	{
		return 0;
	}
	/**
	 * @return the posX
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * @return the posY
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * @return the velX
	 */
	public double getVelX() {
		return velX;
	}
	/**
	 * @return the velY
	 */
	public double getVelY() {
		return velY;
	}
	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}
	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}
	/**
	 * @param posX the posX to set
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	/**
	 * @param posY the posY to set
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	/**
	 * @param velX the velX to set
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}
	/**
	 * @param velY the velY to set
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}
	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}
	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	

}

