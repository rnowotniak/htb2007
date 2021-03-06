/*
 * ScriptJFrame.java
 *
 * Created on 19 styczeń 2008, 23:30
 */

package htb.gui;

/**
 *
 * @author  rob
 */
public class ScriptJFrame extends javax.swing.JFrame {
    
    /** Creates new form ScriptJFrame */
    public ScriptJFrame() {
        this("");
    }
    
    public ScriptJFrame(String str) {
        initComponents();
        scriptJTextArea.setText(str);
        scriptJTextArea.setCaretPosition(0);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeJButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        scriptJTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HTB Script");

        closeJButton.setText("Close");
        closeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeJButtonActionPerformed(evt);
            }
        });

        scriptJTextArea.setColumns(20);
        scriptJTextArea.setFont(new java.awt.Font("Monospaced", 0, 11));
        scriptJTextArea.setRows(5);
        scriptJTextArea.setText("#!/bin/bash\n#\n# HTB Traffic Shaping script\n#\n# This script was automatically created with generator on page:\n#      http://robert.nowotniak.com/htb/\n#\n\ntc qdisc del root dev $(OUT_IFACE) 2> /dev/null\ntc qdisc del root dev $(IN_IFACE) 2> /dev/null\n\n...\n\n\ntc qdisc del root dev $(OUT_IFACE) 2> /dev/null\ntc qdisc del root dev $(IN_IFACE) 2> /dev/null\n\nfasd\nsdf"); // NOI18N
        jScrollPane1.setViewportView(scriptJTextArea);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .add(closeJButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(closeJButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeJButtonActionPerformed
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeJButtonActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeJButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea scriptJTextArea;
    // End of variables declaration//GEN-END:variables
    
}
