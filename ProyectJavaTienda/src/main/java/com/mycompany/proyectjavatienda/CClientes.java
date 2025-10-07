/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectjavatienda;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CClientes {
    public void mostrarClientes(JTable paramTablaClientes){
        CConexion objetoConexion = new CConexion();
        DefaultTableModel modelo= new DefaultTableModel();
        String sql="";
        modelo.addColumn("ID");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Direccion");
        modelo.addColumn("Correo");
        modelo.addColumn("Celular");
        paramTablaClientes.setModel(modelo);
        sql="Select * from Clientes;";
        String [] datos=new String[6];
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
                datos[5]=rs.getString(6);
                modelo.addRow(datos);
            }
            paramTablaClientes.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se mostraron los registros,error :"+e.toString());
        }
        
    }
    public void SeleccionarCliente(JTable paramTablaClientes,JTextField paramID,JTextField paramNombres,
            JTextField paramApellidos,JTextField paramDireccion,JTextField paramCorreo,JTextField paramCelular){
        try{
            int fila =paramTablaClientes.getSelectedRow();
            if (fila>=0){
                paramID.setText(paramTablaClientes.getValueAt(fila, 0).toString());
                paramNombres.setText(paramTablaClientes.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaClientes.getValueAt(fila, 2).toString());
                paramDireccion.setText(paramTablaClientes.getValueAt(fila, 3).toString());
                paramCorreo.setText(paramTablaClientes.getValueAt(fila, 4).toString());
                paramCelular.setText(paramTablaClientes.getValueAt(fila, 5).toString());
                
            }
            else{
                JOptionPane.showMessageDialog(null,"No se selecciono registros,error :");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error de seleccion :"+e.toString());
        }
        
    }
    public void SeleccionarClientes(JTable paramTablaCliente,JTextField paramID,JTextField paramNombres,JTextField paramApellidos 
                ,JTextField paramDireccion,JTextField paramCorreo,JTextField paramCelular){
        try{
            int fila= paramTablaCliente.getSelectedRow();
            if(fila>0){
                paramID.setText(paramTablaCliente.getValueAt(fila, 0).toString());
                paramNombres.setText(paramTablaCliente.getValueAt(fila, 1).toString());
                paramApellidos.setText(paramTablaCliente.getValueAt(fila, 2).toString());
                paramDireccion.setText(paramTablaCliente.getValueAt(fila, 3).toString());
                paramCorreo.setText(paramTablaCliente.getValueAt(fila, 4).toString());
                paramCelular.setText(paramTablaCliente.getValueAt(fila, 5).toString());
            }else{
                JOptionPane.showMessageDialog(null,"No se selecciono registros,error :");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error de seleccion,error :"+e.toString());
        }
    }
}
