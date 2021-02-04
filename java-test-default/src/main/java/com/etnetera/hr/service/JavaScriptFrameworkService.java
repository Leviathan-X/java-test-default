package com.etnetera.hr.service;

import com.etnetera.hr.data.model.JavaScriptFramework;

public interface JavaScriptFrameworkService {
    void createFramework(String name, double version, String depreciationDate, int hypeLevel);
    void updateFramework(Long id, String name, double version, String depreciationDate, int hypeLevel);
    void deleteFramework(Long id);
    JavaScriptFramework getFramework(Long id);
}
