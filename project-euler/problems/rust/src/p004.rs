use rust_math_tools::Palindrome;
use std::collections::HashSet;
use rayon::prelude::*;

#[allow(dead_code)]
fn try1() -> String {
    let mut largest: u64 = 0;
    for n1 in 100u64..1000u64 {
        for n2 in (n1+1)..1000u64 {
            if (n1 * n2).is_palindrome() {
                largest = std::cmp::max(largest, n1 * n2)
            }
        }
    }
    largest.to_string()
}

fn try1_parallel() -> String {
    let largest = (100u64..1000u64)
        .into_par_iter()
        .map(|n1| (n1+1..1000u64)
            .map(|n2| n1*n2)
            .filter(|n1n2| n1n2.is_palindrome())
            .max()
        )
        .max();
    largest.unwrap().unwrap().to_string()
}

/// Slower
#[allow(dead_code)]
fn try2() -> String {
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
            return n.to_string()
        }
    }
    "Error".to_string()
}

pub struct P004 {}
impl crate::Problem for P004 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        try1_parallel()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 4 }
    fn answer_desc(&self) -> String { "Largest palindrome".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("906609".to_string()) }
}
