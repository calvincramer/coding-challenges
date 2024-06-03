-- Find the names of the customer that are not referred by the customer with id = 2.

SELECT name
FROM Customer
WHERE referee_id IS NULL OR referee_id <> 2