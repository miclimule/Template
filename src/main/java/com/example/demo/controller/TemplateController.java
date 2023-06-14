package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
		return "index";
	}
	
	
	@GetMapping("/generic/{name}")
    public String showGenericPage(Model model,@PathVariable String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        List<Object> personnes = crud.getAllEntities(name);
        List<String> fields = crud.getFieldsList(name);
        
        model.addAttribute("items", personnes);
        model.addAttribute("fields", fields);
        model.addAttribute("entitie",name);

        return "list";
    }
}
