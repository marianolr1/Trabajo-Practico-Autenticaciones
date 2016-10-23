package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class FormCliente extends JFrame {

	private JPanel contentPane;
	private JTextField tfClaveAdmin;
	private JTextField tfUsuario;
	private JTextField tfClaveUsu;
	private JTextField tfNuevaClave;
	private JTextField tfIpServidor;
	private JTextField tfPuerto;
	private JComboBox cbMensaje;
	private Controlador controlador;
	private JLabel lblUsuario;
	private JLabel lblClaveUsuario;
	private JLabel lblNuevaClave;
	private JLabel lblClaveAdmin;
	private JLabel lblMensaje;
	private JPanel pnlIzquierdo;
	private JButton btnConectar;
	private JButton btnEnviar;
	private JTextArea taMensaje;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JTextArea taConsola;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCliente frame = new FormCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormCliente() {
		setResizable(false);
		controlador=new Controlador(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1264, 659);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnlPadre = new JPanel();
		contentPane.add(pnlPadre, BorderLayout.CENTER);
		pnlPadre.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCabecera = new JPanel();
		pnlCabecera.setBackground(Color.DARK_GRAY);
		pnlPadre.add(pnlCabecera, BorderLayout.NORTH);
		
		cbMensaje = new JComboBox();
		cbMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.activarCampos();
			}
		});
		cbMensaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		cbMensaje.setModel(new DefaultComboBoxModel(new String[] {"ADD", "MODIFY", "REMOVE", "AUTHENTICATE", "LIST-USERS", "LIST-AUT"}));
		
		JLabel lblTipoDeMensaje = new JLabel("Tipo de Mensaje");
		lblTipoDeMensaje.setForeground(Color.WHITE);
		lblTipoDeMensaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblIpServidor = new JLabel("IP Servidor");
		lblIpServidor.setForeground(Color.WHITE);
		lblIpServidor.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tfIpServidor = new JTextField();
		tfIpServidor.setText("127.0.0.1");
		tfIpServidor.setBorder(null);
		tfIpServidor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfIpServidor.setColumns(10);
		
		JLabel lblPuerto = new JLabel("Puerto");
		lblPuerto.setForeground(Color.WHITE);
		lblPuerto.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tfPuerto = new JTextField();
		tfPuerto.setText("5050");
		tfPuerto.setBorder(null);
		tfPuerto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPuerto.setColumns(10);
		GroupLayout gl_pnlCabecera = new GroupLayout(pnlCabecera);
		gl_pnlCabecera.setHorizontalGroup(
			gl_pnlCabecera.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCabecera.createSequentialGroup()
					.addGap(58)
					.addComponent(lblTipoDeMensaje, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(cbMensaje, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(lblIpServidor)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfIpServidor, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addGap(48)
					.addComponent(lblPuerto)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfPuerto, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(362, Short.MAX_VALUE))
		);
		gl_pnlCabecera.setVerticalGroup(
			gl_pnlCabecera.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCabecera.createSequentialGroup()
					.addContainerGap(19, Short.MAX_VALUE)
					.addGroup(gl_pnlCabecera.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbMensaje, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTipoDeMensaje)
						.addComponent(lblIpServidor)
						.addComponent(tfIpServidor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPuerto)
						.addComponent(tfPuerto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		pnlCabecera.setLayout(gl_pnlCabecera);
		
		pnlIzquierdo = new JPanel();
		pnlIzquierdo.setBackground(Color.DARK_GRAY);
		pnlPadre.add(pnlIzquierdo, BorderLayout.WEST);
		
		lblClaveAdmin = new JLabel("Clave Admin");
		lblClaveAdmin.setForeground(Color.WHITE);
		lblClaveAdmin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tfClaveAdmin = new JTextField();
		tfClaveAdmin.setText("1111");
		tfClaveAdmin.setForeground(Color.DARK_GRAY);
		tfClaveAdmin.setBorder(null);
		tfClaveAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfClaveAdmin.setColumns(10);
		
		tfUsuario = new JTextField();
		tfUsuario.setBorder(null);
		tfUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfUsuario.setColumns(10);
		
		tfClaveUsu = new JTextField();
		tfClaveUsu.setBorder(null);
		tfClaveUsu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfClaveUsu.setColumns(10);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblClaveUsuario = new JLabel("Clave Usuario");
		lblClaveUsuario.setForeground(Color.WHITE);
		lblClaveUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblNuevaClave = new JLabel("Nueva Clave");
		lblNuevaClave.setForeground(Color.WHITE);
		lblNuevaClave.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		tfNuevaClave = new JTextField();
		tfNuevaClave.setBorder(null);
		tfNuevaClave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfNuevaClave.setColumns(10);
		
		JButton btnGenerarMensaje = new JButton("Generar Mensaje");
		btnGenerarMensaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.generarMensaje();
			}
		});
		btnGenerarMensaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_pnlIzquierdo = new GroupLayout(pnlIzquierdo);
		gl_pnlIzquierdo.setHorizontalGroup(
			gl_pnlIzquierdo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlIzquierdo.createSequentialGroup()
					.addContainerGap(48, Short.MAX_VALUE)
					.addGroup(gl_pnlIzquierdo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlIzquierdo.createSequentialGroup()
							.addGroup(gl_pnlIzquierdo.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnlIzquierdo.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNuevaClave)
									.addComponent(lblClaveUsuario)
									.addGroup(gl_pnlIzquierdo.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_pnlIzquierdo.createParallelGroup(Alignment.LEADING)
											.addComponent(lblUsuario)
											.addComponent(tfClaveAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblClaveAdmin))
										.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(tfClaveUsu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addComponent(tfNuevaClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(41))
						.addGroup(Alignment.TRAILING, gl_pnlIzquierdo.createSequentialGroup()
							.addComponent(btnGenerarMensaje)
							.addContainerGap())))
		);
		gl_pnlIzquierdo.setVerticalGroup(
			gl_pnlIzquierdo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlIzquierdo.createSequentialGroup()
					.addGap(40)
					.addComponent(lblClaveAdmin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfClaveAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(lblUsuario)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(lblClaveUsuario)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfClaveUsu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(lblNuevaClave)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(tfNuevaClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
					.addComponent(btnGenerarMensaje, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(77))
		);
		pnlIzquierdo.setLayout(gl_pnlIzquierdo);
		
		JPanel pnlCuerpo = new JPanel();
		pnlCuerpo.setBackground(Color.DARK_GRAY);
		pnlPadre.add(pnlCuerpo, BorderLayout.CENTER);
		
		JLabel lblConsola = new JLabel("Consola");
		lblConsola.setForeground(Color.WHITE);
		lblConsola.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblMensaje = new JLabel("Mensaje");
		lblMensaje.setForeground(Color.WHITE);
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (controlador.conectar(tfIpServidor.getText(),Integer.parseInt(tfPuerto.getText()))) {
					taConsola.append("Conexion establecida \n");
				}
				
			}
		});
		btnConectar.setPreferredSize(new Dimension(67, 25));
		btnConectar.setSize(new Dimension(67, 25));
		btnConectar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taConsola=controlador.enviarMensaje(taMensaje.getText(),taConsola);
				
			}
		});
		btnEnviar.setSize(new Dimension(67, 25));
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		scrollPane = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		GroupLayout gl_pnlCuerpo = new GroupLayout(pnlCuerpo);
		gl_pnlCuerpo.setHorizontalGroup(
			gl_pnlCuerpo.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_pnlCuerpo.createSequentialGroup()
					.addGap(80)
					.addGroup(gl_pnlCuerpo.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_pnlCuerpo.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 933, GroupLayout.PREFERRED_SIZE))
					.addGap(26))
				.addGroup(gl_pnlCuerpo.createSequentialGroup()
					.addContainerGap(511, Short.MAX_VALUE)
					.addComponent(lblConsola)
					.addGap(469))
				.addGroup(gl_pnlCuerpo.createSequentialGroup()
					.addContainerGap(508, Short.MAX_VALUE)
					.addComponent(lblMensaje)
					.addGap(474))
				.addGroup(gl_pnlCuerpo.createSequentialGroup()
					.addContainerGap(398, Short.MAX_VALUE)
					.addComponent(btnConectar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(91)
					.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(348))
		);
		gl_pnlCuerpo.setVerticalGroup(
			gl_pnlCuerpo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCuerpo.createSequentialGroup()
					.addGap(17)
					.addComponent(lblMensaje)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblConsola)
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(45)
					.addGroup(gl_pnlCuerpo.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnConectar, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEnviar, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(267, Short.MAX_VALUE))
		);
		
		taConsola = new JTextArea();
		scrollPane_1.setViewportView(taConsola);
		taConsola.setEditable(false);
		
		taMensaje = new JTextArea();
		taMensaje.setEditable(false);
		scrollPane.setViewportView(taMensaje);
		pnlCuerpo.setLayout(gl_pnlCuerpo);
		controlador.activarCampos();
	}

	public JTextField getTfClaveAdmin() {
		return tfClaveAdmin;
	}

	public void setTfClaveAdmin(JTextField tfClaveAdmin) {
		this.tfClaveAdmin = tfClaveAdmin;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(JTextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}

	public JTextField getTfClaveUsu() {
		return tfClaveUsu;
	}

	public void setTfClaveUsu(JTextField tfClaveUsu) {
		this.tfClaveUsu = tfClaveUsu;
	}

	public JTextField getTfNuevaClave() {
		return tfNuevaClave;
	}

	public void setTfNuevaClave(JTextField tfNuevaClave) {
		this.tfNuevaClave = tfNuevaClave;
	}

	public JTextField getTfIpServidor() {
		return tfIpServidor;
	}

	public void setTfIpServidor(JTextField tfIpServidor) {
		this.tfIpServidor = tfIpServidor;
	}

	public JTextField getTfPuerto() {
		return tfPuerto;
	}

	public void setTfPuerto(JTextField tfPuerto) {
		this.tfPuerto = tfPuerto;
	}


	public JComboBox getCbMensaje() {
		return cbMensaje;
	}

	public void setCbMensaje(JComboBox cbMensaje) {
		this.cbMensaje = cbMensaje;
	}

	public JLabel getLblUsuario() {
		return lblUsuario;
	}

	public void setLblUsuario(JLabel lblUsuario) {
		this.lblUsuario = lblUsuario;
	}

	public JLabel getLblClaveUsuario() {
		return lblClaveUsuario;
	}

	public void setLblClaveUsuario(JLabel lblClaveUsuario) {
		this.lblClaveUsuario = lblClaveUsuario;
	}

	public JLabel getLblNuevaClave() {
		return lblNuevaClave;
	}

	public void setLblNuevaClave(JLabel lblNuevaClave) {
		this.lblNuevaClave = lblNuevaClave;
	}

	public JLabel getLblClaveAdmin() {
		return lblClaveAdmin;
	}

	public void setLblClaveAdmin(JLabel lblClaveAdmin) {
		this.lblClaveAdmin = lblClaveAdmin;
	}

	public JPanel getPnlIzquierdo() {
		return pnlIzquierdo;
	}

	public void setPnlIzquierdo(JPanel pnlIzquierdo) {
		this.pnlIzquierdo = pnlIzquierdo;
	}

	public JTextArea getTaMensaje() {
		return taMensaje;
	}

	public void setTaMensaje(JTextArea taMensaje) {
		this.taMensaje = taMensaje;
	}
	
 /*
		  *  tfClaveAdmin;
			 tfUsuario;
			 tfClaveUsu;
			 tfNuevaClave;
			 tfIpServidor;
		 	 tfPuerto;
			 
	*/
	
}
