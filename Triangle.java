/*
- Program to create Triangle class. Triangle inherits from Shape
- Isa Alatovic
- 29/09/2024
*/
import java.util.Locale; //Will be used to ensure that decimal points are '.' and not ','

class Triangle extends Shape {
	//Attributes
	private double side1;
	private double side2;
	private double side3;
	
	//Constructors
	public Triangle() { //No argument constructor, sides set to 1.0
		super();
		this.side1 = 1.0;
		this.side2 = 1.0;
		this.side3 = 1.0;
	}
	
	public Triangle(double side1, double side2, double side3) { //Default inherited attributes, specified side lengths
		super();
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	public Triangle(double side1, double side2, double side3, String color, boolean filled) { //Regular constructor
		super(color, filled);
		this.side1 = side1;
		this.side2 = side2;
		this.side3 = side3;
	}
	
	//Getters & setters
	
	public double getSide1() {
		return this.side1;
	}
	
	public void setSide1(double side1) {
		this.side1 = side1;
	}
	
	public double getSide2() {
		return this.side2;
	}
	
	public void setSide2(double side2) {
		this.side2 = side2;
	}
	
	public double getSide3() {
		return this.side3;
	}
	
	public void setSide3(double side3) {
		this.side3 = side3;
	}
	
	//Methods
	
	public double getPerimeter() {
		return this.side1 + this.side2 + this.side3;
	}
	
	public double getArea() {
		double s = this.getPerimeter() / 2;
		return Math.sqrt(s * (s - this.side1) * (s - this.side2) * (s - this.side3)); //Makes use of Heron's formula
	}
	
	public boolean equals(Triangle triangle) {
		return (super.equals(triangle) && this.side1 == triangle.getSide1()) && (this.side2 == triangle.getSide2()) &&
				(this.side3 == triangle.getSide3());
	}
	
	public String toString() {
		return String.format(Locale.US, "%s\nSide 1: %.2f\nSide 2: %.2f\nSide 3: %.2f\nArea: %.2f\nPerimeter: %.2f", 
								super.toString(), this.side1, this.side2, this.side3, this.getArea(),
								 this.getPerimeter());
	}
}