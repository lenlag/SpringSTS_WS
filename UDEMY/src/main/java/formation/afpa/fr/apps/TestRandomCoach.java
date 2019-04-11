package formation.afpa.fr.apps;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import formation.afpa.fr.Coach;

public class TestRandomCoach {

	public static void main(String[] args) {
	

		// load the spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextRandom.xml");

		// retrieve the bean from spring container
		Coach coach = context.getBean("RandomCoach", Coach.class); // Coach switches to RandomCoach via Spring
																	// Factory(in beans xml)

		Coach myCoach = context.getBean("RandomCoach", Coach.class);

		// check if they are the same
		boolean result = (coach == myCoach);

		/*
		 * //call methods on the bean System.out.println(coach.getDailyWorkout());
		 * System.out.println(coach.getDailyFortune());
		 */

		// print out the results
		System.out.println("\nPointing to the same object : " + result);

		System.out.println("\nMemory location for theCoach:" + coach); // as toString() will display the class name
																			// by default

		System.out.println("\nMemory location for alphaCoach:" + myCoach + "\n");

		// true (same memory locations for both objects) => if bean scope not defined=>
		// SINGLETON by default
		// false => if bean scope="prototype"

		// close the context
		context.close();

	}

}
