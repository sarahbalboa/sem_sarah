# USE CASE: 1  Produce a Report on the Salary of all Employees

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *HR advisor* I want *to produce a report on the salary of employees of all employees* so that *I can support financial reporting of the organisation.*

### Scope

Company.

### Level

Primary task.

### Preconditions

Database contains all employees salary data.

### Success End Condition

A report is available for HR to provide to finance.

### Failed End Condition

No report is produced.

### Primary Actor

HR Advisor.

### Trigger

A request for finance information of all employees is sent to HR.

## MAIN SUCCESS SCENARIO

1. Finance request salary information for all employees.
2. HR advisor extracts all current salary for all employees in the Company.
3. HR advisor provides report to finance.

## EXTENSIONS

3. **No employees in the database**:
    1. HR advisor informs finance no employees available in database.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 4.0