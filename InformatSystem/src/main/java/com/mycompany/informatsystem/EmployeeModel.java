/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.informatsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Хранит и изменяет список сотрудников.
 *
 * @author Nataly Klimina
 */
public class EmployeeModel implements Serializable {

    private Map<Integer, Employee> employees;
    private final String fileName = "res/Employees.txt";
    private final String fileEmpID = "res/EmployeesID.txt";
    Integer lastId;
    public EmployeeModel() {
        this.employees = new HashMap<>();
        loadLastID();
        load();
    }
    /**
     * Загрузка последнего ID
     * @return 
     */
    public String loadLastID() {
        try {
            FileInputStream inFileSerializ = new FileInputStream(fileEmpID);
            ObjectInputStream ois = new ObjectInputStream(inFileSerializ);
            lastId = (Integer) ois.readObject();
            return null;
        } catch (FileNotFoundException e) {
            return ("Error: file not found.");
        } catch (IOException e) {
            lastId = 1;
            return null;
        } catch (ClassNotFoundException e) {
            return("Class not found.");
        }
    }
    /**
     * Сохранение последнего ID
     * @return 
     */
     public String saveLastId() {
        try {

            FileOutputStream outFileSerializ = new FileOutputStream(fileEmpID);
            Object objSave = lastId;
            ObjectOutputStream oos = new ObjectOutputStream(outFileSerializ);
            oos.writeObject(objSave);
            return null;
        } catch (FileNotFoundException e) {return "Error: file not found.";
        } catch (IOException e) {return "Error.";
        }
    }
    /**
     * Загружает из файла список сотрудников.
     */
    public String load() {
        try {
            FileInputStream inFileSerializ = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(inFileSerializ);
            this.employees = (Map<Integer, Employee>) ois.readObject();
            System.out.println("load:  "+employees.size());
            return "Employyes list loaded.";
        } catch (FileNotFoundException e) {
            return ("Error: file not found.");
        } catch (IOException e) {
            return("Error: file is empty.");
        } catch (ClassNotFoundException e) {
            return("Class not found.");
        }
    }

    /**
     * Сохраняет в файл список сотрудников.
     */
    public String save() {
        try {

            FileOutputStream outFileSerializ = new FileOutputStream(fileName);
            Object objSave = employees;
            ObjectOutputStream oos = new ObjectOutputStream(outFileSerializ);
            oos.writeObject(objSave);
            return "List of employees saved.";
        } catch (FileNotFoundException e) {return "Error: file not found.";
        } catch (IOException e) {return "Error.";
        }
    }

    /**
     *
     * @return список сотрудников
     */
    public Map<Integer, Employee> getEmployees() {
        return employees;
    }

    /**
     * Создает сотрудника.
     *
     * @param name имя сотрудника
     * @param phone телефон сотрудника
     * @param salary зарплата сотрудника
     */
    public String create(String name, int phone, double salary) {
        Integer ID = lastId;
        lastId++;
        Employee employee = new Employee(name,  phone, salary);
        employee.setId(ID);
        employees.put(ID, employee);
        this.save();
        this.saveLastId();
        return("Employee created.");
    }

    /**
     * Удаляет сотрудника.
     *
     * @param id id сотрудника
     */
    public String delete(Integer id) {
        employees.remove(id);
        return ("Employee removed.");
     }
    
    
    /**
     * Устанавливает департамент для выбранного сотрудника.
     *
     * @param empID id сотрудника
     * @param depart департамент
     */
    public String setDepartment(int empID, Department depart) {
        Employee employee = employees.get(empID);
        employee.setDepartment(depart);
        return "Changes has been assigned.";
    }
    /**
     * Устанавливает имя сотрудника
     * @param empID
     * @param name
     * @return 
     */
    public String setName(int empID, String name) {
        Employee employee = employees.get(empID);
        employee.setName(name);
        return "Changes has been assigned.";
    }
    /**
     * Устанавливает номер телефона сотрудника
     * @param empID
     * @param phone
     * @return 
     */
    public String setPhone(int empID, int phone) {
        Employee employee = employees.get(empID);
        employee.setPhone(phone);
        return "Changes has been assigned.";
    }
    /**
     * Устанавливает зарплату сотрудника
     * @param empID
     * @param sal
     * @return 
     */
    public String setSalary(int empID, Double sal) {
        Employee employee = employees.get(empID);
        employee.setSalary(sal);
        return "Changes has been assigned.";
    }
    /**
     * Поиск имени сотрудника по его id.
     *
     * @param id id сотрудника
     * @return имя сотрудника
     */
    public String findNameByID(Integer id) {
        return employees.get(id).getName();
    }

    /**
     * Поиск сотрудника по id.
     *
     * @param id id сотрудника
     * @return сотрудник
     */
    public Employee findEmployeeByID(Integer id) {
        return employees.get(id);
    }

    /**
     * При удалении департамента провряет и удаляет его у сотрудников,которые в
     * нем числились.
     *
     * @param name название департамента
     */
    public void deleteDepartment(String name) {
        for (Iterator<Employee> it = employees.values().iterator(); it.hasNext();) {

            Employee emp = it.next();
            if (name.equals(emp.getDepartmentName())) {
                //Department defaultDepart = new Department();
                emp.setDepartment(null);
            }
        }
    }
    
   /**
    * Формирует список сотрудников для вывода на экран
    * @return 
    */
    public ArrayList <String>  EmpToView () {
        int WIDTH_ID = 4;
        int WIDTH_NAME = 20;
        int WIDTH_DEPART = 15;
        int WIDTH_PHONE = 15;
        int WIDTH_SALARY = 8;
        
        ArrayList <String> strings = new ArrayList();
        
        strings.add ("+----+--------------------+---------------+---------------+--------+");
        strings.add ("| ID |   NAME EMPLOYEE    |   DEPARTMENT  |        PHONE  | SALARY |");
        strings.add ("+----+--------------------+---------------+---------------+--------+");
        
        for (Map.Entry<Integer, Employee> entry : employees.entrySet()) {
            
            String idStr = entry.getKey().toString();
            
            for (int i = idStr.length(); i < WIDTH_ID; i++) {
                idStr = idStr + " ";
            }
            
            String nameStr = entry.getValue().getName();
            if (nameStr.length() > WIDTH_NAME ) {
                nameStr = nameStr.substring(0, WIDTH_NAME);
            }
            else {
            for (int i = nameStr.length(); i < WIDTH_NAME; i++) {
                nameStr = nameStr + " ";
            }
            }
            
            String depStr = entry.getValue().getDepartmentName();
            if (depStr==null) {
                depStr = "NONE";
            }
            if (depStr.length() > WIDTH_DEPART ) {
                depStr = depStr.substring(0, WIDTH_DEPART);
            }
            
            else
            {
            for (int i = depStr.length(); i < WIDTH_DEPART; i++) {
                depStr = depStr + " ";
            }}
            
            String phoneStr = Integer.toString(entry.getValue().getPhone());
            
            for (int i = phoneStr.length(); i < WIDTH_PHONE; i++) {
                phoneStr = phoneStr + " ";
            }
            
            String salStr = Double.toString(entry.getValue().getSalary());
            
            for (int i = salStr.length(); i < WIDTH_SALARY; i++) {
                salStr = salStr + " ";
            }
            
            String resultStr = "|"+idStr+"|"+nameStr+"|"+depStr+"|"+phoneStr+"|"+salStr+"|";
            strings.add(resultStr);
            
        }
        strings.add("+----+--------------------+---------------+---------------+--------+");
        return strings;
    }//toview
    
    
    
    
    
    
    
}