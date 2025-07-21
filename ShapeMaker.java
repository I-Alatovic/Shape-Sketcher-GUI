/*
- Program to create   ShapeMaker class. ShapeMaker inherits from JFrame and provides a GUI that allows users to draw shapes
- Isa Alatovic
- 30/09/2024
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 

class ShapeMaker extends JFrame   {
   //Constants
   private   static final int WIDTH = 750;
   private   static final int HEIGHT   = 425;
   private   final String shapes[] =   {"Triangle", "Rectangle", "Circle"};
   private   final String colours[] = {"Black", "Red", "Blue", "Green"};
   private   final String filled[] =   {"Filled", "Unfilled"};
   
   //Font declarations:
   Font mainHeading = new Font("Serif", Font.BOLD, 28);
   Font subHeading = new Font("SansSerif", Font.BOLD, 24);
   Font text = new Font("Arial", Font.PLAIN, 12);
   Font text2 = new Font("SansSerif", Font.PLAIN, 18);
   Font text3 = new Font("Arial", Font.BOLD, 12);
   
   //Main panels to be   displayed:
   private   shapeMakerPanel shapeSelection;
   private   shapeMakerPanel triangleEditor;
   private   shapeMakerPanel rectangleEditor;
   private   shapeMakerPanel circleEditor;
   
   //Draw frame:
   private JFrame drawFrame;
   private JTextArea infoText;
   private JPanel infoPanel;
   private JSplitPane mainPanel;
   
   //Combobox declarations:
   JComboBox shapesComboBox = new JComboBox(shapes);
   JComboBox coloursComboBox =   new   JComboBox(colours);
   JComboBox filledComboBox = new JComboBox(filled);
   
   //Text field declarations:
   JTextField s1TextField = new JTextField(5);
   JTextField s2TextField = new JTextField(5);
   JTextField s3TextField = new JTextField(5);
   JTextField s1StartXField = new JTextField(5);
   JTextField s1StartYField = new JTextField(5);
   JTextField widthTextField = new JTextField(5);
   JTextField heightTextField = new JTextField(5);
   JTextField rectangleXField = new JTextField(5);
   JTextField rectangleYField = new JTextField(5);
   JTextField radiusTextField = new JTextField(5);
   JTextField circleXField = new JTextField(5);
   JTextField circleYField = new JTextField(5);
   
   //Main method
   public static void main(String[] args) {
      ShapeMaker gui = new ShapeMaker();
      gui.setVisible(true);
   }
   
   //Inner class to create panel template
   private   class shapeMakerPanel extends JPanel { 
      public JPanel buttonPanel =   new   JPanel();
      public JPanel titlePanel = new JPanel();
      public JPanel selectionsPanel =   new   JPanel();
      
      public shapeMakerPanel() {
         super();
         setLayout(new BorderLayout(0, 30));
         selectionsPanel.setLayout(new BoxLayout(selectionsPanel, BoxLayout.Y_AXIS));
         titlePanel.setLayout(new GridLayout(2, 1, 0, 15));
         add(titlePanel,   BorderLayout.NORTH);
         add(buttonPanel, BorderLayout.SOUTH);
         add(selectionsPanel, BorderLayout.CENTER);
      }
   }
   
   //Method to generate copies of the button panel to be used by the editor panels
   private JPanel editorButtonPanel() {
      JPanel editorButtonPanel = new JPanel();
      JButton backButton = new JButton("Back");
      JButton goButton = new JButton("Go!");
      editorButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
      editorButtonPanel.add(backButton);
      editorButtonPanel.add(goButton);
      goButton.addActionListener(new goListener());
      backButton.addActionListener(new backListener());
      return editorButtonPanel;
   }   
   
   //Constructor for ShapeMaker - the "heart" of this program
   public ShapeMaker()   {
      //Sub-panels declarations (to be placed   in main   panels):
         //Used in shapeSelection:
         JPanel shapeSelectorPanel =   new JPanel();
         JPanel colourSelectorPanel = new JPanel();
         JPanel filledSelectorPanel = new JPanel();
         
         //Used in triangleEditor:
         JPanel s1SelectorPanel = new JPanel();
         JPanel s2SelectorPanel = new JPanel();
         JPanel s3SelectorPanel = new JPanel();
         JPanel s1XSelectorPanel = new JPanel();
         JPanel s1YSelectorPanel = new JPanel();
         
         //Used in rectangleEditor:
         JPanel widthSelectorPanel = new JPanel();
         JPanel heightSelectorPanel = new JPanel();
         JPanel rectangleXSelectorPanel = new JPanel();
         JPanel rectangleYSelectorPanel = new JPanel();
         
         //Used in circleEditor:
         JPanel radiusSelectorPanel = new JPanel();
         JPanel circleXSelectorPanel = new JPanel();
         JPanel circleYSelectorPanel = new JPanel();
      
      //Other component declarations:      
         //Used in shapeSelection:
         JLabel selectShape   = new JLabel("Select a shape: ");
         JLabel selectColour = new JLabel("Select a colour: ");
         JLabel selectFilledLabel = new JLabel("Filled or unfilled: ");
         JLabel shapeSelectionTitle = new JLabel("Welcome to Shape Maker", JLabel.CENTER);
         JLabel triangleEditorTitle = new JLabel("Triangle editor", JLabel.LEFT);
         JLabel rectangleEditorTitle   = new JLabel("Rectangle editor", JLabel.LEFT);
         JLabel circleEditorTitle = new JLabel("Circle editor",   JLabel.LEFT);
         JButton   next = new JButton("Next");
         
         //Used in triangleEditor:
         JLabel enterSide1 = new JLabel("Enter side 1: ");
         JLabel enterSide2 = new JLabel("Enter side 2: ");
         JLabel enterSide3 = new JLabel("Enter side 3: ");
         JLabel enterSide1StartX = new JLabel("Enter x-coordinate of start of side 1: ");
         JLabel enterSide1StartY = new JLabel("Enter y-coordinate of start of side 1: ");
         JLabel triangleNote = new JLabel("Note: side 1 will always be horizontal; sides 2 and 3 will always point upwards; default starting coordinates are (500, 500)", JLabel.LEFT);
         
         //Used in rectangleEditor:
         JLabel enterWidth = new JLabel("Enter width: ");
         JLabel enterHeight = new JLabel("Enter height: ");
         JLabel enterRectangleX = new JLabel("Enter x-coordinate of top left vertex of bounding box: ");
         JLabel enterRectangleY = new JLabel("Enter y-coordinate of top left vertex of bounding box: ");
         JLabel rectangleNote = new JLabel("Note: default starting coordinates are (0, 0)", JLabel.LEFT);
         
         //Used in circleEditor:
         JLabel enterRadius = new JLabel("Enter radius: ");
         JLabel enterCircleX = new JLabel("Enter x-coordinate of top left vertex of bounding box: ");
         JLabel enterCircleY = new JLabel("Enter y-coordinate of top left vertex of bounding box: ");
         JLabel circleNote = new JLabel("Note: default starting coordinates are (0, 0)", JLabel.LEFT);
         
         
      //Setting fonts:
      shapeSelectionTitle.setFont(mainHeading);
      triangleEditorTitle.setFont(subHeading);
      rectangleEditorTitle.setFont(subHeading);
      circleEditorTitle.setFont(subHeading);
      selectShape.setFont(text);
      selectColour.setFont(text);
      selectFilledLabel.setFont(text);
      enterSide1.setFont(text);
      enterSide2.setFont(text);
      enterSide3.setFont(text);
      enterWidth.setFont(text);
      enterHeight.setFont(text);
      enterRadius.setFont(text);
      enterSide1StartX.setFont(text);
      enterSide1StartY.setFont(text);
      enterRectangleX.setFont(text);
      enterRectangleY.setFont(text);
      enterCircleX.setFont(text);
      enterCircleY.setFont(text);
      triangleNote.setFont(text3);
      rectangleNote.setFont(text3);
      circleNote.setFont(text3);
      
      
      //Confiugure drawing frame (will not be visible initially):
         //Set up frame
         drawFrame = new JFrame("Draw window");
         
         drawFrame.setSize(1650, 960);
         drawFrame.setLayout(new BorderLayout());
         drawFrame.getContentPane().setBackground(Color.WHITE);
         drawFrame.setResizable(false);
         
         //Set up info panel
         infoPanel = new JPanel();
         infoPanel.setPreferredSize(new Dimension(210, 960));
         infoPanel.setBackground(Color.WHITE);
         infoText = new JTextArea();
         infoText.setEditable(false);
         infoText.setFont(text2);
         infoPanel.add(infoText);
                  
         //Add panels to draw frame
         drawFrame.add(infoPanel, BorderLayout.EAST);
         
         //Set as not visible
         drawFrame.setVisible(false);
      
      //Configure ShapeMaker frame
      this.setTitle("Shape Maker");
      this.setSize(WIDTH,   HEIGHT);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closing ShapeMaker window will end program
      this.setLayout(new BorderLayout());
      this.setResizable(false); //This is   to keep   the   window at the optimum size
      
      //Set up shapeSelection   panel. This   is the first panel displayed to   the   user
      shapeSelection = new shapeMakerPanel();
      shapeSelection.titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
      shapeSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); //Selector panels are left-aligned
      colourSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      filledSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      shapeSelectorPanel.add(selectShape);
      shapeSelectorPanel.add(shapesComboBox);
      colourSelectorPanel.add(selectColour);
      colourSelectorPanel.add(coloursComboBox);
      filledSelectorPanel.add(selectFilledLabel);
      filledSelectorPanel.add(filledComboBox);
      shapeSelection.selectionsPanel.add(shapeSelectorPanel);
      shapeSelection.selectionsPanel.add(colourSelectorPanel);
      shapeSelection.selectionsPanel.add(filledSelectorPanel);
      shapeSelection.titlePanel.add(shapeSelectionTitle); //Title is   at the top of the window and centred
      shapeSelection.buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));   //"Next" button   will appear   at the bottom right   corner
      next.addActionListener(new nextListener());
      shapeSelection.buttonPanel.add(next);
      
      //Set up triangleEditor   panel
      triangleEditor = new shapeMakerPanel();
      //triangleEditor.titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      triangleEditor.titlePanel.add(triangleEditorTitle);
      triangleEditor.titlePanel.add(triangleNote);
      s1SelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); //Left align selector panels
      s2SelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      s3SelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      s1XSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      s1YSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      s1SelectorPanel.add(enterSide1);
      s1SelectorPanel.add(s1TextField);
      s2SelectorPanel.add(enterSide2);
      s2SelectorPanel.add(s2TextField);
      s3SelectorPanel.add(enterSide3);
      s3SelectorPanel.add(s3TextField);
      s1XSelectorPanel.add(enterSide1StartX);
      s1XSelectorPanel.add(s1StartXField);
      s1YSelectorPanel.add(enterSide1StartY);
      s1YSelectorPanel.add(s1StartYField);
      triangleEditor.selectionsPanel.add(s1SelectorPanel);
      triangleEditor.selectionsPanel.add(s2SelectorPanel);
      triangleEditor.selectionsPanel.add(s3SelectorPanel);
      triangleEditor.selectionsPanel.add(s1XSelectorPanel);
      triangleEditor.selectionsPanel.add(s1YSelectorPanel);
      triangleEditor.buttonPanel.add(editorButtonPanel());
      
      //Set up rectangleEditor
      rectangleEditor   = new shapeMakerPanel();
      //rectangleEditor.titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      rectangleEditor.titlePanel.add(rectangleEditorTitle);
      rectangleEditor.titlePanel.add(rectangleNote);
      widthSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); //Left align selector panels
      heightSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      rectangleXSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      rectangleYSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      widthSelectorPanel.add(enterWidth);
      widthSelectorPanel.add(widthTextField);
      heightSelectorPanel.add(enterHeight);
      heightSelectorPanel.add(heightTextField);
      rectangleXSelectorPanel.add(enterRectangleX);
      rectangleXSelectorPanel.add(rectangleXField);
      rectangleYSelectorPanel.add(enterRectangleY);
      rectangleYSelectorPanel.add(rectangleYField);
      rectangleEditor.selectionsPanel.add(widthSelectorPanel);
      rectangleEditor.selectionsPanel.add(heightSelectorPanel);
      rectangleEditor.selectionsPanel.add(rectangleXSelectorPanel);
      rectangleEditor.selectionsPanel.add(rectangleYSelectorPanel);
      rectangleEditor.buttonPanel.add(editorButtonPanel());
      
      //Set up circleEditor
      circleEditor = new shapeMakerPanel();
      //circleEditor.titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      circleEditor.titlePanel.add(circleEditorTitle);
      circleEditor.titlePanel.add(circleNote);
      radiusSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      circleXSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      circleYSelectorPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
      radiusSelectorPanel.add(enterRadius);
      radiusSelectorPanel.add(radiusTextField);
      circleXSelectorPanel.add(enterCircleX);
      circleXSelectorPanel.add(circleXField);
      circleYSelectorPanel.add(enterCircleY);
      circleYSelectorPanel.add(circleYField);
      circleEditor.selectionsPanel.add(radiusSelectorPanel);
      circleEditor.selectionsPanel.add(circleXSelectorPanel);
      circleEditor.selectionsPanel.add(circleYSelectorPanel);
      circleEditor.buttonPanel.add(editorButtonPanel());
      
      //shapeSelection must be displayed first
      this.add(shapeSelection);

      
   }
   
   
   
   private void drawShape() { //Method called whenever a shape is to be drawn - second most important function
      drawFrame.getContentPane().removeAll();
      
      boolean filled = ((String) filledComboBox.getSelectedItem()).equals("Filled");
      String colourString = (String) coloursComboBox.getSelectedItem();   
      
      switch ((String) shapesComboBox.getSelectedItem()) {
            case "Triangle":
               if (triangleInputValid(s1TextField.getText(), s2TextField.getText(), s3TextField.getText(), s1StartXField.getText(), s1StartYField.getText())) {
                  
                  double s1 = Double.parseDouble(s1TextField.getText());
                  double s2 = Double.parseDouble(s2TextField.getText());
                  double s3 = Double.parseDouble(s3TextField.getText());
                  double triangleX = setStartX(s1StartXField.getText(), 500);
                  double triangleY = setStartY(s1StartYField.getText(), 500);
                  
                  Triangle triangle = new Triangle(s1, s2, s3, colourString, filled);
                  ShapeDrawing triangleDrawing = new ShapeDrawing(triangle.getSide1(), triangle.getSide2(), triangle.getSide3(), triangleX, triangleY, colourConverter(triangle.getColor()), triangle.isFilled());
                  infoText.setText(triangle.toString());
                  mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, triangleDrawing, infoPanel);
                  mainPanel.setDividerLocation(1440);
                  mainPanel.setResizeWeight(1.0);
                  mainPanel.setEnabled(false);
                  drawFrame.add(mainPanel, BorderLayout.CENTER);
                  drawFrame.repaint();
                  drawFrame.revalidate();
                  drawFrame.setVisible(true); //Draw frame must now be visible
               }
               break;
            case "Rectangle":
                if (rectangleInputValid(widthTextField.getText(), heightTextField.getText(), rectangleXField.getText(), rectangleYField.getText())) {
                  double width = Double.parseDouble(widthTextField.getText());
                  double height = Double.parseDouble(heightTextField.getText());
                  double rectangleX = setStartX(rectangleXField.getText(), 0);
                  double rectangleY = setStartY(rectangleYField.getText(), 0);

                  Rectangle rectangle = new Rectangle(width, height, colourString, filled);
                  ShapeDrawing rectangleDrawing = new ShapeDrawing(rectangle.getWidth(), rectangle.getHeight(), rectangleX, rectangleY, colourConverter(rectangle.getColor()), rectangle.isFilled());
                  infoText.setText(rectangle.toString());
                  mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, rectangleDrawing, infoPanel);
                  mainPanel.setDividerLocation(1440);
                  mainPanel.setResizeWeight(1.0);
                  mainPanel.setEnabled(false);
                  drawFrame.add(mainPanel, BorderLayout.CENTER);
                  drawFrame.repaint();
                  drawFrame.revalidate();
                  drawFrame.setVisible(true); //Draw frame must now be visible
               }
                break;
             case "Circle":
               if (circleInputValid(radiusTextField.getText(), circleXField.getText(), circleYField.getText())) {
                  double radius = Double.parseDouble(radiusTextField.getText());
                  double circleX = setStartX(circleXField.getText(), 0);
                  double circleY = setStartY(circleYField.getText(), 0);
                  
                  Circle circle = new Circle(radius, colourString, filled);
                  ShapeDrawing circleDrawing = new ShapeDrawing(circle.getRadius(), circleX, circleY, colourConverter(circle.getColor()), circle.isFilled());
                  infoText.setText(circle.toString());
                  mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, circleDrawing, infoPanel);
                  mainPanel.setDividerLocation(1440);
                  mainPanel.setResizeWeight(1.0);
                  mainPanel.setEnabled(false);
                  drawFrame.add(mainPanel, BorderLayout.CENTER);
                  drawFrame.repaint();
                  drawFrame.revalidate();
                  drawFrame.setVisible(true); //Draw frame must now be visible
               }
                break;
      
      }
   }
   

   public class ShapeDrawing extends JScrollPane { //Returns scrollable pane containing panel with the shape drawing
      //The different shapes each take a different number of double inputs, so they can each get their own constructor
      
      public ShapeDrawing(double width, double height, double x, double y, Color color, boolean filled) { //Constructor for rectangle
         RPanel panel = new RPanel((int) width, (int) height, (int) x, (int) y, color, filled);
         this.setViewportView(panel);
         this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

      }
      
      public ShapeDrawing(double radius, double x, double y, Color color, boolean filled) { //Constructor for circle
         CPanel panel = new CPanel((int) radius, (int) x, (int) y, color, filled);
         this.setViewportView(panel);
         this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

      }
      
      public ShapeDrawing(double s1, double s2, double s3, double x, double y, Color color, boolean filled) { //Constructor for triangle
         TPanel panel = new TPanel((int) s1, (int) s2, (int) s3, (int) x, (int) y, color, filled);
         this.setViewportView(panel);
         this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
         this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      }

      
      private class RPanel extends JPanel { //Inner class defining the panel where the rectangle will be drawn
         private int width;
         private int height;
         private int x;
         private int y;
         private Color color;
         private boolean filled;
         
         public RPanel(int width, int height, int x, int y, Color color, boolean filled) {
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
            this.color = color;
            this.filled = filled;
            this.setBackground(Color.WHITE);
            this.setPreferredSize(new Dimension(this.width + this.x + 50, this.height + this.y + 50)); //Panel should be able hold the shape + the starting position + some padding space
         }
         
         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            if (filled) {
               g.fillRect(x, y, width, height);
            } else {
               g.drawRect(x, y, width, height);
            }
         }
      }
      
      private class CPanel extends JPanel { //Inner class defining the panel where the rectangle will be drawn
         private int radius;
         private int x;
         private int y;
         private Color color;
         private boolean filled;
         
         public CPanel(int radius, int x, int y, Color color, boolean filled) {
            this.radius = radius;
            this.x = x;
            this.y = y;
            this.color = color;
            this.filled = filled;
            this.setBackground(Color.WHITE);
            this.setPreferredSize(new Dimension(this.radius * 2 + this.x + 50, this.radius * 2 + this.y + 50)); //Panel should be able hold the shape + the starting position + some padding space
         }
         
         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            if (filled) {
               g.fillOval(x, y, 2 * radius, 2 * radius);
            } else {
               g.drawOval(x, y, 2 * radius, 2 * radius);
            }
         }
      }
      
      private class TPanel extends JPanel { //Inner class defining the panel where the rectangle will be drawn
         private int s1;
         private int s2;
         private int s3;
         private int x;
         private int y;
         private int intersectionX;
         private int intersectionY;
         private Color color;
         private boolean filled;
         
         public TPanel(int s1, int s2, int s3, int x, int y, Color color, boolean filled) {
            this.s1 = s1;
            this.s2 = s2;
            this.s3 = s3;
            int max = Math.max(this.s1, Math.max(this.s2, this.s3));
            this.x = x;
            this.y = y;
            this.color = color;
            this.filled = filled;
            
            /*The following intersection points were calculated by setting s1 to be flat, then formulating two equations
              for the circles whose centres are at either ends of s1, and whose radii are s2 and s3 respectively. By
              solving for x and y in terms of s1, s2 and s3, I was able to find the coordinates of where s2 and s3,
              meaning I can use the draw/fillPolygon method to sketch the triangle.
            */
            
            this.intersectionX = (int) Math.round((Math.pow(this.s1, 2) + Math.pow(this.s2, 2) - Math.pow(this.s3, 2)) / (2 * this.s1)); //This gives us the x-coordinate of where line 2 and 3 meet, where line 1 is fixed and horizontal
            this.intersectionY =  this.y - (int) Math.round(Math.sqrt(Math.pow(this.s2, 2) - Math.pow(this.intersectionX, 2))) ; //Similarly, this gives us the y-coordinate of this intersection
            this.setBackground(Color.WHITE);
            this.setPreferredSize(new Dimension(max + this.x + 50, this.y + 50));
         }
         
         public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            int[] xVertices = {x, intersectionX + this.x, x + s1};
            int[] yVertices = {y, intersectionY, y};
            if (filled) {
               g.fillPolygon(xVertices, yVertices, 3);
            } else {
               g.drawPolygon(xVertices, yVertices, 3);
            }
         }
      }
   }
   
   
   //Funtion to determine whether an integer (passed as a String) is greater than some integer m
   private boolean isIntegerGreaterThan(String n, int m) { 
      try {
         int integer = Integer.parseInt(n);
            return (integer > m);
      }
      catch (NumberFormatException error) {
         return false;
      }
   }
   
   //Function to set the starting x coordinate of a shape depending on whether the field is blank or not
   private double setStartX(String coordinate, int defaultVal) {   
      if (coordinate.equals("")) {
         return defaultVal;
      }
         else {
            return Double.parseDouble(coordinate);
         }
   }
   
   //Similar to previous function, but for y coordinate
   private double setStartY(String coordinate, int defaultVal) {   
      if (coordinate.equals("")) {
         return defaultVal;
      }
         else {
            return Double.parseDouble(coordinate);
         }
   }
   
   //Input validation functions - returns true if input is acceptable, else false + error message
   
   private boolean triangleInputValid(String s1, String s2, String s3, String x, String y) {
      
      if (s1.equals("") || s2.equals("")|| s3.equals("")) { //If any of the side length fields are empty
         JOptionPane.showMessageDialog(triangleEditor, "Please enter values for all the side lengths", "Empty input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      if (!(isIntegerGreaterThan(s1, 0)) || !(isIntegerGreaterThan(s2, 0)) || !(isIntegerGreaterThan(s3, 0))) { //If any of the side length fields have inputs that are not positive integers > 0
         JOptionPane.showMessageDialog(triangleEditor, "Side lengths must be integers greater than 0", "Invalid input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      double dS1 = Double.parseDouble(s1); //Temporary variables so that > operator can be used
      double dS2 = Double.parseDouble(s2);
      double dS3 = Double.parseDouble(s3);
      
      if (!(((dS1 + dS2) > dS3) && ((dS1 + dS3) > dS2) && ((dS2 + dS3) > dS1))) { //Checks whether sides can form a triangle using triangle inequality
         JOptionPane.showMessageDialog(triangleEditor, "Sides do not form a triangle (does not fulfil triangle inequality: a + b > c, a + c > b, b + c > a)", "Invalid input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      if ((!(isIntegerGreaterThan(x, -1)) && !(x.equals(""))) || ((!(isIntegerGreaterThan(y, -1)) && !(y.equals(""))))) { //Validates input for starting coordinate fields
         JOptionPane.showMessageDialog(triangleEditor, "Starting coordinates must be integers greater than or equal to 0 (or left empty for default starting point (500, 500)) ", "Invalid input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      return true;
   }
   
   private boolean rectangleInputValid(String width, String height, String x, String y) {
      if (width.equals("") || height.equals("")) {
         JOptionPane.showMessageDialog(rectangleEditor, "Please enter values for the width and/or height", "Empty input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      if (!(isIntegerGreaterThan(width, 0)) || !(isIntegerGreaterThan(height, 0))) {
         JOptionPane.showMessageDialog(rectangleEditor, "Width and height must be integers greater than 0", "Invalid input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      if ((!(isIntegerGreaterThan(x, -1)) && !(x.equals(""))) || ((!(isIntegerGreaterThan(y, -1)) && !(y.equals(""))))) {
         JOptionPane.showMessageDialog(rectangleEditor, "Starting coordinates must be integers greater than or equal to 0 (or left empty for default starting point (0, 0)) ", "Invalid input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      return true;
   }
   
   private boolean circleInputValid(String radius, String x, String y) {
      if (radius.equals("")) {
         JOptionPane.showMessageDialog(circleEditor, "Please enter values for the radius", "Empty input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      if (!(isIntegerGreaterThan(radius, 0))) {
         JOptionPane.showMessageDialog(circleEditor, "Radius must be an integer greater than 0", "Invalid input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      if ((!(isIntegerGreaterThan(x, -1)) && !(x.equals(""))) || ((!(isIntegerGreaterThan(y, -1)) && !(y.equals(""))))) {
         JOptionPane.showMessageDialog(circleEditor, "Starting coordinates must be integers greater than or equal to 0 (or left empty for default starting point (0, 0)) ", "Invalid input", JOptionPane.ERROR_MESSAGE);
         return false;
      }
      
      return true;
   }
   
   //ActionListeners - for Back, Next and Go!
   
   private class backListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         ShapeMaker.this.getContentPane().removeAll(); // Remove   the   current   panel
         ShapeMaker.this.add(shapeSelection);
         ShapeMaker.this.revalidate(); // Refresh the frame to show the new panel
         ShapeMaker.this.repaint();
      }
   }
   
   private   class nextListener implements ActionListener {
      public void   actionPerformed(ActionEvent   e) {
         ShapeMaker.this.getContentPane().removeAll(); // Remove   the   current   panel
         switch ((String) shapesComboBox.getSelectedItem()) {
            case "Triangle":
               ShapeMaker.this.add(triangleEditor);
               break;
            case "Rectangle":
               ShapeMaker.this.add(rectangleEditor);
               break;
            case "Circle":
               ShapeMaker.this.add(circleEditor);
               break;
         }
         
         ShapeMaker.this.revalidate();// Refresh the frame to show the new panel
         ShapeMaker.this.repaint();
      }
   }
   
   private class goListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         drawShape();
         
      }
   }
   
   //Utility functions - includes colourConverter
   
   private Color colourConverter(String colourString) {
      switch (colourString) {
         case "Black":
            return Color.BLACK;
         case "Red":
            return Color.RED;
         case "Blue":
            return Color.BLUE;
         case "Green":
            return Color.GREEN;
      }
      return Color.BLACK;
   }
}