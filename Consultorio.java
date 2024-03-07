
public class Consultorio {

	public static void main(String[] args) {
		
		ConexaoBD bancoDeDados = new ConexaoBD();
		bancoDeDados.conectar();
		if(bancoDeDados.estaConectado()) {
			System.out.println("Conectado");
			Dentista dentista = new Dentista();	
			Paciente paciente = new Paciente();
			bancoDeDados.desconectar();
		}
		else {
			System.out.println("Não foi possível conectar");
		}	
	}
}
