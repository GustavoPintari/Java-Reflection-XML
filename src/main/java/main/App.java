package main;

import java.math.BigDecimal;

import br.edu.fema.domain.model.Aluno;
import br.edu.fema.domain.model.Curso;
import br.edu.fema.domain.model.Disciplina;
import br.edu.fema.domain.model.Professor;
import br.edu.fema.framework.utils.XmlUtils;

public class App {

	public static void main(String[] args) {
		final Curso bcc = Curso.of("BCC", "Ciência da Computação");
		//final Curso ads = Curso.of("ADS", "Análise e Desenv. de Sistemas");

		final Professor guilherme = Professor.of(1, "Guilherme", "Me");
		final Professor alex = Professor.of(2, "Alex", "Dr");

		final Disciplina topicosAvancados = Disciplina.of("Tópicos Avançados", guilherme, 4);
		final Disciplina bancosDadosI = Disciplina.of("Bancos de Dados I", alex, 6);

		final Aluno joao = Aluno.of("100", "João", bcc);

		joao.adicionarDisciplina(topicosAvancados)
			.adicionarDisciplina(bancosDadosI)
			.adicionarNotas(topicosAvancados,
					BigDecimal.valueOf(7), BigDecimal.valueOf(9.6), BigDecimal.valueOf(3.4))
			.adicionarNotas(bancosDadosI,
					BigDecimal.valueOf(4.8), BigDecimal.valueOf(3.6), BigDecimal.valueOf(5))
			.calcularMedias();


		final String xml = XmlUtils.toXml(joao);
		System.out.println(xml);
	}
}
