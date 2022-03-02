# USE CASE: 6  View an Employee's Details with a Given Name

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to  view and employee's details* so that *the employee's promotion request can be supported.*

### Scope

Employee.

### Level

Primary task.

### Preconditions

We know the name of the employee.  Database contains current employee details.

### Success End Condition

An employee details are available for HR.

### Failed End Condition

No employee details are available.

### Primary Actor

HR Advisor.

### Trigger

A view request for an employee's details to support of the employee's promotion is sent to HR.

## MAIN SUCCESS SCENARIO

1. Request of details to support an employee's promotion.
2. HR advisor captures name of the employee to get details for.
3. HR advisor extracts current details from the employee of the name.
4. HR advisor views details of employee.

## EXTENSIONS

1. **Employee does not exist**:
    1. HR advisor informs the company that the employee does not exist.
    2. HR advisor asks for correct employee's name.
   
2. **Employee's details are not available**:
   1.  HR advisor informs the company that the employee details are not available.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 8.0