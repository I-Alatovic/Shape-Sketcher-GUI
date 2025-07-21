/*
- Program to create Rectangle class. Rectangle inherits from Shape
- Isa Alatovic
- 29/09/2024
*/

import java.util.Locale; //Will be used to ensure that decimal points are '.' and not ','

class Rectangle extends Shape {
	//Attributes
	private double width;
	private double height;
	
	//Constructors
	Rectangle() { //No argument constructor, width and height set to 1.0
		super();
		this.width = 1.0;
		this.height = 1.0;
	}
	
	Rectangle(double width, double height) { //Default inherited attributes, specified width and height
		super();
		this.width = width;
		this.height = height;
	}
	
	Rectangle(double width, double height, String color, boolean filled) { //Regular constructor
		super(color, filled);
		this.width = width;
		this.height = height;
	}
	
	//Getters & setters
	public double getWidth() {
		return this.width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	//Methods
	public String toString() {
		return String.format(Locale.US, "%s\nWidth: %.2f\nHeight: %.2f\nArea: %.2f\nPerimeter: %.2f", 
								super.toString(), this.width, this.height, this.getArea(), this.getPerimeter());
	}
	
	public double getArea() {
		return this.width * this.height;
	}
	
	public double getPerimeter() {
		return 2 * (this.width + this.height);
	}
	
	public boolean equals(Rectangle rectangle) {
		return (super.equals(rectangle) && this.width == rectangle.getWidth()) && (this.height == rectangle.getHeight());
	}
}