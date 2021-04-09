extern crate rust_math_tools;
use rust_math_tools::factorial;

extern crate num_bigint;
use num_bigint::BigUint;

fn main() {
    let fac100 = factorial(100);
    println!("{}", fac100);
    println!("{}", fac100.to_string().chars().map(|c| c.to_digit(10).unwrap()).sum::<u32>());
}
