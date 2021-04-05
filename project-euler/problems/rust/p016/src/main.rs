extern crate num_bigint;
use num_bigint::BigUint;

extern crate num_traits;
use num_traits::One;
use std::ops::Div;

fn main() {
    let two = BigUint::parse_bytes(b"2", 10).unwrap();
    let big = two.pow(1000);
    println!("{}", big);
    println!("sum: {}", big.to_string().as_bytes().iter().map(|&c| (c as u64) - 0x30).sum::<u64>());
}
