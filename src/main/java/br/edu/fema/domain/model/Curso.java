package br.edu.fema.domain.model;

import br.edu.fema.framework.annotations.XmlElement;

import java.util.Objects;

@XmlElement("curso")
public class Curso {
    @XmlElement
    private String sigla;
    @XmlElement
    private String descricao;
    public Curso(String sigla, String descricao) {
        this.sigla = sigla;
        this.descricao = descricao;
    }
    // Curso c = new Curso(...);
    // Curso c = Curso.of(...);
    public static Curso of(String sigla, String descricao) {
        Objects.requireNonNull(sigla);
        Objects.requireNonNull(descricao);
        return new Curso(sigla, descricao);
    }
}
