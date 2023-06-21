package com.example.demo.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Depense;
import com.example.demo.model.Evenement;
import com.example.demo.model.InfoClass;
import com.example.demo.model.State;
import com.example.demo.model.Vartiste;
import com.example.demo.model.Vbillet;
import com.example.demo.model.Vlieu;
import com.example.demo.model.Vlogistique;
import com.example.demo.model.Vsonorisation;

@Controller
public class TemplateController {
	
	CrudControl crud = new CrudControl();

	@GetMapping("/liste/{name}")
	private String getListe(Model model,@PathVariable String name) {
		model.addAttribute("data", name);
		return "list";
	}
	
	@GetMapping("/")
	private String index() {
		return "login";
	}
	
	@GetMapping("/supprimer")
	private String supprimer(Model model , String name , long id) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		crud.deleteById(name, id);
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		List<Object> personnes = crud.getAllEntities(name);
        List<String> fields = crud.getFieldsList(name);
        List<InfoClass> infoClasses = crud.getInfoClass(name);
        List<Integer> taille = crud.paginate(0,name);
        int min = taille.get(0);
        int max = taille.get(1);
        
        model.addAttribute("infos", infoClasses);
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
		return "list";
	}
	
	@GetMapping("/modifier")
	private String update(Model model,String name , long id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Object> list = crud.findById(name, id);
		List<InfoClass> infoClasses = crud.getInfoClass(name,list.get(0));
		
		model.addAttribute("infos",infoClasses);
		model.addAttribute("entitie",name);
		return "update";
	}
	
	@PostMapping("/update")
	private String update(Model model , @RequestParam("data") String[] data , @RequestParam("entitie") String entitie) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String name = entitie;
		
		crud.update(data, name);
		
		List<Object> personnes = crud.getAllEntities(name,0);
        List<String> fields = crud.getFieldsList(name);
        List<InfoClass> infoClasses = crud.getInfoClass(name);
        
        model.addAttribute("infos", infoClasses);
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);
		return "list";
	}
	
	@PostMapping("/updateImg")
	private String update(Model model , @RequestParam("data") String[] data , @RequestParam("entitie") String entitie, @RequestParam("img") MultipartFile image) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		String name = entitie;
		
		String base64 = "";
		System.out.println(image);
		
		if (!image.isEmpty()) {
		    byte[] imageBytes = image.getBytes();
		    base64 = Base64.getEncoder().encodeToString(imageBytes);
		}
		
		String[] tab = new String[data.length + 1]; // +1 pour img
		System.arraycopy(data, 0, tab, 0, data.length); // Copier les éléments de data
		tab[data.length] = base64; 
		
		crud.update(tab, name);
		
		List<Object> personnes = crud.getAllEntities(name,0);
        List<String> fields = crud.getFieldsList(name);
        List<InfoClass> infoClasses = crud.getInfoClass(name);
        
        model.addAttribute("infos", infoClasses);
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);
		return "list";
	}
	
	
	@GetMapping("/generic")
    public String showGenericPage(Model model,String name,int page) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
		List<Object> personnes = crud.getAllEntities(name);
        List<String> fields = crud.getFieldsList(name);
        List<InfoClass> infoClasses = crud.getInfoClass(name);
        List<Integer> taille = crud.paginate(page,name);
        int min = taille.get(0);
        int max = taille.get(1);
        
        model.addAttribute("infos", infoClasses);
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        
        return "list";
    }
	
	@GetMapping("/devis")
	private String devis(Model model) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String name = "Evenement";
		List<Object> personnes = crud.getAllEntities(name);
        List<String> fields = crud.getFieldsList(name);
        List<InfoClass> infoClasses = crud.getInfoClass(name);
        
        model.addAttribute("infos", infoClasses);
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);
		return "vuedevis";
	}
	
	@GetMapping("/state")
	private String state(Model model) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String name = "Evenement";
		List<Object> event = crud.getAllEntities(name);
		List<String> fields = crud.getFieldsList("State");
		List<State> state = new ArrayList<>();
		for (int id = 1; id <event.size(); id++) {
			float taxe = 0;
			List<Object> evenement = crud.findById("Evenement", id);
			List<Object> artist = crud.getAllEntities("Vartiste", "select a.id as id, a.nom as nom , a.tarif as tarif , ae.duree as duree from artisteevenement ae join artiste a on a.id=ae.artisteid where ae.evenementid="+id);
			List<Object> lieu = crud.getAllEntities("Vlieu", "select \r\n"
					+ " le.id as idle, \r\n"
					+ " l.id as idl, \r\n"
					+ "	cp.id as idcp, \r\n"
					+ " lp.id as idlp, \r\n"
					+ "	l.address as address , \r\n"
					+ "	lp.nbrplace as nbrplace , \r\n"
					+ "	le.heure as heure , \r\n"
					+ "	le.minute as minute, \r\n"
					+ " le.dateevenement as dateevenement, \r\n"
					+ "	le.prix as prix \r\n"
					+ "from lieuevenement le \r\n"
					+ "	join lieu l on l.id=le.lieuid \r\n"
					+ "	join lieuplace lp on lp.lieuid=l.id \r\n"
					+ "	join categorieplace cp on cp.id=lp.categorieplaceid \r\n"
					+ "where le.evenementid="+id);
			List<Object> sono = crud.getAllEntities("Vsonorisation", "select s.id as id, s.type as type, s.tarif as tarif , se.duree as duree from sonorisationevenement se join sonorisation s on s.id=se.sonorisationid where se.evenementid="+id);
			List<Object> logis = crud.getAllEntities("Vlogistique", "select l.id as id, l.type as type , l.tarifjour as tarifjour , le.duree as duree from logistiqueevenement le join logistique l on l.id=le.logistiqueid where le.evenementid="+id);
			List<Object> depense = crud.getAllEntities("Depense", "select d.id as id, d.name as name , d.prix as prix from depense d where d.evenementid="+id);
			
			List<Object> billet = crud.getAllEntities("Vbillet", "select \r\n"
					+ " be.id as id,\r\n"
					+ "	lp.nbrplace as nbrplace,\r\n"
					+ "	be.prix as prix, \r\n"
					+ " be.taxe as taxe ,\r\n"
					+ "	lp.id as idlp, \r\n"
					+ "	cp.categorie as categorie\r\n"
					+ "from billetevenement be\r\n"
					+ "join categorieplace cp on cp.id = be.categorieplaceid \r\n"
					+ "join lieuplace lp on lp.categorieplaceid = cp.id \r\n"
					+ "where be.evenementid ="+id);
			
			float prixtotal = 0;
			float recettetotal = 0;
			float totalplace = 0;
			float prix = 0;
			float recette = 0;
			
			for (Object object : billet) {
				recettetotal += ((((Vbillet) object).getPrix()*((Vbillet) object).getNbrplace())*(100-((Vbillet) object).getTaxe()))/100;
				totalplace += ((Vbillet) object).getNbrplace();
				if (taxe<((Vbillet) object).getTaxe()) {
					taxe = ((Vbillet) object).getTaxe();
				}
				recette += ((((Vbillet) object).getPrix()*((Vbillet) object).getNbrplace()));
			}
			
			for (Object object : lieu) {
				prixtotal += ((Vlieu) object).getPrix();
				System.out.println("Lieu : "+((Vlieu) object).getPrix());
			}
			for (Object object : artist) {
				prixtotal += (((Vartiste) object).getTarif() * ((Vartiste) object).getDuree());
				System.out.println("Artiste : " + ((Vartiste) object).getTarif());
			}
			for (Object object : depense) {
				prixtotal += ((Depense) object).getPrix();
				System.out.println("Depense : " + ((Depense) object).getPrix());
			}
			for (Object object : logis) {
				prixtotal += (((Vlogistique) object).getTarifjour()* ((Vlogistique) object).getDuree());
				System.out.println("Logis : " + ((Vlogistique) object).getTarifjour());
			}
			for (Object object : sono) {
				prixtotal += (((Vsonorisation) object).getTarif() * ((Vsonorisation) object).getDuree());
				System.out.println("Sono : " + ((Vsonorisation) object).getTarif());
			}
			
			state.add(new State(((Evenement) evenement.get(0)).getName(), recettetotal, prixtotal, recette-prixtotal, taxe, recettetotal-prixtotal));
		}
		
		model.addAttribute("items", state);
		model.addAttribute("fields", fields);
		model.addAttribute("entitie","State");
		
		return "state";
	}
	
	@GetMapping("/devisdetail")
	private String devisdetail(Model model , int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Object> evenement = crud.findById("Evenement", id);
		List<Object> artist = crud.getAllEntities("Vartiste", "select a.id as id, a.nom as nom , a.tarif as tarif , ae.duree as duree from artisteevenement ae join artiste a on a.id=ae.artisteid where ae.evenementid="+id);
		List<Object> lieu = crud.getAllEntities("Vlieu", "select \r\n"
				+ " le.id as idle, \r\n"
				+ " l.id as idl, \r\n"
				+ "	cp.id as idcp, \r\n"
				+ " lp.id as idlp, \r\n"
				+ "	l.address as address , \r\n"
				+ "	lp.nbrplace as nbrplace , \r\n"
				+ "	le.heure as heure , \r\n"
				+ "	le.minute as minute, \r\n"
				+ " le.dateevenement as dateevenement, \r\n"
				+ "	le.prix as prix \r\n"
				+ "from lieuevenement le \r\n"
				+ "	join lieu l on l.id=le.lieuid \r\n"
				+ "	join lieuplace lp on lp.lieuid=l.id \r\n"
				+ "	join categorieplace cp on cp.id=lp.categorieplaceid \r\n"
				+ "where le.evenementid="+id);
		List<Object> sono = crud.getAllEntities("Vsonorisation", "select s.id as id, s.type as type, s.tarif as tarif , se.duree as duree from sonorisationevenement se join sonorisation s on s.id=se.sonorisationid where se.evenementid="+id);
		List<Object> logis = crud.getAllEntities("Vlogistique", "select l.id as id, l.type as type , l.tarifjour as tarifjour , le.duree as duree from logistiqueevenement le join logistique l on l.id=le.logistiqueid where le.evenementid="+id);
		List<Object> depense = crud.getAllEntities("Depense", "select d.id as id, d.name as name , d.prix as prix from depense d where d.evenementid="+id);
		
		List<Object> billet = crud.getAllEntities("Vbillet", "select \r\n"
				+ " be.id as id,\r\n"
				+ "	lp.nbrplace as nbrplace,\r\n"
				+ "	be.prix as prix, \r\n"
				+ " be.taxe as taxe ,\r\n"
				+ "	lp.id as idlp, \r\n"
				+ "	cp.categorie as categorie\r\n"
				+ "from billetevenement be\r\n"
				+ "join categorieplace cp on cp.id = be.categorieplaceid \r\n"
				+ "join lieuplace lp on lp.categorieplaceid = cp.id \r\n"
				+ "where be.evenementid ="+id);
		
		float prixtotal = 0;
		float recettetotal = 0;
		float totalplace = 0;
		
		for (Object object : billet) {
			recettetotal += ((((Vbillet) object).getPrix()*((Vbillet) object).getNbrplace())*(100-((Vbillet) object).getTaxe()))/100;
			totalplace += ((Vbillet) object).getNbrplace();
		}
		
		for (Object object : lieu) {
			prixtotal += ((Vlieu) object).getPrix();
			System.out.println("Lieu : "+((Vlieu) object).getPrix());
		}
		for (Object object : artist) {
			prixtotal += (((Vartiste) object).getTarif() * ((Vartiste) object).getDuree());
			System.out.println("Artiste : " + ((Vartiste) object).getTarif());
		}
		for (Object object : depense) {
			prixtotal += ((Depense) object).getPrix();
			System.out.println("Depense : " + ((Depense) object).getPrix());
		}
		for (Object object : logis) {
			prixtotal += (((Vlogistique) object).getTarifjour()* ((Vlogistique) object).getDuree());
			System.out.println("Logis : " + ((Vlogistique) object).getTarifjour());
		}
		for (Object object : sono) {
			prixtotal += (((Vsonorisation) object).getTarif() * ((Vsonorisation) object).getDuree());
			System.out.println("Sono : " + ((Vsonorisation) object).getTarif());
		}
		
		System.out.println("prix total : " + prixtotal);
		model.addAttribute("evenement", evenement);
		model.addAttribute("artist", artist);
		model.addAttribute("lieu", lieu);
		model.addAttribute("sono", sono);
		model.addAttribute("logis", logis);
		model.addAttribute("depense", depense);
		model.addAttribute("prixtotal", prixtotal);
		model.addAttribute("billet", billet);
		model.addAttribute("recette", recettetotal);
		model.addAttribute("totalplace", totalplace);
		return "devisdetail";
	}
	
	@GetMapping("/devisvrai")
	private String devisvrai(Model model , int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Object> evenement = crud.findById("Evenement", id);
		List<Object> artist = crud.getAllEntities("Vartiste", "select a.id as id, a.nom as nom , a.tarif as tarif , ae.duree as duree from artisteevenement ae join artiste a on a.id=ae.artisteid where ae.evenementid="+id);
		List<Object> lieu = crud.getAllEntities("Vlieu", "select \r\n"
				+ " le.id as idle, \r\n"
				+ " l.id as idl, \r\n"
				+ "	cp.id as idcp, \r\n"
				+ "	l.address as address , \r\n"
				+ "	lp.nbrplace as nbrplace , \r\n"
				+ "	le.heure as heure , \r\n"
				+ "	le.minute as minute, \r\n"
				+ " le.dateevenement as dateevenement, \r\n"
				+ "	le.prix as prix \r\n"
				+ "from lieuevenement le \r\n"
				+ "	join lieu l on l.id=le.lieuid \r\n"
				+ "	join lieuplace lp on lp.lieuid=l.id \r\n"
				+ "	join categorieplace cp on cp.id=lp.categorieplaceid \r\n"
				+ "where le.evenementid="+id);
		List<Object> sono = crud.getAllEntities("Vsonorisation", "select s.id as id, s.type as type, s.tarif as tarif , se.duree as duree from sonorisationevenement se join sonorisation s on s.id=se.sonorisationid where se.evenementid="+id);
		List<Object> logis = crud.getAllEntities("Vlogistique", "select l.id as id, l.type as type , l.tarifjour as tarifjour , le.duree as duree from logistiqueevenement le join logistique l on l.id=le.logistiqueid where le.evenementid="+id);
		List<Object> depense = crud.getAllEntities("Depense", "select d.id as id, d.name as name , d.prix as prix from depense d where d.evenementid="+id);
		
		List<Object> billet = crud.getAllEntities("Vbillet", "select \r\n"
				+ " be.id as id,\r\n"
				+ "	bv.nbrvente as nbrvente,\r\n"
				+ "	be.prix as prix,\r\n"
				+ "	cp.categorie as categorie, \r\n"
				+ " be.taxe as taxe ,\r\n"
				+ " cp.nbrplace as nbrplace \r\n"
				+ "from billetevenement be\r\n"
				+ "join categorieplace cp on cp.id = be.categorieplaceid \r\n"
				+ "join billetvente bv on bv.billetid=be.id \r\n"
				+ "where be.evenementid = "+id);
		
		float prixtotal = 0;
		float recettetotal = 0;
		
		for (Object object : billet) {
			recettetotal += ((((Vbillet) object).getPrix()*((Vbillet) object).getNbrvente())*(100-((Vbillet) object).getTaxe()))/100;
		}
		
		for (Object object : lieu) {
			prixtotal += ((Vlieu) object).getPrix();
			System.out.println("Lieu : "+((Vlieu) object).getPrix());
		}
		for (Object object : artist) {
			prixtotal += (((Vartiste) object).getTarif() * ((Vartiste) object).getDuree());
			System.out.println("Artiste : " + ((Vartiste) object).getTarif());
		}
		for (Object object : depense) {
			prixtotal += ((Depense) object).getPrix();
			System.out.println("Depense : " + ((Depense) object).getPrix());
		}
		for (Object object : logis) {
			prixtotal += (((Vlogistique) object).getTarifjour()* ((Vlogistique) object).getDuree());
			System.out.println("Logis : " + ((Vlogistique) object).getTarifjour());
		}
		for (Object object : sono) {
			prixtotal += (((Vsonorisation) object).getTarif() * ((Vsonorisation) object).getDuree());
			System.out.println("Sono : " + ((Vsonorisation) object).getTarif());
		}
		
		System.out.println("prix total : " + prixtotal);
		model.addAttribute("evenement", evenement);
		model.addAttribute("artist", artist);
		model.addAttribute("lieu", lieu);
		model.addAttribute("sono", sono);
		model.addAttribute("logis", logis);
		model.addAttribute("depense", depense);
		model.addAttribute("prixtotal", prixtotal);
		model.addAttribute("billet", billet);
		model.addAttribute("recette", recettetotal);
		return "devisvrai";
	}
	
	@GetMapping("/affichedetail")
	private String affiche(Model model , int id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		List<Object> evenement = crud.findById("Evenement", id);
		List<Object> artist = crud.getAllEntities("Vartiste", "select a.id as id, a.nom as nom , a.tarif as tarif , ae.duree as duree , a.img as img from artisteevenement ae join artiste a on a.id=ae.artisteid where ae.evenementid="+id);
		List<Object> lieu = crud.getAllEntities("Vlieu", "select \r\n"
				+ " le.id as idle, \r\n"
				+ " l.id as idl, \r\n"
				+ "	cp.id as idcp, \r\n"
				+ "	l.address as address , \r\n"
				+ "	lp.nbrplace as nbrplace , \r\n"
				+ "	le.heure as heure , \r\n"
				+ "	le.minute as minute, \r\n"
				+ "	le.prix as prix , \r\n"
				+ " le.dateevenement as dateevenement, \r\n"
				+ " l.img as img \r\n"
				+ "from lieuevenement le \r\n"
				+ "	join lieu l on l.id=le.lieuid \r\n"
				+ "	join lieuplace lp on lp.lieuid=l.id \r\n"
				+ "	join categorieplace cp on cp.id=lp.categorieplaceid \r\n"
				+ "where le.evenementid="+id);
		List<Object> sono = crud.getAllEntities("Vsonorisation", "select s.id as id, s.type as type, s.tarif as tarif , se.duree as duree from sonorisationevenement se join sonorisation s on s.id=se.sonorisationid where se.evenementid="+id);
		List<Object> logis = crud.getAllEntities("Vlogistique", "select l.id as id, l.type as type , l.tarifjour as tarifjour , le.duree as duree from logistiqueevenement le join logistique l on l.id=le.logistiqueid where le.evenementid="+id);
		List<Object> depense = crud.getAllEntities("Depense", "select d.id as id, d.name as name , d.prix as prix from depense d where d.evenementid="+id);
		
		List<Object> billet = crud.getAllEntities("Vbillet", "select \r\n"
				+ " be.id as id,\r\n"
				+ "	cp.nbrplace as nbrplace,\r\n"
				+ "	be.prix as prix,\r\n"
				+ "	cp.categorie as categorie\r\n"
				+ "from billetevenement be\r\n"
				+ "join categorieplace cp on cp.id = be.categorieplaceid \r\n"
				+ "where be.evenementid = "+id);
		
		
		model.addAttribute("evenement", evenement);
		model.addAttribute("artist", artist);
		model.addAttribute("lieu", lieu);
		model.addAttribute("sono", sono);
		model.addAttribute("logis", logis);
		model.addAttribute("depense", depense);
		model.addAttribute("billet", billet);
		return "affiche";
	}
	
	
	@PostMapping("/insertImg")
	private String insert(Model model , @RequestParam("data") String[] data , @RequestParam("entitie") String entitie, @RequestParam("img") MultipartFile image) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		String name = entitie;
		String base64 = "";
		
		if (!image.isEmpty()) {
		    byte[] imageBytes = image.getBytes();
		    base64 = Base64.getEncoder().encodeToString(imageBytes);
		}
		String[] tab = new String[data.length + 1]; // +1 pour img
		System.arraycopy(data, 0, tab, 0, data.length); // Copier les éléments de data
		tab[data.length] = base64; 
//		Object obj = crud.initialisationEntitie(name, data);
//		System.out.println(obj.toString());
		
		crud.insert(tab, name);
		
		List<Object> personnes = crud.getAllEntities(name,0);
        List<String> fields = crud.getFieldsList(name);
        List<InfoClass> infoClasses = crud.getInfoClass(name);
        
        model.addAttribute("infos", infoClasses);
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);
        model.addAttribute("base64Image", base64);
        
		return "list";
	}
	
	@PostMapping("/insert")
	private String insert(Model model , @RequestParam("data") String[] data , @RequestParam("entitie") String entitie) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		String name = entitie;
		
//		Object obj = crud.initialisationEntitie(name, data);
//		System.out.println(obj.toString());
		
		crud.insert(data, name);
		
		List<Object> personnes = crud.getAllEntities(name,0);
        List<String> fields = crud.getFieldsList(name);
        List<InfoClass> infoClasses = crud.getInfoClass(name);
        
        model.addAttribute("infos", infoClasses);
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);
        
		return "list";
	}
}
