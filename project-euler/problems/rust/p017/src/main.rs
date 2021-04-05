extern crate rust_math_tools;
use rust_math_tools::num_to_string;

fn main() {
    for n in 0..=1000 {
        println!("{} -> {}", n, num_to_string(n))
    }
}
