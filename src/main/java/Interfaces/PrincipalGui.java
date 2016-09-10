/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Utiles.DatosGenericos;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;

/**
 *
 * @author nico
 */
public class PrincipalGui extends javax.swing.JFrame {
    /**
     * Creates new form PrincipalGui
     */
    private DesktopPaneImage desktop;
    
    public PrincipalGui() {
        initComponents();
        desktop= new DesktopPaneImage();
        this.add(desktop);
        setTitle(DatosGenericos.nombreGimnasio);
    }
    

    
    public void setActionListener(ActionListener lis){
        this.botDesconectar.addActionListener(lis);
        this.botSocios.addActionListener(lis);
        this.botSalir.addActionListener(lis);
        this.botActividades.addActionListener(lis);
        this.botUsuario.addActionListener(lis);
        this.ingreso.addActionListener(lis);
        this.impresionAranceles.addActionListener(lis);
        this.depurar.addActionListener(lis);
        this.btnArticulos.addActionListener(lis);
        this.btnCargarVenta.addActionListener(lis);
        this.botCumple.addActionListener(lis);
                this.registrarCompra.addActionListener(lis);
        this.proveedores.addActionListener(lis);
        this.btnGastos.addActionListener(lis);
        this.btnCaja.addActionListener(lis);
        this.btnGestionEjercicios.addActionListener(lis);
        this.btnAlimentos.addActionListener(lis);
        this.btnCrearRutina.addActionListener(lis);
        this.btnDietas.addActionListener(lis);
        this.btnEstadisticasIngreso.addActionListener(lis);
        this.btnEstadisticasSocios.addActionListener(lis);
                //this.enviar.addActionListener(lis);
        this.cargarBackup.addActionListener(lis);
        this.crearBackup.addActionListener(lis);
        this.cambiosEmail.addActionListener(lis);
    }

    public JMenuItem getBtnEstadisticasSocios() {
        return btnEstadisticasSocios;
    }

    public JMenuItem getBtnEstadisticasIngreso() {
        return btnEstadisticasIngreso;
    }
    
    public JMenuItem getCargarBackup() {
        return cargarBackup;
    }

    public JMenuItem getCrearBackup() {
        return crearBackup;
    }

    public JMenuItem getCambiosEmail() {
        return cambiosEmail;
    }
//    public JMenuItem getEnviar() {
//        return enviar;
//    }

    
    public JMenuItem getDepurar() {
        return depurar;
    }

    public JMenuItem getBtnDietas() {
        return btnDietas;
    }

    public JMenuItem getBotDesconectar() {
        return botDesconectar;
    }

    public JButton getBtnCargarVenta() {
        return btnCargarVenta;
    }

    public JMenuItem getBtnAlimentos() {
        return btnAlimentos;
    }

    public JMenuItem getBtnGestionEjercicios() {
        return btnGestionEjercicios;
    }

    public JButton getBotCumple() {
        return botCumple;
    }

    public JButton getBtnArticulos() {
        return btnArticulos;
    }

    public JMenuItem getBtnCrearRutina() {
        return btnCrearRutina;
    }

    public JButton getBtnGastos() {
        return btnGastos;
    }

    public JButton getBtnCaja() {
        return btnCaja;
    }

   

    public JMenuItem getImpresionAranceles() {
        return impresionAranceles;
    }

    public JButton getBotUsuario() {
        return botUsuario;
    }

    public JMenuItem getBotSalir() {
        return botSalir;
    }

    public JButton getBotSocios() {
        return botSocios;
    }



    public DesktopPaneImage getDesktop() {
        return desktop;
    }

    public JButton getBotActividades() {
        return botActividades;
    }

    public JButton getIngreso() {
        return ingreso;
    }

    public JButton getProveedores() {
        return proveedores;
    }

    public JButton getRegistrarCompra() {
        return registrarCompra;
    }


    

    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBotones = new javax.swing.JPanel();
        botSocios = new javax.swing.JButton();
        botActividades = new javax.swing.JButton();
        botUsuario = new javax.swing.JButton();
        ingreso = new javax.swing.JButton();
        btnArticulos = new javax.swing.JButton();
        btnCargarVenta = new javax.swing.JButton();
        botCumple = new javax.swing.JButton();
        proveedores = new javax.swing.JButton();
        registrarCompra = new javax.swing.JButton();
        btnCaja = new javax.swing.JButton();
        btnGastos = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        botDesconectar = new javax.swing.JMenuItem();
        botSalir = new javax.swing.JMenuItem();
        depurar = new javax.swing.JMenuItem();
        cambiosEmail = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        impresionAranceles = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        tecPro = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        btnGestionEjercicios = new javax.swing.JMenuItem();
        btnCrearRutina = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        btnAlimentos = new javax.swing.JMenuItem();
        btnDietas = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        btnEstadisticasIngreso = new javax.swing.JMenuItem();
        btnEstadisticasSocios = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        crearBackup = new javax.swing.JMenuItem();
        cargarBackup = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(DatosGenericos.nombreGimnasio);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/logo_chico.png")).getImage());

        panelBotones.setLayout(new java.awt.GridLayout(0, 1));

        botSocios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/clients.png"))); // NOI18N
        botSocios.setToolTipText("Gestión de socios");
        panelBotones.add(botSocios);

        botActividades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/actividad.png"))); // NOI18N
        botActividades.setToolTipText("Gestión de actividades");
        panelBotones.add(botActividades);

        botUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuario.png"))); // NOI18N
        botUsuario.setToolTipText("Gestión de usuarios");
        panelBotones.add(botUsuario);

        ingreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ingreso.png"))); // NOI18N
        ingreso.setToolTipText("Ingreso de clientes");
        panelBotones.add(ingreso);

        btnArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productos.png"))); // NOI18N
        panelBotones.add(btnArticulos);

        btnCargarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/vender.png"))); // NOI18N
        panelBotones.add(btnCargarVenta);

        botCumple.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cumple.png"))); // NOI18N
        botCumple.setToolTipText("registros de cumpleaños");
        panelBotones.add(botCumple);

        proveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedor.png"))); // NOI18N
        proveedores.setToolTipText("Gestión de proveedores");
        panelBotones.add(proveedores);

        registrarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/compras.png"))); // NOI18N
        registrarCompra.setToolTipText("Registrar Compra");
        panelBotones.add(registrarCompra);

        btnCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/rsz_cash_register.png"))); // NOI18N
        btnCaja.setToolTipText("caja del dia");
        panelBotones.add(btnCaja);

        btnGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/andromoney.png"))); // NOI18N
        btnGastos.setToolTipText("Reporte de gastos e ingresos");
        panelBotones.add(btnGastos);

        getContentPane().add(panelBotones, java.awt.BorderLayout.LINE_START);

        jMenu1.setText("Archivo");
        jMenu1.setToolTipText("");

        botDesconectar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cerrar.png"))); // NOI18N
        botDesconectar.setText("Cerrar sesión");
        jMenu1.add(botDesconectar);

        botSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/apagar.png"))); // NOI18N
        botSalir.setText("Salir");
        jMenu1.add(botSalir);

        depurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/1397349580_virus_definitions.png"))); // NOI18N
        depurar.setText("Depurar datos");
        jMenu1.add(depurar);

        cambiosEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/mail.png"))); // NOI18N
        cambiosEmail.setText("Cambiar correo");
        jMenu1.add(cambiosEmail);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Documentos");

        impresionAranceles.setText("Aranceles para imprimir");
        jMenu3.add(impresionAranceles);

        jMenuBar1.add(jMenu3);

        jMenu2.setText("Acerca de");

        tecPro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/acerca.png"))); // NOI18N
        tecPro.setText("Tec-Pro Software");
        tecPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tecProActionPerformed(evt);
            }
        });
        jMenu2.add(tecPro);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Gestion de rutinas y ejercicios");

        btnGestionEjercicios.setText("Gestion de ejercicios");
        jMenu4.add(btnGestionEjercicios);

        btnCrearRutina.setText("Gestion de rutinas");
        jMenu4.add(btnCrearRutina);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Gestion dietas");

        btnAlimentos.setText("Gestión alimentos");
        jMenu5.add(btnAlimentos);

        btnDietas.setText("Gestión dietas");
        jMenu5.add(btnDietas);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Estadisticas");

        btnEstadisticasIngreso.setText("Estadisticas de ingresos");
        jMenu6.add(btnEstadisticasIngreso);

        btnEstadisticasSocios.setText("Estadisticas sobre socios");
        jMenu6.add(btnEstadisticasSocios);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Backup");

        crearBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/guardarBack.png"))); // NOI18N
        crearBackup.setText("Crear Backup");
        jMenu7.add(crearBackup);

        cargarBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/subirBack.png"))); // NOI18N
        cargarBackup.setText("Cargar Backup");
        jMenu7.add(cargarBackup);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tecProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tecProActionPerformed
        AcercaDe acercaDe= new AcercaDe(this, true);
        acercaDe.setLocationRelativeTo(this);
        acercaDe.setVisible(true);
    }//GEN-LAST:event_tecProActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botActividades;
    private javax.swing.JButton botCumple;
    private javax.swing.JMenuItem botDesconectar;
    private javax.swing.JMenuItem botSalir;
    private javax.swing.JButton botSocios;
    private javax.swing.JButton botUsuario;
    private javax.swing.JMenuItem btnAlimentos;
    private javax.swing.JButton btnArticulos;
    private javax.swing.JButton btnCaja;
    private javax.swing.JButton btnCargarVenta;
    private javax.swing.JMenuItem btnCrearRutina;
    private javax.swing.JMenuItem btnDietas;
    private javax.swing.JMenuItem btnEstadisticasIngreso;
    private javax.swing.JMenuItem btnEstadisticasSocios;
    private javax.swing.JButton btnGastos;
    private javax.swing.JMenuItem btnGestionEjercicios;
    private javax.swing.JMenuItem cambiosEmail;
    private javax.swing.JMenuItem cargarBackup;
    private javax.swing.JMenuItem crearBackup;
    private javax.swing.JMenuItem depurar;
    private javax.swing.JMenuItem impresionAranceles;
    private javax.swing.JButton ingreso;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JButton proveedores;
    private javax.swing.JButton registrarCompra;
    private javax.swing.JMenuItem tecPro;
    // End of variables declaration//GEN-END:variables
}
