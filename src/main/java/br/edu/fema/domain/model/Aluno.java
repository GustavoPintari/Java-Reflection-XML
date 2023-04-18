package br.edu.fema.domain.model;

import br.edu.fema.framework.annotations.XmlElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@XmlElement("aluno")
public class Aluno {
	@XmlElement
	private String ra;
	@XmlElement
	private String nome;
	@XmlElement
	private Curso curso;
	@XmlElement
	private boolean matriculado;
	@XmlElement
	private List<Disciplina> disciplinas;
	@XmlElement(fieldAttribute = "descricao", itemsName = "nota")
	private Map<Disciplina, List<BigDecimal>> notas;

	@XmlElement(fieldAttribute = "descricao")
	private Map<Disciplina, BigDecimal> medias;

	@XmlElement(dateFormat="dd/MM/yyyy")
	private Date dataMatricula;

	public Aluno(String ra, String nome, Curso curso) {

		Calendar cal = Calendar.getInstance();

		this.ra = ra;
		this.nome = nome;
		this.curso = curso;
		this.setDataMatricula(cal.getTime());
		this.matriculado = true;
		this.setDataMatricula(new Date());
		this.disciplinas = new LinkedList<>();
		this.notas = new LinkedHashMap<>();
		this.medias = new LinkedHashMap<>();
	}

	public static Aluno of(String ra, String nome, Curso curso) {
		Objects.requireNonNull(ra);
		Objects.requireNonNull(nome);
		Objects.requireNonNull(curso);
		return new Aluno(ra, nome, curso);
	}

	public Aluno adicionarDisciplina(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
		return this;
	}

	public void calcularMedias() {
		for (Disciplina disciplina : notas.keySet()) {
			List<BigDecimal> notasDisciplina = notas.get(disciplina);
			BigDecimal soma = BigDecimal.ZERO;
			for (BigDecimal nota : notasDisciplina) {
				soma = soma.add(nota);
			}
			BigDecimal media = soma.divide(BigDecimal.valueOf(notasDisciplina.size()), RoundingMode.HALF_UP);
			this.medias.put(disciplina, media);
		}
	}

	public Map<Disciplina, BigDecimal> getMedias() {
		return medias;
	}

	public Aluno adicionarNotas(Disciplina disciplina, BigDecimal... notas) {
		List<BigDecimal> list = Arrays.asList(notas);
		this.notas.put(disciplina, list);
		return this;
	}

	public Map<Disciplina, List<BigDecimal>> getNotas() {
		return notas;
	}

	public Date getDataMatricula() {
		return dataMatricula;
	}

	public void setDataMatricula(Date dataMatricula) {
		this.dataMatricula = dataMatricula;
	}
}
