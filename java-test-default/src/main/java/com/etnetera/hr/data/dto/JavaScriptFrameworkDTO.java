package com.etnetera.hr.data.dto;

public class JavaScriptFrameworkDTO {

    private Long id;
    private String name;
    private double version;
    private String depreciationDate;
    private int hypeLevel;

    public JavaScriptFrameworkDTO() { }

    public JavaScriptFrameworkDTO(Long id, String name, double version, String depreciationDate, int hypeLevel) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.depreciationDate = depreciationDate;
        this.hypeLevel = hypeLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getVersion() { return version; }
    public void setVersion(double version) { this.version = version; }

    public String getDepreciationDate() { return depreciationDate; }
    public void setDepreciationDate(String depreciationDate) { this.depreciationDate = depreciationDate; }

    public int getHypeLevel() { return hypeLevel; }
    public void setHypeLevel(int hypeLevel) { this.hypeLevel = hypeLevel; }
}
