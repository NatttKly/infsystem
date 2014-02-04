/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.informatsystem;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SController {
   ArrayList <Employee> list;
   DepartmentModel depModel = new DepartmentModel();
    EmployeeModel empModel = new EmployeeModel(); 
    
    Object convertData (String[] data) {//Object
        switch (data[0]) {
            case "createEmp":
                 
                empModel.create(data[1], Integer.parseInt(data[2]),Double.parseDouble(data[3] ));
                this.list = new ArrayList(empModel.getEmployees().values());//list = (ArrayList <Employee>)empModel.getEmployees().values();
                return list;
            case "createDep":    
                depModel.create(data[1]);
                return (ArrayList <Department>)depModel.getDepartments();
            case "changeEmp":    
                empModel.setName(Integer.parseInt(data[1]),data[2]);
                empModel.setPhone(Integer.parseInt(data[1]),Integer.parseInt( data[3]));
                empModel.setSalary(Integer.parseInt(data[1]), Double.parseDouble(data[4]));
                return (ArrayList <Employee>)empModel.getEmployees();
            case "changeDep":    
                depModel.setName(Integer.parseInt(data[1]), data[2]);
                return (ArrayList <Department>)depModel.getDepartments();
            case "delEmp":    
                empModel.delete(Integer.parseInt(data[1]));
                return (ArrayList <Employee>)empModel.getEmployees();
            case "delDep":    
                depModel.delete(Integer.parseInt(data[1]));
                return (ArrayList <Department>)depModel.getDepartments();
                 
        }
      return null;
    }
    
}
