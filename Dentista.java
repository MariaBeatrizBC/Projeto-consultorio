import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dentista {
	private int nextId = 0;
	private static int IdDentista;
	private String nomeDentista;
	private String especialidade;	
	private List<Consulta> consultasAgendadas;
	Paciente paciente;

	public Dentista() {
		this.IdDentista = nextId++;
		this.nomeDentista = null;
		this.especialidade = null;
		this.consultasAgendadas = new ArrayList<>();
	}
	
	public static int getIdDentista() {
		return IdDentista;
	}
	public void setIdDentista(int IdDentista) {
		this.IdDentista = IdDentista;
	}
	public String getNomeDentista() {
		return nomeDentista;
	}
	public void setNomeDentista(String nomeDentista) {
		this.nomeDentista = nomeDentista;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}
