package com.etnetera.hr.data.model;

import org.springframework.http.converter.json.GsonBuilderUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple data entity describing basic properties of every JavaScript framework.
 * 
 * @author Etnetera
 *
 */
@Entity
public class JavaScriptFramework {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Size(max = 30)
	//@Column(nullable = false, length = 30)	// MySQL isn't too friendly with this
	private String name;
	private double version;
	// private Date depreciationDate;
	private String depreciationDate;
	private int hypeLevel;

	public JavaScriptFramework() { }

	public JavaScriptFramework(String name) {
		this.name = name;
	}

	public JavaScriptFramework(String name, double version, String depreciationDate, int hypeLevel) {
		this.name = name;
		this.version = version;
		//this.depreciationDate = parseDate(depreciationDate);
		this.depreciationDate = depreciationDate;
		this.hypeLevel = hypeLevel;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getVersion() { return version; }
	public void setVersion(double version) { this.version = version; }

	/*public String getDepreciationDate() { return depreciationDate.toString(); }
	public void setDepreciationDate(String depreciationDate) { this.depreciationDate = parseDate(depreciationDate); }*/
	public String getDepreciationDate() { return depreciationDate; }
	public void setDepreciationDate(String depreciationDate) { this.depreciationDate = depreciationDate; }

	public int getHypeLevel() { return hypeLevel; }
	public void setHypeLevel(int hypeLevel) { this.hypeLevel = hypeLevel; }

	@Override
	public String toString() {
		return "JavaScriptFramework [id=" + id + ", name=" + name + "]";
	}

	/*public static Date parseDate(String date) {
		try {
			System.out.println();
			return new SimpleDateFormat("yyyy-MM-dd").parse(date);
		}
		catch (Exception e) {
			return null;
		}
	}*/
}
