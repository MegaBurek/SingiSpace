package singispace.plugin;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

@Repository
public class PluginRepository {
	private HashMap<String, ArrayList<Plugin>> pluginRepository = new HashMap<String, ArrayList<Plugin>>();
	public PluginRepository() {
		
	}
	public void registerPlugin(PluginDescription pluginDescription) {
		if(pluginRepository.get(pluginDescription.getCategory()) == null) {
			pluginRepository.put(pluginDescription.getCategory(), new ArrayList<Plugin>());
		}
		Plugin plugin = new Plugin(pluginDescription);
		pluginRepository.get(pluginDescription.getCategory()).add(plugin);
	}
	
	public ArrayList<Plugin> getPlugins(String category) {
		return pluginRepository.get(category);
	}

}
