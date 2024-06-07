"""
Table: Products
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| product_id    | int     |
| new_price     | int     |
| change_date   | date    |
+---------------+---------+
(product_id, change_date) is the primary key (combination of columns with unique values) of this table.
Each row of this table indicates that the price of some product was changed to a new price at some date.

Write a solution to find the prices of all products on 2019-08-16. Assume the price of all products before any change is 10.

Return the result table in any order.
"""

SELECT uniqs.product_id, COALESCE(p.new_price, 10) as price
FROM ( SELECT DISTINCT product_id FROM Products ) uniqs
LEFT JOIN (
    SELECT product_id, MAX(change_date) as md
    FROM Products
    WHERE change_date <= "2019-08-16"
    GROUP BY product_id
) max_date ON uniqs.product_id = max_date.product_id
LEFT JOIN Products p ON uniqs.product_id = p.product_id AND md = p.change_date