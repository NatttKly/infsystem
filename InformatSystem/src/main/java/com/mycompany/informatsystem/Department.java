/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.informatsystem;

import java.io.Serializable;

/**
 * Устанавливает и хранит данные о департаменте.
 *
 * @author Nataly Klimina
 */
public class Department implements Serializable {

    private String name;
    private Employee manager;
    private int id;
    public Department() {
    }

    /**
     *
     * @param nameDepartment название департамента
     * @param headDepartment начальник департамента
     */
    public Department(String nameDepartment, Employee headDepartment) {
        name = nameDepartment;
        manager = headDepartment;
    }

    /**
     *
     * @param departName название департамента
     */
    public void setName(String departName) {
        name = departName;
    }

    /**
     *
     * @return название департамента
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param employee начальник департамента
     */
    public void setManager(Employee employee) {
        manager = employee;
    }

    /**
     *
     * @return начальник департамента
     */
    public Employee getManager() {
        return manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
