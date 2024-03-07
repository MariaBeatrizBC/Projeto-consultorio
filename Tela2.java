
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.SwingConstants;

public class Tela2 {

	JFrame Tela2;
	private JTextField nomeDentista;
	private JTextField especialidade;
	protected Object frame;
	protected Object getframe;
	private JTable tbDadosDentista;
	private JTextField tfId;
	private JTextField tfBusca;
	ConexaoBD bancoDeDados = new ConexaoBD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela2 window = new Tela2();
					window.getTela2().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTela2(new JFrame());
		getTela2().setTitle("Tela2");
		getTela2().setBounds(100, 100, 660, 415);
		getTela2().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getTela2().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Insira os dados do dentista:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(157, 17, 244, 29);
		getTela2().getContentPane().add(lblNewLabel);
		
		nomeDentista = new JTextField();
		nomeDentista.setToolTipText("");
		nomeDentista.setBounds(157, 100, 244, 20);
		getTela2().getContentPane().add(nomeDentista);
		nomeDentista.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nome:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBackground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(101, 103, 46, 14);
		getTela2().getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Especialidade:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(61, 133, 86, 14);
		getTela2().getContentPane().add(lblNewLabel_5);
		
		especialidade = new JTextField();
		especialidade.setBounds(157, 130, 244, 20);
		getTela2().getContentPane().add(especialidade);
		especialidade.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar dentista");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nomeDentista.getText().equals("") || especialidade.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite o nome e especialidade para prosseguir com o cadastro.");
				}
				else {
				ConexaoBD bancoDeDados = new ConexaoBD();
				bancoDeDados.conectar();
				
				Dentista dentista = new Dentista();
				bancoDeDados.inserirDentista(nomeDentista.getText(), especialidade.getText());
				tfId.setText("");
				nomeDentista.setText("");
				especialidade.setText("");
				JOptionPane.showMessageDialog(null, "Dentista cadastrado com sucesso.");
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(435, 59, 151, 29);
		getTela2().getContentPane().add(btnNewButton);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela2.setVisible(false);
				Tela1 window = new Tela1();
				window.getTela1().setVisible(true);
			}
		});
		btnVoltar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVoltar.setBackground(Color.CYAN);
		btnVoltar.setBounds(6, 10, 74, 36);
		Tela2.getContentPane().add(btnVoltar);
		
		JButton btnVerificarDentistas = new JButton("Listar dentistas");
		btnVerificarDentistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "Select * from dentista order by idDentista";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					ResultSet rs = preparedStatement.executeQuery();
					
					DefaultTableModel modelo = (DefaultTableModel) tbDadosDentista.getModel();
					modelo.setNumRows(0);
					
					while(rs.next()) {
						modelo.addRow(new Object[]{rs.getString("idDentista"), rs.getString("nomeDentista"), rs.getString("especialidade")});
					}
					rs.close();
					bancoDeDados.desconectar();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnVerificarDentistas.setFont(new Font("Arial", Font.PLAIN, 12));
		btnVerificarDentistas.setBackground(Color.CYAN);
		btnVerificarDentistas.setBounds(467, 141, 119, 29);
		Tela2.getContentPane().add(btnVerificarDentistas);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 256, 524, -106);
		Tela2.getContentPane().add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(45, 180, 541, 106);
		Tela2.getContentPane().add(scrollPane_1);
		
		tbDadosDentista = new JTable();
		tbDadosDentista.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"idDentista", "nomeDentista", "especialidade"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(tbDadosDentista);
		
		JButton btnApagar = new JButton("Selecionar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(tfBusca.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Digite o Id antes de selecionar.");
				}else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "select * from dentista where idDentista like ?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, "%" + tfBusca.getText());
					
					ResultSet rs = preparedStatement.executeQuery();
					
					while(rs.next()) {
						tfId.setText(rs.getString("idDentista"));
						nomeDentista.setText(rs.getString("nomeDentista"));
						especialidade.setText(rs.getString("especialidade"));
						
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
		btnApagar.setBounds(338, 314, 119, 36);
		Tela2.getContentPane().add(btnApagar);
		
		JButton btnEditar = new JButton("Alterar dados");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum dentista selecionado! Selecione para alterar.");
				}
				else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "update dentista set nomeDentista=?, especialidade=? where idDentista=?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, nomeDentista.getText());
					preparedStatement.setString(2, especialidade.getText());
					preparedStatement.setString(3, tfId.getText());

					preparedStatement.execute();
					preparedStatement.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dentista alterado.");
					
					tfBusca.setText("");
					tfId.setText("");
					nomeDentista.setText("");
					especialidade.setText("");
					
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
		btnEditar.setBounds(467, 314, 119, 36);
		Tela2.getContentPane().add(btnEditar);
		
		JButton btnApagar_1 = new JButton("Apagar");
		btnApagar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nenhum dentista selecionado! Selecione antes de apagar.");
				}
				else {
				try {
					Connection con = ConexaoBD.conectar();
					
					String query = "delete from dentista where idDentista=?";
					
					PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(query);
					
					preparedStatement.setString(1, tfId.getText());
					preparedStatement.execute();
					preparedStatement.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dentista excluído.");
					
					tfBusca.setText("");
					tfId.setText("");
					nomeDentista.setText("");
					especialidade.setText("");
					
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
		btnApagar_1.setBounds(45, 314, 107, 36);
		Tela2.getContentPane().add(btnApagar_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Id:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1.setBackground(Color.BLACK);
		lblNewLabel_2_1.setBounds(101, 70, 46, 14);
		Tela2.getContentPane().add(lblNewLabel_2_1);
		
		tfId = new JTextField();
		tfId.setHorizontalAlignment(SwingConstants.LEFT);
		tfId.setEditable(false);
		tfId.setToolTipText("");
		tfId.setColumns(10);
		tfId.setBounds(157, 67, 124, 20);
		Tela2.getContentPane().add(tfId);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Digite o Id para selecionar:");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_2_1_1.setBackground(Color.BLACK);
		lblNewLabel_2_1_1.setBounds(174, 314, 154, 14);
		Tela2.getContentPane().add(lblNewLabel_2_1_1);
		
		tfBusca = new JTextField();
		tfBusca.setBounds(195, 331, 107, 19);
		Tela2.getContentPane().add(tfBusca);
		tfBusca.setColumns(10);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfId.setText("");
				nomeDentista.setText("");
				especialidade.setText("");
				
			}
		});
		btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
		btnLimpar.setBackground(Color.CYAN);
		btnLimpar.setBounds(467, 99, 119, 29);
		Tela2.getContentPane().add(btnLimpar);
	}

	public JFrame getTela2() {
		return Tela2;
	}

	public void setTela2(JFrame tela2) {
		Tela2 = tela2;
		Tela2.getContentPane().setBackground(new Color(255, 192, 203));
	}
}
