/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import ABMs.ABMSocios;
import Interfaces.AbmClienteGui;
import Interfaces.CargarHuellaGui;
import Interfaces.DietasSocioGui;
import Interfaces.FichaMedicaGui;
import Interfaces.FormaDePagoGui;
import Interfaces.GuiCrearRutina;
import Interfaces.GuiVerRutinas;
import Interfaces.PagoCuentaEfectivo;
import Interfaces.PrincipalGui;
import Interfaces.RegistrarPagoGui;
import Interfaces.TodasAsisGui;
import Interfaces.VerDietaEditarGui;
import Interfaces.VerDietaGui;
import Modelos.Arancel;
import Modelos.Ficha;
import Modelos.Gasto;
import Modelos.Pago;
import Modelos.Rutina;
import Modelos.Socio;
import Modelos.Socioarancel;
import Utiles.DatosGenericos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;

/**
 *
 * @author nico
 */
public class ControladorAbmCliente implements ActionListener {

    private AbmClienteGui clienteGui;
    private boolean isNuevo;
    private RegistrarPagoGui pagoGui;
    private FichaMedicaGui fichaMedicaGui;
    private CargarHuellaGui cargarHuellaGui;
    private ABMSocios abmsocio;
    private Socio s;
    private boolean fichaNueva;
    private ActualizarDatos actualizarDatos;
    private String dniViejo;
    private PrincipalGui principal;
    GuiCrearRutina guiCrearRutina;
    ControladorGuiCrearRutina controladorGuiCrearRutina;

    //ControladorGuiRutinas controladorGuiRutinas;
    public ControladorAbmCliente(AbmClienteGui clienteGui, ActualizarDatos actualizarDatos, PrincipalGui p) throws JRException, ClassNotFoundException, SQLException {
        principal = p;
        this.clienteGui = clienteGui;
        this.clienteGui.setActionListener(this);
        abmsocio = new ABMSocios();
        this.actualizarDatos = actualizarDatos;
        guiCrearRutina = new GuiCrearRutina();
        controladorGuiCrearRutina = new ControladorGuiCrearRutina(guiCrearRutina);
        principal.getDesktop().add(guiCrearRutina);

        //principal.getDesktop().add(guiVerRutina);
    }

    private void CargarDatosSocio(Socio s) {
        s.set("NOMBRE", clienteGui.getNombre().getText().toUpperCase());
        s.set("APELLIDO", clienteGui.getApellido().getText().toUpperCase());
        s.set("TEL", clienteGui.getTelefono().getText().toUpperCase());
        s.set("DNI", clienteGui.getDni().getText().toUpperCase());
        s.set("DIR", clienteGui.getDireccion().getText().toUpperCase());
        s.set("FACEBOOK", clienteGui.getTxtFacebook().getText());
        s.set("NOSCONOCIOPOR", clienteGui.getBoxConocio().getSelectedItem());
        s.set("CELULAR", clienteGui.getTxtCelular().getText());
        s.set("MAIL", clienteGui.getTxtMail().getText());
        if (clienteGui.getFechaNacimJDate().getCalendar() != null) {
            s.set("FECHA_NAC", dateToMySQLDate(clienteGui.getFechaNacimJDate().getCalendar().getTime(), false));
        }
        if (clienteGui.getSexo().getSelectedIndex() == 1) {
            s.set("SEXO", "M");
        } else {
            s.set("SEXO", "F");
        }

    }

    public void CargarFicha(Ficha ficha) {
        /* if(ficha.get("FACTOR") == "RH+"){
         fichaMedicaGui.getSigno().setSelectedIndex(0);
         }
         if(ficha.get("FACTOR") == "+"){
         fichaMedicaGui.getSigno().setSelectedIndex(0);
         }
         if(ficha.get("FACTOR") == "RH-"){
         fichaMedicaGui.getSigno().setSelectedIndex(1);
         }
         if(ficha.get("FACTOR") == "-"){
         fichaMedicaGui.getSigno().setSelectedIndex(1);
         }
         if(ficha.get("FACTOR") == null){
         fichaMedicaGui.getSigno().setSelectedIndex(2);
         }*/
        if (ficha.get("FACTOR") != null) {
            fichaMedicaGui.getSigno().setSelectedItem(ficha.get("FACTOR"));
        } else {
            fichaMedicaGui.getSigno().setSelectedItem(ficha.get("FACTOR"));
        }
        if (ficha.get("GRUPO_SANG") != null) {
            fichaMedicaGui.getLetraSangui().setSelectedItem(ficha.get("GRUPO_SANG"));
        } else {
            fichaMedicaGui.getLetraSangui().setSelectedItem("NE");
        }
        fichaMedicaGui.getTelEmergencia().setText(ficha.getString("TEL_EMERG"));
        fichaMedicaGui.getTextoAlergias().setText(ficha.getString("ALERGICO"));
        fichaMedicaGui.getTextoMedicamentos().setText(ficha.getString("MEDICAM"));
        fichaMedicaGui.getObservaciones().setText(ficha.getString("OBSERV"));
        if (ficha.getInteger("ARTROSIS") != null) {
            if (ficha.getInteger("ARTROSIS") != 0) {
                fichaMedicaGui.getArtrosis().setSelected(true);
            }
        }
        if (ficha.getInteger("ASMA") != null) {
            if (ficha.getInteger("ASMA") != 0) {
                fichaMedicaGui.getAsma().setSelected(true);
            }
        }
        if (ficha.getInteger("CARDIACO") != null) {
            if (ficha.getInteger("CARDIACO") != 0) {
                fichaMedicaGui.getCardiaco().setSelected(true);
            }
        }
        if (ficha.getInteger("DIABETES") != null) {
            if (ficha.getInteger("DIABETES") != 0) {
                fichaMedicaGui.getDiabetes().setSelected(true);
            }
        }
        if (ficha.getInteger("EMBARAZO") != null) {
            if (ficha.getInteger("EMBARAZO") != 0) {
                fichaMedicaGui.getEmbarazo().setSelected(true);
            }
        }
        if (ficha.getInteger("ENDOCRINOLOGIA") != null) {
            if (ficha.getInteger("ENDOCRINOLOGIA") != 0) {
                fichaMedicaGui.getEndocrinologia().setSelected(true);
            }
        }
        if (ficha.getInteger("HUESOS") != null) {
            if (ficha.getInteger("HUESOS") != 0) {
                fichaMedicaGui.getHuesoLigam().setSelected(true);
            }
        }
        if (ficha.getInteger("PULMONARES") != null) {
            if (ficha.getInteger("PULMONARES") != 0) {
                fichaMedicaGui.getEnfPulmonar().setSelected(true);
            }
        }
        if (ficha.getInteger("EPILEPTICO") != null) {
            if (ficha.getInteger("EPILEPTICO") != 0) {
                fichaMedicaGui.getEpileptico().setSelected(true);
            }
        }
        if (ficha.getInteger("HIPERTENSION") != null) {
            if (ficha.getInteger("HIPERTENSION") != 0) {
                fichaMedicaGui.getHipertension().setSelected(true);
            }
        }
        if (ficha.getInteger("DEPORTIVA") != null) {
            if (ficha.getInteger("DEPORTIVA") != 0) {
                fichaMedicaGui.getLesionDeportiva().setSelected(true);
            }
        }
        if (ficha.getInteger("OBESIDAD") != null) {
            if (ficha.getInteger("OBESIDAD") != 0) {
                fichaMedicaGui.getObesidad().setSelected(true);
            }
        }
        if (ficha.getInteger("REUMA") != null) {
            if (ficha.getInteger("REUMA") != 0) {
                fichaMedicaGui.getReuma().setSelected(true);
            }
        }
    }

    /*va true si se quiere usar para mostrarla por pantalla es decir 12/12/2014 y false si va 
     para la base de datos, es decir 2014/12/12*/
    public String dateToMySQLDate(Date fecha, boolean paraMostrar) {
        if (paraMostrar) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(fecha);
        } else {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(fecha);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clienteGui.getBotEliminarCancelar()) {
            //System.out.println("Boton eliminar pulsado");
            if (clienteGui.getBotEliminarCancelar().getText().equals("Eliminar")) {
                clienteGui.bloquearCampos(true);
                int ret = JOptionPane.showConfirmDialog(clienteGui, "¿Desea borrar a " + clienteGui.getNombre().getText() + " " + clienteGui.getApellido().getText() + " ? ", null, JOptionPane.YES_NO_OPTION);
                if (ret == JOptionPane.YES_OPTION) {
                    if (abmsocio.baja(s)) {
                        JOptionPane.showMessageDialog(clienteGui, "Socio dado de baja exitosamente!");
                        clienteGui.limpiarCampos();
                        clienteGui.setVisible(false);
                        actualizarDatos.cargarSocios();
                    } else {
                        JOptionPane.showMessageDialog(clienteGui, "Ocurrio un error inesperado");
                    }

                }
            } else {
                //System.out.println("cancelé !");
                int ret = JOptionPane.showConfirmDialog(clienteGui, "¿Desea cancelar los cambios?", null, JOptionPane.YES_NO_OPTION);
                if (ret == JOptionPane.YES_OPTION) {
                    clienteGui.limpiarCampos();
                    clienteGui.setVisible(false);
                }
            }

        }
        if (ae.getSource() == clienteGui.getBotFicha()) {
            //System.out.println("Boton ficha pulsado");
            fichaMedicaGui = new FichaMedicaGui(null, true);
            fichaMedicaGui.getTextoAlergias().setEnabled(true);
            fichaMedicaGui.getTextoMedicamentos().setEnabled(true);
            fichaMedicaGui.setActionListener(this);
            fichaMedicaGui.setLocationRelativeTo(null);
            Socio socio = Socio.first("DNI = ?", clienteGui.getDni().getText());
            Ficha f = Ficha.first("ID_DATOS_PERS = ?", socio.get("ID_DATOS_PERS"));
            ////System.out.println(f.get("ID_DATOS_PERS"));
            if (f == null) {
                //int ret=JOptionPane.showConfirmDialog(clienteGui, "Socio sin ficha, ¿Desea crear ficha?",null,JOptionPane.YES_NO_OPTION);
                //if(ret == JOptionPane.YES_OPTION){
                fichaNueva = true;
                fichaMedicaGui.getjButton1().setEnabled(false);
                JOptionPane.showMessageDialog(fichaMedicaGui, "Socio sin ficha, debe cargar ficha");
                fichaMedicaGui.getTextoAlergias().setEnabled(true);
                fichaMedicaGui.getTextoMedicamentos().setEditable(true);
                fichaMedicaGui.setVisible(true);
                //System.out.println("el valor es " + fichaNueva);
            } else {
                CargarFicha(f);
                fichaMedicaGui.setVisible(true);
                fichaMedicaGui.getTextoAlergias().setEnabled(true);
                fichaMedicaGui.getTextoMedicamentos().setEditable(true);
                fichaNueva = false;
                fichaMedicaGui.setEnabled(true);
                //System.out.println("el valor es " + fichaNueva);
            }

        }
        if (ae.getSource() == clienteGui.getBotGuardar()) {
            if (!isNuevo) {
                //System.out.println("Se modificó uno que existia");
                s = new Socio();
                CargarDatosSocio(s);
                int rows = clienteGui.getTablaActivDefault().getRowCount();
                LinkedList listaran = new LinkedList();
                for (int i = 0; i < rows; i++) { //ahora si :P abajo en el alta estaba bien, aca en el modificar no lo habia cambiado :P
                    // boolean val =  clienteGui.getTablaActividades().getValueAt(i, 1).equals(false);
                    //System.out.println(clienteGui.getTablaActividades().getValueAt(i, 1) != null);
                    // //System.out.println( clienteGui.getTablaActividades().getValueAt(i, 1).equals(false));
                    if (clienteGui.getTablaActividades().getValueAt(i, 1) != null) {//|| clienteGui.getTablaActividades().getValueAt(i, 1).equals(false)){
                        if (clienteGui.getTablaActividades().getValueAt(i, 1).equals(true)) {

                            Arancel a = Arancel.first("nombre = ?", clienteGui.getTablaActividades().getValueAt(i, 0));
                            listaran.add(a);
                            //System.out.println(a.get("nombre"));
                        }
                    }

                }
                //Object o = clienteGui.getTablaActividades().getValueAt(1, 1).equals(true);
                if (abmsocio.modificar(s, listaran, dniViejo)) {
                    JOptionPane.showMessageDialog(clienteGui, "Socio modificado exitosamente!");
                    clienteGui.bloquearCampos(true);
                    //clienteGui.limpiarCampos();
                    clienteGui.getBotGuardar().setEnabled(false);
                    clienteGui.getBotModif().setEnabled(true);
                    clienteGui.getBotPago().setEnabled(true);
                    clienteGui.getBotPagoCuenta().setEnabled(true);
                    clienteGui.getBotHuella().setEnabled(true);
                    clienteGui.getBotFicha().setEnabled(true);
                    clienteGui.getBotEliminarCancelar().setText("Eliminar");
                    actualizarDatos.cargarSocios();
                } else {
                    JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                //System.out.println("Boton guardó uno nuevito");
                ////System.out.println(clienteGui.getTablaActividades().getValueAt(0, 0));
                //debe mantener abierta la ventana y que se habilite el botón de la huella, la huella
                //solo puede ser creada si el usuario existe
                s = new Socio();
                // boolean val;
                CargarDatosSocio(s);
                if (s.getString("DNI").equals("") || s.getString("APELLIDO").equals("")) {
                    JOptionPane.showMessageDialog(clienteGui, "Faltan datos obligatorios", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    int rows = clienteGui.getTablaActivDefault().getRowCount();
                    //System.out.println("La cant de rows es " + rows);
                    LinkedList listaran = new LinkedList();
                    for (int i = 0; i < rows; i++) {
                        // //System.out.println(clienteGui.getTablaActividades().getValueAt(i, 0));
                        //    val = (boolean) clienteGui.getTablaActividades().getValueAt(i, 1); 
                        //  //System.out.println("se te pudrio");
                        if (clienteGui.getTablaActividades().getValueAt(i, 1) != null) {
                            Arancel a = Arancel.first("nombre = ?", clienteGui.getTablaActividades().getValueAt(i, 0));
                            listaran.add(a);
                            //System.out.println("esta GIL");
                        } else {
                            //System.out.println("no esta GIL");
                        }
                    }
                    if (abmsocio.alta(s, listaran)) {
                        JOptionPane.showMessageDialog(clienteGui, "Socio guardado exitosamente!");
                        clienteGui.bloquearCampos(true);
                        //clienteGui.limpiarCampos();
                        clienteGui.getBotNuevo().setEnabled(true);
                        clienteGui.getBotGuardar().setEnabled(false);
                        clienteGui.getBotHuella().setEnabled(true);
                        clienteGui.getBotFicha().setEnabled(true);
                        clienteGui.getBotModif().setEnabled(true);
                        clienteGui.getBotPago().setEnabled(true);
                        clienteGui.getBotPagoCuenta().setEnabled(true);
                        clienteGui.getBotEliminarCancelar().setText("Eliminar");
                        actualizarDatos.cargarSocios();
                        s = Socio.findFirst("DNI = ? ", clienteGui.getDni().getText());
                        clienteGui.setTitle(s.getString("APELLIDO") + " " + s.getString("NOMBRE"));

                    } else {
                        JOptionPane.showMessageDialog(clienteGui, "Ocurrió un error, revise los datos", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }

        }
        if (ae.getSource() == clienteGui.getBotHuella()) {
            //System.out.println("Boton huella pulsado");
            s = Socio.findFirst("DNI = ? ", clienteGui.getDni().getText());
            try {
                //System.out.println((s == null) + " " + s.getInteger("ID_DATOS_PERS"));
                cargarHuellaGui = new CargarHuellaGui(null, s.getInteger("ID_DATOS_PERS"));//Aca va el ID del cliente !
                cargarHuellaGui.setTitle("Huella de " + s.getString("NOMBRE") + " " + s.getString("APELLIDO"));
            } catch (SQLException ex) {
                Logger.getLogger(ControladorAbmCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            cargarHuellaGui.setLocationRelativeTo(null);
            cargarHuellaGui.setVisible(true);

        }
        if (ae.getSource() == clienteGui.getBotModif()) {
            //System.out.println("Boton modif pulsado");
            clienteGui.bloquearCampos(false);
            //clienteGui.getDni().setEnabled(false);
            clienteGui.getBotEliminarCancelar().setText("Cancelar");
            clienteGui.getBotModif().setEnabled(false);
            clienteGui.getBotGuardar().setEnabled(true);
            clienteGui.getBotPago().setEnabled(false);
            clienteGui.getBotPagoCuenta().setEnabled(false);
            clienteGui.getBotHuella().setEnabled(false);
            clienteGui.getBotFicha().setEnabled(false);
            isNuevo = false;
            clienteGui.getTablaActivDefault().setRowCount(0);
            Socio socio = Socio.first("DNI = ?", clienteGui.getDni().getText());
            LazyList<Socioarancel> socaran = Socioarancel.where("id_socio = ?", socio.get("ID_DATOS_PERS"));
            Iterator<Socioarancel> iter = socaran.iterator();
            LinkedList<String> tieneAran = new LinkedList();
            while (iter.hasNext()) {
                Socioarancel arsoc = iter.next();
                Arancel ar = Arancel.first("id = ?", arsoc.get("id_arancel"));
                tieneAran.add(ar.getString("nombre"));
                //System.out.println(ar.get("nombre") + " gil");
            }
            Iterator<String> itiene = tieneAran.iterator();
            while (itiene.hasNext()) {
                String tiene = itiene.next();
                Object row[] = new Object[2];
                row[0] = tiene;
                row[1] = true;
                clienteGui.getTablaActivDefault().addRow(row);
            }
            LazyList<Arancel> listArancel = Arancel.findAll();
            LinkedList<String> Aranceles = new LinkedList();
            Iterator<Arancel> it = listArancel.iterator();
            while (it.hasNext()) {
                Arancel a = it.next();
                Aranceles.add(a.getString("nombre"));
            }
            Aranceles.removeAll(tieneAran);
            Iterator<String> itt = Aranceles.iterator();
            while (itt.hasNext()) {
                String tiene2 = itt.next();
                Object row[] = new Object[2];
                row[0] = tiene2;
                // row[1] = false;
                clienteGui.getTablaActivDefault().addRow(row);
            }
            dniViejo = clienteGui.getDni().getText();
        }

        if (ae.getSource() == clienteGui.getBotNuevo()) {
            //System.out.println("Boton nuevo pulsado");
            isNuevo = true;
            clienteGui.limpiarCampos();
            clienteGui.bloquearCampos(false);
            clienteGui.getBtnVerRutina().setEnabled(false);
            clienteGui.setBotonesNuevo(true);
            clienteGui.setTitle("Alta de socio");
            clienteGui.getTablaActivDefault().setRowCount(0);
            LazyList<Arancel> ListAran = Arancel.findAll();
            Iterator<Arancel> ite = ListAran.iterator();
            while (ite.hasNext()) {
                Arancel a = ite.next();
                Object row[] = new Object[2];
                row[0] = a.getString("nombre");
                clienteGui.getTablaActivDefault().addRow(row);
                /*Se debe abrir la ventana de clientes para permitir el alta de giles*/
            }

        }
        if (ae.getSource() == clienteGui.getBotPago()) {
            //System.out.println("Boton pago pulsado");
            /*SE DEBERÁ MODIFICAR EL CONSTRUCTOR DE REGISTRARPAGOGUI PARA QUE TOME
             UN CLIENTE ASÍ SE HACE EL PAGO TODO DESDE ESA CLASE*/
            s = abmsocio.getSocio(s);
            pagoGui = new RegistrarPagoGui(null, true, s);
            pagoGui.setLocationRelativeTo(null);
            pagoGui.setVisible(true);
            cargarSocioPantalla(s);

        }
        if (ae.getSource() == clienteGui.getBotPagoCuenta()) {
            //System.out.println("Boton pago pulsado");
            /*SE DEBERÁ MODIFICAR EL CONSTRUCTOR DE REGISTRARPAGOGUI PARA QUE TOME
             UN CLIENTE ASÍ SE HACE EL PAGO TODO DESDE ESA CLASE*/
            s = abmsocio.getSocio(s);
            FormaDePagoGui formaDePago = new FormaDePagoGui(null, true, true);
            formaDePago.setLocationRelativeTo(null);
            formaDePago.setVisible(true);
            int opcionFormaPago = formaDePago.getReturnStatus();
            switch (opcionFormaPago) {
                case FormaDePagoGui.RET_EFECTIVO:
                    PagoCuentaEfectivo pagoEfectivo = new PagoCuentaEfectivo(null, true, s.getBigDecimal("cuenta_corriente"));
                    pagoEfectivo.setLocationRelativeTo(null);
                    pagoEfectivo.setVisible(true);
                    if (PagoCuentaEfectivo.RET_OK == pagoEfectivo.getReturnStatus()) {
                        Base.openTransaction();
                        s.setBigDecimal("cuenta_corriente", s.getBigDecimal("cuenta_corriente").add(pagoEfectivo.getPago().setScale(2, RoundingMode.CEILING)));
                        s.saveIt();
                        Pago.createIt("ID_DATOS_PERS", s.getString("ID_DATOS_PERS"), "FECHA", dateToMySQLDate(pagoEfectivo.getFecha().getDate(), false), "MONTO", pagoEfectivo.getPago().setScale(2, RoundingMode.CEILING), "MODO", "EFECTIVO ANTERIOR", "DESCRIPCION", "PAGO PARA SALDAR CUENTA CUENTA CORRIENTE");
                        int idDato = 16;//d.getInteger("id"); id del dato de PAGO DE VENTA
                        Gasto g = Gasto.first("dato_id = ? and fecha = ?", idDato, dateToMySQLDate(Calendar.getInstance().getTime(), false));
                        if (g != null) {
                            BigDecimal monto = g.getBigDecimal("monto");
                            monto = monto.add(pagoEfectivo.getPago());
                            g.setBigDecimal("monto", monto);
                            g.saveIt();
                        } else {
                            Gasto ga = Gasto.create("dato_id", idDato, "monto", pagoEfectivo.getPago(), "fecha", dateToMySQLDate(Calendar.getInstance().getTime(), false), "descrip", "PAGO DE CUENTA CORRIENTE");
                            ga.saveIt();
                        }
                        Base.commitTransaction();
                    }
                    break;
                case FormaDePagoGui.RET_TARJETA:
                    break;
            }
            cargarSocioPantalla(s);

        }
        /*
         ESTO PERTENECE AL CONTROLADOR DE LA FICHA MEDICA
         */
        if (fichaMedicaGui != null) {
            if (ae.getSource() == fichaMedicaGui.getAceptar()) {
                if (fichaNueva) {
                    if (altaFicha()) {
                        JOptionPane.showMessageDialog(fichaMedicaGui, "Ficha creada exitosamente!");
                        fichaMedicaGui.dispose();
                    } else {
                        JOptionPane.showMessageDialog(fichaMedicaGui, "Ocurrio un error", "Error", JOptionPane.ERROR_MESSAGE);
                        fichaMedicaGui.dispose();
                    }
                }
                if (!fichaNueva) {
                    int ret = JOptionPane.showConfirmDialog(null, "Desea modificar la ficha?", null, JOptionPane.YES_NO_OPTION);
                    if (ret == JOptionPane.YES_OPTION) {
                        if (modFicha()) {
                            JOptionPane.showMessageDialog(fichaMedicaGui, "Ficha modificada exitosamente!");
                            fichaMedicaGui.dispose();
                        } else {
                            JOptionPane.showMessageDialog(fichaMedicaGui, "Ocurrio un error", "Error", JOptionPane.ERROR_MESSAGE);
                            fichaMedicaGui.dispose();
                        }

                    }
                }
            }

            if (ae.getSource() == fichaMedicaGui.getjButton1()) { // boton de eliminar ficha
                Socio s = Socio.first(" DNI = ?", clienteGui.getDni().getText());
                int ret = JOptionPane.showConfirmDialog(null, "Desea eliminar la ficha de " + s.getString("NOMBRE") + " " + s.getString("APELLIDO") + " ?", null, JOptionPane.YES_NO_OPTION);
                if (ret == JOptionPane.YES_OPTION) {
                    Ficha f = Ficha.first("ID_DATOS_PERS = ?", s.get("ID_DATOS_PERS"));
                    if (eliminarFicha(f)) {
                        JOptionPane.showMessageDialog(fichaMedicaGui, "Ficha eliminada exitosamente!");
                        fichaMedicaGui.dispose();
                    } else {
                        JOptionPane.showMessageDialog(fichaMedicaGui, "Ocurrio un error", "Error", JOptionPane.ERROR_MESSAGE);
                        fichaMedicaGui.dispose();
                    }
                }
            }
        }
        if (ae.getSource().equals(clienteGui.getBtnVerRutina())) {
            abrirBase();
            Socio soc = Socio.first("DNI = ?", clienteGui.getDni().getText());
            LazyList<Rutina> listaRutinas = Rutina.where("socio_id = ?", soc.get("ID_DATOS_PERS"));
            if (listaRutinas.isEmpty()) {
                int ret = JOptionPane.showConfirmDialog(null, "Este socio no posee rutinas. Decea agregar una?", null, JOptionPane.YES_NO_OPTION);
                if (ret == JOptionPane.YES_OPTION) {
                    guiCrearRutina.setVisible(true);
                    //controladorGuiCrearRutina.ActualizarListaEjercicios();
                    controladorGuiCrearRutina.ActualizarListaSocios();
                    //guiCrearRutina.getTxtSocio().setText(clienteGui.getNombre().getText()+" "+clienteGui.getApellido().getText());
                    //guiCrearRutina.getTxtIdSocio().setText(clienteGui.getDni().getText());
                }
            } else {
                GuiVerRutinas guiVerRutina;
                try {
                    guiVerRutina = new GuiVerRutinas(principal, false);
                    guiVerRutina.getTablaRutinasDefault().setRowCount(0);

                    guiVerRutina.getTxtSocio().setText(clienteGui.getNombre().getText() + " " + clienteGui.getApellido().getText());
                    Socio s = Socio.first("DNI = ?", clienteGui.getDni().getText());
                    guiVerRutina.getTxtID().setText(s.getString("ID_DATOS_PERS"));
                    guiVerRutina.setTitle("Rutinas de " + clienteGui.getNombre().getText() + " " + clienteGui.getApellido().getText());
                    for (Rutina r : listaRutinas) {
                        Object[] row = new Object[3];
                        row[0] = r.get("id");
                        //row[1] = r.get("dia");
                        row[1] = dateToMySQLDate(r.getDate("fecha_inicio"), true);
                        row[2] = dateToMySQLDate(r.getDate("fecha_fin"), true);
                        guiVerRutina.getTablaRutinasDefault().addRow(row);
                    }
                    guiVerRutina.setVisible(true);
                } catch (JRException ex) {
                    Logger.getLogger(ControladorAbmCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControladorAbmCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ControladorAbmCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        if (ae.getSource().equals(clienteGui.getBtnDietas())) {
            DietasSocioGui dietaSocioGui = new DietasSocioGui(principal, false, Socio.findFirst("dni =?", clienteGui.getDni().getText()).getInteger("id_datos_pers"));
            dietaSocioGui.setLocationRelativeTo(clienteGui);
            dietaSocioGui.setVisible(true);

        }
        if (ae.getSource() == clienteGui.getBtnAddPhoto()) {
            if(s==null){
                JOptionPane.showMessageDialog(clienteGui, "Primero de de alta un socio");
            }else{
                WebCam webCam = new WebCam(null, true, s.getInteger("id_datos_pers"));
                webCam.setLocationRelativeTo(clienteGui);
                webCam.setVisible(true);

                clienteGui.setPicture(s.getInteger("ID_DATOS_PERS"));
                webCam.webcam.close();
            }

        }
                if(ae.getSource()== clienteGui.getBtnDeletePhoto()){
            File imVieja=new File(System.getProperty("user.dir")+"/user_images/"+s.getInteger("ID_DATOS_PERS")+".jpg");
            boolean result =imVieja.delete();
            if(result){//se borro bien{
                JOptionPane.showMessageDialog(clienteGui, "Imagen borrada exitosamente");
                clienteGui.setPicture(-1);
            }
            
        }
    }

    private boolean eliminarFicha(Ficha f) {
        f.delete();
        return true;
    }

    private boolean altaFicha() {
        Socio s = Socio.first(" DNI = ?", clienteGui.getDni().getText());
        Ficha nueva = Ficha.create("ID_DATOS_PERS", s.get("ID_DATOS_PERS"), "TEL_EMERG", fichaMedicaGui.getTelEmergencia().getText(), "ALERGICO", fichaMedicaGui.getTextoAlergias().getText(), "MEDICAM", fichaMedicaGui.getTextoMedicamentos().getText(), "OBSERV", fichaMedicaGui.getObservaciones().getText(), "GRUPO_SANG", fichaMedicaGui.getLetraSangui().getSelectedItem(), "FACTOR", fichaMedicaGui.getSigno().getSelectedItem());
        if (fichaMedicaGui.getArtrosis().isSelected()) {
            nueva.set("ARTROSIS", 1);
        } else {
            nueva.set("ARTROSIS", 0);
        }
        if (fichaMedicaGui.getAsma().isSelected()) {
            nueva.set("ASMA", 1);
        } else {
            nueva.set("ASMA", 0);
        }
        if (fichaMedicaGui.getCardiaco().isSelected()) {
            nueva.set("CARDIACO", 1);
        } else {
            nueva.set("CARDIACO", 0);
        }
        if (fichaMedicaGui.getDiabetes().isSelected()) {
            nueva.set("DIABETES", 1);
        } else {
            nueva.set("DIABETES", 0);
        }
        if (fichaMedicaGui.getEmbarazo().isSelected()) {
            nueva.set("EMBARAZO", 1);
        } else {
            nueva.set("EMBARAZO", 0);
        }
        if (fichaMedicaGui.getEndocrinologia().isSelected()) {
            nueva.set("ENDOCRINOLOGIA", 1);
        } else {
            nueva.set("ENDOCRINOLOGIA", 0);
        }
        if (fichaMedicaGui.getHuesoLigam().isSelected()) {
            nueva.set("HUESOS", 1);
        } else {
            nueva.set("HUESOS", 0);
        }
        if (fichaMedicaGui.getEnfPulmonar().isSelected()) {
            nueva.set("PULMONARES", 1);
        } else {
            nueva.set("PULMONARES", 0);
        }
        if (fichaMedicaGui.getEpileptico().isSelected()) {
            nueva.set("EPILEPTICO", 1);
        } else {
            nueva.set("EPILEPTICO", 0);
        }
        if (fichaMedicaGui.getHipertension().isSelected()) {
            nueva.set("HIPERTENSION", 1);
        } else {
            nueva.set("HIPERTENSION", 0);
        }
        if (fichaMedicaGui.getLesionDeportiva().isSelected()) {
            nueva.set("DEPORTIVA", 1);
        } else {
            nueva.set("DEPORTIVA", 0);
        }
        if (fichaMedicaGui.getObesidad().isSelected()) {
            nueva.set("OBESIDAD", 1);
        } else {
            nueva.set("OBESIDAD", 0);
        }
        if (fichaMedicaGui.getReuma().isSelected()) {
            nueva.set("REUMA", 1);
        } else {
            nueva.set("REUMA", 0);
        }
        nueva.saveIt();
        return true;
    }

    private boolean modFicha() {
        Socio s = Socio.first(" DNI = ?", clienteGui.getDni().getText());
        Ficha nueva = Ficha.first("ID_DATOS_PERS = ?", s.get("ID_DATOS_PERS"));
        nueva.set("TEL_EMERG", fichaMedicaGui.getTelEmergencia().getText(), "ALERGICO", fichaMedicaGui.getTextoAlergias().getText(), "MEDICAM", fichaMedicaGui.getTextoMedicamentos().getText(), "OBSERV", fichaMedicaGui.getObservaciones().getText(), "GRUPO_SANG", fichaMedicaGui.getLetraSangui().getSelectedItem(), "FACTOR", fichaMedicaGui.getSigno().getSelectedItem());
        if (fichaMedicaGui.getArtrosis().isSelected()) {
            nueva.set("ARTROSIS", 1);
        } else {
            nueva.set("ARTROSIS", 0);
        }
        if (fichaMedicaGui.getAsma().isSelected()) {
            nueva.set("ASMA", 1);
        } else {
            nueva.set("ASMA", 0);
        }
        if (fichaMedicaGui.getCardiaco().isSelected()) {
            nueva.set("CARDIACO", 1);
        } else {
            nueva.set("CARDIACO", 0);
        }
        if (fichaMedicaGui.getDiabetes().isSelected()) {
            nueva.set("DIABETES", 1);
        } else {
            nueva.set("DIABETES", 0);
        }
        if (fichaMedicaGui.getEmbarazo().isSelected()) {
            nueva.set("EMBARAZO", 1);
        } else {
            nueva.set("EMBARAZO", 0);
        }
        if (fichaMedicaGui.getEndocrinologia().isSelected()) {
            nueva.set("ENDOCRINOLOGIA", 1);
        } else {
            nueva.set("ENDOCRINOLOGIA", 0);
        }
        if (fichaMedicaGui.getHuesoLigam().isSelected()) {
            nueva.set("HUESOS", 1);
        } else {
            nueva.set("HUESOS", 0);
        }
        if (fichaMedicaGui.getEnfPulmonar().isSelected()) {
            nueva.set("PULMONARES", 1);
        } else {
            nueva.set("PULMONARES", 0);
        }
        if (fichaMedicaGui.getEpileptico().isSelected()) {
            nueva.set("EPILEPTICO", 1);
        } else {
            nueva.set("EPILEPTICO", 0);
        }
        if (fichaMedicaGui.getHipertension().isSelected()) {
            nueva.set("HIPERTENSION", 1);
        } else {
            nueva.set("HIPERTENSION", 0);
        }
        if (fichaMedicaGui.getLesionDeportiva().isSelected()) {
            nueva.set("DEPORTIVA", 1);
        } else {
            nueva.set("DEPORTIVA", 0);
        }
        if (fichaMedicaGui.getObesidad().isSelected()) {
            nueva.set("OBESIDAD", 1);
        } else {
            nueva.set("OBESIDAD", 0);
        }
        if (fichaMedicaGui.getReuma().isSelected()) {
            nueva.set("REUMA", 1);
        } else {
            nueva.set("REUMA", 0);
        }
        nueva.saveIt();
        return true;
    }

    public void setIsNuevo(boolean isNuevo) {
        this.isNuevo = isNuevo;
    }

    public void abrirBase() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/" + DatosGenericos.dataBaseName, DatosGenericos.userDB, DatosGenericos.passwordDB);
        }
    }

    public void setSocio(Socio s) {
        this.s = s;
    }

    private void cargarSocioPantalla(Socio socio) {
        clienteGui.getNombre().setText(s.getString("NOMBRE"));
        clienteGui.getApellido().setText(s.getString("APELLIDO"));
        clienteGui.getTelefono().setText(s.getString("TEL"));
        clienteGui.getDni().setText(s.getString("DNI"));
        clienteGui.getDireccion().setText(s.getString("DIR"));
        if (s.getString("SEXO").equals("M")) {
            clienteGui.getSexo().setSelectedIndex(1);
        } else {
            clienteGui.getSexo().setSelectedIndex(0);
        }
        clienteGui.getFechaNacimJDate().setDate(s.getDate("FECHA_NAC"));;
        clienteGui.getLabelFechaIngreso().setText(dateToMySQLDate(s.getDate("FECHA_ING"), true));
        clienteGui.getLabelFechaVenci().setText(dateToMySQLDate(s.getDate("FECHA_PROX_PAGO"), true));
        clienteGui.getSaldoCorriente().setText(s.getBigDecimal("cuenta_corriente").toString());
        clienteGui.getTablaActivDefault().setRowCount(0);
        LazyList<Socioarancel> ListSocAran = Socioarancel.where("id_socio = ?", s.get("ID_DATOS_PERS"));
        Iterator<Socioarancel> ite = ListSocAran.iterator();
        clienteGui.setPicture(socio.getInteger("ID_DATOS_PERS"));            

        while (ite.hasNext()) {
            Socioarancel sa = ite.next();
            Arancel a = Arancel.first("id = ?", sa.get("id_arancel"));
            Object row1[] = new Object[2];
            row1[0] = a.getString("nombre");
            row1[1] = true;
            clienteGui.getTablaActivDefault().addRow(row1);
            /*Se debe abrir la ventana de clientes para permitir el alta de giles*/
        }
    }

}
