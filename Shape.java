/*
- Program to create (abstract) Shape class
- Isa Alatovic
- 29/09/2024
*/

abstract class Shape {
	//Attributes
	private String colour;
	private boolean filled;
	
	//Constructors
	public Shape() { //No argument constructor
		this.colour = "black";
		this.filled = false;
	}
	
	public Shape(String colour, boolean filled) { //Regular constructor
		this.colour = colour;
		this.filled = filled;
	}
	
	//Getters & setters
	public String getColor() {
		return this.colour;
	}
	
	public boolean isFilled() {
		return this.filled;
	}
	
	public void setColor(String color) {
		this.colour = color;
	}
	
	public void setFilled(boolean filled) {
		this.filled = filled;
	}
	
	//Methods
	public String toString() { //Returns string representation of shape
		return String.format("Colour: %s \nFilled: %b", this.colour, this.filled);
	}
	
	public boolean equals(Shape shape) {
		return getColor().equals(shape.getColor()) && isFilled() == shape.isFilled();
	}
	
	//Abstract classes to be used by subclasses
	abstract double getArea(); //Returns area of shape

	abstract double getPerimeter(); //Returns perimeter of shape
	
}