package formation.afpa.fr.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import formation.afpa.fr.dto.SpecieDTO;

@Component
public class ServiceSpecieWSRest {

	static final String BASE_URL = "http://10.111.61.31:8080/rest/species"; // le serveur de JCV

//	private long current_id = 0L;

	public void listAsString() {
		RestTemplate rt = new RestTemplate();
		String result = rt.getForObject(BASE_URL, String.class);
		System.out.println("String = " + result);
		System.out.println();
	}

	public List<SpecieDTO> list() { // cette fonction doit retourner une list pour la rajouter dans le model depuis
									// controller
		RestTemplate rt = new RestTemplate();
		return Arrays.asList(rt.getForObject(BASE_URL, SpecieDTO[].class));

	}

	public void listAsMap() {
		RestTemplate rt = new RestTemplate();
		Map<?, ?>[] maps = rt.getForObject(BASE_URL, Map[].class);
		for (Map<?, ?> map : maps) {
			for (Object key : map.keySet()) {
				System.out.print(key.toString() + " = " + map.get(key).toString() + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public Long post(SpecieDTO sp) {
		RestTemplate rt = new RestTemplate();

		HttpHeaders head = new HttpHeaders();

		head.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		head.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<SpecieDTO> body = new HttpEntity<>(sp, head);
		long id = rt.postForObject(BASE_URL, body, Long.class);

		System.out.println("id = " + id);
		System.out.println();
		// current_id = id;

		return id;
	}

	public void delete(Long id) {
		RestTemplate rt = new RestTemplate();
		rt.delete(BASE_URL + "/" + id);
	}

	public void update(SpecieDTO sp) {
		RestTemplate rt = new RestTemplate();
		
		HttpHeaders head = new HttpHeaders();
		head.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		head.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<SpecieDTO> body = new HttpEntity<>(sp, head);

		rt.put(BASE_URL + "/" + sp.getId(), body); //pas de type de retour => pas de 3ème argument

	}

	public SpecieDTO getById(Long id) {
		RestTemplate rt = new RestTemplate();
		SpecieDTO sp = rt.getForObject(BASE_URL + "/" + id, SpecieDTO.class);

		return sp;
	}

//	public static void main(String[] args) {
//		ServiceSpecieWSRest rca = new ServiceSpecieWSRest();
//		rca.listAsString();
//		rca.list();
//		rca.listAsMap();
//		rca.post(new SpecieDTO());
//		rca.list();
//		rca.delete(88L);
//		rca.list();
//	}
}
