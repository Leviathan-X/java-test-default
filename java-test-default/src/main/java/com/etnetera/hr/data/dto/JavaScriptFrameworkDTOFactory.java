package com.etnetera.hr.data.dto;

import com.etnetera.hr.data.model.JavaScriptFramework;

public class JavaScriptFrameworkDTOFactory {
    public static JavaScriptFrameworkDTO build(JavaScriptFramework javaScriptFramework) {
        return new JavaScriptFrameworkDTO(javaScriptFramework.getId(), javaScriptFramework.getName(),
                javaScriptFramework.getVersion(), javaScriptFramework.getDepreciationDate(),
                javaScriptFramework.getHypeLevel());
    }
}
