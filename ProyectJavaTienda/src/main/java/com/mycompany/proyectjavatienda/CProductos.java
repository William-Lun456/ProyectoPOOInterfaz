/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectjavatienda;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
                //datos[5]=rs.getString(6);
                modelo.addRow(datos);
            }
            paramTablaProductos.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"No se mostraron los registros,error :"+e.toString());
        }
    }   
}
