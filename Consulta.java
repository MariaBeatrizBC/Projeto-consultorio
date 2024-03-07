public class Consulta {
	private int proximoId = 0;
	private static int idConsulta;
	private String dataConsulta;
	private String tipo;
    private double preco;
    private String dentistaAssociado;
    private String pacienteAssociado;

	public Consulta() {
    	this.idConsulta = proximoId++;
        this.tipo = " ";
        this.preco = 0;
        this.dataConsulta = "00/00/0000";
    }
    
	public static int getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public String getDentistaAssociado() {
		return dentistaAssociado;
	}
	public void setDentistaAssociado(String dentistaAssociado) {
		this.dentistaAssociado = dentistaAssociado;
	}
	public String getPacienteAssociado() {
		return pacienteAssociado;
	}
	public void setPacienteAssociado(String pacienteAssociado) {
		this.pacienteAssociado = pacienteAssociado;
	}

	public String toString() {
        return "Consulta " + idConsulta + "|" + "Dentista: " + dentistaAssociado + ", Paciente: " + pacienteAssociado + ", Tipo: " + tipo + ", Preço da Consulta: " + preco + ", Data da Consulta: " + dataConsulta + "|";
    }
}



