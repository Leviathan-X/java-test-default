package com.etnetera.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etnetera.hr.data.model.JavaScriptFramework;

/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends JpaRepository<JavaScriptFramework, Long> {
    JavaScriptFramework findJavaScriptFrameworkById(Long id);
}
