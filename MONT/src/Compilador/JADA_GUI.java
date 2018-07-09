package Compilador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import org.jw.menage.ui.components.TextLineNumber;

/**
 *
 * @author DeusArcana
 */
public class JADA_GUI extends javax.swing.JFrame {
    String Tokens="";
    String Error="";
    public JADA_GUI() {
        super("Proyecto MONT");
        initComponents();
        UIManager.put("TabbedPane.selectedBackground", Color.magenta);
        
        TextLineNumber tln = new TextLineNumber(CodeTextPanel);
        int red = 66;
        int green = 66;
        int blue = 66;
        Color myBlue = new Color(red,green,blue);
        
        int red2 = 103;
        int green2 = 58;
        int blue2 = 183;
        Color myMorado = new Color(red2,green2,blue2);
        
        tln.setBackground(myBlue);
        tln.setCurrentLineForeground(myMorado);
        tln.setForeground(Color.white);
        jsCode.setRowHeaderView( tln );

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jsCode = new javax.swing.JScrollPane();
        CodeTextPanel = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tokensTextPane = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtResulSintactico = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        errorTextPane = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtErrorSintactico = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenuItem = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        openMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        rechargeMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        propertiesMenuItem = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenuItem = new javax.swing.JMenu();
        undoMenuItem = new javax.swing.JMenuItem();
        redoMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        copyMenuItem = new javax.swing.JMenuItem();
        cutMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        commandMenu = new javax.swing.JMenu();
        copyActualLinesMenuItem = new javax.swing.JMenuItem();
        cutActualLinesMenuItem = new javax.swing.JMenuItem();
        deleteActualLinesMenuItem = new javax.swing.JMenuItem();
        duplicateActualLinesMenuItem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        selectActualLinesMenuItem = new javax.swing.JMenuItem();
        selectActualParraphMenuItem = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        moveUpActualLinesMenuItem = new javax.swing.JMenuItem();
        moveDownActualLinesMenuItem = new javax.swing.JMenuItem();
        formatMenu = new javax.swing.JMenu();
        commentLinesMenuItem = new javax.swing.JMenuItem();
        decommentLinesMenuItem = new javax.swing.JMenuItem();
        commentDecommentLinesMenuItem = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        incrementIndentationMenuItem = new javax.swing.JMenuItem();
        decrementIndentationMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(33, 33, 33));
        setExtendedState(MAXIMIZED_BOTH);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jButton1.setBackground(new java.awt.Color(106, 27, 154));
        jButton1.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Compilar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        CodeTextPanel.setBackground(new java.awt.Color(66, 66, 66));
        CodeTextPanel.setFont(new java.awt.Font("Century Gothic", 3, 16)); // NOI18N
        CodeTextPanel.setForeground(new java.awt.Color(255, 255, 255));
        CodeTextPanel.setCaretColor(new java.awt.Color(255, 255, 255));
        jsCode.setViewportView(CodeTextPanel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsCode)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jsCode, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(33, 33, 33));

        jTabbedPane1.setBackground(new java.awt.Color(66, 66, 66));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        tokensTextPane.setBackground(new java.awt.Color(66, 66, 66));
        tokensTextPane.setColumns(20);
        tokensTextPane.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        tokensTextPane.setForeground(new java.awt.Color(255, 255, 255));
        tokensTextPane.setRows(5);
        jScrollPane4.setViewportView(tokensTextPane);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Resultado Lexico", jPanel5);

        txtResulSintactico.setBackground(new java.awt.Color(66, 66, 66));
        txtResulSintactico.setColumns(20);
        txtResulSintactico.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        txtResulSintactico.setForeground(new java.awt.Color(255, 255, 255));
        txtResulSintactico.setRows(5);
        jScrollPane1.setViewportView(txtResulSintactico);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Resultado Sintactico", jPanel6);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jPanel4.setBackground(new java.awt.Color(33, 33, 33));

        jTabbedPane2.setBackground(new java.awt.Color(66, 66, 66));
        jTabbedPane2.setForeground(new java.awt.Color(255, 255, 255));

        errorTextPane.setBackground(new java.awt.Color(66, 66, 66));
        errorTextPane.setColumns(20);
        errorTextPane.setRows(5);
        jScrollPane3.setViewportView(errorTextPane);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Errores Lexicos", jPanel7);

        txtErrorSintactico.setBackground(new java.awt.Color(66, 66, 66));
        txtErrorSintactico.setColumns(20);
        txtErrorSintactico.setFont(new java.awt.Font("Century Gothic", 3, 14)); // NOI18N
        txtErrorSintactico.setForeground(new java.awt.Color(255, 255, 255));
        txtErrorSintactico.setRows(5);
        jScrollPane5.setViewportView(txtErrorSintactico);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Errores Sintacticos", jPanel8);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );

        menuBar.setBackground(new java.awt.Color(33, 33, 33));

        fileMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        fileMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        fileMenuItem.setText("Archivo");

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        newMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        newMenuItem.setText("Nuevo");
        fileMenuItem.add(newMenuItem);
        fileMenuItem.add(jSeparator1);

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        openMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        openMenuItem.setText("Abrir");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenuItem.add(openMenuItem);
        fileMenuItem.add(jSeparator2);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        saveMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        saveMenuItem.setText("Guardar");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenuItem.add(saveMenuItem);

        saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        saveAsMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        saveAsMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        saveAsMenuItem.setText("Guardar como...");
        fileMenuItem.add(saveAsMenuItem);

        rechargeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        rechargeMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        rechargeMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        rechargeMenuItem.setText("Recargar");
        fileMenuItem.add(rechargeMenuItem);
        fileMenuItem.add(jSeparator3);

        propertiesMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        propertiesMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        propertiesMenuItem.setText("Propiedades");
        fileMenuItem.add(propertiesMenuItem);
        fileMenuItem.add(jSeparator4);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        exitMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        exitMenuItem.setText("Salir");
        fileMenuItem.add(exitMenuItem);

        menuBar.add(fileMenuItem);

        editMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        editMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        editMenuItem.setText("Editar");

        undoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        undoMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        undoMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        undoMenuItem.setText("Deshacer");
        editMenuItem.add(undoMenuItem);

        redoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        redoMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        redoMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        redoMenuItem.setText("Rehacer");
        editMenuItem.add(redoMenuItem);
        editMenuItem.add(jSeparator5);

        copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        copyMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        copyMenuItem.setText(" Copiar");
        editMenuItem.add(copyMenuItem);

        cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        cutMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        cutMenuItem.setText("Cortar");
        editMenuItem.add(cutMenuItem);

        pasteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        pasteMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        pasteMenuItem.setText("Pegar");
        editMenuItem.add(pasteMenuItem);

        deleteMenuItem.setBackground(new java.awt.Color(33, 33, 33));
        deleteMenuItem.setForeground(new java.awt.Color(255, 255, 255));
        deleteMenuItem.setText("Eliminar");
        editMenuItem.add(deleteMenuItem);
        editMenuItem.add(jSeparator6);

        commandMenu.setBackground(new java.awt.Color(33, 33, 33));
        commandMenu.setForeground(new java.awt.Color(255, 255, 255));
        commandMenu.setText("Comandos");

        copyActualLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        copyActualLinesMenuItem.setText("Copiar lineas actuales");
        commandMenu.add(copyActualLinesMenuItem);

        cutActualLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        cutActualLinesMenuItem.setText("Cortar lineas actuales");
        commandMenu.add(cutActualLinesMenuItem);

        deleteActualLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        deleteActualLinesMenuItem.setText("Borrar lineas actuales");
        commandMenu.add(deleteActualLinesMenuItem);

        duplicateActualLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        duplicateActualLinesMenuItem.setText("Duplicar lineas o selección");
        commandMenu.add(duplicateActualLinesMenuItem);
        commandMenu.add(jSeparator7);

        selectActualLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        selectActualLinesMenuItem.setText("Seleccionar lineas actuales");
        commandMenu.add(selectActualLinesMenuItem);

        selectActualParraphMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        selectActualParraphMenuItem.setText("Seleccionar parrafo actual");
        commandMenu.add(selectActualParraphMenuItem);
        commandMenu.add(jSeparator8);

        moveUpActualLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_UP, java.awt.event.InputEvent.ALT_MASK));
        moveUpActualLinesMenuItem.setText("Mover linea(s) hacia arriba");
        commandMenu.add(moveUpActualLinesMenuItem);

        moveDownActualLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, java.awt.event.InputEvent.ALT_MASK));
        moveDownActualLinesMenuItem.setText("Mover linea(s) hacia abajo");
        commandMenu.add(moveDownActualLinesMenuItem);

        editMenuItem.add(commandMenu);

        formatMenu.setBackground(new java.awt.Color(33, 33, 33));
        formatMenu.setForeground(new java.awt.Color(255, 255, 255));
        formatMenu.setText("Formato");

        commentLinesMenuItem.setText("Comentar lineas");
        formatMenu.add(commentLinesMenuItem);

        decommentLinesMenuItem.setText("Descomentar lineas");
        formatMenu.add(decommentLinesMenuItem);

        commentDecommentLinesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        commentDecommentLinesMenuItem.setText("Comentar / Descomentar lineas");
        formatMenu.add(commentDecommentLinesMenuItem);
        formatMenu.add(jSeparator9);

        incrementIndentationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        incrementIndentationMenuItem.setText("Incrementar sangría");
        formatMenu.add(incrementIndentationMenuItem);

        decrementIndentationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        decrementIndentationMenuItem.setText("Decrementar sangría");
        formatMenu.add(decrementIndentationMenuItem);

        editMenuItem.add(formatMenu);

        menuBar.add(editMenuItem);

        helpMenu.setBackground(new java.awt.Color(33, 33, 33));
        helpMenu.setForeground(new java.awt.Color(255, 255, 255));
        helpMenu.setText("Ayuda");
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void guardarArchivo() {
         try
         {
          String nombre="";
          JFileChooser file=new JFileChooser();
          file.showSaveDialog(this);
          File guarda =file.getSelectedFile();

          if(guarda !=null)
          {
           /*guardamos el archivo y le damos el formato directamente,
            * si queremos que se guarde en formato doc lo definimos como .doc*/
            FileWriter  save=new FileWriter(guarda+".jada");
            save.write(CodeTextPanel.getText());
            save.close();
            showMessageDialog(null,"El archivo se a guardado Exitosamente");
            }
         }
          catch(IOException ex)
          {
           showMessageDialog(null,"Su archivo no se ha guardado");
          }
    }
    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
       String texto="";
    JFileChooser sa=new JFileChooser();
    sa.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    int res=sa.showOpenDialog(this);
    File archivo=sa.getSelectedFile();
    if(archivo==null || archivo.getName().equals("")){
        showMessageDialog(this,"archivo no valido");
    }
    try{
        FileReader fr=new FileReader(archivo);
        BufferedReader br=new BufferedReader(fr);
        
        String str;
        
        
        while((str=br.readLine())!=null){
            texto+=str+"\n";

        }
        br.close();
    }catch(FileNotFoundException e){
        showMessageDialog(this, "archivo no enconrado");
    }catch(IOException e1){
        showMessageDialog(this, "archivo no enconrado");
    }
    CodeTextPanel.setText(texto);
    
   
    
         // TODO add your handling code here:
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        guardarArchivo();        // TODO add your handling code here:
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                 try {
            Analisis ar = new Analisis();
            ar.probarLexerFile((JTextPane) CodeTextPanel);
            for(int i=0;i<ar.tam();i++){
            Tokens+=ar.v.get(i)+"\n";
     
                  
          }
            for(int i=0;i<ar.tamerror();i++){
            Error+=ar.e.get(i)+"\n";
          }
           
        Sintactico sintactico=new Sintactico();
        String resultadoSintactico=sintactico.procesarTokens(Tokens);
        //String [] resultadoSintInd=resultadoSintactico.split("_");
        txtResulSintactico.setText(resultadoSintactico);
        //txtErrorSintactico.setText(resultadoSintInd[1].toUpperCase());
        //JOptionPane.showMessageDialog(null,resultadoSintactico);
        tokensTextPane.setText(Tokens);
        errorTextPane.setText(Error);
        Tokens="";
        Error="";
        ar.vaciar();
        ar.vaciarerror();// TODO add your handling code here:
        } catch (IOException ex) {
            Logger.getLogger(JADA_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
              
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JADA_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            new JADA_GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane CodeTextPanel;
    private javax.swing.JMenu commandMenu;
    private javax.swing.JMenuItem commentDecommentLinesMenuItem;
    private javax.swing.JMenuItem commentLinesMenuItem;
    private javax.swing.JMenuItem copyActualLinesMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutActualLinesMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem decommentLinesMenuItem;
    private javax.swing.JMenuItem decrementIndentationMenuItem;
    private javax.swing.JMenuItem deleteActualLinesMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenuItem duplicateActualLinesMenuItem;
    private javax.swing.JMenu editMenuItem;
    private javax.swing.JTextArea errorTextPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenuItem;
    private javax.swing.JMenu formatMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem incrementIndentationMenuItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JScrollPane jsCode;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem moveDownActualLinesMenuItem;
    private javax.swing.JMenuItem moveUpActualLinesMenuItem;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem propertiesMenuItem;
    private javax.swing.JMenuItem rechargeMenuItem;
    private javax.swing.JMenuItem redoMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem selectActualLinesMenuItem;
    private javax.swing.JMenuItem selectActualParraphMenuItem;
    private javax.swing.JTextArea tokensTextPane;
    private javax.swing.JTextArea txtErrorSintactico;
    private javax.swing.JTextArea txtResulSintactico;
    private javax.swing.JMenuItem undoMenuItem;
    // End of variables declaration//GEN-END:variables
}
