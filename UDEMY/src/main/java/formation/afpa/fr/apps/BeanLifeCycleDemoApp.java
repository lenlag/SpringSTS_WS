package formation.afpa.fr.apps;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import formation.afpa.fr.Coach;

public class BeanLifeCycleDemoApp {

	public static void main(String[] args) {
		
		// load the Spring config file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
		
		
		// retrieve bean from Spring container
		Coach theCoach = context.getBean("myCoach", Coach.class); //init method called
		
		
		System.out.println(theCoach.getDailyWorkout());
		
		
		//close the context
		context.close(); //destroy method called
		
		
	}

}
