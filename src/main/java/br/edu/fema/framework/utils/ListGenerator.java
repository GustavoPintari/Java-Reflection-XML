package br.edu.fema.framework.utils;

import java.util.List;

import br.edu.fema.framework.annotations.XmlElement;

public class ListGenerator implements XmlGenerationStrategy {
	@Override
    public void generateXmlFragment(StringBuilder xml, Object value, XmlElement xmlElementField) {
        List<?> list = (List<?>) value;
        StringBuilder xmlList = new StringBuilder();
        for (Object element : list) {
            xmlList.append(XmlUtils.toXml(element));
        }
        xml.append(xmlList);
    }
}