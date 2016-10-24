package cliente;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Controlador {

	private FormCliente ventana;
	private Cliente cliente;
	private String mensaje;
	
	public Controlador(FormCliente ventana) {
		this.ventana=ventana;
	}
	
	public void activarCampos(){
		ventana.getTfClaveAdmin().setBackground(ventana.getLblUsuario().getForeground());
		ventana.getTfUsuario().setVisible(false);
		ventana.getTfClaveUsu().setVisible(false);
		ventana.getTfNuevaClave().setVisible(false);
		
		//ventana.getLblClaveAdmin().setVisible(false);
		ventana.getLblClaveUsuario().setVisible(false);
		ventana.getLblNuevaClave().setVisible(false);
		ventana.getLblUsuario().setVisible(false);
		ventana.getLblClaveAdmin().setForeground(ventana.getLblUsuario().getForeground());
		
		switch (ventana.getCbMensaje().getSelectedItem().toString()) {
		case "ADD":
			ventana.getTfClaveAdmin().setVisible(true);
			ventana.getTfUsuario().setVisible(true);
			ventana.getTfClaveUsu().setVisible(true);
			ventana.getLblClaveAdmin().setVisible(true);
			ventana.getLblClaveUsuario().setVisible(true);
			ventana.getLblUsuario().setVisible(true);
			break;

		case "MODIFY":
			ventana.getTfUsuario().setVisible(true);
			ventana.getTfClaveUsu().setVisible(true);
			ventana.getTfNuevaClave().setVisible(true);
			ventana.getLblClaveUsuario().setVisible(true);
			ventana.getLblClaveAdmin().setForeground(ventana.getPnlIzquierdo().getBackground());
			ventana.getTfClaveAdmin().setBackground(ventana.getPnlIzquierdo().getBackground());
			ventana.getLblNuevaClave().setVisible(true);
			ventana.getLblUsuario().setVisible(true);
			break;
			
		case "REMOVE":
			ventana.getTfUsuario().setVisible(true);
			ventana.getTfClaveAdmin().setVisible(true);
			ventana.getLblClaveAdmin().setVisible(true);
			ventana.getLblUsuario().setVisible(true);
			
			break;
		
		case "AUTHENTICATE":
			ventana.getTfUsuario().setVisible(true);
			ventana.getTfClaveUsu().setVisible(true);
			ventana.getLblUsuario().setVisible(true);
			ventana.getLblClaveUsuario().setVisible(true);
			ventana.getLblClaveAdmin().setForeground(ventana.getPnlIzquierdo().getBackground());
			ventana.getTfClaveAdmin().setBackground(ventana.getPnlIzquierdo().getBackground());
			break;
			
		case "LIST-USERS":
			ventana.getTfClaveAdmin().setVisible(true);
			ventana.getLblClaveAdmin().setVisible(true);
			break;
			
		case "LIST-AUT":
			ventana.getTfClaveAdmin().setVisible(true);
			ventana.getTfUsuario().setVisible(true);
			ventana.getLblUsuario().setVisible(true);
			ventana.getLblClaveAdmin().setVisible(true);
			break;
			
		default:
			break;
		}
		
	}
	public void generarMensaje(){
		switch (ventana.getCbMensaje().getSelectedItem().toString()) {
		case "ADD":
			if (ventana.getTfClaveAdmin().getText().equals("") || ventana.getTfUsuario().getText().equals("") || ventana.getTfClaveUsu().getText().equals("")) {
				JOptionPane.showMessageDialog(ventana,"Completar campos","Atención!",JOptionPane.WARNING_MESSAGE);
			}else {
				mensaje="<MESSAGE TYPE='ADD'><USERNAME>"+ventana.getTfUsuario().getText()+"</USERNAME><PASSWORD>"+ventana.getTfClaveUsu().getText()+"</PASSWORD><ADM-PASS>"+ventana.getTfClaveAdmin().getText()+"</ADM-PASS></MESSAGE>";
			
			}

			
			break;

		case "MODIFY":
			if (ventana.getTfUsuario().getText().equals("") || ventana.getTfClaveUsu().getText().equals("") || ventana.getTfNuevaClave().getText().equals("")) {
				JOptionPane.showMessageDialog(ventana,"Completar campos","Atención!",JOptionPane.WARNING_MESSAGE);
			}else {
				mensaje="<MESSAGE TYPE='MODIFY'><USERNAME>"+ventana.getTfUsuario().getText()+"</USERNAME><PASSWORD>"+ventana.getTfClaveUsu().getText()+"</PASSWORD><NEW-PASS>"+ventana.getTfNuevaClave().getText()+"</NEW-PASS></MESSAGE>";
			}
		
			break;
			
		case "REMOVE":
			if (ventana.getTfUsuario().getText().equals("") || ventana.getTfClaveAdmin().getText().equals("") ) {
				JOptionPane.showMessageDialog(ventana,"Completar campos","Atención!",JOptionPane.WARNING_MESSAGE);
			}else {
				mensaje="<MESSAGE TYPE='REMOVE'><USERNAME>"+ventana.getTfUsuario().getText()+"</USERNAME><ADM-PASS>"+ventana.getTfClaveAdmin().getText()+"</ADM-PASS></MESSAGE>";
			}
	
			break;
		
		case "AUTHENTICATE":
			if (ventana.getTfUsuario().getText().equals("") || ventana.getTfClaveUsu().getText().equals("") ) {
				JOptionPane.showMessageDialog(ventana,"Completar campos","Atención!",JOptionPane.WARNING_MESSAGE);
			}else {
				mensaje="<MESSAGE TYPE='AUTHENTICATE'><USERNAME>"+ventana.getTfUsuario().getText()+"</USERNAME><PASSWORD>"+ventana.getTfClaveUsu().getText()+"</PASSWORD></MESSAGE>";
			}
			
			break;
			
		case "LIST-USERS":
			if (ventana.getTfClaveAdmin().getText().equals("")) {
				JOptionPane.showMessageDialog(ventana,"Completar campos","Atención!",JOptionPane.WARNING_MESSAGE);
			}else {
				mensaje="<MESSAGE TYPE='LIST-USERS'><ADM-PASS>"+ventana.getTfClaveAdmin().getText()+"</ADM-PASS></MESSAGE>";
			}
			
			break;
			
		case "LIST-AUT":
			if (ventana.getTfUsuario().getText().equals("") || ventana.getTfClaveAdmin().getText().equals("") ) {
				JOptionPane.showMessageDialog(ventana,"Completar campos","Atención!",JOptionPane.WARNING_MESSAGE);
			}else {
				mensaje="<MESSAGE TYPE='LIST-AUT'><USERNAME>"+ventana.getTfUsuario().getText()+"</USERNAME><ADM-PASS>"+ventana.getTfClaveAdmin().getText()+"</ADM-PASS></MESSAGE>";
			}
		
			break;
			
		default:
			break;
		}
		ventana.getTaMensaje().setText(mensaje);
		
	}
	public boolean conectar(String ip, int puerto){
		if (cliente==null) {
			cliente = new Cliente();  
		
		}
		if (cliente.creaSocket(ip, puerto)) {
			return true;
		} else {
			return false;
		}

		//return cliente!=null;
        
	}
	public JTextArea enviarMensaje(String mensaje, JTextArea taConsola){
		JTextArea taCons=null;
		if (ventana.getTfPuerto().getText().equals("") || ventana.getTfIpServidor().getText().equals("") ) {
			JOptionPane.showMessageDialog(ventana,"Completar campos Ip Servidor, Puerto","Atención!",JOptionPane.WARNING_MESSAGE);
		}else {
			if (cliente!=null) {
				taCons=cliente.enviaMensaje(mensaje,taConsola);
			}else {
				taCons=taConsola;
			}
		}
		return taCons;
	}

}
