package br.edu.fema.framework.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import br.edu.fema.framework.annotations.XmlElement;

public class MapGenerator implements XmlGenerationStrategy {

	@Override
	public void generateXmlFragment(StringBuilder xml, Object value, XmlElement xmlElementField) {
		Map<?, ?> map = (Map<?, ?>) value;
		String fieldAttribute = xmlElementField.fieldAttribute();
		String itemsName = xmlElementField.itemsName();

		for (Map.Entry<?, ?> entry : map.entrySet()) {

			Object key = entry.getKey();
			Class<?> keyType = key.getClass();

			try {
				Field fieldName = keyType.getDeclaredField(fieldAttribute);
				String className = keyType.getSimpleName().toLowerCase();
				fieldName.setAccessible(true);
				Object fieldValue = fieldName.get(key);

				xml.append("<").append(className).append(" ").append(fieldAttribute).append("=\"").append(fieldValue)
						.append("\"").append(">");

				Object entryValue = entry.getValue();

				if (entryValue instanceof List) {
					List<?> list = (List<?>) entryValue;
			        for (Object val : list) {
						xml.append("<").append(itemsName).append(">")
							.append(val).append("</").append(itemsName).append(">");
					}
				} else {
			    	xml.append(entryValue);
				}

				xml.append("</").append(className).append(">");

			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
			}
		}
	}
}
