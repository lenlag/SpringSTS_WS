package formation.afpa.fr.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import formation.afpa.fr.entity.Coach;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		//read spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//get the bean from the spring container
		Coach theCoach = context.getBean("tennisCoach", Coach.class); //default bean = class name with 1st lower case
		
		//call a method on a bean
		System.out.println(theCoach.getDailyWorkout());
		
		//close the context
		context.close();
		

	}

}
