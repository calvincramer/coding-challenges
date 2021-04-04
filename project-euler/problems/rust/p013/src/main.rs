extern crate num_bigint;
use num_bigint::BigUint;

extern crate num_traits;
use num_traits::Zero;

use std::fs::File;
use std::io::{BufReader, BufRead};

fn main() {
    // Big int sum:
    let mut sum : BigUint = Zero::zero();

    // Read numbers
    let f = File::open("numbers.txt");
    match f {
        Err(e) => {
            println!("File doesn't exist {}", e);
            std::process::exit(1);
        },
        _ => (),
    }
    let lines = BufReader::new(f.unwrap()).lines();

    for line in lines {
        match line {
            Ok(l) => sum = sum + BigUint::parse_bytes(l.as_bytes(), 10).unwrap(),
            Err(e) => println!("error! {}", e),
        }
    }
    println!("{}", sum);
}
// Answer: 5537376230
