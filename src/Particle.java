/* 
 *  Lab 1: Particle Collision
 *  2-5-2017
 *  Authors: Keely Weisbeck
 *  		 Spencer Cornish
 * 	
 *  This file contains the particle class
 *  which holds data about particles
 */
import java.awt.Color;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Particle {
	private int count; // Count of collisions this particle has made
	private double posX; // X Position of the center of the sphere
	private double posY; // Y Position of the center of the sphere
	private double velX; // X Velocity of the particle
	private double velY; // Y Velocity of the particle
	private double mass; // Mass of the particle
	private double radius; // Radius of the particle
	private Color color; // Color of the particle

	public Particle(double posX, double posY, double velX, double velY, double radius, double mass, int r, int g, int b) {
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		this.mass = mass;
		this.radius = radius;
		this.color = new Color(r, g, b);
	}

	public Particle() {
		posX = StdRandom.uniform(0.0, 1.0);
		posY = StdRandom.uniform(0.0, 1.0);
		velX = StdRandom.uniform(-0.005, 0.02);
		velY = StdRandom.uniform(-0.005, 0.02);
		radius = StdRandom.uniform(0.01, 0.09);
		mass = 0.5;
		int r = StdRandom.uniform(0, 255);
		int g = StdRandom.uniform(0, 255);
		int b = StdRandom.uniform(0, 255);
		color = new Color(r, g, b);
	}

	public double collidesV() {
		if (velX > 0)
			return (1 - radius - posX) / velX; // Positive Y Velocity
		else if (velX < 0)
			return (radius - posX) / velX;
		else
			return -1;
	}

	public double collidesH() {
		if (velY > 0)
			return (1 - radius - posY) / velY;
		else if (velY < 0)
			return (radius - posY) / velY;
		else
			return -1;
	}

	public double collides(Particle b) {
		double dX = (this.posX) - (b.posX); // Change in X Position
		double dY = this.posY - b.posY; // Change in Y Position
		
		double dvX = this.velX - b.velX; // Change in X Velocity
		double dvY = this.velY - b.velY; // Change in Y Velocity
		
		double dVdP = (dX * dvX) + (dY * dvY);
		double dVdV = (dvX*dvX)+(dvY*dvY);
		double dPdP = (dX*dX)+(dY*dY);
		double sigma = this.radius + b.radius;
		double d = (dVdP*dVdP) - dVdV * (dPdP - sigma*sigma);
		//System.out.println(d);
		if(dVdP >= 0) return -1;
		if(d < 0) return -1;
		return -(dVdP + Math.sqrt(d)) / dVdV;
	}

	public void bounceV() // Vertical Wall Hit
	{
		// System.out.println("BounceVerticalWall");
		velX = -velX;
		count += 1; // Increase collision count by 1
	}

	public void bounceH() // Horizontal Wall Hit
	{
		// System.out.println("BounceHorizontalWall");
		velY = -velY;
		count += 1; // Increase collision count by 1
	}

	public void bounce(Particle b) {
		double dX = this.posX - b.posX; // Change in X Position
		double dY = this.posY - b.posY; // Change in Y Position
		
		double dvX = this.velX - b.velX; // Change in X Velocity
		double dvY = this.velY - b.velY; // Change in Y Velocity
		
		double sigma = this.radius + b.radius;

		double dVdP = (dX * dvX) + (dY * dvY);
		
		double j = (2 * mass * b.mass * dVdP)/(sigma*(mass+b.mass));
		double jX = (j*dX)/sigma;
		double jY = (j*dY)/sigma;
		this.velX -= jX / this.mass;
		this.velY -= jY / this.mass;
		b.velX += jX / b.mass;
		b.velY += jY / b.mass;
		
		
		count++; // Increase collision count by 1
		b.count++;
	}

	public int getCollisionCount() {
		return count;
	}

	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(posX, posY, radius);
	}

	public void move(double moveTime) {
		posX += velX * moveTime;
		posY += velY * moveTime;
	}



}
