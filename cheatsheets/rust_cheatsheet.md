# Rust Cheatsheet

## General
```rust
//Type aliases
type NewTypeName = OldAndLongTypeNameThatIsTooLongToTypeBecauseProgrammersWantToTypeAsLittleAsPossible;

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
for i in 0u64..=1000u64 { ... }
// Loop in reverse
for i in (0..1000).rev() { ... }


// Infinite loop
loop { ... }
```

## Structs
```rust
struct Name {
	num_arms: u32,
	num_toes: u32,
	...
}
impl Name {
	pub fn function1(...) { ... }
	fn function2(&mut self, ...) { ... }
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

// Concatenate
// .append() TODO
// .concat() TODO
[vec1, vec2].concat();	// Produces a new vector? Consumes both?
```

## Arrays
```rust
let arr = [0, 1, 2, 3, 4];	// defaults to i32
let arr = [0u64, 1, 2, 3, 4];
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

// Big numbers easier to read:
let x : u64 = 123_452_624;
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
s.to_string()	// str to String

// <Chars> type has many useful features for ascii characters
// Chars contains UNICODE characters, and are 4 bytes each.
chs = s.char()
s.rev()
s.collect()
s.eq(other)

// 1 byte ascii characters:
let chars : &[u8] = "asdf".as_bytes();
println!("{}", std::str::from_utf8(s).unwrap());	// [u8]-> String for printing


// Substrings - use slices
let slice = &str[0..5];
let otherString: String = string[0..5].to_string();

// Multiline strings:
let s : &'static str = "asdf1\
                        asdf2\
                        asdf3";	// Equivalent to "asdf1asdf2asdf3"
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


// Chose to do nothing:
match something {
	1 => ...,
	2 => (),	// DO NOTHING CASE
	_ => ...,
}

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

## Useful crates
* integer-sqrt
* num-bigint
* num-traits
* bigdecimal


## Closures
```rust
// Closures are like anonymous functions, and can capture variables from outside scope
// Normal function can NEVER access outside scope
let mut max : u32  = 0;
let mut work = |n1, n2, n3, n4| {
    max = std::cmp::max(max, n1 * n2 * n3 * n4)
};
work(1,2,3,4);
work(2,3,4,5);

// Capturing things as references:
"asdf".to_string().as_bytes().iter().map(|c| c - 0x30);	// c is a &u8
"asdf".to_string().as_bytes().iter().map(|&c| c - 0x30);	// c is a u8
// This allows us to take the sum:
"asdf".to_string().as_bytes().iter().map(|&c| (c as u64) - 0x30).sum::<u64>();
```

## Functional stuff
```rust
// Sum of 1 to 100, squared
let x : i64 = (1..=100).sum::<i64>().pow(2);
// Sum of squares, 1 to 100
let x : i64 = (1..=100).map(|x:i64| x.pow(2)).sum();

// Collectors:
max()
max_by()
max_by_key()
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

## Tuples and unpacking
```rust
fn f(...) -> (u64, i64) {
	...
	(1, 2)	// Return
}

let (a, b) = f(...);
```

## Derive
```rust
// Implement copy trait
#[derive(Copy, Clone)]

// Implement {:?} printing
#[derive(Debug)]

// Implement comparison of enums with == (else you are forced to use a match statement)
#[derive(PartialEq, Eq)]
```

## Traits - interfaces
```rust
trait MyTrait {
	fn foo(self) -> something;
}

impl MyTrait for MyStruct {
	fn foo(self) -> something {
		...
	}
}
```


# Todo:

* match guards
* pub, no pub functions, other options?
* ? operator