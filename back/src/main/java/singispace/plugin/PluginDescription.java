package singispace.plugin;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.http.HttpMethod;

public class PluginDescription {
	private String name;
	private String category;
	private String description;
	private String url;
	private HashMap<HttpMethod, HashSet<String>> endpoints = new HashMap<HttpMethod, HashSet<String>>();
	public PluginDescription() {
		
	}
	public PluginDescription(String name, String category, String description, String url,
			HashMap<HttpMethod, HashSet<String>> endpoints) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.url = url;
		this.endpoints = endpoints;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public HashMap<HttpMethod, HashSet<String>> getEndpoints() {
		return endpoints;
	}
	public void setEndpoints(HashMap<HttpMethod, HashSet<String>> endpoints) {
		this.endpoints = endpoints;
	}
	
	

}
