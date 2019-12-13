package singispace.plugin;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Plugin {
	private PluginDescription pd;
	public Plugin() {
		
	}
	public Plugin(PluginDescription pd) {
		super();
		this.pd = pd;
	}
	public PluginDescription getPd() {
		return pd;
	}
	public void setPd(PluginDescription pd) {
		this.pd = pd;
	}
	public ResponseEntity<String> sendRequest(HttpMethod method, String url) {
		RestTemplate rt = new RestTemplate();
		return rt.getForEntity(url, String.class);
	}

}
