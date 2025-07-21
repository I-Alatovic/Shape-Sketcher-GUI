/*
- Program to create Circle class. Circle inherits from Shape
- Isa Alatovic
- 29/09/2024
*/
import java.util.Locale; //Will be used to ensure that decimal points are '.' and not ','

class Circle extends Shape {
	//Attributes
	private double radius;
	
	//Constructors
	Circle() { //No argument constructor, radius set to 1.0
		super();
		this.radius = 1.0;
	}
	
	Circle(double radius) { //Default inherited attributes, specified radius
		super();
		this.radius = radius;
	}
	
	Circle(double radius, String color, boolean filled) { //Regular constructor
		super(color, filled);
		this.radius = radius;
	}
	
	//Getters & setters
	public double getRadius() {
		return this.radius;
	}
	
	public void setRadius(double newRadius) {
		this.radius = newRadius;
	}
	
	//Methods
	public String toString() {
		return String.format(Locale.US, "%s\nRadius: %s\nArea: %.2f\nPerimeter: %.2f", super.toString(), this.radius, this.getArea(), this.getPerimeter());
	}
	
	public double getArea() {
		return Math.PI * Math.pow(this.radius, 2);
	}
	
	public double getPerimeter() {
		return 2 * Math.PI * this.radius;
	}
	
	public boolean equals(Circle circle) { //Returns true if the Circle objects have the same radius, false otherwise
		return super.equals(circle) && this.radius == circle.getRadius();
	}
}