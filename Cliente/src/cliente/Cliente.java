package cliente;



import java.net.*;

import javax.swing.JTextArea;

import java.io.*;

public class Cliente {
	private String mensaje;
	private Socket socket;

	public Cliente(/*String ip, int puerto*/){
		//this.mensaje=mensaje;
		//creaSocket(ip,puerto);
	}
    
    public void creaSocket(String ip,int puerto) {
        
//	        BufferedReader in2;
        
        try {
            socket = new Socket(ip,puerto);
            
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JTextArea enviaMensaje(String mensaje,JTextArea taConsola){
    	if (socket!=null) {
    		try {
    			PrintWriter out;
                BufferedReader in;
            	out = new PrintWriter(socket.getOutputStream(),true);
            	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            	//mensaje="<MESSAGE TYPE='AUTHENTICATE'><USERNAME>mcortinez</USERNAME><PASSWORD>1111</PASSWORD></MESSAGE>";
            	out.println(mensaje);
            	String strLinea;
            	//for (int i = 0; i < in.lines().count() ;i++) {
            	//	strLinea=in.readLine();
            	//	taConsola.append(strLinea+"\n");
				//}
            	while ((strLinea=in.readLine())!=null) {
            		taConsola.append(strLinea+"\n");
            		//System.out.println(strLinea);
            		String aa="";
            		if (strLinea.equals("")) {
						break;
					}
            		//if (str) {
						
					//}
        		
            	}
            	
            	out.close();
            	in.close();
            	
            	//socket.close();
            	
			} catch (SocketException e) {
				taConsola.append("Debe conectarse nuevamente \n");
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
    		//return taConsola;
    		
		}else {
			taConsola.append("Error de Socket \n");
		}
    	
    	return taConsola;
    	
//          out.writeUTF("<MESSAGE TYPE='LIST-AUT'><USERNAME>yuyo</USERNAME><ADM-PASS>1234</ADM-PASS></MESSAGE>");
//        out.writeUTF("<MESSAGE TYPE='ADD'><USERNAME>prueba</USERNAME><PASSWORD>1234</PASSWORD><ADM-PASS>1234</ADM-PASS></MESSAGE>");
//          out.writeUTF("<MESSAGE TYPE='AUTHENTICATE'><USERNAME>jolpi</USERNAME><PASSWORD>1234</PASSWORD></MESSAGE>");
//        out.writeUTF("<MESSAGE TYPE='REMOVE'><USERNAME>prueba</USERNAME><ADM-PASS>1234</ADM-PASS></MESSAGE>");
//        out.writeUTF("<MESSAGE TYPE='MODIFY'><USERNAME>yuyo</USERNAME><PASSWORD>1234</PASSWORD><NEW-PASS>1233</NEW-PASS></MESSAGE>");
//        System.out.println(in2.readUTF());
    	
    }
}



