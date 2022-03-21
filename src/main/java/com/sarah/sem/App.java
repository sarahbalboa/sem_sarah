package com.sarah.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay) {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                                + "/employees?allowPublicKeyRetrieval=true&useSSL=false",
                        "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " +    Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }
    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Employee getEmployee(int ID, String date) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            Statement stmt4 = con.createStatement();
            Statement stmt5 = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;
            String strSelectTitle =
                    "SELECT title "
                            + "FROM titles "
                            + "WHERE emp_no = " + ID + " AND to_date = '" + date + "';";
            String strSelectDep =
                    "SELECT dept_name FROM " +
                            "departments JOIN dept_emp ON departments.dept_no = dept_emp.dept_no " +
                            "WHERE emp_no = " + ID + " AND to_date = '" + date + "';";
            String strSelectSalary =
                    "SELECT salary "
                            + "FROM salaries "
                            + "WHERE emp_no = " + ID + " AND to_date = '" + date + "';";

            //job title, salary, department, and manager
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ResultSet rset2 = stmt2.executeQuery(strSelectTitle);
            ResultSet rset3 = stmt3.executeQuery(strSelectDep);
            ResultSet rset4 = stmt4.executeQuery(strSelectSalary);

            Department dept = new Department();

            // Return new employee if valid.
            // Check one is returned
            if (rset.next() && rset2.next() && rset3.next() && rset4.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset2.getString("title");
                dept.dept_name = rset3.getString("dept_name");
                emp.dept = dept;
                emp.salary = rset4.getInt("salary");

                return emp;
            } else if (rset.next() && rset2.next() && rset3.next() && rset4.next()) {
                Employee emp = new Employee();

                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.title = rset2.getString("title");
                //emp.dept = (Department) rset3.getObject("dept_name");
                dept.dept_name = rset3.getString("dept_name");
                emp.dept = dept;
                emp.salary = rset4.getInt("salary");

                return emp;
            } else
                return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }


    //displau employee method
    public void displayEmployee(Employee emp) {
        if (emp != null) {


            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept.dept_name);
        }
        else {
            System.out.println("Employee is empty");
        }


    }

    //get manager ID with the employees department name and teh last date
    public int getMangerID(String deptName, String date) {

        try {
            // Create an SQL statement
            Statement stmt6 = con.createStatement();

            // Create string for SQL statement
            String strSelectManagerName =
                    "SELECT emp_no FROM dept_manager " +
                            "JOIN departments ON dept_manager.dept_no = departments.dept_no " +
                            "WHERE dept_name = '" + deptName + "' AND to_date = '" + date + "';";

            // Execute SQL statement
            ResultSet rset = stmt6.executeQuery(strSelectManagerName);

            // Return new employee if valid.
            // Check one is returned
            if (rset.next()) {
                Employee manag = new Employee();
                manag.emp_no = rset.getInt("emp_no");

                return manag.emp_no;
            } else
                return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get manager details");
            return 0;
        }
    }

    //display manager method
    public void displayManager(Employee manag) {
        if (manag != null) {
            System.out.println("Manager: "
                    + manag.first_name + " " + manag.last_name + "\n");
        }
    }

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public ArrayList<Employee> getAllSalaries() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    public ArrayList<Employee> getEmployeeSalaryRole() {

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries, titles "
                            + "WHERE employees.emp_no = salaries.emp_no "
                            + "AND employees.emp_no = titles.emp_no "
                            + "AND salaries.to_date = '9999-01-01' "
                            + "AND titles.to_date = '9999-01-01' "
                            + "AND titles.title LIKE 'Engineer'"
                            + " ORDER BY employees.emp_no ASC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");
                employees.add(emp);
            }
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /**
     * Prints a list of employees.
     *
     * @param employees The list of employees to print.
     */
    public void printSalaries(ArrayList<Employee> employees) {
        // Check employees is not null
        if (employees == null)
        {
            System.out.println("No employees");
            return;
        }
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "Emp No", "First Name", "Last Name", "Salary"));
        // Loop over all employees in the list
        for (Employee emp : employees) {

            if (emp == null)
                continue;
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s",
                            emp.emp_no, emp.first_name, emp.last_name, emp.salary);
            System.out.println(emp_string);
        }
    }

    public static void main(String[] args) {
        // Create new Application
        App a = new App();

        /* Connect to database
        a.connect();

        // ------------------- Issue #6 -----------
        //date
        String date = "9999-01-01";
        Department dept = new Department();
        String department = "Engineer";
        dept.dept_name = department;
        // Get Employee
        Employee emp = a.getEmployee(255530, date);
        // Display results
        a.displayEmployee(emp);
        /*get manager from the employees department ------------------
        String department = emp.dept.dept_name; //department
        int managerID = a.getMangerID(department,date); //manager ID
        Employee manager = a.getEmployee(managerID, date); //manager name
        a.displayManager(manager); //display manager*/


        /* -------------------------Issue #1 ----------------
        ArrayList<Employee> employees = a.getAllSalaries();
        // Test the size of the returned data - should be 240124
        System.out.println(employees.size());
        */
        /* ----------------------- Issue #4 ---------------

        ArrayList<Employee> employees = a.getEmployeeSalaryRole();

        a.printSalaries(employees);

        */
        //New connect ---------------------------
        if(args.length < 1){
            a.connect("localhost:33060", 30000);
        }else{
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        // Get Employee
        String date = "9999-01-01";
        Employee emp = a.getEmployee(255530, date);
        // Display results
        a.displayEmployee(emp);
        // Disconnect from database
        a.disconnect();
    }

    public Department getDepartment(String dept_no) {
        return null;
    }

    public ArrayList<Employee> getSalariesByDepartment(Department dept) {
        return null;
    }
}