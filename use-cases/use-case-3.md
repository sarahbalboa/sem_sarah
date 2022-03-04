# USE CASE: 3 Produce a Report on the Salary of Employees from a Given Department Manager's Department 

## CHARACTERISTIC INFORMATION

### Goal in Context

As a *Department Manager* I want *to produce a report on the salary of employees  in my department* so that *I can support financial reporting for my department.*

### Scope

Department.

### Level

Primary task.

### Preconditions

We know the Manager's name adn department.  Database contains current employee salary data.

### Success End Condition

A report is available for the Department Manager to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

Department Manager.

### Trigger

A request for finance information is sent to Department Manager.

## MAIN SUCCESS SCENARIO

1. Finance request salary information for a given Department Manager.
2. Department Manager captures their department name to get information for.
3. Department Manager extracts current salary information of all employees of their department.
4. Department Manager provides report to finance.

## EXTENSIONS

1**No employees in their department**:
    1. Department Manager informs finance no employees exists in their department.

## SUB-VARIATIONS

The user is not a Department Manager.

## SCHEDULE

**DUE DATE**: Release 6.0