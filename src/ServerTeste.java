
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author adston
 */
public class ServerTeste {
    public static void main(String[] args) throws IOException{
            
        ServerSocket srvSocket = new ServerSocket(5566);
        System.out.println("Aguardando envio de arquivo ...");
        Socket socket = srvSocket.accept();
         
        //2
        byte[] objectAsByte = new byte[socket.getReceiveBufferSize()];
        BufferedInputStream bf = new BufferedInputStream(
        socket.getInputStream());
        bf.read(objectAsByte);
         
        //3
        Arquivo arquivo = (Arquivo) getObjectFromByte(objectAsByte);
         
        //4
        String dir = arquivo.getDiretorioDestino().endsWith("/") ? arquivo
           .getDiretorioDestino() + arquivo.getNome() : arquivo
           .getDiretorioDestino() + "/" + arquivo.getNome();
        System.out.println("Escrevendo arquivo " + dir);
 
        //5
        FileOutputStream fos = new FileOutputStream(dir);
        fos.write(arquivo.getConteudo());
        fos.close();
 
        // TODO Auto-generated catch block
     }
 
   
 
   private static Object getObjectFromByte(byte[] objectAsByte) {
     Object obj = null;
     ByteArrayInputStream bis = null;
     ObjectInputStream ois = null;
     try {
        bis = new ByteArrayInputStream(objectAsByte);
        ois = new ObjectInputStream(bis);
        obj = ois.readObject();
 
        bis.close();
        ois.close();
 
     } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
     } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();           
     }                 
      
     return obj;
              
   }
}

