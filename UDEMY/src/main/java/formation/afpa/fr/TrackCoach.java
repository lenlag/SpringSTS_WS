package formation.afpa.fr;

import formation.afpa.fr.fortune.IFortuneService;

public class TrackCoach implements Coach {

	private IFortuneService fortuneService;
	
	public TrackCoach() {
		
	}
	
	public TrackCoach(IFortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		return "Just do it : " +  fortuneService.getFortune();
	}
	
	
	// add an int method
	public void doMyStartupStuff() {
		System.out.println("TrackCoach: inside method doMyStartupStuff");
	}
	
	// add a destroy method
	public void doMyCleanupStuff() {
		System.out.println("TrackCoach: inside method doMyCleanupStuff");
	}

}
