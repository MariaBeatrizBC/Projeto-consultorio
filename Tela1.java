
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Tela1 {

	JFrame Tela1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela1 window = new Tela1();
					window.Tela1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Tela1 = new JFrame();
		Tela1.setBackground(new Color(0, 128, 128));
		Tela1.getContentPane().setBackground(new Color(255, 192, 203));
		Tela1.setTitle("Tela1");
		Tela1.setBounds(100, 100, 620, 365);
		Tela1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tela1.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seja bem-vindo(a) ao Odonto Patty!");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(130, 72, 356, 59);
		Tela1.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Cadastrar Dentista");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela2 window = new Tela2();
				window.getTela2().setVisible(true);
				Tela1.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton.setBounds(40, 193, 169, 23);
		Tela1.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cadastrar Paciente");
		btnNewButton_1.setBackground(new Color(0, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela3 window = new Tela3();
				window.getTela3().setVisible(true);
				Tela1.setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_1.setBounds(219, 193, 175, 23);
		Tela1.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Agendar Consulta");
		btnNewButton_2.setBackground(new Color(0, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tela4 window = new Tela4();
				window.getTela5().setVisible(true);
				Tela1.setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_2.setBounds(404, 193, 154, 23);
		Tela1.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Sair");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_4.setBackground(new Color(0, 255, 255));
		btnNewButton_4.setFont(new Font("Arial", Font.PLAIN, 12));
		btnNewButton_4.setBounds(219, 236, 175, 23);
		Tela1.getContentPane().add(btnNewButton_4);
	}

	public JFrame getTela1() {
		return Tela1;
	}

	public void setTela1(JFrame tela1) {
		Tela1 = tela1;
	}

}
