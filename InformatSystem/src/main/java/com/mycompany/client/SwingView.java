/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Natalya Klimina
 */
public class SwingView extends JFrame {
ClientController controller;
//Dialog dialog;
   // int i = 0;
    //-------------some data for tables------------------        
    String[] elements= {"Вася", "Петя", "Иван"};
    
    JTabbedPane tabPanelMain = new JTabbedPane();
    JPanel tabEmp = new JPanel();
    JPanel tabDep = new JPanel();
    JPanel jpOnBox1 = new JPanel();
    JPanel jpOnBox2 = new JPanel();
    JPanel jpOnBox3 = new JPanel();
    JPanel jpOnBox4 = new JPanel();
    JPanel jpOnBox5 = new JPanel();
    JPanel jpOnBox6 = new JPanel();
    JPanel jpOnBox7 = new JPanel();
    JPanel jpOnBox8 = new JPanel();
    JTextField nameEmpTF = new JTextField();
    JTextField phoneEmpTF = new JTextField();
    JTextField salEmpTF = new JTextField();
    JTextField idEmpTF = new JTextField();
    JTextField idCangeEmp = new JTextField();
    JTextField dataEmp = new JTextField();
    JTextField idSetDepEmp = new JTextField();
    JTextField idDep = new JTextField();
    JTextField idSetMgr = new JTextField();
    JTextField idDelDep = new JTextField();
    JTextField nameDep = new JTextField();
    JLabel label = new JLabel("Enter name employee:");
    JLabel labe2 = new JLabel("Enter phone employee:");
    JLabel labe3 = new JLabel("Enter salary employee:");
    JLabel labe5 = new JLabel("Enter id employee:");
    JLabel labe6 = new JLabel("Enter id employee:");
    JLabel labe7 = new JLabel("Enter data:");
    JLabel labe8 = new JLabel("Enter id employee:");
    JLabel labe9 = new JLabel("Enter department:");
    JLabel labe10 = new JLabel("Enter id department:");
    JLabel labe11 = new JLabel("Enter id employee:");
    JLabel labe12 = new JLabel("Enter id department:");
    JLabel labe13 = new JLabel("Enter name department:");
    JLabel labe14 = new JLabel("Enter id department:");
    JLabel labe15 = new JLabel("Enter data:");
   // JLabel labelError = new JLabel("Error: Incorrect format data.");
    JButton buttonCreateEmp = new JButton("Add employee");
    JButton b2 = new JButton("Delete employee");
    JButton b3 = new JButton("Change employee");
    JButton b4 = new JButton("Set department");
    JButton b5 = new JButton("Set manager");
    JButton b6 = new JButton("Delete department");
    JButton b7 = new JButton("Create department");
    JButton b8 = new JButton("Change department");
    JComboBox combo = new JComboBox(elements);
    ModelTableEmp modEmp;
    JTable tableEmployee;
    JScrollPane scrollPaneEmployee;
    JTable tableDep;
    JScrollPane scrollPaneDep;
    
    
    public SwingView(ClientController controller) throws HeadlessException {
       
        modEmp = new ModelTableEmp(controller.getEmployees());
    tableEmployee = new JTable(modEmp);
    scrollPaneEmployee = new JScrollPane(tableEmployee);
    tableDep = new JTable(new ModelTableDep(controller.getDepartments()));
    scrollPaneDep = new JScrollPane(tableDep);
        this.controller = controller;
        Insets insets =getInsets();
        setSize(1050 + insets.left + insets.right,
                760 + insets.top + insets.bottom);
        Container pane = getContentPane();
        
        //dialog = new Dialog();
        pane.setLayout(null);
        pane.add(tabPanelMain);
        tabEmp.setLayout(null);
        tabDep.setLayout(null);
        tabPanelMain.addTab("Employees", tabEmp);
        tabPanelMain.addTab("Departments", tabDep);
        tabPanelMain.setBounds(20, 10, 1000, 700);
        //---------------table employee----------------------------------    
        tabEmp.add(scrollPaneEmployee);
        scrollPaneEmployee.setBounds(30, 20, 600, 400);
        //---------------table depart----------------------------------   
        tabDep.add(scrollPaneDep);
        scrollPaneDep.setBounds(30, 20, 600, 400);

        addBoxCreateEmp();
        addBoxDeleteEmp();
        addBoxSetDepartForEmp();
        addBoxChangeEmp();
        addBoxSetManager();
        addBoxCreateDepart();
        addBoxDeleteDepart();
        addBoxChangeDep();
        addActionListeners();
        
        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

//    private static void createAndShowGUI() {
//        JFrame.setDefaultLookAndFeelDecorated(true);
//
//        JFrame frame = new JFrame("AbsoluteLayoutDemo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        SwingView vieww = new SwingView(frame.getContentPane());// addComponentsToPane
//
//        Insets insets = frame.getInsets();
//        frame.setSize(1050 + insets.left + insets.right,
//                760 + insets.top + insets.bottom);
//        frame.setVisible(true);
//    }

//    public  void createGUI() {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }

    public void addBoxCreateEmp() {
        Box box1 = Box.createVerticalBox();
        tabEmp.add(box1);
        box1.setBorder(new TitledBorder("Create employee"));
        box1.setBounds(650, 5, 320, 180);
        jpOnBox1.setLayout(null);
        box1.add(jpOnBox1);
        jpOnBox1.add(nameEmpTF);
        nameEmpTF.setBounds(150, 5, 150, 20);
        jpOnBox1.add(phoneEmpTF);
        phoneEmpTF.setBounds(150, 40, 150, 20);
        jpOnBox1.add(salEmpTF);
        salEmpTF.setBounds(150, 80, 150, 20);
        jpOnBox1.add(label);
        jpOnBox1.add(labe2);
        jpOnBox1.add(labe3);
        label.setBounds(10, 5, 150, 20);
        labe2.setBounds(10, 40, 150, 20);
        labe3.setBounds(10, 80, 150, 20);
        jpOnBox1.add(buttonCreateEmp);
        buttonCreateEmp.setBounds(80, 115, 150, 30);
    }

    public void addBoxDeleteEmp() {
        Box box2 = Box.createVerticalBox();
        tabEmp.add(box2);
        box2.setBorder(new TitledBorder("Delete employee"));
        box2.setBounds(650, 200, 320, 120);
        jpOnBox2.setLayout(null);
        box2.add(jpOnBox2);
        jpOnBox2.add(idEmpTF);
        idEmpTF.setBounds(150, 10, 150, 20);
        jpOnBox2.add(labe5);
        labe5.setBounds(10, 10, 150, 20);
        jpOnBox2.add(b2);
        b2.setBounds(80, 50, 150, 30);
    }

    public void addBoxSetDepartForEmp() {
        Box box4 = Box.createVerticalBox();
        tabEmp.add(box4);
        box4.setBorder(new TitledBorder("Set department employee"));
        box4.setBounds(650, 510, 320, 150);
        jpOnBox4.setLayout(null);
        box4.add(jpOnBox4);
        jpOnBox4.add(idSetDepEmp);
        idSetDepEmp.setBounds(150, 5, 150, 20);
        jpOnBox4.add(labe8);
        labe8.setBounds(10, 5, 150, 20);
        jpOnBox4.add(labe9);
        labe9.setBounds(10, 40, 150, 20);
        combo.setSelectedIndex(1);
        jpOnBox4.add(combo);
        combo.setBounds(150, 40, 150, 20);
        jpOnBox4.add(b4);
        b4.setBounds(80, 80, 150, 30);
    }

    public void addBoxChangeEmp() {
        Box box3 = Box.createVerticalBox();
        tabEmp.add(box3);
        box3.setBorder(new TitledBorder("Change employee"));
        box3.setBounds(650, 335, 320, 160);
        jpOnBox3.setLayout(null);
        box3.add(jpOnBox3);
        jpOnBox3.add(idCangeEmp);
        idCangeEmp.setBounds(150, 10, 150, 20);
        jpOnBox3.add(labe6);
        labe6.setBounds(10, 10, 150, 20);
        jpOnBox3.add(dataEmp);
        dataEmp.setBounds(150, 50, 150, 20);
        jpOnBox3.add(labe7);
        labe7.setBounds(10, 50, 150, 20);
        jpOnBox3.add(b3);
        b3.setBounds(80, 90, 150, 30);
    }

    public void addBoxSetManager() {
        Box box5 = Box.createVerticalBox();
        tabDep.add(box5);
        box5.setBorder(new TitledBorder("Set manager"));
        box5.setBounds(650, 10, 320, 150);
        jpOnBox5.setLayout(null);
        box5.add(jpOnBox5);
        jpOnBox5.add(idDep);
        idDep.setBounds(150, 5, 150, 20);
        jpOnBox5.add(labe10);
        labe10.setBounds(10, 5, 150, 20);
        jpOnBox5.add(labe11);
        labe11.setBounds(10, 40, 150, 20);
        jpOnBox5.add(idSetMgr);
        idSetMgr.setBounds(150, 40, 150, 20);
        jpOnBox5.add(b5);
        b5.setBounds(80, 80, 150, 30);
    }

    public void addBoxCreateDepart() {
        Box box7 = Box.createVerticalBox();
        tabDep.add(box7);
        box7.setBorder(new TitledBorder("Create department"));
        box7.setBounds(650, 320, 320, 120);
        jpOnBox7.setLayout(null);
        box7.add(jpOnBox7);
        jpOnBox7.add(nameDep);
        nameDep.setBounds(150, 10, 150, 20);
        jpOnBox7.add(labe13);
        labe13.setBounds(10, 10, 150, 20);
        jpOnBox7.add(b7);
        b7.setBounds(80, 50, 150, 30);
    }

    public void addBoxDeleteDepart() {
        Box box6 = Box.createVerticalBox();
        tabDep.add(box6);
        box6.setBorder(new TitledBorder("Delete department"));
        box6.setBounds(650, 180, 320, 120);
        jpOnBox6.setLayout(null);
        box6.add(jpOnBox6);
        jpOnBox6.add(idDelDep);
        idDelDep.setBounds(150, 10, 150, 20);
        jpOnBox6.add(labe12);
        labe12.setBounds(10, 10, 150, 20);
        jpOnBox6.add(b6);
        b6.setBounds(80, 50, 150, 30);
    }
     public void addBoxChangeDep() {
        Box box7 = Box.createVerticalBox();
        tabDep.add(box7);
        box7.setBorder(new TitledBorder("Change department"));
        box7.setBounds(650, 455, 320, 160);
        jpOnBox8.setLayout(null);
        box7.add(jpOnBox8);
        jpOnBox8.add(idCangeEmp);
        idCangeEmp.setBounds(150, 10, 150, 20);
        jpOnBox8.add(labe14);
        labe14.setBounds(10, 10, 150, 20);
        jpOnBox8.add(dataEmp);
        dataEmp.setBounds(150, 50, 150, 20);
        jpOnBox8.add(labe15);
        labe15.setBounds(10, 50, 150, 20);
        jpOnBox8.add(b8);
        b8.setBounds(80, 90, 150, 30);
    }
     public void addActionListeners() { 
         
    buttonCreateEmp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameEmpTF.getText();
                String phone = phoneEmpTF.getText();
                String sal = salEmpTF.getText();
               
                controller.createEmployee(name, phone, sal);
              
            }
        });
    
     b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           
            }
        });
     b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
     b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
     b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
     b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    
     b7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
     b8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    
    
    
    
    
    
}
     public void showError(String string){
//         DialogFrame dialog = new DialogFrame(string);
//        dialog.setVisible(true);
     }
     public void changeEmpTable () {
         modEmp.fireTableDataChanged();
     }
}