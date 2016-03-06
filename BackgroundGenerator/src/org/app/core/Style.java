package org.app.core;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Style {
	private Map<String, String> styles;
	
	public Style (String styleValue) {
		String[] strValues = styleValue.split(";");
		
		styles = new HashMap<String, String>();
		
		for (String str : strValues) {
			styles.put(str.split(":")[0], str.split(":")[1]);
		}
	}
	
	public String getStyle(String styleName) {
		return styles.get(styleName);
	}
	
	public void setStyle(String styleName, String styleValue) {
		styles.put(styleName, styleValue);
	}
	
	@Override
	public String toString() {
		String str = "";
		
		Iterator<Entry<String, String>> it = styles.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<String, String> pair = (Entry<String, String>) it.next();
			str += pair.getKey() + ":" + pair.getValue() + ";";
			it.remove();
		}
		
		return str;
	}
}
