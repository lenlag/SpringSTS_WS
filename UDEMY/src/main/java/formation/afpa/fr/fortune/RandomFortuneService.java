package formation.afpa.fr.fortune;

import java.util.Random;

public class RandomFortuneService implements IFortuneService {

	private String fortune1 = "Today is your lucky day!";
	private String fortune2 = "You are very pretty today!";
	private String fortune3 = "The chance will smile you today";
	
	@Override
	public String getFortune() {
		String[] myFortuneArray = new String[3];
		myFortuneArray[0] = fortune1;
		myFortuneArray[1] = fortune2;
		myFortuneArray[2] = fortune3;
		
		String random = (myFortuneArray[new Random().nextInt(myFortuneArray.length)]);
		
		return random;
	}

}
