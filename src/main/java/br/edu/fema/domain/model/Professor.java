package br.edu.fema.domain.model;

import br.edu.fema.framework.annotations.XmlElement;

import java.util.Objects;

@XmlElement("professor")
public class Professor {
    // fromXml("xml", Professor.class);
    @XmlElement
    private long matricula;
    @XmlElement
    private String nome;
    @XmlElement
    private String titulacaoAcademica;
    public Professor(long matricula, String nome, String titulacaoAcademica) {
        this.matricula = matricula;
        this.nome = nome;
        this.titulacaoAcademica = titulacaoAcademica;
    }
    public static Professor of(long matricula, String nome,
                               String titulacaoAcademica) {
        Objects.requireNonNull(nome);
        Objects.requireNonNull(titulacaoAcademica);
        return new Professor(matricula, nome, titulacaoAcademica);
    }
}
