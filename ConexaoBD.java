import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

public class ConexaoBD {
	private static Connection connection = null;
	private static Statement statement = null;
	private ResultSet resultset = null;
	
	public static Connection conectar() {
		String servidor = "jdbc:mysql://localhost:3306/consultorio?useTimezone=true&serverTimezone=UTC&useSSL=false";
		String usuario = "root";
		String senha = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(servidor,usuario,senha);
			statement = connection.createStatement();
		}catch (Exception e) {
		    System.out.println("Erro" + e.getMessage());
		}
		return connection;		
	}
	public boolean estaConectado() {
		if(this.connection != null) {
			return true;
		}
		else {
			return false;
		}		
	}
	//Dentistas
	public void inserirDentista(String nome, String especialidade) {
			try {
				int idDentista = Dentista.getIdDentista();
				String query = "insert into Dentista (idDentista, nomeDentista, especialidade) values ('" + idDentista + "', '"+ nome+"','"+ especialidade +"')";
				//System.out.println(query);
				this.statement.executeUpdate(query);
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
	}	
	
	//Pacientes
	public void inserirPaciente(String nome, String dataNasc) {
		try {
			int idPaciente = Paciente.getIdPaciente();
			String query = "insert into Paciente (idPaciente, nomePaciente, dataNascimento) values ('" + idPaciente + "', '"+ nome+"','"+ dataNasc +"')";
			//System.out.println(query);
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}	
	public void agendarConsulta(String dataConsulta, String tipoConsulta, double valor, int idDent, int idPac) {
		try {
			int idConsulta = Consulta.getIdConsulta();
			String query = "insert into Consulta (idConsulta, dataConsulta, tipoConsulta, valor, idDentista, idPaciente) values ('" + idConsulta + "', '"+ dataConsulta +"','"+ tipoConsulta +"','"+ valor +"','"+ idDent +"','"+ idPac + "')";
			//System.out.println(query);
			this.statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	public void alterarConsulta(int id, String dataConsulta, String tipo, double preco, int idDent, int idPac) {
		try {
	        String query = "update Consulta set dataConsulta = ?, tipo = ?, preco = ?, idDentista = ?, idPaciente = ? where idConsulta = ?";
	        PreparedStatement preparedStatement = (PreparedStatement) this.connection.prepareStatement(query);
	        preparedStatement.setString(1, dataConsulta);
	        preparedStatement.setString(2, tipo);
	        preparedStatement.setDouble(3, preco);
	        preparedStatement.setDouble(4, idDent);
	        preparedStatement.setDouble(5, idPac);
	        preparedStatement.setInt(6, id);

	        //System.out.println(query);
	        preparedStatement.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("Erro: " + e.getMessage());
	    }
	}
	public void desconectar() {
		try {
			this.connection.close();
		}catch(Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
	
}
