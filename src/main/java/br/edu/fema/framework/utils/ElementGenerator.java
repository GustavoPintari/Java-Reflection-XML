package br.edu.fema.framework.utils;

import br.edu.fema.framework.annotations.XmlElement;

public class ElementGenerator implements XmlGenerationStrategy {

	@Override
    public void generateXmlFragment(StringBuilder xml, Object value, XmlElement xmlElementField) {
        xml.append(XmlUtils.toXmlFields(value));
    }
}
