/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.informatsystem;

import java.io.Serializable;

/**
 * Устанавливает и хранит данные о сотруднике.
 *
 * @author Nataly Klimina
 */
public class Employee implements Serializable {

    private String name;
    private Department depart;
    private int phone;
    private double salary;
    private int id;

    public Employee() {
    }

    /**
     *
     * @param nameEmployee Содержит имя сотрудника
     * @param phoneEmployee Содержит телефон сотрудника
     * @param salaryEmployee Содержит зарплату сотрудника
     */
    public Employee(String nameEmployee,  int phoneEmployee, double salaryEmployee) {

        name = nameEmployee;
        phone = phoneEmployee;
        salary = salaryEmployee;
    }

    /**
     *
     * @param nameEmployee имя сотрудника
     */
    public void setName(String nameEmployee) {
        name = nameEmployee;
    }

    /**
     *
     * @return имя сотрудника
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param department департамент сотрудника
     */
    public void setDepartment(Department department) {
        depart = department;
       
        
    }

    /**
     *
     * @return департамент сотрудника
     */
    public Department getDepartment() {
        return depart;
    }

    /**
     *
     * @return название департамента сотрудника
     */
    public String getDepartmentName() {
        if (depart == null) return "None";
        return depart.getName();
    }

    /**
     *
     * @param phoneEmployee телефон сотрудника
     */
    public void setPhone(int phoneEmployee) {
        phone = phoneEmployee;
    }

    /**
     *
     * @return телефон сотрудника
     */
    public int getPhone() {
        return phone;
    }

    /**
     *
     * @param salaryEmployee зарплата сотрудника
     */
    public void setSalary(double salaryEmployee) {
        salary = salaryEmployee;
    }

    /**
     *
     * @return зарплата сотрудника
     */
    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
