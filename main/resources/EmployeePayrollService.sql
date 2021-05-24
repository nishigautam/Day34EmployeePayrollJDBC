MySQL  localhost:33060+ ssl  SQL > USE payroll_service;
Default schema set to `payroll_service`.
Fetching table and column names from `payroll_service` for auto-completion... Press ^C to stop.
MySQL  localhost:33060+ ssl  payroll_service  SQL > SELECT DATABASE();
+-----------------+
| DATABASE()      |
+-----------------+
| payroll_service |
+-----------------+
1 row in set (0.0011 sec)
MySQL  localhost:33060+ ssl  payroll_service  SQL > CREATE TABLE employee_payroll
                                                 -> (
                                                 -> id INT unsigned NOT NULL AUTO_INCREMENT,
                                                 -> name     VARCHAR(50)  NOT NULL,
                                                 -> salary   DOUBLE       NOT NULL,
                                                 -> start    DATE         NOT NULL,
                                                 -> PRIMARY KEY (id)
                                                 -> );
Query OK, 0 rows affected (0.1219 sec)
MySQL  localhost:33060+ ssl  payroll_service  SQL > SHOW Tables;
+---------------------------+
| Tables_in_payroll_service |
+---------------------------+
| employee_payroll          |
+---------------------------+
1 row in set (0.0066 sec)
MySQL  localhost:33060+ ssl  payroll_service  SQL > INSERT INTO employee_payroll (name, salary, start) VALUES
                                                 -> ('Bill', 650000.00, '2020-12-30'),
                                                 -> ('Terissa', 430000.00, '2021-09-20'),
                                                 -> ('Charlie', 780000.00, '2018-01-16');
Query OK, 3 rows affected (0.0145 sec)

Records: 3  Duplicates: 0  Warnings: 0
MySQL  localhost:33060+ ssl  payroll_service  SQL > SELECT * FROM employee_payroll;
+----+---------+--------+------------+
| id | name    | salary | start      |
+----+---------+--------+------------+
|  1 | Bill    | 650000 | 2020-12-30 |
|  2 | Terissa | 430000 | 2021-09-20 |
|  3 | Charlie | 780000 | 2018-01-16 |
+----+---------+--------+------------+
3 rows in set (0.0014 sec)

MySQL  localhost:33060+ ssl  payroll_service  SQL > UPDATE employee_Payroll SET salary = 3000000.00 WHERE name = 'Terissa';
Query OK, 1 row affected (0.0228 sec)

Rows matched: 1  Changed: 1  Warnings: 0
MySQL  localhost:33060+ ssl  payroll_service  SQL > SELECT * FROM employee_payroll;
+----+---------+---------+------------+
| id | name    | salary  | start      |
+----+---------+---------+------------+
|  1 | Bill    |  650000 | 2020-12-30 |
|  2 | Terissa | 3000000 | 2021-09-20 |
|  3 | Charlie |  780000 | 2018-01-16 |
+----+---------+---------+------------+
3 rows in set (0.0042 sec)
