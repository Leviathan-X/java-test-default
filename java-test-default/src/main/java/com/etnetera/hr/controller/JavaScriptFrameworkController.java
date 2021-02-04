package com.etnetera.hr.controller;

import com.etnetera.hr.data.dto.JavaScriptFrameworkDTO;
import com.etnetera.hr.data.dto.JavaScriptFrameworkDTOFactory;
import com.etnetera.hr.service.JavaScriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.etnetera.hr.data.model.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.lang.reflect.Method;


/**
 * Simple REST controller for accessing application logic.
 * 
 * @author Etnetera
 *
 */
@RestController
public class JavaScriptFrameworkController extends EtnRestController {

	private final JavaScriptFrameworkRepository repository;
	private final JavaScriptFrameworkService service;

	@Autowired
	public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository, JavaScriptFrameworkService service) {
		this.repository = repository;
		this.service = service;
	}

	@GetMapping("/frameworks")
	public Iterable<JavaScriptFramework> frameworks() {
		return repository.findAll();
	}

	@PostMapping("/framework/add")
	public ResponseEntity<?> addFramework(@RequestBody @Valid JavaScriptFramework javaScriptFramework,
										  BindingResult bindingResult) throws MethodArgumentNotValidException {
		if (bindingResult.hasErrors()) throw new MethodArgumentNotValidException(null, bindingResult);
		this.service.createFramework(javaScriptFramework.getName(), javaScriptFramework.getVersion(),
				javaScriptFramework.getDepreciationDate(), javaScriptFramework.getHypeLevel());
		return new ResponseEntity<>("Framework added!", HttpStatus.CREATED);
	}

	@PostMapping("/framework/{id}/update")
	public ResponseEntity<?> updateFramework(@PathVariable Long id, @RequestBody JavaScriptFramework javaScriptFramework) {
		this.service.updateFramework(id, javaScriptFramework.getName(), javaScriptFramework.getVersion(),
				javaScriptFramework.getDepreciationDate(), javaScriptFramework.getHypeLevel());
		return new ResponseEntity<>("Framework updated!", HttpStatus.OK);
	}

	@PostMapping("/framework/{id}/delete")
	public ResponseEntity<?> deleteFramework(@PathVariable Long id) {
		this.service.deleteFramework(id);
		return new ResponseEntity<>("Framework deleted!", HttpStatus.OK);
	}

	/**
	 * In the assignment it says that we should add a function that allows searching through the frameworks.
	 * I hope that the getFramework method is what was meant by the instructions.
	 * @author Igor
	 */
	@GetMapping("/framework/{id}")
	public JavaScriptFrameworkDTO getFramework(@PathVariable Long id) {
		return new JavaScriptFrameworkDTOFactory().build(this.service.getFramework(id));
	}
}
