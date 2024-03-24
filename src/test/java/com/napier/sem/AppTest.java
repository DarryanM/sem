package com.napier.sem;

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
        ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(null);
        app.printSalaries(employees);
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
    @Test
    void displayEmployeeTestNull()
    {
        app.displayEmployee(null);
    }

    @Test
    void displayEmployee()
    {
        Employee emp = new Employee();
        app.displayEmployee(emp);

    }

    @Test
    void printSalariesByRoleTestNull()
    {
        //Test Print Salaries By Role Null
        app.printSalariesByRole(null);

        //Test Print Salaries By Role Test Empty
        ArrayList<Employee> employees1 = new ArrayList<Employee>();
        app.printSalariesByRole(employees1);

        //Test Print Salaries By Role Contains null
        employees1.add(null);
        app.printSalariesByRole(employees1);

        //Test Print Salaries By Role Display Values
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.salary = 55000;
        employees1.add(emp);
        app.printSalariesByRole(employees1);

    }

    @Test
    void printSalariesByDepartment()
    {
        //Test Print Salaries By Role Null
        app.printSalariesByDepartment(null);

        //Test Print Salaries By Role Test Empty
        ArrayList<Employee> employees3 = new ArrayList<Employee>();
        app.printSalariesByDepartment(employees3);

        //Test Print Salaries By Role Contains null
        employees3.add(null);
        app.printSalariesByRole(employees3);

        //Test Print Salaries By Role Display Values
        Employee emp = new Employee();
        emp.emp_no = 1;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        emp.salary = 55000;
        employees3.add(emp);
        app.printSalariesByDepartment(employees3);

    }

}