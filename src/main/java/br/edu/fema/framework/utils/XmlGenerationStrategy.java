package br.edu.fema.framework.utils;

import br.edu.fema.framework.annotations.XmlElement;

public interface XmlGenerationStrategy {
    void generateXmlFragment(StringBuilder xml, Object value, XmlElement xmlElementField);
}

