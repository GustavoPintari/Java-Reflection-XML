package br.edu.fema.framework.utils;

import java.text.SimpleDateFormat;

import br.edu.fema.framework.annotations.XmlElement;

public class DateGenerator implements XmlGenerationStrategy {

	@Override
	public void generateXmlFragment(StringBuilder xml, Object value, XmlElement xmlElementField) {
		String dateFormat = xmlElementField.dateFormat();
		SimpleDateFormat formater = new SimpleDateFormat(dateFormat);

		xml.append(formater.format(value));
	}
}
