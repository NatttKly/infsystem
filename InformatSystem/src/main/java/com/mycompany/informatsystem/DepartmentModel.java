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
import java.util.Iterator;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Хранит и изменяет список департаментов.
 *
 * @author Nataly Klimina
 */
public class DepartmentModel implements Serializable {

    private  Map<Integer, Department> departments = new HashMap<>();
    String fileName = "res/Departments.txt";
    String fileDepId = "res/DepartmentsID.txt";
    Integer lastId;

    public DepartmentModel() {
    }

    /**
     * Загрузка последнего ID
     *
     * @return
     */
    public String loadLastId() {
        try {
            FileInputStream inFileSerialize = new FileInputStream(fileDepId);
            try (ObjectInputStream ois = new ObjectInputStream(inFileSerialize)) {
                lastId = (Integer) ois.readObject();
                return null;
            }
        } catch (FileNotFoundException e) {
            return "Error: file not found.";
        } catch (IOException | ClassNotFoundException e) {
            lastId = 1;
            return null;
        }
    }

    /**
     * Сохранение последнего ID
     *
     * @return
     */
    public String saveLastId() {
        try {

            FileOutputStream outFileSerializ = new FileOutputStream(fileDepId);
            Object objSave = lastId;
            try (ObjectOutputStream oos = new ObjectOutputStream(outFileSerializ)) {
                oos.writeObject(objSave);
                return null;
            }
        } catch (FileNotFoundException e) {
            return "Error: file not found.";
        } catch (IOException e) {
            return "Error.";
        }

    }

    /**
     * Создает департамент.
     *
     * @param name название департамента
     */
    public String create(String name) {
        Integer ID = lastId;
        lastId++;
        Employee defaultEmployee = new Employee();
        Department department = new Department(name, defaultEmployee);
        department.setId(ID);
        departments.put(ID, department);
        return ("Department created.");
    }

    /**
     * Удаляет департамент.
     *
     * @param id id департамента
     */
    public String delete(Integer id) {
        departments.remove(id);
        return ("Department removed.");
    }

    /**
     * Загружает список департаментов из файла.
     */
    public String load() {
        try {
            FileInputStream inFileSerializ = new FileInputStream(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(inFileSerializ)) {
                departments = (Map<Integer, Department>) ois.readObject();
                return "Departments list loaded.";
            }
        } catch (FileNotFoundException e) {
            return "Error: file not found.";
        } catch (IOException | ClassNotFoundException e) {
            return "Error: file is empty.";
        }
    }

    /**
     * Сохраняет список департаментов в файл.
     */
    public String save() {
        try {

            FileOutputStream outFileSerializ = new FileOutputStream(fileName);
            Object objSave = departments;
            try (ObjectOutputStream oos = new ObjectOutputStream(outFileSerializ)) {
                oos.writeObject(objSave);
                return "List of departments saved";
            }
        } catch (FileNotFoundException e) {
            return "Error: file not found.";
        } catch (IOException e) {
            return "Error.";
        }

    }

    /**
     *
     * @return список департаментов
     */
    public Map<Integer, Department> getDepartments() {
        return departments;
    }

    /**
     * Поиск департамента по id
     *
     * @param id id департамента
     * @return департамент
     */
    public Department findByID(Integer id) {
        return departments.get(id);
    }

    /**
     * Поиск названия департамента по его id.
     *
     * @param id id департамента
     * @return название департамента
     */
    public String findNameByID(Integer id) {
        return departments.get(id).getName();
    }

    /**
     * Устанавливает начальника для выбранного департамента.
     *
     * @param id id департамента
     * @param emp сотрудник
     */
    public String setManager(Integer id, Employee emp) {
        Department dep = departments.get(id);
        dep.setManager(emp);
        return ("Manager have been assigned");
    }

    /**
     * Кстановка имени департамента
     *
     * @param id
     * @param name
     * @return
     */
    public String setName(Integer id, String name) {
        Department dep = departments.get(id);
        dep.setName(name);
        return ("Manager have been assigned");
    }

    /**
     * При удалнии сотрудника проверяет и удаляет его из департаментов, где он
     * являлся начальником.
     *
     * @param name имя сотрудника
     */
    public void deleteEmployee(String name) {
        for (Iterator<Department> it = departments.values().iterator(); it.hasNext();) {
            Department dep = it.next();
            if (name.equals(dep.getManager().getName())) {
                Employee defaultManager = new Employee();
                dep.setManager(defaultManager);
            }
        }
    }

    /**
     * Формирует список департаментов для вывода на экран
     *
     * @return
     */
    public ArrayList<String> DepToView() {
        int WIDTH_ID = 4;
        int WIDTH_NAME = 15;
        int WIDTH_MANAGER = 20;

        ArrayList<String> strings = new ArrayList();

        strings.add("+----+---------------+--------------------+");
        strings.add("| ID |  DEPARTMENT   |    NAME MANAGER    |");
        strings.add("+----+---------------+--------------------+");

        for (Map.Entry<Integer, Department> entry : departments.entrySet()) {

            String idStr = entry.getKey().toString();

            for (int i = idStr.length(); i < WIDTH_ID; i++) {
                idStr = idStr + " ";
            }

            String nameStr = entry.getValue().getName();
            if (nameStr.length() > WIDTH_NAME) {
                nameStr = nameStr.substring(0, WIDTH_NAME);
            } else {
                for (int i = nameStr.length(); i < WIDTH_NAME; i++) {
                    nameStr = nameStr + " ";
                }
            }
            String mgrStr = entry.getValue().getManager().getName();
            if (mgrStr == null) {
                mgrStr = "NONE";
            }
            if (mgrStr.length() > WIDTH_MANAGER) {
                mgrStr = mgrStr.substring(0, WIDTH_MANAGER);
            } else {
                for (int i = mgrStr.length(); i < WIDTH_MANAGER; i++) {
                    mgrStr = mgrStr + " ";
                }
            }
            String resultStr = "|" + idStr + "|" + nameStr + "|" + mgrStr + "|";
            strings.add(resultStr);
        }

        strings.add("+----+---------------+--------------------+");
        return strings;
    }//toview
}

