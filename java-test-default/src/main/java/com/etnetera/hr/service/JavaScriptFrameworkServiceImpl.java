package com.etnetera.hr.service;

import com.etnetera.hr.data.model.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class JavaScriptFrameworkServiceImpl implements JavaScriptFrameworkService {

    private final JavaScriptFrameworkRepository javaScriptFrameworkRepository;

    @Autowired
    public JavaScriptFrameworkServiceImpl(JavaScriptFrameworkRepository javaScriptFrameworkRepository) {
        this.javaScriptFrameworkRepository = javaScriptFrameworkRepository;
    }

    @Override
    public void createFramework(String name, double version, String depreciationDate, int hypeLevel) {
        this.javaScriptFrameworkRepository.save(new JavaScriptFramework(name, version, depreciationDate, hypeLevel));
    }

    @Override
    public void updateFramework(Long id, String name, double version, String depreciationDate, int hypeLevel) {
        JavaScriptFramework javaScriptFramework = new JavaScriptFramework(name, version, depreciationDate, hypeLevel);
        javaScriptFramework.setId(id);
        this.javaScriptFrameworkRepository.save(javaScriptFramework);
    }

    @Override
    public void deleteFramework(Long id) {
        this.javaScriptFrameworkRepository.deleteById(id);
    }

    @Override
    public JavaScriptFramework getFramework(Long id) {
        JavaScriptFramework javaScriptFramework = this.javaScriptFrameworkRepository.findJavaScriptFrameworkById(id);
        if(javaScriptFramework != null) {
            return javaScriptFramework;
        }
        else return null;
    }
}
