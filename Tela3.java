
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.SwingConstants;

public class Tela3 {

	private JFrame Tela3;
	private JTextField nomePaciente;
	private JTextField dataNascimento;
	private JTable tbDados;
	private Statement statement = null;
	private ResultSet resultset = null;
	ConexaoBD bancoDeDados = new ConexaoBD();
	private Connection connection;
	private JTextField tfBusca;
	private JTextField tfId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela3 window = new Tela3();
					window.Tela3.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public Tela3() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Tela3 = new JFrame();
		Tela3.getContentPane().setBackground(new Color(255, 192, 203));
		Tela3.setTitle("Tela3");
		Tela3.setBounds(100, 100, 660, 415);
		Tela3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tela3.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insira os dados do paciente:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(133, 19, 257, 26);
		Tela3.getContentPane().add(lblNewLabel);
		
		nomePaciente = new JTextField();
		nomePaciente.setBounds(193, 99, 230, 20);
		Tela3.getContentPane().add(nomePaciente);
		nomePaciente.setColumns(10);
		
		dataNascimento = new JTextField();
		dataNascimento.setBounds(193, 129, 230, 20);
		Tela3.getContentPane().add(dataNascimento);
		dataNascimento.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(137, 102, 46, 14);
		Tela3.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Data de Nascimento:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(57, 131, 128, 14);
		Tela3.getContentPane().add(lblNewLabel_2);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela3.setVisible(false);
				Tela1 window = new Tela1();
				window.getTela1().setVisible(true);
			}
		});
		
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVoltar.setBackground(Color.CYAN);
		btnVoltar.setBounds(10, 15, 74, 36);
		Tela3.getContentPane().add(btnVoltar);
		
		JButton btnCadastrarPaciente = new JButton("Cadastrar paciente");
		btnCadastrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nomePaciente.getText().equals("") || dataNascimento.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite o nome e data de nascimento para prosseguir com o cadastro.");
				}
				else {
				ConexaoBD bancoDeDados = new ConexaoBD();
				bancoDeDados.conectar();

				Paciente paciente = new Paciente();
				bancoDeDados.inserirPaciente(nomePaciente.getText(), dataNascimento.getText());
				tfId.setText("");
				nomePaciente.setText("");
				dataNascimento.setText("");
				JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso.");
				}
			}
		});
		btnCadastrarPaciente.setFont(new Font("Arial", Font.PLAIN, 12));
		btnCadastrarPaciente.setBackground(Color.CYAN);
		btnCadastrarPaciente.setBounds(438, 62, 151, 30);
		Tela3.getContentPane().add(btnCadastrarPaciente);
		
		JButton btnVerificarPacientes = new JButton("Listar pacientes");
		btnVerificarPacientes.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {				
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "Select * from paciente order by idPaciente";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = preparedStatement.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						modelo.addRow(new Object[]{rs.getString("idPaciente"), rs.getString("nomePaciente"), rs.getString("dataNascimento")});
					}
					rs.close();
					bancoDeDados.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVerificarPacientes.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVerificarPacientes.setBackground(Color.CYAN);
		btnVerificarPacientes.setBounds(470, 137, 119, 26);
		Tela3.getContentPane().add(btnVerificarPacientes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 170, 532, 123);
		Tela3.getContentPane().add(scrollPane);
		
		tbDados = new JTable();
		tbDados.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"idPaciente", "nomePaciente", "dataNascimento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(tbDados);
		
		JButton btnApagar_1 = new JButton("Apagar");
		btnApagar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum paciente selecionado! Selecione antes de apagar.");
				}
				else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "delete from paciente where idPaciente=?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, tfId.getText());
					preparedStatement.execute();
					preparedStatement.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Paciente excluído.");
					
					tfBusca.setText("");
					tfId.setText("");
					nomePaciente.setText("");
					dataNascimento.setText("");
					
					bancoDeDados.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				}
			}
		});
		btnApagar_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnApagar_1.setBackground(Color.CYAN);
		btnApagar_1.setBounds(26, 328, 107, 36);
		Tela3.getContentPane().add(btnApagar_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Digite o Id para selecionar:");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1_1.setBackground(Color.BLACK);
		lblNewLabel_2_1_1.setBounds(155, 328, 154, 14);
		Tela3.getContentPane().add(lblNewLabel_2_1_1);
		
		tfBusca = new JTextField();
		tfBusca.setColumns(10);
		tfBusca.setBounds(176, 345, 107, 19);
		Tela3.getContentPane().add(tfBusca);
		
		JButton btnApagar = new JButton("Selecionar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite o Id antes de selecionar.");
				}else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "select * from paciente where idPaciente like ?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, "%" + tfBusca.getText());
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						tfId.setText(rs.getString("idPaciente"));
						nomePaciente.setText(rs.getString("nomePaciente"));
						dataNascimento.setText(rs.getString("dataNascimento"));
						
					}
					
					tfBusca.setText("");
					preparedStatement.close();
					con.close();					
					bancoDeDados.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				}
			}
		});
		btnApagar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnApagar.setBackground(Color.CYAN);
		btnApagar.setBounds(319, 328, 119, 36);
		Tela3.getContentPane().add(btnApagar);
		
		JButton btnEditar = new JButton("Alterar dados");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum paciente selecionado! Selecione para alterar.");
				}
				else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "update paciente set nomePaciente=?, dataNascimento=? where idPaciente=?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, nomePaciente.getText());
					preparedStatement.setString(2, dataNascimento.getText());
					preparedStatement.setString(3, tfId.getText());

					preparedStatement.execute();
					preparedStatement.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dentista alterado.");
					
					tfBusca.setText("");
					tfId.setText("");
					nomePaciente.setText("");
					dataNascimento.setText("");
					
					bancoDeDados.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				}
			}
		});
		btnEditar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnEditar.setBackground(Color.CYAN);
		btnEditar.setBounds(448, 328, 119, 36);
		Tela3.getContentPane().add(btnEditar);
		
		tfId = new JTextField();
		tfId.setToolTipText("");
		tfId.setHorizontalAlignment(SwingConstants.LEFT);
		tfId.setEditable(false);
		tfId.setColumns(10);
		tfId.setBounds(193, 68, 124, 20);
		Tela3.getContentPane().add(tfId);
		
		JLabel lblNewLabel_2_1 = new JLabel("Id:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1.setBackground(Color.BLACK);
		lblNewLabel_2_1.setBounds(125, 70, 46, 14);
		Tela3.getContentPane().add(lblNewLabel_2_1);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfId.setText("");
				nomePaciente.setText("");
				dataNascimento.setText("");
			}
		});
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimpar.setBackground(Color.CYAN);
		btnLimpar.setBounds(470, 101, 119, 26);
		Tela3.getContentPane().add(btnLimpar);
	}

	public JFrame getTela3() {
		return Tela3;
	}

	public void setTela3(JFrame tela3) {
		Tela3 = tela3;
	}
	
	public void conectar() {
		String servidor = "jdbc:mysql://localhost:3306/consultorio?useTimezone=true&serverTimezone=UTC&useSSL=false";
		String usuario = "root";
		String senha = "";
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor,usuario,senha);
			this.statement = this.connection.createStatement();
		}catch (Exception e) {
		    System.out.println("Erro" + e.getMessage());
		}		
	}
	public boolean estaConectado() {
		if(this.connection != null) {
			return true;
		}
		else {
			return false;
		}		
	}
}

