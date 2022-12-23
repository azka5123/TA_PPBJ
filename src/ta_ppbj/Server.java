/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ta_ppbj;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author azka
 */
public class Server extends javax.swing.JFrame {
    String clientids="server";
     DataInputStream din;
     DataOutputStream dout;
    ServerSocket ss;
    HashMap clientColl = new HashMap();

    /**
     * Creates new form Server
     */
    public Server() {
        try {
            initComponents();
            ss = new ServerSocket(5123);
            this.jLabel2.setText("Server Started");
            new ClientAccept().start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class ClientAccept extends Thread {

        public void run() {
            while (true) {
                try {
                    Socket s = ss.accept();
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    if (clientColl.containsKey(i)) {
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("Sudah Terdaftar");
                    } else {
                        clientColl.put(i, s);
                        jTextArea1.append(i + " joined !\n");
                        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                        dout.writeUTF("");
                        new msgRead(s, i).start();
                        new prepareClientList().start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class msgRead extends Thread {

        Socket s;
        String ID;

        msgRead(Socket s, String ID) {
            this.s = s;
            this.ID = ID;
        }

        public void run() {
            while (!clientColl.isEmpty()) {
                try {
                    String i = new DataInputStream(s.getInputStream()).readUTF();
                    if (i.equals("ifgawigbahifwbyKAJSBFKJBq982aslfh")) {
                        clientColl.remove(ID);
                        jTextArea1.append(ID + ": removed! \n");
                        new prepareClientList().start();
                        Set k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while (itr.hasNext()) {
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("");
                                } catch (Exception e) {
                                    clientColl.remove(key);
                                    jTextArea1.append(key + ": removed!");
                                    new prepareClientList().start();
                                }
                            } 
                        }
                    }else if (i.contains("#4343434343@@@@325@@")) {
                                i = i.substring(20);
                                StringTokenizer st = new StringTokenizer(i, ":");
                                String id = st.nextToken();
                                i = st.nextToken();
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(id)).getOutputStream()).writeUTF("< " + ID + " ke "+id+" > " + i);
                                } catch (Exception e) {
                                    clientColl.remove(id);
                                    jTextArea1.append(id + ": removed!");
                                    new prepareClientList().start();
                                }
                            }else{
                        Set k = clientColl.keySet();
                        Iterator itr = k.iterator();
                        while(itr.hasNext()){
                            String key = (String) itr.next();
                            if (!key.equalsIgnoreCase(ID)) {
                                try {
                                    new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF("< " + ID + " ke semuanya > " + i);
                                } catch (Exception e) {
                                    clientColl.remove(key);
                                    jTextArea1.append(key + ": removed!");
                                    new prepareClientList().start();
                                }
                            } 
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class prepareClientList extends Thread {

        public void run() {
            try {
                String ids = "";
                Set k = clientColl.keySet();
                Iterator itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    ids += key + ",";
                }
                if (ids.length() != 0) {
                    ids = ids.substring(0, ids.length() - 1);
                }
                itr = k.iterator();
                while (itr.hasNext()) {
                    String key = (String) itr.next();
                    try {
                        new DataOutputStream(((Socket) clientColl.get(key)).getOutputStream()).writeUTF(":;.,/=" + ids);
                    } catch (Exception e) {
                        clientColl.remove(key);
                        jTextArea1.append(key + ": removed!");                    
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));

        jLabel1.setText("Status : ");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel2.setText("..........................................");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Server.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
