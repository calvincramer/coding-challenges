extern crate num_bigint;
use num_bigint::BigUint;

extern crate num_traits;
use num_traits::One;
use std::ops::Div;

fn main() {
    let mut fac40 : BigUint = One::one();
    for n in 2u64..=40 { fac40 = fac40 * BigUint::from(n); }

    let mut fac20 : BigUint = One::one();
    for n in 2u64..=20 { fac20 = fac20 * BigUint::from(n); }
    println!("{}", fac40.div(fac20.pow(2)));
}
// Answer: 137846528820
