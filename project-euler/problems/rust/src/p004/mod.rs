use rust_math_tools::Palindrome;
use std::collections::HashSet;

fn try1() -> (i32, String, String) {
    let mut largest: u64 = 0;
    for n1 in 100u64..1000u64 {
        for n2 in (n1+1)..1000u64 {
            if (n1 * n2).is_palindrome() {
                largest = std::cmp::max(largest, n1 * n2)
            }
        }
    }
    (4, "Largest palindrome".to_string(), largest.to_string())
}

/// Slower
#[allow(dead_code)]
fn try2() -> (i32, String, String) {
    let mut my_set = HashSet::new();
    for n1 in 100u64..1000u64 {
        for n2 in (n1+1)..1000u64 { // Multiplication is associative
            my_set.insert(n1*n2);
        }
    }
    let mut sorted: Vec<u64> = my_set.into_iter().collect::<Vec<u64>>();
    sorted.sort();
    for n in sorted.iter().rev() {
        if (*n).is_palindrome() {
            return (4, "Largest palindrome".to_string(), n.to_string())
        }
    }
    (4, "Largest palindrome".to_string(), "Error".to_string())
}

pub struct P004 {}
impl crate::Problem for P004 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        try1()
        // Answer: 906609
    }
}