# Rust Cheatsheet

## General
```rust
//Type aliases
type NewTypeName = OldAndLongTypeNameThatIsTooLongToTypeBecauseProgrammersWantToTypeAsLittleAsPossible;

// Implement copy trait
#[derive(Copy, Clone)]
struct or enum

// use statements for clean code
//		Can use inside of function do don't need to type out the whole name of a struct or an enum
//		use Enum;
//		let x = A;		// Where Enum::A exists
```

## Control Flow
```rust
while <condition> { ... }


```

## Structs
```rust
struct Name {
	...
}
impl Name {
	function1() ...
	function2() ...
	...
}
// Call function:
Name::function1();
```

## Enums
```rust
enum A {
	A, B, C
}
enum B {
	A = 0, B = 10, C = 900
}

```

## Vectors
```rust
use std::vec::Vec;

// Create
let x = vec![1, 2, 3];
let x = Vec::new();

// Iterate
for n in x.iter() {
	...
}

// Iterate with index
for (i, n) in x.iter().enumerate() {
	...
}

```

## Hashmap
```rust
use std::collections::HashMap;

// Create
let mut table: HashMap<i32, i32> = HashMap::new();

// Contains key?
table.contains_key(&key);

// Get from table
table[&key];

// Insert
table.insert(key, value);

```


## Printing
```rust
println!("string");
println!("var = {}", var);
println!("vec = {:?}", vec);
```

## Numbers
```rust
let x = 1;
x.to_string()
// Literal numbers:
let x = 1; 		// x is i32 by default
let x = 1u8;	// place type at end of number literal

```

## Strings
```rust
// String is a growable, heap-allocated string, UTF-8 chars
let s: String = "asdf"
s.chars()		// To character array
let s2: String = s.chars().rev().collect()	// Reverse string into another string
s.len()			// Length
s.chars()		// Returns Chars iterator
// str is an immutable fixed-length string somewhere in memory

// Chars type has many useful features for ascii characters
chs = s.char()
s.rev()
s.collect()
s.eq(other)

// Substrings - use slices
let slice = &str[0..5];
let otherString: String = string[0..5].to_string();

```

## Matching
```rust
let x = 5;
match x {
	1 => println!("x is one"),
	2 | 3 => println!("x is 2 or 3"),
	_ => println!("x is not one two or three"),
}
// ref and ref mut cases
```

# Todo:

* match guards
* pub, no pub functions, other options?
* ? operator