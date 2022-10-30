
import com.formdev.flatlaf.FlatIntelliJLaf;
import compilerTools.CodeBlock;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Timer;

/**
 *
 * @author Mabel
 */
import compilerTools.Directory;
import compilerTools.ErrorLSSL;
import compilerTools.Functions;
import compilerTools.Grammar;
import compilerTools.Production;
import compilerTools.TextColor;
import compilerTools.Token;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Interfaz_Gráfica extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;//Guarda los tokens generados
    private ArrayList<ErrorLSSL> errors;//Guarda los errores sintácticos, semánticos o lógicos
    private ArrayList<TextColor> textsColor;//Colores de palabras reservadas
    private Timer timerKeyReleased;//Colorea la palabra del editor de código
    private ArrayList<Production> identProd;//Extrae los identificadores del análisis sintáctico
    private HashMap<String, String> identificadores;//Guarda los identificadores
    private boolean CodigoCompilado = false; //Indica si el compilador ha sido compilado

    public Interfaz_Gráfica() {
        initComponents();
        init();
    }

    private void init() {
        title = "Compilador_Mabel";
        setLocationRelativeTo(null);//Centrar ventana
        setTitle(title);

        directorio = new Directory(this, txtCode, title, ".cpp");

        //Sobreescribir el método WindowClosing
        addWindowListener(new WindowAdapter() {// Se activa cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit(); //Pregunta si queremos guardar
                System.exit(0);//Sale del programa indicando queno hubo ningun error
            }
        });
        Functions.setLineNumberOnJTextComponent(txtCode);//Muestra los números de línea
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> {
            timerKeyReleased.stop();

            colorAnalysis();
        });

        Functions.insertAsteriskInName(this, txtCode, () -> {//Pone el asterisco en la ventana cada vez que se edta el código
            timerKeyReleased.restart();
        });
        //Inicializando todos los elementos a vacío
        tokens = new ArrayList<>();
        errors = new ArrayList<>();
        textsColor = new ArrayList<>();
        identProd = new ArrayList<>();
        identificadores = new HashMap<>();

        //Entra un autocompletador  en el editor de código ctrl + espacio
        Functions.setAutocompleterJTextComponent(new String[]{"using namespace std;", "int main(){}", "for(int i = 0; i<10;i++){  }"}, txtCode, () -> {
            timerKeyReleased.restart();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNuevo = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        btnCompilar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCode = new javax.swing.JTextPane();
        BtnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 153));

        btnNuevo.setBackground(new java.awt.Color(255, 255, 153));
        btnNuevo.setFont(new java.awt.Font("Sitka Banner", 1, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnAbrir.setBackground(new java.awt.Color(255, 255, 153));
        btnAbrir.setFont(new java.awt.Font("Sitka Banner", 1, 14)); // NOI18N
        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(255, 255, 153));
        btnGuardar.setFont(new java.awt.Font("Sitka Banner", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setBackground(new java.awt.Color(255, 255, 153));
        btnGuardarC.setFont(new java.awt.Font("Sitka Banner", 1, 14)); // NOI18N
        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        btnCompilar.setBackground(new java.awt.Color(255, 255, 153));
        btnCompilar.setFont(new java.awt.Font("Sitka Banner", 1, 14)); // NOI18N
        btnCompilar.setText("Análisis Semántico");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(153, 153, 153));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jtaOutputConsole.setForeground(new java.awt.Color(153, 0, 0));
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        jScrollPane1.setViewportView(txtCode);

        BtnLimpiar.setBackground(new java.awt.Color(255, 255, 153));
        BtnLimpiar.setFont(new java.awt.Font("Sitka Banner", 1, 14)); // NOI18N
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(btnCompilar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BtnLimpiar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(6, 6, 6)
                                .addComponent(btnAbrir)
                                .addGap(8, 8, 8)
                                .addComponent(btnGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(btnGuardarC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(388, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCompilar)
                        .addGap(114, 114, 114)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnAbrir)
                            .addComponent(btnGuardar)
                            .addComponent(btnGuardarC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(BtnLimpiar)
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(339, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New();
        LimpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis();
            LimpiarCampos();
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) {
            LimpiarCampos();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) {
            LimpiarCampos();
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) {
            if (directorio.Save()) {
                compile();
            }
        } else {
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        CodigoCompilado = false;
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void compile() {
        LimpiarCampos();//Limpia los campos
        Analisis_Lexico();//Ejecuta el análisis léxico
        LlenarTablaTokens();//Llena la tabla de tokens
        Analisis_Sintactico();//Ejecuta análisis sintáctico
        Analisis_Semantico();//Ejecuta análisis semántico
        ImprimirConsola();
        CodigoCompilado = true;
    }
//Análisis Léxico

    private void Analisis_Lexico() {
        System.out.println("---ANÁISIS LÉXICO--");
        // Extraer tokens
        Lexer lexer;
        try {
            File codigo = new File("code.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = txtCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexer = new Lexer(entrada);
            while (true) {
                Token token = lexer.yylex();
                if (token == null) {
                    break;
                }
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
    }

    //Análisis Sintáctico
    private void Analisis_Sintactico() {
        System.out.println("---ANÁISIS SINTÁCTICO--");
        Grammar gramatica = new Grammar(tokens, errors);//Pasando tokens y errores como párametros

        /*Eliminación de errores*/
        gramatica.delete(new String[]{"Error1"}, 1);

        /*Agrupando valores*/
        gramatica.group("VALOR", "(NUMERO|CADENA|NUM_DEC)", true);

        /*Declaración de variables numeros enteros*/
        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true, identProd);
        gramatica.group("VARIABLES", "VARIABLE OPE_ASIG VALOR", true, 1,
                "Error sintáctico {}, falta el tipo de dato de la variable[#/%]");

        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true);
        gramatica.group("VARIABLES", "TIPO_DATO OPE_ASIG VALOR", true, 2,
                "Error sintáctico {}, falta definir variable[#/%]");

        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true);
        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE VALOR", true, 3,
                "Error sintáctico {}, falta operador de asignacion[#/%]");

        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true);
        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG", true, 4,
                "Error sintáctico {}, fvalor de asignacion incorrecto [#/%]");

        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true);
        gramatica.group("VARIABLES", "TIPO_DATO", true, 5,
                "Error sintáctico {}, no se ha definido ninguna variable[#/%]");

        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true);
        gramatica.group("VARIABLES", "VARIABLE", true, 6,
                "Error sintáctico {}, [#/%]");

        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true);
        gramatica.group("VARIABLES", "OPE_ASIG", true, 7,
                "Error sintáctico {}, [#/%]");

        gramatica.group("VARIABLES", "TIPO_DATO VARIABLE OPE_ASIG VALOR", true);
        gramatica.group("VARIABLES", "VALOR", true, 8,
                "Error sintáctico {}, [#/%]");
        gramatica.initialLineColumn();

        gramatica.show();
    }

    private void Analisis_Semantico() {
        System.out.println("---ANÁISIS SEMÁNTICO--");

        for (Production id : identProd) {
            System.out.println(id.lexemeRank(0, -1));
            System.out.println(id.lexicalCompRank(0, -1));
        }
        HashMap<String, String> idenDataType = new HashMap<>();
        /*Enteros*/
        idenDataType.put("int", "NUMERO");
        idenDataType.put("long", "NUMERO");
        idenDataType.put("short", "NUMERO");

        /*Decimales*/
        idenDataType.put("double", "NUM_DEC");
        idenDataType.put("float", "NUM_DEC");

        /*Cadenas*/
        idenDataType.put("string", "CADENA");
        idenDataType.put("char", "CADENA");
        idenDataType.put("String", "CADENA");

        for (Production id : identProd) {

            //Numeros Enteros
            if (!idenDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1))) {
                errors.add(new ErrorLSSL(1, "Error Semántico {}:  valor no compatible con el tipo de dato [#/%]", id, true));

            } else if (id.lexicalCompRank(-1).equals("NUMERO") && !id.lexemeRank(-1).matches("0|[1-9][0-9]*")) {
                errors.add(new ErrorLSSL(2, "Error Semántico {}: el valor asignado no es un entero [#/%]", id, false));
            } else {
                identificadores.put(id.lexemeRank(1), id.lexemeRank(-1));
            }

            //Numeros Decimales
            if (!idenDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1))) {
                errors.add(new ErrorLSSL(3, "Error Semántico {}:  valor no compatible con el tipo de dato [#/%]", id, true));

            } else if (id.lexicalCompRank(-1).equals("NUM_DEC") && !id.lexemeRank(-1).matches("[1-9][0-9]*.[1-9][0-9]*")) {

                errors.add(new ErrorLSSL(4, "Error Semántico {}: el valor asignado no es un decimal [#/%]", id, false));

            } else {
                identificadores.put(id.lexemeRank(1), id.lexemeRank(-1));
            }

            //Cadenas de Texto
            if (!idenDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1))) {
                errors.add(new ErrorLSSL(5, "Error Semántico {}:  valor no compatible con el tipo de dato [#/%]", id, true));

            } else if (id.lexicalCompRank(-1).equals("CADENA") && !id.lexemeRank(-1).matches("\"[A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]+\"")) {

                errors.add(new ErrorLSSL(6, "Error Semántico {}: el valor asignado no es una cadena de texto [#/%]", id, false));

            } else {
                identificadores.put(id.lexemeRank(1), id.lexemeRank(-1));
            }

//            //Booleano
//            if (!idenDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1))) {
//                errors.add(new ErrorLSSL(7, "Error Semántico {}:  valor no compatible con el tipo de dato [#/%]", id, true));
//
//            } else if (id.lexicalCompRank(-1).equals("DAT_BOL") && !id.lexemeRank(-1).matches("\true|\flase")) {
//
//                errors.add(new ErrorLSSL(8, "Error Semántico {}: el valor asignado no es booleano", id, false));
//
//            } else {
//                identificadores.put(id.lexemeRank(1), id.lexemeRank(-1));
//            }
        }//for
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear();
        /* Extraer rangos de colores */
        LexerColor lexerColor;
        try {
            File codigo = new File("c.encrypter");
            FileOutputStream output = new FileOutputStream(codigo);
            byte[] bytesText = txtCode.getText().getBytes();
            output.write(bytesText);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8"));
            lexerColor = new LexerColor(entrada);
            while (true) {
                TextColor textColor = lexerColor.yylex();
                if (textColor == null) {
                    break;
                }
                textsColor.add(textColor);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage());
        }
        Functions.colorTextPane(textsColor, txtCode, new Color(40, 40, 40));
    }

    private void LlenarTablaTokens() {
        tokens.forEach(token -> {
            //Cada posición será igual a una columna
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"};
            Functions.addRowDataInTable(tblTokens, data);
        });
    }

    private void ImprimirConsola() {
        int sizeErrors = errors.size();//Guarda cantidad de errores

        //Si encunetra un error
        if (sizeErrors > 0) {

            Functions.sortErrorsByLineAndColumn(errors);
            String strErrors = "\n";
            for (ErrorLSSL error : errors) {//Va recorriendo cada error detectado
                String strError = String.valueOf(error);//Guarda cadena con el error
                strErrors += strError + "\n";
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores...");

        } else {//Si no encuentra un error
            jtaOutputConsole.setText("Compilación terminada.");
        }
        jtaOutputConsole.setCaretPosition(0);
    }

    private void LimpiarCampos() {
        Functions.clearDataInTable(tblTokens);
        jtaOutputConsole.setText("");
        tokens.clear();
        errors.clear();
        identProd.clear();
        identificadores.clear();
        CodigoCompilado = false;
    }

    public static void main(String args[]) {

        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Gráfica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Gráfica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Gráfica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Gráfica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Interfaz_Gráfica().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTable tblTokens;
    private javax.swing.JTextPane txtCode;
    // End of variables declaration//GEN-END:variables
}
