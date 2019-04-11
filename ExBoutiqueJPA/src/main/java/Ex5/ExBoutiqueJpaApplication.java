package Ex5;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExBoutiqueJpaApplication implements CommandLineRunner {

	private static Log log = LogFactory.getLog(ExBoutiqueJpaApplication.class);
	
	@Autowired
	ImageRepository imageRep;
	
	@Autowired
	WarrantyRepository warRep;
	
	@Autowired
	CategoryRepository catRep;
	
	@Autowired
	ItemRepository itemRep;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ExBoutiqueJpaApplication.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {
	log.info("coucou");
		
	// TEST CRUDs :
	
	//************** Image *******************
/*	
	//CREATE
	Image backpack = new Image(null, "img/backpack.jpg", "Backpack");
	Image bike = new Image (null, "img/bike.jpg", "Bike");
	Image cellphone = new Image(null, "img/cellphone.jpg", "Cellphone");
	Image laptop = new Image(null, "img/laptop.jpg", "laptop");
	Image watch = new Image(null, "img/watch.jpg", "watch");
	
//	imageRep.save(backpack);
//	imageRep.save(bike);
//	imageRep.save(cellphone);
//	imageRep.save(laptop);
//	imageRep.save(watch);
	
	//COUNT
	log.info(imageRep.count());
	
	//FIND ALL
	List<Image> list = (List<Image>)imageRep.findAll();
	for (Image i : list) {
		log.info(i.toString());
	}
	
	//UPDATE
	Image image1 = imageRep.findById(3l).get();
	image1.setAltText("This is a bike");
	imageRep.save(image1);
	
	//Find By id
	log.info(imageRep.findById(2l).get());
	
	//DELETE
	
//	imageRep.delete(image1); // if the image1 name has been defines before via findById()
	
	//or
	
	try {
		imageRep.deleteById(3l); // in case id does not exist
	} catch (Exception e) {
		
	}
	
	// or
	
	if(imageRep.existsById(3l)) {
		imageRep.deleteById(3l);
	}
*/	
	
	//***************Warranty*******************
/*	
	//CREATE
	Warranty warranty1 = new Warranty(null, "Bike warranty",  "This warranty covers accidental drop or other cases...");
	Warranty warranty2 = new Warranty(null, "Backpack", "This is a 2 years warranty...");
	Warranty warranty3 = new Warranty(null, "Cellphone warranty", "Please, read carefully this note...");
	Warranty warranty4 = new Warranty(null, "Laptop warranty", "Your laptop receives 24month warranty for free...");
	Warranty warranty5 = new Warranty(null, "Watch warranty", "The ceiling will be applied to the sum of direct and indirect...");
	
//	warRep.save(warranty1);
//	warRep.save(warranty2);
//	warRep.save(warranty3);
//	warRep.save(warranty4);
//	warRep.save(warranty5);
	
	
	//UPDATE
	
	Warranty warr1 = warRep.findById(7l).get();
	warr1.setSummary("BackPack warranty");
	warRep.save(warr1);
	
	//FIND
	
	log.info(warRep.findById(7l).get());
	
	List<Warranty> list = (List<Warranty>) warRep.findAll();
	for (Warranty w : list) {
		log.info(w);
	}
	
	//DELETE
	
	Warranty warr2 = warRep.findById(6l).get();
	warRep.delete(warr2);

	*/
	
	// *****************Category*********************
	
	
	// CREATE
/*	
	Category gifts = new Category (null, "Gifts", "AAA77", "Gifts for your beloved ones"); // id = null , as AUTO_INCEREMENT, list of Items = null, as CascadeType.PERSIST, i.e. Entities should be created one by one. Use REFRESH Cascade type to create on the fly 
	Category gadgets = new Category(null, "Gadgets", "G421", "High tech gadgets");
	Category accessories = new Category(null, "Accessories", "ACC", "Trend accessories");
	Category travel = new Category (null, "Travel items", "TRVL", "Items for business & holiday travels");
	Category sport = new Category (null, "Sport equipment", "SPORT", "Equipment for various kinds of sports");
	
	catRep.save(gifts);
	catRep.save(gadgets);
	catRep.save(accessories);
	catRep.save(travel);
	catRep.save(sport);
	
	// FIND BY
	List<Category> list = (List<Category>) catRep.findAll();
	for (Category c : list) {
		log.info(c);
	}
	
	
	Category cat1 = catRep.findById(1l).get();
	log.info(cat1);
	
	
	//UPDATE
	
	cat1.setCode("A777");
	cat1.setDescription("Best gifts for your beloved ones");
	catRep.save(cat1);
	
	//COUNT
	log.info(catRep.count());
	
	//DELETE
	Category cat2 = catRep.findById(5l).get();
//	catRep.delete(cat2);
	
	try {
		catRep.deleteById(6l);
	} catch (Exception e) { // if the id does not exist
		
	}
	
	if(catRep.existsById(6l)) {
		catRep.deleteById(6l);
	}
	
*/
		
	
	//********************Item*************************
/*	
	//CREATE
	
	Item backpack = new Item (null, "Backpack", "B8867", "Backpack ideal for hiking", 50, new HashSet<Category>(), warRep.findById(7l).get(), new HashSet<Image>());
	Item bike = new Item (null, "Bike", "HJGF3544", "VTT Bike", 1000, new HashSet<Category>(), warRep.findById(11l).get(), new HashSet<Image>());
	Item cellphone = new Item (null, "Cellphone", "SHGVF454", "Smartphone", 750, new HashSet<Category>(), warRep.findById(8l).get(), new HashSet<Image>());	
	Item laptop = new Item (null, "Laptop", "Lh4545", "Adapted for business needs", 2200, new HashSet<Category>(), warRep.findById(9l).get(), new HashSet<Image>());
	Item watch = new Item (null, "Watch", "SW545", "Trend watch for women", 150, new HashSet<Category>(), warRep.findById(10l).get(), new HashSet<Image>());
	
//	itemRep.save(watch);
//	itemRep.save(laptop);
//	itemRep.save(bike);
//	itemRep.save(backpack);
//	itemRep.save(cellphone);
	
	
	// FIND BY
	
	List<Item> iList =  (List<Item>) itemRep.findAll(); // findAll() returns a List<>()
	for (Item i : iList) {
		log.info(i);
	}
	
	try {
		log.info(itemRep.findById(7l).get()); // in case the id does not exist
	} catch (Exception e) {
		
	}
	
	try {
		log.info(itemRep.findById(3l).get());
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	
	// COUNT
	
	log.info(itemRep.count());
	
	
	// UPDATE
	
	Item item1 = itemRep.findById(11l).get();
	Category cat1 = catRep.findById(9l).get();
//	item1.getCategories().add(cat1);
//	item1.getImages().add(imageRep.findById(4l).get());
//	itemRep.save(item1);

	
	
	
	//DELETE
	
	try {
		itemRep.deleteById(12l);
	} catch (Exception e) {
		
	}
	
	if (itemRep.existsById(12l)) {
		itemRep.deleteById(12l);;
	}
	
*/
		
	List<Item> list = (List<Item>) itemRep.findAll();
	for (Item item : list) {
		log.info(item);
	}
	
	
	Category other = new Category(null, "Other", "OTH54", "Other items");
//	catRep.save(other);
	
	Item it1 = itemRep.findById(11l).get();
	Item it2 = itemRep.findById(10l).get();
	
	it1.getCategories().addAll(catRep.findByName("Other"));
	it2.getCategories().addAll(catRep.findByName("Other"));
	
//	itemRep.save(it1);
//	itemRep.save(it2);
	
	
	list = (List<Item>) itemRep.findAll();
	for (Item item : list) {
		log.info(item);
	}
	
	List<Category> catList = (List<Category>) catRep.findAll();
	for (Category category : catList) {
		log.info(category);
	}
	
	
	Item redShoes = new Item(null, "Louboutins", "LW7", "Famous Louboutin red sole shoes", 1500, new HashSet<Category>(), null, new HashSet<Image>());
//	itemRep.save(redShoes);
	
//	itemRep.delete(itemRep.findById(13l).get());
	
	}
	
	
	
}

