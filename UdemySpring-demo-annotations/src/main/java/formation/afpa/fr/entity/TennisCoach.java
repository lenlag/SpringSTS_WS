package formation.afpa.fr.entity;

import org.springframework.stereotype.Component;

@Component //("thatSillyCoach") //commented to use a default bean
public class TennisCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		
		return "Practice your backhand volley";
	}

}
