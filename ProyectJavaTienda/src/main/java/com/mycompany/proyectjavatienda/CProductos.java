/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectjavatienda;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class CProductos {
    public void mostrarProductos(JTable paramTablaProductos){
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo= new DefaultTableModel();
        String sql="";
        modelo.addColumn("ID");
        modelo.addColumn("Categoria");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        paramTablaProductos.setModel(modelo);
        sql="Select * from Productos;";
        String [] datos=new String[5];
        Statement st;
        try{
            st=objetoConexion.establecerConexion().createStatement();
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                datos[4]=rs.getString(5);                    
                //datos[5]=rs.getString(6);
                modelo.addRow(datos);
            }
            paramTablaProductos.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se mostraron los registros,error :"+e.toString());
        }
    }   
    public void SeleccionarProducto(JTable paramTablaProductos, JTextField paramID, JComboBox paramCategoria,
                                    JTextField paramNombre, JTextField paramPrecio, JTextField paramStock) {
        try {
            int fila = paramTablaProductos.getSelectedRow();
            if (fila >= 0) {
                paramID.setText(paramTablaProductos.getValueAt(fila, 0).toString());
                paramCategoria.setSelectedItem(paramTablaProductos.getValueAt(fila, 1).toString());
                paramNombre.setText(paramTablaProductos.getValueAt(fila, 2).toString());
                paramPrecio.setText(paramTablaProductos.getValueAt(fila, 3).toString());
                paramStock.setText(paramTablaProductos.getValueAt(fila, 4).toString());
            } else {
                JOptionPane.showMessageDialog(null, "No se seleccionó ningún registro.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de selección: " + e.toString());
        }
    }

    // Método para insertar un nuevo producto
    public void InsertarProducto(JComboBox paramCategoria, JTextField paramNombre,
                                 JTextField paramPrecio, JTextField paramStock) {
        CConexion objetoConexion = new CConexion();
        String consulta = "INSERT INTO Productos (Categoria, Nombre, Precio, Stock) VALUES (?, ?, ?, ?);";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, paramCategoria.getSelectedItem().toString());
            cs.setString(2, paramNombre.getText());
            cs.setBigDecimal(3, new java.math.BigDecimal(paramPrecio.getText()));
            cs.setInt(4, Integer.parseInt(paramStock.getText()));

            cs.execute();
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el producto.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el producto: " + e.toString());
        }
    }

    // Método para modificar un producto existente
    public void ModificarProducto(JTextField paramID, JComboBox paramCategoria, JTextField paramNombre,
                                  JTextField paramPrecio, JTextField paramStock) {
        CConexion objetoConexion = new CConexion();
        String consulta = "UPDATE Productos SET Categoria=?, Nombre=?, Precio=?, Stock=? WHERE ID=?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setString(1, paramCategoria.getSelectedItem().toString());
            cs.setString(2, paramNombre.getText());
            cs.setBigDecimal(3, new java.math.BigDecimal(paramPrecio.getText()));
            cs.setInt(4, Integer.parseInt(paramStock.getText()));
            cs.setInt(5, Integer.parseInt(paramID.getText()));

            cs.execute();
            JOptionPane.showMessageDialog(null, "Se modificó correctamente el producto.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar el producto: " + e.toString());
        }
    }

    // Método para eliminar un producto
    public void EliminarProducto(JTextField paramID) {
        CConexion objetoConexion = new CConexion();
        String consulta = "DELETE FROM Productos WHERE ID=?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            cs.setInt(1, Integer.parseInt(paramID.getText()));
            cs.execute();
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el producto.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.toString());
        }
    }

}
