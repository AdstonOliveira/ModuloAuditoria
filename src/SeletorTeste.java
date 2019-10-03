
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SeletorTeste extends javax.swing.JFrame {
    /**
     * Creates new form seletor
     */
    public SeletorTeste() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldDestino = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldPorta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDiretorio = new javax.swing.JTextField();
        labelTamanho = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Arquivo:");

        jButton1.setText("Selecionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("I.P :");

        jTextFieldDestino.setText("localhost");

        jLabel3.setText("Porta:");

        jTextFieldPorta.setText("5566");

        jLabel4.setText("Destino:");

        jTextFieldDiretorio.setText("C:\\");

            labelTamanho.setText("Tamanho: ");

            jButton2.setText("Enviar");
            jButton2.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton2ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldDestino)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldPorta)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldDiretorio))
                        .addComponent(labelTamanho)
                        .addComponent(jButton2))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton1)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextFieldDiretorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(labelTamanho)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents
    Arquivo arquivo;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser jfc = new JFileChooser();
        if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            File fileSelected = jfc.getSelectedFile();
           
            byte[] bFile = new byte[(int) fileSelected.length()];
            FileInputStream fis;
            try {
                fis = new FileInputStream(fileSelected);
                fis.read(bFile);
                fis.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SeletorTeste.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SeletorTeste.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            long kbSize = fileSelected.length() / 1024;
            labelTamanho.setText(kbSize + " KB");
           
          arquivo = new Arquivo();
          arquivo.setConteudo(bFile);
          arquivo.setDataHoraUpload(new Date());
          arquivo.setNome(fileSelected.getName());
          arquivo.setTamanhoKB(kbSize);
          arquivo.setIpDestino(jTextFieldDestino.getText());
          arquivo.setPortaDestino(jTextFieldPorta.getText());
         arquivo.setDiretorioDestino(jTextFieldDiretorio.getText().trim());
      }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        enviarArquivoServidor();
    }//GEN-LAST:event_jButton2ActionPerformed
private void enviarArquivoServidor(){
   if (validaArquivo()){
    try {
        Socket socket = new Socket(jTextFieldDestino.getText().trim(),
          Integer.parseInt(jTextFieldPorta.getText().trim()));
 
        BufferedOutputStream bf = new BufferedOutputStream
        (socket.getOutputStream());
 
        byte[] bytea = serializarArquivo();
        bf.write(bytea);
        bf.flush();
        bf.close();
        socket.close();
    } catch (UnknownHostException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
   }
}
 
private byte[] serializarArquivo(){
   try {
      ByteArrayOutputStream bao = new ByteArrayOutputStream();
      ObjectOutputStream ous;
      ous = new ObjectOutputStream(bao);
      ous.writeObject(arquivo);
      return bao.toByteArray();
   } catch (IOException e) {
      e.printStackTrace();
   }
 
   return null;
}
 
private boolean validaArquivo(){
        long tamanhoPermitidoKB = 5024;
   if (arquivo.getTamanhoKB() > tamanhoPermitidoKB){
      JOptionPane.showMessageDialog(this,
       "Tamanho máximo permitido atingido ("+(tamanhoPermitidoKB/1024)+")");
      return false;
   }else{
      return true;
   }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SeletorTeste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeletorTeste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeletorTeste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeletorTeste.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SeletorTeste().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldDestino;
    private javax.swing.JTextField jTextFieldDiretorio;
    private javax.swing.JTextField jTextFieldPorta;
    private javax.swing.JLabel labelTamanho;
    // End of variables declaration//GEN-END:variables
}
