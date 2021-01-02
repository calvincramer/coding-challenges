# PYTHON 3.7 CHEAT SHEET

[TOC]


# Data Structures

---

## Dictionary - map, hashmap

```python
d = {}                     # Creates an empty dictionary
d = { k1:v1, k2:v2, ... }  # Create with initial key value pairs
d[key] = value             # Add an entry
d.update[h]  # For each key value pair in the dict h, if the key already exists in d, it will 
             #     replace the value, otherwise it will add the key value pair into the dictionary
d.update(k1 = v1, k2 = v2, k3 = v3, ...)  # Alternative syntax
for k in d:            # Iterate over by key k
for k in d.keys():     # Iterate over by key k
for k,v in d.items():  # Iterate over by key value pair (k,v)
for v in d.values():   # Iterate over by value v
del d[key]             # Delete an entry by key
d.clear()              # Deletes all entries
d.copy()               # Makes a shallow copy
dict.fromkeys(iterable, [value])  # Creates a dictionary from an iterable, all with the same value
d.get(key, default=None)  # Searches for key, returns value associated with key if found,
                          #     else None, or whatever default is set to
d.pop(key)  # Returns the value associated with key, and removes the entry from the 
            #     dictionary, if key not found it raises an error
d.pop(key, default)  # If key is not found then default is returned
d.popitem()  # Removes and returns an arbitrary (key, value) pair, will raise exception if
             #     dictionary is empty
d.setdefault(key, default=None)  # If key is already in the dict, then the existing value 
                                 #     will be returned, otherwise it will place (key, default) 
                                 #     pair in the dict
```

#### Multikey dictionary

1. Use tuple for key

2. Use `defaultdict`  # `from collections import defaultdict`

## Set - unique elements bag

```python
s = set()          # Create empty set
s = set(iterable)  # Create set from iterable
s = {1, 2, 3}      # Set literal (note that {} is an empty dictionary)
s.add(el)          # Adds element el to the set (if element already in set nothing happens)
s.clear()          # Removes all elements from the set
s.copy()           # Returns shallow copy of the set
s.difference(b)    # Returns new set of all elements in s but not in set b
s - b              # Same as difference()
s.difference_update(b)    # Removes all elements of set b from s if present (doesn't make new set)
s.discard(el)      # Removes element el if in set
el in s            # Determines if an element el is in the set
s.intersection(b)  # Returns new set of elements that are in both sets s and b
s & b              # Same as intersection()
s.intersection_update(b)  # Sets s to be the intersection of s and b
s.isdisjoint(b)    # Returns true if no common elements, false otherwise
s.issubset(b)      # Returns true if all elements in s are also in b
s.issuperset(b)    # Returns true if all elements in b are also in s 
s.pop()            # Removes and returns any one element, KeyError if empty
s.remove(el)       # Removes element el from the set, KeyError if el not in the set
s.symmetric_difference(b)  # Returns new set with all elements that only occur in either s or b
s ^ b                      # Same as symmetric_difference()
s.symmetric_difference_update()  # Same as symmetric_difference() but updates s as the result
s.union(b)         # Returns new set where each element is in either set s or b  
s | b              # Same as union()
s.update(b)        # Updates s to inclde all elements of set b (same as union operation)
```

## List - Like a flexible array

### Functions
```python
l = [1,2,3,4]          # Create list
l = list(iterable)     # Create list from other iterable
l.append(item)         # Add item to end of list
l.clear()              # Removes all items
l.copy()               # Shallow copy
l.count(item)          # Returns number of occurences of item
del l[index]           # Deletes item by index
l.extend(iterable)     # Adds all elements from argument to list
item in l              # Determines if item is in the list
l.index(item)          # Returns first index where item occurs, raises ValueError if not found
l.index(item, l, r)    # Optional sliced notation ( equivalent to lst[l:r].index(item) )
l.insert(index, item)  # Inserts item at index, shifts everything to the right
l.pop(index)           # Removes and returns item at index
l.pop()                # Removes and returns last item
l.remove(item)         # Removes first occurrence of item, ValueError thrown if not found
l.reverse()            # Reverses list in place (doesn't return anything)
l.sort()               # Sorts list in place
l.sort(key, reverse)   # Sorts list in place, key is a function, reverse = True for descending
```

## Tuples - TODO
count() ???
index() ???

## Strings - immutable string

```python
s = "abc"       # String liteal
s = 'abc'       # String literal
s = '''abs'''   # String may span multiple lines
s = """abc"""   # String may span multiple lines
s.capitalize()  # Capitalizes first character in string
s.casefold()    # Same as lower()
s.center(w, c)           # Returns s in the center of width w, width padding of character c
s.count(X, [s, e])       # Returns count of substring X, from start s to end e
s.encode(enc, err)       # Encodes string in encoding enc with error handling scheme err
s.endswith(suf, [s, e])  # Checks if s ends with suf (or any suffixes in tuple) in splice s to e
s.expandtabs(tabsize)    # Returns string with all tabs replaces with tabsize spaces
s.find(sub, [s, e])      # Returns index of first occuring substring sub in s (-1 if not found)
s.format(...)            # Formats string (positional placement)
s.format_map(dict)       # Formats strings given a map
substring in s           # Determines if the substring is somewhere in s
s.index(sub, [s, e])     # Same as find() but throws ValueError when sub not found
s.isalnum()       # Whether or not all characters are alpha-numeric (and at least one character)
s.isalpha()       # Whether or not all characters are alphabetical (and at least one character)
s.isdecimal()     # Whether or not all characters are numbers 0 to 9 (and at least one character)
s.isdigit()       # Almost same as isdecimal(), but more restrictive
s.isidentifier()  # Whether or not can be variable or class name
s.islower()       # Whether or not all characters are lowercase (False if empty)
s.isnumeric()     # All characters are decimals, fractions, other Unicode number crap
s.isprintable()   # All characters are printable to screen
s.isspace()       # All characters are spaces, tabs or newlines
s.istitle()       # All words are uppercase (not all capital) 
s.isupper()       # All characters are uppercase (False if empty)
s.join(iterable)  # Combines string objects in iterable into a string separated by s
s.ljust(w, c)     # Returns left-justified string of length w with fill character c
s.lower()         # Returns lowercase string
s.lstrip()        # Returns string without leading whitespace characters
s.lstrip(string)  # Returns string without leading characters in string
s.maketrans(x)    # Makes a translation table, x must be a dictionary from char to char
s.maketrans(x, y)        # x and y must be strings of same length (from x to y)
s.maketrans(x, y, z)     # Like the previous but all characters in the string z are mapped to None
s.partition(sep)         # Returns 3 tuple of (sep, sep, after) if found, else (s, "", "")
s.replace(f, t)          # Replaces all occurrences of substring f to substring t
s.replace(f, t, c)       # Replaces at most c occurrences of f with t from the left
s.rfind(sub, [s, e])     # Similar to find() but searches from the right
s.rindex(sub, [s, e])    # Like rfind() but raises ValueError when sub not found
s.rjust()                # Returns right-justified string of length w with fill character c
s.rpartition()           # Same as paritition() but searches from right
s.rsplit(sep, [maxn])    # Like split but if manx is provided then done from the right
s.rstrip([chars])        # Like lstrip() but with the trailing characters
s.split(None, [maxn])    # Splits strings with separator being any whitespace, at most maxn splits
s.split(sep, [maxn])     # Splits strings where sep occurs, at most maxn splits
s.splitlines(keepends)   # Returns list of lines in string (breaks on \n, \r and others)
s.startswith(p, [s, e])  # Whether or not s starts with prefix p within optional splice s to e
s.startswith(t, [s, e])  # Whether or not any string in tuple t is a prefix
s.strip()                # Does lstrip() and rstrip()
s.swapcase()             # Returns string with each character opposite case
s.title()                # Returns string where every word is Title Cased
s.translate(d)           # Takes translation table d which is mapping between characters
s.upper()                # Returns uppercase string
s.zfill(w)               # Fills string with zeros on the left to make the return string length w
```

# Built-in Functions

---

### Numeric Functions

```python
abs(num)        # Returns the absolute value of an integer, floating point, or complex number
bin(int)        # Returns binary representation of an integer as a string
complex(a, b)   # Creates a complex object of a+bj
divmod(a, b)    # If a and b both integers, returns (a // b, a % b), else if one or both of them 
			   #     are floating point then returns (int(a // b), a % b)
float(string)   # Casts string into floating point (can use "inf", "-inf", "nan")
hex(int)        # Returns hexadecimal representation of an integer as a string
int(string, [base])  # Casts string to integer, optional base for the string (int("20", 16) == 32)
oct(int)        # Returns octal representation of an integer as a string
pow(x, y, [z])  # Same as x ** y, if z present, more efficiently calculates (x ** y) mod z
round(num, [digits])  # Rounds number to specified digits after decimal point
sum(iterable)   # Returns sum of iterable
```

### Data Structures
```python
bytearray(string, 'utf-8')  # Makes a mutable array of bytes given a string, or integer size
bytes()          # Immutable version of bytearray()
dict()           # Creates dictionary from list of (key, value) pairs, or like:
                 #     dict(key1 = val1, key2 = val2, key3 = val3, ...) 
frozenset(iterable)         # Immutable set
list(iterable)              # Creates list from an iterable
next(iterator, [default])   # Gets next item from iterator, returns default if reached end
set(iterable)    # Converts iterable to set
super()          # Call parent class functions, data
tuple(iterable)  # Creates a tuple out of an iterable
```

### Iterable functions, Map

```python
all(iterable)       # Returns true if all elements are true (or non-zero if numbers), or empty
any(iterable)       # Returns true if at least one true (or non-zero) element, false if empty
enumerate(iterable)  # Enumerates an iterable starting at 0, returns pair (index, element)
filter(function, iterable)  # Keeps only the items that pass (function(item) returns True)
len(iterable)       # Returns number of items in iterable
map(function, iterable)  # Applies function to each item in iterable, returns resulting list
					   #     note it actually returns an iterator, surround with list()
max(arg1, arg2, ... , argn, [key])  # Returns max of args, optional function key (like len)
max(iterable)       # Returns max of iterable
max(iterables)      # Returns iterable which contains the maximum
min()               # Like max()
reversed(sequence)  # Returns iterator that reverses sequence iterable
sorted(iterable)    # Returns sorted list or other iterable
sum(iterable)       # Returns sum of iterable
```

### Strings, Printing

```python
ascii()  # Escapes non ascii characters:
	s = "abc 公共汽车"
	print(s)  # abc 公共汽车
	print(ascii(s))  # 'abc \u516c\u5171\u6c7d\u8f66'
chr(n)	  # Converts unicode integer n to its character
format()  # TODO
ord(character)  # Returns Unicode value of a character
print()   # TODO
repr()    # TODO
str()     # TODO
```

### Useful Functions

```python
help()  # TODO
breakpoint()  # Starts a commandline debugging session at this line
input([prompt])  # Gathers input from user until newline (no newline returned)
isinstance(object, b)  # Determines if object is of class or type b. b can be a tuple of 
                       #     classes and types, and will return true if object is instance of any
issubclass(class, class)  # Similar to isinstance()
iter(iterable, [sentinel])  # Returns iterator over iterable, if sentinel is provided, iterable
                            #     must be a callable object (function) and the iteration will stop
                            #     when sentinel value is reached.
open(filename, ...)  # Opens a file
property()  # Alternative way of using @property decorators
range(start, stop, [step])  # Loops
type(object)    # Gets type object from object
zip(iterables)  # enumerate over multiple iterables at the same time (will stop when at minimum
			   #     length iterable)
```

### Inspection / Reflection
```python
callable()  # TODO
classmethod()  # ? Reflection?
delattr()  # TODO
dir()  # TODO
getattr()  # TODO
globals()  # TODO
hasattr()  # TODO
locals()  # TODO
setattr()  # TODO
vars()  # TODO
```

### Meta Functions

```python
compile()  # TODO
eval()  # TODO
exec()  # TODO
```

### Others

```python
bool([x])     # Returns x if x is a boolean, false if empty string, 0, empty list, ...
hash(object)  # Returns the hash of an object as an integer
id(object)    # Unique identifier number for every object (used by is keyword internally)
__import__()  # Used by import keyword
memoryview(object)  # Byte-level read / write access for objects that support buffer protocol
object()            # Returns cosmic superclass blank object
slice(start, stop, [step])  # Returns a slice object. array[sliceobj]
staticmethod()              # Alternative way to using @staticmethod decorator
```


# Keywords

---

```python
if        # if boolean expression: ...
elif      # elif boolean expression: ...
else      # else: ...

for       # for iterable: ...
while     # while boolean expression:

and       # and
or        # or
not       # not

from      # from Module import function
import    # import Module
as        # import Module as NewName -- gives alias to make typing easier

break     # Ends enclosing for loop
continue  # Skips to next iteration in for loop

class     # class ClassName: ...
def       # def FunctionName(...): ...
return    # Return val -- from function

True      # True 
False     # False

try       # try: ...
except    # try: ... except: (Error)
		 # try: ... except SpecificError: ...
finally   # try: ... except: ... finally: ... -- finally block is always executed at end
else      # try: ... except: ... else: ... finally: ... -- else block when no exception occurred 

assert    # assert (boolean expression) -- raises AssertionError if False, otherwise does nothing
raise     # raise ErrorObject

global    # global var -- takes in existing global var into current scope (used to write)
nonlocal  # nonlocal var -- takes in var from outside scope (used to get outer function variable)

del       # Delete reference to object, or items from list or dictionary
None      # None, Null, Nill
in        # val in iterable
is        # obj1 is obj2 -- tests if referring to same object
lambda    # lambda args: expression -- annonymous function
pass      # Does nothing, use if function not implemented
with      # Similar to try-finally, uses context manager's __enter__ and __exit__ methods
yield     # yield expression -- creates a generator object (space-efficient looping)
```

# Syntax, Sugar Sugary Goodness, Other Features, Examles

---

## For + If List Comprehension
```python
[output_var for item in iterable if condition]
Example:
[w.lower() for w in re.split(" |,|\.", sentance) if w != ""]
```

Takes each item from a list, runs the condition, then the output_var code. If the condition is passed, then output_var is placed in the list

Can also do dictionary comprehension

## Ternary Statement

```python
true_statement if condition else false_condition
```

## Chained comparators

```python
instead of:
if a < b and b < c: {}
we can do:
if a < b < c: {}
Works for these operators:
">" | "<" | "==" | ">=" | "<=" | "!=" | "is" ["not"] | ["not"] "in"

```

## Reduce, Lambda - TODO

```python
... 
```

## Assert

```python
assert 0 == 0 # Passes assert so nothing happens
assert 0 == 1 # AssertionError is thrown, program stopped if not caught
```


# Things to Remember, Python Peculiarities

---

## `!=` vs `is not`

!= and == call `__eq__` or `__cmp__`, while `is not` compares that the objects on either side are the same object
(shallow equality check, 'comparing identity')

## Typed parameters for arguments - TODO

## Bitwise operators

* _a_ `&` _b_ - bitwise AND
* _a_ `|` _b_ - bitwise OR
* _a_ `^` _b_ - bitwise XOR
* `~` _a_ - ones complement (flip bits)
* _a_ `<<` _b_ - bitwise shift left (by _b_ bits)
* _a_ `>>` _b_ - bitwise shift right (by _b_ bits)

## Permutations and Combinations
```python
from itertools import permutations
permutations(list)
or
from itertools import combinations
combinations(list)
```

## Get all callable methods on an object
```python
methods = [method_name for method_name in dir(object) if callable(getattr(object, method_name))]
```

## Try Except Else, Raise errors

### Catch any error:

```python
try:
	... code
except:
    ... error occurred 
```

### Catch a specific error:

```python
try:
	... code
except SpecificError:
    ... SpecificError occurred 
```

### Catch specific errors:

```python
try:
	... code
except (SpecificError1, SpecificError2, SpecificError3):
    ... One of the errors occurred 
```

### Else clause:

```python
try:
	... code
except:
    ... error occurred 
else:
	... will be executed if no errors occur
```

### Unpack error into object

```python
try:
	... code
except Error as err:
	... Error captured as err
```

### Raise Errors:

```python
raise ValueError
raise ValueError()
raise ValueError("Explanatory error message")
```


### Typed parameter support

```python
# In order to have a function like:
def f(a: List[int])
# we need to include this:
from typing import List
```

## Beautiful examples

```python
def transpose(self, A):  
    return list(zip(*A))
```


# To Look into and learn and TODO (search TODO)

* from Queue import PriorityQueue
* set literal vs set([sadfasdasldjhflaksjdf])
* Common imports -- random
* import heapq
    * heapq.heappush, heappop
* String functions -- isalpha, isdigit, isupper, islower, etc
* Make nice table of contents, actual table?
* Things to Remember, Python Peculiarities section to be organized into other sections
* Standard for [`code -- comment`] lines or [`code - comment`], standard for when to use __bold__ / _italics_
* Standard for when to use [> quote] markdown syntax
* Standard for code blocks
* Keywords, group into groups
* Complex numbers section (math operations)
* Named tuples
* os.environ['PYTHONBREAKPOINT'] = 'foo.bar.baz'
* from functools import partial
* decorators
* @tag things
* Standard types - https://docs.python.org/3/library/stdtypes.html#typesseq-range
* Create program to go through each code block and convert long comments to multiline comments (split after number of characters)
* Types parameters, typed return statements (def a(b) -> type)
* \_\_function\_\_()  functions
* string module
* Writing function with optional parameters, implied lists ( func(1,2,3,4) = func([1,2,3,4]) )
* String section
* Ordered set
* bytearray 
* frozenset
* import threading -- threading.Lock class Semaphores, multithreading in general

# Document Standards

## Standard 1: Multiline code

```python
​```python
code
code  # Comments (should have two spaces in-between code and #, and # and Comments)
code            # Comment (group comments nicely)
code code code  # Comment (don't use tabs in-between code and comments, use spaces)
code code       # Comment
​```
```
## Standard 2: no wrapping lines of code

## Standard 3: Multiline comments
```python
​```python
code  # Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt  
      #     on ullamco laboris nisi ut aliquip ex ea commodo consequa aboris nisi ut aliquip ex ea 
      #     commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse 
      #     cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupid.
    (4 space indent from first character)
​```
```

## Standard 4: Capitalize comments

## Standard 5: Functions with multiple parameter signatures

```python
function()           # Comment (separate the functions into multiple lines)
function(x)          # Comment (don't try to explain two in one)
function(x, y, [z])  # Comment (very  rarely should you do this)
```

## Standard 6: Common names

```python
iterable - any object that satisfied pythons ___ protocol (has certain functions)
sequence - TODO
list - a list
function
num - a numeric type
int - a number that is an integer
...
TODO
```