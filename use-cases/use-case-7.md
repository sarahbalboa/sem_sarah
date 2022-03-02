# USE CASE: 4 Update an Employee's Details

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to update an employee's details* so that *employee's details are kept up-to-date.*

### Scope

Employee.

### Level

Primary task.

### Preconditions

We know the new employee's details.  Database contains current employee's details.

### Success End Condition

Employee's details are updated.

### Failed End Condition

Employee's details are not updated.

### Primary Actor

HR Advisor.

### Trigger

A request for update employee's details is sent to HR.

## MAIN SUCCESS SCENARIO

1. Request for update employee's details with given details.
2. HR advisor captures current employee's details to find them in the database.
3. HR advisor captures new employee's details to update them in the database.
4. HR advisor updates employees details.
5. Employee is up-to-date.

## EXTENSIONS

1. **Employee does not exist**:
    1. HR advisor informs the Company that Employee does not exist.
    2. HR advisor informs employee that their previous details are incorrect.
   
2. **Employee's new details are not acceptable**:
    1. HR Advisor asks the employee to give the new correct details

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 9.0