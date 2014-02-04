/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ClientController {
    
    ArrayList <Employee> employees;
    ArrayList <Department> departments;
    String[] stringForSend = new String[5];//= "";
    SwingView view ;//= new SwingView(this);
    Client client;
    public ClientController(Client client) {
        super();
        this.client = client;
        view = new SwingView(this);
    }

    public String[] getStringForSend() {
        return stringForSend;
    }
    
    
    void parsingData (Object emp,Object dep) {
        
        this.employees = (ArrayList <Employee>)emp;
        this.departments = (ArrayList <Department>)dep;    
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }
    
    public void createEmployee (String name, String phone, String sal) {
        
        try {
      int phoneEmp = Integer.parseInt(phone);
      double salary = Double.parseDouble(sal);
      stringForSend[0]="createEmp";
      stringForSend[1]=name;
      stringForSend[2]=phone;
      stringForSend[3]=sal;
      stringForSend[4]="";
      client.setIsString(true);
      
      view.changeEmpTable ();
      } catch  (Exception e) {
          view.showError("Error:Incorrect data format");
  } 
    }
}
