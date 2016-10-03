// Postfix Calculator Applet
// CS 201 HW 8
// Name: Mateja Sela
// Date: 04/26/2016

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*; // for Stack

public class Calc extends Applet implements ActionListener {

    // instance variables

    Label result;         // label used to show result
    Stack<Integer> stack; // stack used for calculations
    int current;          // current number being entered
    boolean entered;      // whether current number has been entered
                          // if so, show number in red

    // local color constants
    static final Color black = Color.black;
    static final Color white = Color.white;
    static final Color red = Color.red;
    static final Color green = Color.green;
    static final Color blue = Color.blue;
    static final Color yellow = Color.yellow;
    static final Color dred = new Color(160, 0, 100);
    static final Color dgreen = new Color(0, 120, 90);
    static final Color dblue = Color.blue.darker();

    public void init() {
    	
    	// Set the general layout
    	setLayout(new BorderLayout());
    	
    	
    	
    	// set up the top result display
    	result = new Label("0");
    	result.setBackground(white);
    	result.setForeground(dgreen);
    	Panel top = new Panel();
    	top.setLayout(new BorderLayout());
    	top.add("North", new Label());
    	top.add("East", new Label());
    	top.add("Center", result);
    	top.add("South", new Label());
    	top.add("West", new Label());
    	top.setBackground(blue);
    	add("North",top);
    	result.setAlignment(Label.RIGHT);
        
    	// Add a new panel with GridLayout
    	// For all the number and operations buttons
        Panel p1 = new Panel();
        p1.setLayout(new GridLayout(4, 4));
        p1.add(CButton("7", dred, yellow));
        p1.add(CButton("8", dred, yellow));
        p1.add(CButton("9", dred, yellow));
        p1.add(CButton("+", dred, green));
        p1.add(CButton("4", dred, yellow));
        p1.add(CButton("5", dred, yellow));
        p1.add(CButton("6", dred, yellow)); // TITLE LABEL IN THE COURSE THINGY
        p1.add(CButton("-", dred, green));
        p1.add(CButton("1", dred, yellow));
        p1.add(CButton("2", dred, yellow));
        p1.add(CButton("3", dred, yellow));
        p1.add(CButton("*", dred, green));
        p1.add(CButton("(-)", dred, yellow));
        p1.add(CButton("0", dred, yellow));
        p1.add(CButton("Pop", dred, yellow));
        p1.add(CButton("/", dred, green));
        
        
        // Add a new pannel for Enter and
        // Clear buttons
        add( p1);
        p1.setBackground(blue);
        Panel p2 = new Panel();
        p2.setLayout(new GridLayout(1,1));
        p2.add(CButton("Enter", dred, red));
        p2.add(CButton("Clear", dred, red));
        add("South", p2);
        p2.setBackground(blue);
        Font font = new Font("TimesRoman",Font.BOLD, 20);
        setFont(font);
        
        // initialize a stack object
        stack = new Stack<Integer>();
        // set initial current to 0
        current = 0;
    }
    // create a colored button
    protected Button CButton(String s, Color fg, Color bg) {
        Button b = new Button(s);
        b.setBackground(bg);
        b.setForeground(fg);
        b.addActionListener(this);
        return b;
    }

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            String label = ((Button)e.getSource()).getLabel();
         // Addition button function
            if (label.equals("+")){
            	if (entered == false){
            		pusher();
            	}
            	if(stack.size() >= 2){
            		add();
            	}
            }
         // Subtraction button function
            else if (label.equals("-")){
            	if (entered == false){
            		pusher();
            	}
            	if(stack.size() >= 2){
            		sub();
            	}
            	else{
            		minusSign();
            	}
            }
            // Multiplication button function
            else if (label.equals("*")){
            	if (entered == false){
            		pusher();
            	
            	}
            	if(stack.size() == 1){
            		stack.push(0);
            		entered = true;
            		current = 0;
            		show(current);
            	}
            	if(stack.size() >= 2){
            		mult();
            	}
            }
            // Enter button function
            else if (label.equals("Enter")){
            	pusher();
            }
            // division button action
            else if (label.equals("/")){
            	if (entered == false){
            		pusher();
            	}
            	if(stack.size() == 1){
            		stack.push(0);
            		entered = true;
            		current = 0;
            		show(current);
            	}
            	if(stack.size() >= 2){
            		div();
            	}
            }
            //Minus sign button action
            else if (label.equals("(-)")){
            	minusSign();
            }
            else if (label.equals("Clear")){
            	clear();
            }
            //pop button action
            else if (label.equals("Pop")){
            	if (entered == false){
            		if (stack.size() == 0){
            			show(current);
            		}
            		else{
            		stack.push(stack.peek());
            		current = stack.peek();
            		show(current);
            		current = 0;
            		entered = true;
            		}
            	}
            	if (stack.size() == 0){
            		stack.push(0);
            		entered = true;
            		current = 0;
            		show(current);
            	}
            	if (stack.size()>=1){
                	pop();
            	}
            }
            
            else {     // number button
            	int n = Integer.parseInt(label);
            	number(n);
            	
            } 
        }
    }
    

    // display number n in result label
    protected void show(int n) {
    	if (entered == true){
    		result.setForeground(dred);
    	}
    	if(entered == false){
    		result.setForeground(dgreen);
    	}
        result.setText(Integer.toString(n));
        
    }
    // helper function for
    // addition of two numbers
    protected void add() {
        int adding = stack.pop() + stack.pop();
        stack.push(adding);
        show(adding);
        entered = true;
         
    }
    // helper function for
    // subtraction of two numbers
    protected void sub() {
    	int number1 = stack.pop();
    	int number2 = stack.pop();
    	int subtract = number2 - number1;
   		stack.push(subtract);
   		show(subtract);
   		entered = true;
   		 
    }
    // helper function for
    // multiplication of two numbers
    protected void mult() {
    	int multiplication = stack.pop()*stack.pop();
   		stack.push(multiplication);
   		show(multiplication);
   		entered = true;
   		 
    }
    // helper function
    // for dividing 2 numbers
    protected void div() {
    	int number2 = stack.pop();
    	int number1 = stack.pop();
    	if (number2 == 0){
    		current = 0;
    		show(current);
    	}
    	else{
    	int division= number1/number2;
   		stack.push(division);
   		show(division);
   		entered = true;
   		 
    	}
    }
    // helper function to clear
    protected void clear(){
    	while(stack.size() > 0){
    		stack.pop();
    	}
    	current = 0;
    	show(current);
    }
    // handle number buttons
    protected void number(int n) {
        entered = false;
   		current = current*10 + n;
   		show(current);
    	}
    
    // helper function to push the
    // current element onto stack
    protected void pusher(){
    	entered = true;
    	stack.push(current);
        show(current);
        current = 0;
    }
    // Helper function for the minus
    // sign changer
    protected void minusSign(){
    	if (entered == false){
        	current *=-1;
        	show(current);
        	}
    	
    	else if (stack.size()>0){
    		current = stack.pop()* (-1);
    		show(current);
        	}
        pusher();   	
    }
    
    // helper function
    // for the pop button
    protected void pop(){
    	if (stack.size() > 1){
			current = stack.pop();
			show(stack.peek()); // peek at the top
			current = 0;
			 
    	}
    	else if(stack.size() == 1){
    		stack.pop();
    		current = 0;
    		show(current);
    		 
    	}
    	else{
    		show(0);
    		current = 0;
    		 
    	}
    }
}
