package br.edu.fema.framework.utils;

import br.edu.fema.framework.annotations.XmlElement;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

public abstract class XmlUtils {

	public static String toXml(Object object) {

		final StringBuilder xml = new StringBuilder("");
		final Class<?> klass = object.getClass();

		if (klass.isAnnotationPresent(XmlElement.class)) {

			final XmlElement xmlElementKlass = klass.getDeclaredAnnotation(XmlElement.class);

			if (xmlElementKlass.enabled()) {
				final String klassName = !xmlElementKlass.value().isEmpty() ? xmlElementKlass.value()
						: klass.getSimpleName();

				xml.append("<").append(klassName).append(">");
				xml.append(toXmlFields(object));
				xml.append("</").append(klassName).append(">");
			}
		}
		return xml.toString();
	}

	public static String toXmlFields(Object object) {

		StringBuilder xml = new StringBuilder("");
		Field[] fields = object.getClass().getDeclaredFields();

		for (Field field : fields) {
			if (XmlUtils.validateXmlElementAnnotation(field)) {

				XmlElement xmlElementField = field.getDeclaredAnnotation(XmlElement.class);

				Object value = getValueFromField(object, field);

				String fieldName = !xmlElementField.value().isEmpty() ? xmlElementField.value() : field.getName();

				Class<?> type = field.getType();

				xml.append("<").append(fieldName).append(">");
				generateXmlFragment(xml, value, type, xmlElementField);
				xml.append("</").append(fieldName).append(">");
			}
		}
		return xml.toString();
	}

	private static void generateXmlFragment(StringBuilder xml, Object value, Class<?> type,
			XmlElement xmlElementField) {
		if (value == null) {
			return;
		}

		XmlGenerationStrategy generator;
		if (type.isAnnotationPresent(XmlElement.class)) {
			generator = new ElementGenerator();
		} else if (List.class.isAssignableFrom(type)) {
			generator = new ListGenerator();
		} else if (Map.class.isAssignableFrom(type)) {
			generator = new MapGenerator();
		} else if (Date.class.isAssignableFrom(type)) {
			generator = new DateGenerator();
		} else {
			xml.append(value);
			return;
		}
		generator.generateXmlFragment(xml, value, xmlElementField);
	}

	private static boolean validateXmlElementAnnotation(Field field) {
		if (field.isAnnotationPresent(XmlElement.class)) {
			return field.getDeclaredAnnotation(XmlElement.class).enabled();
		}
		return false;
	}

	private static Object getValueFromField(Object object, Field field) {
		Object value = null;
		try {
			field.setAccessible(true);
			value = field.get(object);
		} catch (IllegalAccessException ignored) {
		} finally {
			field.setAccessible(false);
		}
		return value;
	}
}
