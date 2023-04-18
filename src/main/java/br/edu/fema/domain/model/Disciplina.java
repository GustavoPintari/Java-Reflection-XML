package br.edu.fema.domain.model;

import br.edu.fema.framework.annotations.XmlElement;

import java.util.Objects;

@XmlElement("disciplina")
public class Disciplina {
    @XmlElement
    private String descricao;
    @XmlElement
    private Professor professor;
    @XmlElement
    private int quantidadeAulas;

    public Disciplina(String descricao, Professor professor,
                      int quantidadeAulas) {
        this.descricao = descricao;
        this.professor = professor;
        this.quantidadeAulas = quantidadeAulas;
    }

    public static Disciplina of(String descricao, Professor professor,
                                int quantidadeAulas) {
        Objects.requireNonNull(descricao);
        Objects.requireNonNull(professor);
        if (quantidadeAulas < 2) {
            throw new IllegalArgumentException("Quantidade de aulas" +
                    " deve ser igual ou superior a 2 aulas");
        }
        return new Disciplina(descricao, professor, quantidadeAulas);
    }
}
