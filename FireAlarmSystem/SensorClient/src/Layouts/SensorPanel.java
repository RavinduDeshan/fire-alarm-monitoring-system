/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Layouts;

import Models.Sensor;
import Services.SensorClientServiceImpl;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author GRP
 */
public class SensorPanel extends javax.swing.JFrame {

    SensorClientServiceImpl service = new SensorClientServiceImpl();

    //Set Timer to detectDeactivation
    Timer timer;

    //Set Timer to detect AutoUpdate
    Timer timer2;

    //Declare a Sensor Object
    Sensor sensor;

    //Sensor ID Property
    String SensorID;

    /**
     * Creates new form CustomerReg
     */
    public SensorPanel() {
        initComponents();

        //avoid unauthorized access
        dispose();
        JPanel pane5 = new JPanel();
        JOptionPane.showMessageDialog(pane5, "Unauthorised Access. Please Activate", "ByPassed Attempt", JOptionPane.ERROR_MESSAGE);

        System.exit(-1);
    }

    public SensorPanel(Sensor s1) {

        initComponents();

        //set object
        sensor = s1;

        //set Jframe labels value according to the Sensor object properties
        setDetails(sensor);

        //Start detect Deactivations
        detectDeactiation();

        //start autoUpdate
        AutoUpdate();

        //set Sensor ID
        this.SensorID = sensor.getId();

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
        seconds = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        seconds1 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        ID = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        room = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        co2 = new javax.swing.JComboBox<>();
        smoke = new javax.swing.JComboBox<>();
        floor = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sensor Panel");
        setAlwaysOnTop(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setLayout(null);

        seconds.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        seconds.setForeground(new java.awt.Color(102, 255, 255));
        seconds.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        seconds.setText("Seconds");
        seconds.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        seconds.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(seconds);
        seconds.setBounds(180, 270, 60, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fire Alarm Monitoring System");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(20, 240, 190, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Sensor Panel");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 200, 240, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Update Sensor  In :");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel6);
        jLabel6.setBounds(10, 270, 140, 20);

        seconds1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        seconds1.setForeground(new java.awt.Color(102, 255, 255));
        seconds1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        seconds1.setText("10");
        seconds1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        seconds1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(seconds1);
        seconds1.setBounds(150, 270, 30, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/85751-digital-background-1920x1080-4k.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(-150, -30, 420, 870);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Sensor ID");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("CO2 Level");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Smoke Level");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 0, 204));
        jLabel24.setText("* Set These Fields.");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 0, 204));
        jLabel21.setText("*");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 0, 204));
        jLabel22.setText("*");

        ID.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        ID.setForeground(new java.awt.Color(51, 153, 255));
        ID.setText("ID");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 153, 255));
        jLabel27.setText("Room:");

        room.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        room.setForeground(new java.awt.Color(255, 153, 255));
        room.setText("Room");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 153, 255));
        jLabel28.setText("Floor:");

        co2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        co2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        co2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                co2ActionPerformed(evt);
            }
        });

        smoke.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        smoke.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        floor.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        floor.setForeground(new java.awt.Color(255, 153, 255));
        floor.setText("Room");

        jButton2.setBackground(new java.awt.Color(204, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Deactivate");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel17))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(co2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(90, 90, 90))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel15)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel21)
                                            .addGap(137, 137, 137)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(138, 138, 138)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel22))
                                        .addComponent(smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(floor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(co2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(smoke, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel28)
                                    .addComponent(floor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(room))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel22))))
                        .addGap(32, 32, 32)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24)))
                .addGap(155, 155, 155)
                .addComponent(jLabel17)
                .addGap(176, 176, 176))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(270, -50, 470, 360);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Even Trigger when JFRAME closing
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

        //stop repeting timer
        timer.setRepeats(false);
        //stop timer , timer 2               
        timer.stop();
        timer2.stop();

        try {

            //set Sensor Status "Inactive"
            service.upadteStateInActive(sensor);

        } catch (SQLException ex) {
            JPanel pane5 = new JPanel();
            JOptionPane.showMessageDialog(pane5, "Sensor Update Error Occured.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_formWindowClosed

    //Deactivate Sensor
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        //stop repeting timer
        timer.setRepeats(false);

        //stop timer , timer 2 
        timer.stop();
        timer2.stop();

        try {
            //set Sensor Status "Inactive"
            service.upadteStateInActive(sensor);
        } catch (SQLException ex) {
            JPanel pane5 = new JPanel();
            JOptionPane.showMessageDialog(pane5, "Sensor Update Error Occured.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        dispose();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void co2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_co2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_co2ActionPerformed

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
            java.util.logging.Logger.getLogger(SensorPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SensorPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SensorPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SensorPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SensorPanel().setVisible(true);
            }
        });
    }

    //Set Details into the Jframe lables
    public void setDetails(Sensor sensor) {

        //set ID
        ID.setText(sensor.getId());
        //Set Room No
        room.setText(sensor.getLocation_roomNo());
        //set Floor No
        floor.setText(sensor.getLocation_floorNo());

        //set Co2 Level
        if (sensor.getCo2_level().equals("1")) {
            co2.setSelectedIndex(0);
        } else if (sensor.getCo2_level().equals("2")) {
            co2.setSelectedIndex(1);

        } else if (sensor.getCo2_level().equals("3")) {
            co2.setSelectedIndex(2);

        } else if (sensor.getCo2_level().equals("4")) {
            co2.setSelectedIndex(3);

        } else if (sensor.getCo2_level().equals("5")) {
            co2.setSelectedIndex(4);

        } else if (sensor.getCo2_level().equals("6")) {
            co2.setSelectedIndex(5);

        } else if (sensor.getCo2_level().equals("7")) {
            co2.setSelectedIndex(6);

        } else if (sensor.getCo2_level().equals("8")) {
            co2.setSelectedIndex(7);

        } else if (sensor.getCo2_level().equals("9")) {
            co2.setSelectedIndex(8);

        } else if (sensor.getCo2_level().equals("10")) {
            co2.setSelectedIndex(9);

        }

        //Set Smoke Level
        if (sensor.getSmoke_level().equals("1")) {
            smoke.setSelectedIndex(0);
        } else if (sensor.getSmoke_level().equals("2")) {
            smoke.setSelectedIndex(1);
        } else if (sensor.getSmoke_level().equals("3")) {
            smoke.setSelectedIndex(2);
        } else if (sensor.getSmoke_level().equals("4")) {
            smoke.setSelectedIndex(3);
        } else if (sensor.getSmoke_level().equals("5")) {
            smoke.setSelectedIndex(4);
        } else if (sensor.getSmoke_level().equals("6")) {
            smoke.setSelectedIndex(5);
        } else if (sensor.getSmoke_level().equals("7")) {
            smoke.setSelectedIndex(6);
        } else if (sensor.getSmoke_level().equals("8")) {
            smoke.setSelectedIndex(7);
        } else if (sensor.getSmoke_level().equals("9")) {
            smoke.setSelectedIndex(8);
        } else if (sensor.getSmoke_level().equals("10")) {
            smoke.setSelectedIndex(9);

        }

    }

    //Detect if Admin Deactivted the Sensor by Admin Panel
    public void detectDeactiation() {

        //initiate timer    
        timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                try {
                    //Check if sensor is inActive
                    if (service.checkTimerInActive(sensor.getId())) {

                        //Stop Timer
                        timer.setRepeats(false);

                        timer.stop();

                        //diplay error dialog
                        JPanel pane5 = new JPanel();
                        JOptionPane.showMessageDialog(pane5, "Sensor ID " + sensor.getId() + "  is Deactivated by Admin.", "Manual Deactivation Occurred ", JOptionPane.ERROR_MESSAGE);

                        dispose();

                    }

                    //catch exceptions
                } catch (SQLException ex) {
                    Logger.getLogger(SensorPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        //set Timer prperties
        timer.setRepeats(true); // Only execute once
        timer.start();
    }

    //Auto update Sensor Details after 10 seconds
    public void AutoUpdate() {

        //initiate timer                     
        timer2 = new Timer(1000, new ActionListener() {

            //Time to auto Update in seconds
            int initial = 10;

            //Counter
            int c = 0;

            @Override
            public void actionPerformed(ActionEvent arg0) {

                //Check counter equals to iniatial time
                if (c == initial) {

                    c = 0;
                }
                //increment counter
                c++;

                //seconds remaining
                int timeup = initial - c;

                //Set Seconds value to Display in the jframe Label
                seconds1.setText(String.valueOf((timeup)));

                try {

                    //Check 10 seconds past
                    if (timeup == 0) {

                        //update sensor details in the database through REST API
                        service.upadteSensor(sensor, co2.getSelectedItem().toString(), smoke.getSelectedItem().toString());
                    }

                    //catch exceptions
                } catch (SQLException ex) {
                    Logger.getLogger(SensorPanel.class.getName()).log(Level.SEVERE, null, ex);

                    JPanel pane5 = new JPanel();
                    JOptionPane.showMessageDialog(pane5, "Sensor Update Error Occured.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    Logger.getLogger(SensorPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        //set Timer Properties
        timer2.setRepeats(true); // Only execute once
        timer2.start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JComboBox<String> co2;
    private javax.swing.JLabel floor;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel room;
    private javax.swing.JLabel seconds;
    private javax.swing.JLabel seconds1;
    private javax.swing.JComboBox<String> smoke;
    // End of variables declaration//GEN-END:variables
}
