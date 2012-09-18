package draw;

import java.util.HashMap;
import java.util.Map;

public class ParameterSet {
	
	private Map<String, Double> doubleValues;
	
	private Map<String, Boolean> booleanValues;
	
	private Map<String, Object> objectValues;
	
	public ParameterSet() {
		this.doubleValues = new HashMap<String, Double>();
		this.booleanValues = new HashMap<String, Boolean>();
		this.objectValues = new HashMap<String, Object>();
	}
	
	public void set(String key, double value) {
		this.doubleValues.put(key, value);
	}
	
	public void set(String key, Object value) {
		this.objectValues.put(key, value);
	}
	
	public void set(String key, boolean value) {
		this.booleanValues.put(key, value);
	}

	public double getDouble(String key) {
		if (doubleValues.containsKey(key)) {
			return doubleValues.get(key);
		} else {
			return 1.0;
		}
	}
	
	public boolean getBoolean(String key) {
		return booleanValues.get(key);
	}
	
	public Object getObject(String key) {
		return objectValues.get(key);
	}
	
}
