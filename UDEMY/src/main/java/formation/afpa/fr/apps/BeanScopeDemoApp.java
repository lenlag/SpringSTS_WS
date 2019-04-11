package formation.afpa.fr.apps;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import formation.afpa.fr.Coach;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		
		// load the Spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		
		// retrieve bean from Spring container
		Coach theCoach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		//check if they are the same
		boolean result = (theCoach == alphaCoach);
		
		//print out the results
		System.out.println("\nPointing to the same object : " + result);
		
		System.out.println("\nMemory location for theCoach:" + theCoach); // as toString() will display the class name by default
		
		System.out.println("\nMemory location for alphaCoach:" + alphaCoach + "\n");
		
		//true (same memory locations for both objects) => if bean scope not defined=> SINGLETON by default 
		//false => if bean scope="prototype"
		
		//close the context
		
		context.close();
		
		
	}

}
