package formation.afpa.fr.apps;

import formation.afpa.fr.Coach;
import formation.afpa.fr.TrackCoach;

public class MyApp {

	public static void main(String[] args) {
	
		//create the object
		Coach theCoach = new TrackCoach();
		
		// use the object
		System.out.println(theCoach.getDailyWorkout());
		
		
	}

}
