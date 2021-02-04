package com.etnetera.hr;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.etnetera.hr.data.model.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * Class used for Spring Boot/MVC based tests.
 * 
 * @author Etnetera
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
//@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class JavaScriptFrameworkTests {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private JavaScriptFrameworkRepository repository;

	private void prepareData() throws Exception {
		JavaScriptFramework react = new JavaScriptFramework("ReactJS");
		JavaScriptFramework vue = new JavaScriptFramework("Vue.js");
		
		repository.save(react);
		repository.save(vue);
	}

	@Test
	public void frameworksTest() throws Exception {
		prepareData();

		mockMvc.perform(get("/frameworks")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("ReactJS")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Vue.js")));
	}

	/**
	 * I don't think that adding a new framework into the "/frameworks" path is really RESTful..
	 * @author Igor
	*/
	/*@Test
	public void addFrameworkInvalid() throws JsonProcessingException, Exception {
		JavaScriptFramework framework = new JavaScriptFramework();
		mockMvc.perform(post("/frameworks").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(framework)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors[0].field", is("name")))
				.andExpect(jsonPath("$.errors[0].message", is("NotEmpty")));
		
		framework.setName("verylongnameofthejavascriptframeworkjavaisthebest");
		mockMvc.perform(post("/frameworks").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(framework)))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errors", hasSize(1)))
			.andExpect(jsonPath("$.errors[0].field", is("name")))
			.andExpect(jsonPath("$.errors[0].message", is("Size")));
	}*/

	@Test
	public void addFramework() throws Exception {
		prepareData();

		this.mockMvc.perform(post("/framework/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(new JavaScriptFramework("Angular", 8.4, "2. 2. 2042", 10))))
				.andExpect(status().isCreated())
				.andExpect(content().string("Framework added!"));

		mockMvc.perform(get("/frameworks")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[2].id", is(3)))
				.andExpect(jsonPath("$[2].name", is("Angular")))
				.andExpect(jsonPath("$[2].version", is(8.4)));
	}

	@Test
	public void updateFramework() throws Exception {
		prepareData();

		this.mockMvc.perform(post("/framework/2/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsBytes(new JavaScriptFramework("Vue.js", 0.1, "Prehistoric Age", 0))))
				.andExpect(status().is2xxSuccessful());

		mockMvc.perform(get("/frameworks")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Vue.js")))
				.andExpect(jsonPath("$[1].version", is(0.1)));
	}

	@Test
	public void deleteFramework() throws Exception {
		prepareData();

		mockMvc.perform(get("/frameworks")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)));	// Two frameworks exist in the DB

		this.mockMvc.perform(post("/framework/1/delete")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		mockMvc.perform(get("/frameworks")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(1)));	// Now only one framework exists in the DB
	}

	@Test
	public void getFramework() throws Exception {
		prepareData();

		mockMvc.perform(get("/framework/1")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("ReactJS")));

		mockMvc.perform(get("/framework/2")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(2)))
				.andExpect(jsonPath("$.name", is("Vue.js")));
	}

	@Test
	public void addFrameworkInvalid() throws Exception {
		JavaScriptFramework framework = new JavaScriptFramework();
		mockMvc.perform(post("/framework/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(framework)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors[0].field", is("name")))
				.andExpect(jsonPath("$.errors[0].message", is("NotBlank")));

		framework.setName("verylongnameofthejavascriptframeworkjavaisthebest");
		mockMvc.perform(post("/framework/add").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(framework)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.errors", hasSize(1)))
				.andExpect(jsonPath("$.errors[0].field", is("name")))
				.andExpect(jsonPath("$.errors[0].message", is("Size")));
	}
}
