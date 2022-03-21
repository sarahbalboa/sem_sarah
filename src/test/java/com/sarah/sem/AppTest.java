package com.sarah.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();

    }

    @Test
    void printSalariesTestNull()
    {
        app.printSalaries(null);
    }

    @Test
    void printSalariesTestEmpty()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        app.printSalaries(employees);
    }

    @Test
    void printSalariesTestContainsNull()
    {
        ArrayList<Employee> employess = new ArrayList<Employee>();
        employess.add(null);
        app.printSalaries(employess);
    }

    @Test
    void printSalaries()
    {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.title = "Engineer";
        emp.salary = 55000;
        employees.add(emp);
        app.printSalaries(employees);
    }
    //test null -- test empty --
    @Test
        void displayEmployee()
        {
            Employee emp = new Employee();
            Department dept = new Department();

            emp.emp_no = 1;
            emp.first_name = "Kevin";
            emp.last_name = "Chalmers";
            emp.title = "Engineer";
            emp.salary = 55000;
            dept.dept_name = "Engineer";
            emp.dept = dept;
            app.displayEmployee(emp);
        }


    @Test
    void displayEmployeeNull()
    {
        app.displayEmployee(null);
    }


}