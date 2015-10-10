package Model;
import java.io.*;

/*
 * String ccc = "asdasdasd";
	        System.out.println(ccc);
	  	  	byte[] asd = ccc.getBytes();
	  	  String str = new String(asd, StandardCharsets.UTF_8);
	  	  System.out.println(str);
 */

public class FileUtility {
  static protected void trasferisci_a_byte_file_binario(DataInputStream src, DataOutputStream dest) throws IOException {
	  
    // ciclo di lettura da sorgente e scrittura su destinazione
    int buffer = 0;
    try {
      // esco dal ciclo se leggo un valore negativo -> EOF
      while ((buffer = src.read()) >= 0) {
        dest.write(buffer);System.out.println("Invio effettuato");
      }
      dest.flush();
    } catch (EOFException e) {
      System.out.println("Problemi, i seguenti: ");
      e.printStackTrace();
    }
  } // trasferisci_a_byte_file_binario

  
  static protected void trasferisci_N_byte_file_binario(DataInputStream src,  DataOutputStream dest, long daTrasferire) throws IOException {
    int cont = 0;
    // ciclo di lettura da sorgente e scrittura su destinazione
    int buffer = 0;
    try {
      // esco dal ciclo quando ho letto il numero di byte da trasferire
      while (cont < daTrasferire) {
        buffer = src.read();
        dest.write(buffer);
        cont++;
      }
      dest.flush();
      System.out.println("Byte trasferiti: " + cont);
    }
    // l'eccezione dovrebbe scattare solo se chi chiama la funzione ha impostato un numero sbagliato di byte da leggere
    catch (EOFException e) {
      System.out.println("Problemi, i seguenti: ");
      e.printStackTrace();
    }
  } // trasferisci_N_byte_file_binario
  
  static protected void trasferisci_N_byte_file_binario_fast(DataInputStream src,  DataOutputStream dest, int packetLung,long blocchi,int resto){
	    int cont = 0;
	    int somm=0;
	    // ciclo di lettura da sorgente e scrittura su destinazione
	    int buffer = 0;
	    byte[] bs = new byte[packetLung];System.out.println("Blocchi: "+blocchi+"  resto: "+resto+"\n\n");
	    try {
	      // esco dal ciclo quando ho letto il numero di byte da trasferire
	    	for(int i=0; i < blocchi; i++){
	    		cont=src.read(bs);
	    		dest.write(bs,0,cont);somm+=cont;
	    	}
	    	bs = new byte[resto];
	    	cont=src.read(bs);
	    	dest.write(bs,0,cont);
	    	somm+=cont;
	    	
	    	//System.out.println("restanti: "+cont);
	    	 
	      
	      dest.flush();
	      System.out.println("Byte trasferiti: " + somm + "\n\n");
	    }
	    // l'eccezione dovrebbe scattare solo se chi chiama la funzione ha impostato un numero sbagliato di byte da leggere
	    catch (EOFException e) {
	      System.out.println("Problemi, i seguenti: ");
	      e.printStackTrace();
	    }catch (Exception e) {
            System.out.println("Problemi nell'invio di FAST -----");
            e.printStackTrace();
            //socket.close();
            // il client esce in modo anomalo
            System.exit(3);
          }
	  } // trasferisci_N_byte_file_binario
  
}
