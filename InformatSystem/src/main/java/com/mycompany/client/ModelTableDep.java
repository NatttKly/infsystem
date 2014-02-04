/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admin
 */
public class ModelTableDep extends AbstractTableModel{
    
    ArrayList <Department> departments;

    public ModelTableDep(ArrayList<Department> departments) {
        super();
        this.departments = departments;
    }

    @Override
    public int getRowCount() {
      return departments.size();
    }

    @Override
    public int getColumnCount() {
     return 3;
    }

    @Override
    public Object getValueAt(int r, int c) {
     switch (c) {
            case 0:
                return departments.get(r).getId();
            case 1:
                return departments.get(r).getName();
            case 2:
                return departments.get(r).getManager().getName();
            
             default: 
                 return ""; 
                
        }
    }
    
    @Override
public String getColumnName(int c) {
    switch (c) {
        case 0:
            return "Id";
        case 1:
            return "Department";
        case 2:
            return "Name";  
        default: 
            return "Other";   
    }
  } 
    
    
}
