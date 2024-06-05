"""
Table: Activity
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| player_id    | int     |
| device_id    | int     |
| event_date   | date    |
| games_played | int     |
+--------------+---------+
(player_id, event_date) is the primary key (combination of columns with unique values) of this table.
This table shows the activity of players of some games.
Each row is a record of a player who logged in and played a number of games (possibly 0) before logging out on someday using some device.

Write a solution to report the fraction of players that logged in again on the day after the day they first logged in, rounded to 2 decimal places. In other words, you need to count the number of players that logged in for at least two consecutive days starting from their first login date, then divide that number by the total number of players.
"""
-- LOGGED IN THE DAY AFTER
-- FIRST LOGGED IN

SELECT ROUND(COUNT(DISTINCT a1.player_id) / (SELECT COUNT(DISTINCT player_id) FROM Activity), 2) AS fraction
FROM Activity a1
JOIN Activity a2 ON a1.player_id = a2.player_id AND DATEDIFF(a2.event_date, a1.event_date) = 1
WHERE (a1.player_id, a1.event_date) IN (
    SELECT player_id, MIN(event_date) FROM Activity GROUP BY player_id
)
