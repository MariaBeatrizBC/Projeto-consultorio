
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class Tela4 {

	private JFrame Tela4;
	private JTextField tipoDeConsulta;
	private JTextField dataDaConsulta;
	private JTextField idDentista;
	private JTextField valorConsulta;
	private JTextField idPaciente;
	private JTable tbDadosConsulta;
	private JTextField tfBusca;
	ConexaoBD bancoDeDados = new ConexaoBD();
	private JTextField tfId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela4 window = new Tela4();
					window.Tela4.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Tela4 = new JFrame();
		Tela4.getContentPane().setBackground(new Color(255, 192, 203));
		Tela4.setTitle("Tela4");
		Tela4.setBounds(100, 100, 660, 415);
		Tela4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tela4.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Agendamento de consulta");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(94, 19, 459, 22);
		Tela4.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de consulta:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(41, 102, 102, 14);
		Tela4.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Data da consulta:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(41, 134, 96, 14);
		Tela4.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Valor:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(269, 134, 74, 14);
		Tela4.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Id do Dentista:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(267, 69, 102, 14);
		Tela4.getContentPane().add(lblNewLabel_5);
		
		tipoDeConsulta = new JTextField();
		tipoDeConsulta.setBounds(149, 100, 270, 20);
		Tela4.getContentPane().add(tipoDeConsulta);
		tipoDeConsulta.setColumns(10);
		
		dataDaConsulta = new JTextField();
		dataDaConsulta.setBounds(149, 132, 123, 20);
		Tela4.getContentPane().add(dataDaConsulta);
		dataDaConsulta.setColumns(10);
		
		idDentista = new JTextField();
		idDentista.setBounds(367, 70, 52, 20);
		Tela4.getContentPane().add(idDentista);
		idDentista.setColumns(10);
		
		valorConsulta = new JTextField();
		valorConsulta.setBounds(334, 132, 85, 20);
		Tela4.getContentPane().add(valorConsulta);
		valorConsulta.setColumns(10);
		
		JButton btnAgendarConsulta = new JButton("Agendar consulta");
		btnAgendarConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipoDeConsulta.getText().equals("") || dataDaConsulta.getText().equals("") ||  valorConsulta.getText().equals("") || idPaciente.getText().equals("") || idDentista.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite todos os dados para prosseguir com o agendamento.");
				}
				else {
				ConexaoBD bancoDeDados = new ConexaoBD();
				bancoDeDados.conectar();
				Consulta consulta = new Consulta();
				String preco = valorConsulta.getText();
	            // Converte o texto para double
	            double valor = Double.parseDouble(preco);
	            String texto = idPaciente.getText();
	            // Converte o texto para número inteiro
	            int idPac = Integer.parseInt(texto);
	            String texto2 = idDentista.getText();
	            int idDent = Integer.parseInt(texto2);
				bancoDeDados.agendarConsulta(dataDaConsulta.getText(), tipoDeConsulta.getText(), valor , idDent, idPac);
				
				JOptionPane.showMessageDialog(null, "Consulta agendada com sucesso.");
				
				tfId.setText("");
				idPaciente.setText("");
				idDentista.setText("");
				dataDaConsulta.setText("");
				tipoDeConsulta.setText("");
				valorConsulta.setText("");
				bancoDeDados.desconectar();
				}
			}
		});
		btnAgendarConsulta.setFont(new Font("Arial", Font.PLAIN, 12));
		btnAgendarConsulta.setBackground(Color.CYAN);
		btnAgendarConsulta.setBounds(463, 95, 150, 28);
		Tela4.getContentPane().add(btnAgendarConsulta);
		
		idPaciente = new JTextField();
		idPaciente.setColumns(10);
		idPaciente.setBounds(220, 70, 52, 20);
		Tela4.getContentPane().add(idPaciente);
		
		JLabel textIdPaciente = new JLabel("Id do Paciente:");
		textIdPaciente.setHorizontalAlignment(SwingConstants.CENTER);
		textIdPaciente.setFont(new Font("Arial", Font.PLAIN, 12));
		textIdPaciente.setBounds(119, 69, 102, 14);
		Tela4.getContentPane().add(textIdPaciente);
		
		JButton btnVoltar_1 = new JButton("Voltar");
		btnVoltar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela4.setVisible(false);
				Tela1 window = new Tela1();
				window.getTela1().setVisible(true);
			}
		});
		btnVoltar_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVoltar_1.setBackground(Color.CYAN);
		btnVoltar_1.setBounds(20, 13, 74, 36);
		Tela4.getContentPane().add(btnVoltar_1);
		
		JButton btnLimpar = new JButton("Limpar dados");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfId.setText("");
				idPaciente.setText("");
				idDentista.setText("");
				dataDaConsulta.setText("");
				tipoDeConsulta.setText("");
				valorConsulta.setText("");
			}
		});
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimpar.setBackground(Color.CYAN);
		btnLimpar.setBounds(463, 51, 150, 28);
		Tela4.getContentPane().add(btnLimpar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 181, 586, 130);
		Tela4.getContentPane().add(scrollPane);
		
		tbDadosConsulta = new JTable();
		tbDadosConsulta.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"idConsulta", "tipoConsulta", "DataConsulta", "valor", "idDentista", "idPaciente"
			}
		));
		scrollPane.setViewportView(tbDadosConsulta);
		
		JButton btnApagar_1 = new JButton("Apagar");
		btnApagar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhuma consulta selecionada! Selecione antes de apagar.");
				}
				else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "delete from consulta where idConsulta=?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, tfId.getText());
					preparedStatement.execute();
					preparedStatement.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dentista excluído.");
					
					tfBusca.setText("");
					tfId.setText("");
					tipoDeConsulta.setText("");
					dataDaConsulta.setText("");
					valorConsulta.setText("");
					idDentista.setText("");
					idPaciente.setText("");
					
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
		btnApagar_1.setBounds(41, 321, 107, 36);
		Tela4.getContentPane().add(btnApagar_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Digite o Id para selecionar:");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1_1.setBackground(Color.BLACK);
		lblNewLabel_2_1_1.setBounds(170, 321, 154, 14);
		Tela4.getContentPane().add(lblNewLabel_2_1_1);
		
		tfBusca = new JTextField();
		tfBusca.setColumns(10);
		tfBusca.setBounds(191, 338, 107, 19);
		Tela4.getContentPane().add(tfBusca);
		
		JButton btnApagar = new JButton("Selecionar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite o Id antes de selecionar.");
				}else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "select * from consulta where idConsulta like ?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, "%" + tfBusca.getText());
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						tfId.setText(rs.getString("idConsulta"));
						tipoDeConsulta.setText(rs.getString("tipoConsulta"));
						dataDaConsulta.setText(rs.getString("dataConsulta"));
						valorConsulta.setText(rs.getString("valor"));
						idDentista.setText(rs.getString("idDentista"));
						idPaciente.setText(rs.getString("idPaciente"));
						
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
		btnApagar.setBounds(334, 321, 119, 36);
		Tela4.getContentPane().add(btnApagar);
		
		JButton btnEditar = new JButton("Alterar dados");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhuma consulta selecionada! Selecione para alterar.");
				}
				else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "update consulta set tipoConsulta=?, dataConsulta=?, valor=?, idDentista=?, idPaciente=? where idConsulta=?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, tipoDeConsulta.getText());
					preparedStatement.setString(2, dataDaConsulta.getText());
					preparedStatement.setString(3, valorConsulta.getText());
					preparedStatement.setString(4, idDentista.getText());
					preparedStatement.setString(5, idPaciente.getText());
					preparedStatement.setString(6, tfId.getText());

					preparedStatement.execute();
					preparedStatement.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Consulta alterada.");
					
					tfBusca.setText("");
					tfId.setText("");
					tipoDeConsulta.setText("");
					dataDaConsulta.setText("");
					valorConsulta.setText("");
					idDentista.setText("");
					idPaciente.setText("");
					
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
		btnEditar.setBounds(463, 321, 119, 36);
		Tela4.getContentPane().add(btnEditar);
		
		JButton btnListarConsultas = new JButton("Listar consultas");
		btnListarConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "Select * from consulta order by idConsulta";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = preparedStatement.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDadosConsulta.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						modelo.addRow(new Object[]{rs.getString("idConsulta"), rs.getString("tipoConsulta"), rs.getString("dataConsulta"), rs.getString("valor"), rs.getString("idDentista"), rs.getString("idPaciente")});
					}
					rs.close();
					bancoDeDados.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnListarConsultas.setFont(new Font("Arial", Font.PLAIN, 12));
		btnListarConsultas.setBackground(Color.CYAN);
		btnListarConsultas.setBounds(463, 136, 150, 29);
		Tela4.getContentPane().add(btnListarConsultas);
		
		JLabel IdConsulta = new JLabel("Id:");
		IdConsulta.setHorizontalAlignment(SwingConstants.CENTER);
		IdConsulta.setFont(new Font("Arial", Font.PLAIN, 12));
		IdConsulta.setBounds(0, 69, 102, 14);
		Tela4.getContentPane().add(IdConsulta);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setColumns(10);
		tfId.setBounds(70, 67, 52, 20);
		Tela4.getContentPane().add(tfId);
	}

	public JFrame getTela5() {
		return Tela4;
	}

	public void setTela5(JFrame tela5) {
		Tela4 = tela5;
	}
}
