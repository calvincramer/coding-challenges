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

## Types
```sh
# Integer
u8 u16 u32 u64 u128

i8 i16 i32 i64 i128

# Boolean
false true

```

## Control Flow
```rust
while <condition> { ... }

// Inclusive - exclusive range
for i in 0..1000 { ... }
// With step
for i in (0..1000).step_by(2) { ... }
// Inclusive - inclusive range
for i in 0..=1000 { ... }
// Specify  type of number (i32 by default)
for i in 0u64..=1000u64 { ... }\

// Infinite loop
loop { ... }
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
// <String> is a growable, heap-allocated string, UTF-8 chars
let s: String = "asdf";
s.chars()		// To character array
let s2: String = s.chars().rev().collect()	// Reverse string into another string
s.len()			// Length
s.chars()		// Returns Chars iterator

// <str> is an immutable fixed-length string somewhere in memory
let s : &'static str = "asdf";

// <Chars> type has many useful features for ascii characters
// Chars contains UNICODE characters, and are 4 bytes each.
chs = s.char()
s.rev()
s.collect()
s.eq(other)

// 1 byte ascii characters:
let chars : &[u8] = "asdf".as_bytes();
println!("{}", std::str::from_utf8(s).unwrap());	// u8 -> String for printing


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

## Using crates
```rust
// usingarchive.rs
extern crate myothercrate;
use myothercrate::somepublicfunction;

// usingarchive Cargo.toml
[dependencies]
myothercrate = { path = "../relative/path/to/myothercrate" }

// myothercrate.rs
pub fn somepublicfunction(...) -> ... { ... }
```

## Functional stuff
```rust
// Sum of 1 to 100, squared
let x : i64 = (1..=100).sum::<i64>().pow(2);
// Sum of squares, 1 to 100
let x : i64 = (1..=100).map(|x:i64| x.pow(2)).sum();
```

## Cargo
```sh
# Make new project / crate:
TODO

cargo build		# Just build, no run
cargo run		# Build (if not built) and run (will no rebuild it already built)
cargo clean		# Delete build artifacts

```

## Casting
```rust
// Number types
let x : u64 = u8var as u64;
```



# Todo:

* match guards
* pub, no pub functions, other options?
* ? operator