"""
Table: Seat

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| student     | varchar |
+-------------+---------+
id is the primary key (unique value) column for this table.
Each row of this table indicates the name and the ID of a student.
id is a continuous increment.

Write a solution to swap the seat id of every two consecutive students. If the number of students is odd, the id of the last student is not swapped.

Return the result table ordered by id in ascending order.
"""

SELECT s1.id, COALESCE(s2.student, s1.student) AS student
FROM (
    SELECT *, id + IF(id % 2 = 1, 1, -1) AS foo
    FROM Seat
) s1
LEFT JOIN Seat s2 ON s1.foo = s2.id
