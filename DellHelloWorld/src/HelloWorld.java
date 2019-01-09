import java.util.Random;
import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("Hello World! I'm the Java Magic 8 Ball.");
	    System.out.println("Please ask me a yes/no question.");
	    
	    Scanner reader = new Scanner(System.in);
	     reader.nextLine();

	    Random randNumGenerator = new Random();
	    int randNum = randNumGenerator.nextInt(3); // Give me a random number between 0 (inclusive) and 3 (exclusive) -> 0, 1, or 2

	    if (randNum == 0) {
	      System.out.println("As I see it, yes");
	    }
	    if (randNum == 1) {
	      System.out.println("Don't count on it");
	    }
	    if (randNum == 2) {
	      System.out.println("Reply hazy, try again");
	    }
	    
	    reader.close();
	}

}
