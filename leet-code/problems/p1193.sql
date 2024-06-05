"""
Table: Transactions
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| country       | varchar |
| state         | enum    |
| amount        | int     |
| trans_date    | date    |
+---------------+---------+
id is the primary key of this table.
The table has information about incoming transactions.
The state column is an enum of type ["approved", "declined"].

Write an SQL query to find for each month and country, the number of transactions and their total amount, the number of approved transactions and their total amount.

Return the result table in any order.
"""

SELECT
    SUBSTR(t.trans_date, 1, 7) AS month,
    t.country,
    COUNT(t.id) AS trans_count,
    SUM(IF(t.state = "approved", 1, 0)) AS approved_count,
    SUM(t.amount) AS trans_total_amount,
    SUM(IF(t.state = "approved", t.amount, 0)) as approved_total_amount
FROM Transactions t
GROUP BY month, t.country
ORDER BY month ASC, t.country ASC
