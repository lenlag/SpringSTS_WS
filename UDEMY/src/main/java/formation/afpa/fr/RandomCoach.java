package formation.afpa.fr;

import formation.afpa.fr.fortune.IFortuneService;

public class RandomCoach implements Coach {
	
	private IFortuneService fortuneService;
	
	//no-arg constructor
	public RandomCoach() {
		System.out.println("RandomCoach : inside no-arg constructor");
	}
	
	

	public RandomCoach(IFortuneService fortuneService) {
		super();
		this.fortuneService = fortuneService;
	}


	@Override
	public String getDailyWorkout() {
		return "RandomCoach : Practice some random workout";
	}

	@Override
	public String getDailyFortune() {
		
		return "I'm inside getDailyFortune method of RandomCoach => " + fortuneService.getFortune();
	}


	

}
