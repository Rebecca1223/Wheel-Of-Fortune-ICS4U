/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acceb
 */
public class Test extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public Test() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlIntro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnInstructions = new javax.swing.JButton();
        btnGame = new javax.swing.JButton();
        pnlInstructions = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        pnlGame = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnBack2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Intro page");

        btnInstructions.setText("Instructions");
        btnInstructions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInstructionsActionPerformed(evt);
            }
        });

        btnGame.setText("Game");
        btnGame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlIntroLayout = new javax.swing.GroupLayout(pnlIntro);
        pnlIntro.setLayout(pnlIntroLayout);
        pnlIntroLayout.setHorizontalGroup(
            pnlIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIntroLayout.createSequentialGroup()
                .addContainerGap(146, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(142, 142, 142))
            .addGroup(pnlIntroLayout.createSequentialGroup()
                .addGroup(pnlIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlIntroLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(btnGame))
                    .addGroup(pnlIntroLayout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(btnInstructions)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlIntroLayout.setVerticalGroup(
            pnlIntroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIntroLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(87, 87, 87)
                .addComponent(btnInstructions)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGame)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        getContentPane().add(pnlIntro, "card2");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Instructions");

        btnBack1.setText("Main Menu");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlInstructionsLayout = new javax.swing.GroupLayout(pnlInstructions);
        pnlInstructions.setLayout(pnlInstructionsLayout);
        pnlInstructionsLayout.setHorizontalGroup(
            pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstructionsLayout.createSequentialGroup()
                .addGroup(pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlInstructionsLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(jLabel2))
                    .addGroup(pnlInstructionsLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(btnBack1)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        pnlInstructionsLayout.setVerticalGroup(
            pnlInstructionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInstructionsLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addGap(86, 86, 86)
                .addComponent(btnBack1)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        getContentPane().add(pnlInstructions, "card3");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Game");

        btnBack2.setText("Main Menu");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGameLayout = new javax.swing.GroupLayout(pnlGame);
        pnlGame.setLayout(pnlGameLayout);
        pnlGameLayout.setHorizontalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addGroup(pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack2)
                    .addComponent(jLabel3))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        pnlGameLayout.setVerticalGroup(
            pnlGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(73, 73, 73)
                .addComponent(btnBack2)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        getContentPane().add(pnlGame, "card4");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInstructionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInstructionsActionPerformed
        // TODO add your handling code here:
        pnlInstructions.setVisible(true);
        pnlGame.setVisible(false);
        pnlIntro.setVisible(false);
    }//GEN-LAST:event_btnInstructionsActionPerformed

    private void btnGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGameActionPerformed
        // TODO add your handling code here:
        pnlGame.setVisible(true);
        pnlInstructions.setVisible(false);
        pnlIntro.setVisible(false);
    }//GEN-LAST:event_btnGameActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
        pnlIntro.setVisible(true);
        pnlGame.setVisible(false);
        pnlInstructions.setVisible(false);
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        // TODO add your handling code here:
        pnlIntro.setVisible(true);
        pnlGame.setVisible(false);
        pnlInstructions.setVisible(false);
    }//GEN-LAST:event_btnBack2ActionPerformed

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
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnGame;
    private javax.swing.JButton btnInstructions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel pnlGame;
    private javax.swing.JPanel pnlInstructions;
    private javax.swing.JPanel pnlIntro;
    // End of variables declaration//GEN-END:variables
}
