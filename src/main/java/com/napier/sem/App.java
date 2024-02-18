package com.napier.sem;

import java.sql.*;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        // Connect to database
        a.connect();
        // Get Employee
        Employee emp = a.getEmployee(255530);
        // Display results
        a.displayEmployee(emp);

        // Disconnect from database
        a.disconnect();
    }

        /**
         * Connection to MySQL database.
         */
        private Connection con = null;

        /**
         * Connect to the MySQL database.
         */
        public void connect()
        {
            try
            {
                // Load Database driver
                Class.forName("com.mysql.cj.jdbc.Driver");
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("Could not load SQL driver");
                System.exit(-1);
            }

            int retries = 10;
            for (int i = 0; i < retries; ++i)
            {
                System.out.println("Connecting to database...");
                try
                {
                    // Wait a bit for db to start
                    Thread.sleep(30000);
                    // Connect to database
                    con = DriverManager.getConnection("jdbc:mysql://db:3306/employees?useSSL=false", "root", "example");
                    System.out.println("Successfully connected");
                    break;
                }
                catch (SQLException sqle)
                {
                    System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                    System.out.println(sqle.getMessage());
                }
                catch (InterruptedException ie)
                {
                    System.out.println("Thread interrupted? Should not happen.");
                }
            }
        }

        /**
         * Disconnect from the MySQL database.
         */
        public void disconnect()
        {
            if (con != null)
            {
                try
                {
                    // Close connection
                    con.close();
                }
                catch (Exception e)
                {
                    System.out.println("Error closing connection to database");
                }
            }
        }

    public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "select e.emp_no, e.first_name, e.last_name, t.title, dt.dept_no, s.salary, "
                            + "(select emp_no from dept_manager where dept_no = dt.dept_no and to_date = '9999-01-01') as ManagerID,"
                            + "(select dept_name from departments where dept_no = dt.dept_no) as dept_name, "
                            + "(Select concat(first_name, ' ' ,last_name) from employees where emp_no = ManagerID) as manager "
                            + "From employees as e "
                            + "inner join titles as t on e.emp_no = t.emp_no "
                            + "inner join salaries as s on e.emp_no = s.emp_no "
                            + "inner join dept_emp as dm on e.emp_no = dm.emp_no "
                            + "inner join departments as dt on dm.dept_no = dt.dept_no "
                            + "WHERE e.emp_no = " + ID;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                Employee emp = new Employee();
                emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");
                emp.manager = rset.getString("manager");
                emp.title = rset.getString("title");
                emp.dept_name = rset.getString("dept_name");
                emp.salary = rset.getInt("salary");
                return emp;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get employee details");
            return null;
        }
    }

    public void displayEmployee(Employee emp)
    {
        if (emp != null)
        {
            System.out.println(
                    emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");
        }
    }
}