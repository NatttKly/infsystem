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
public class ModelTableEmp extends AbstractTableModel{

    ArrayList <Employee> employees;
    
    public ModelTableEmp(ArrayList <Employee> employees) {
    super();
    this.employees = employees;
    }

    
    
    
    @Override
    public int getRowCount() {
     return employees.size();
    }

    @Override
    public int getColumnCount() {
    return 5;
    }

    @Override
    public Object getValueAt(int r, int c) {
    switch (c) {
            case 0:
                return employees.get(r).getId();
            case 1:
                return employees.get(r).getName();
            case 2:
                return employees.get(r).getPhone();
             case 3:
                return employees.get(r).getSalary();
             case 4:
                return employees.get(r).getDepartmentName();
             default: 
                  return "error"; 
    }
    
    }
     @Override
public String getColumnName(int c) {
    switch (c) {
        case 0:
            return "Id";
        case 1:
            return "Name";
        case 2:
            return "Phone"; 
        case 3:
            return "Salary"; 
        case 4:
            return "Department";     
        default: 
            return "Other";   
    }
  }

}
