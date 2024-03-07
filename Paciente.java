import java.time.LocalDate;
import java.time.Period;

public class Paciente {
	private int proximoId = 0;
	private static int idPaciente;
    private String nomePaciente;
    private LocalDate dataNascimento;
    Consulta consulta;
    Dentista dentista;
    
    public Paciente() {
    	this.idPaciente = proximoId++;
        this.nomePaciente = null;
        this.dataNascimento = null;
    }

    public static int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}




