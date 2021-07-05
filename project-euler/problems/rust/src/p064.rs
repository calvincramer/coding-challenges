use num_integer::Roots;
use rust_math_tools::is_square;
use rayon::prelude::*;

#[derive(Copy, Clone, Debug)]
struct Term {
    front: u64,
    numerator: u64,
    bottom: u64,
    s: u64,
    sqrt_floor: u64,
}

impl Term {
    fn new(a: u64, n: u64, b: u64, s: u64, sqrt_floor: u64) -> Term {
        Term {
            front: a,
            numerator: n,
            bottom: b,
            s: s,
            sqrt_floor: sqrt_floor,
        }
    }
    fn new_start(num: u64) -> Term {
        Term {
            front: num.sqrt(),
            numerator: 1,
            bottom: num.sqrt(),
            s: num,
            sqrt_floor: num.sqrt(),
        }
    }
}

fn iter_term(term: Term) -> Term {
    let n_next = (term.s - term.bottom.pow(2)) / term.numerator;
    let a_next = (term.sqrt_floor + term.bottom) / n_next;
    let b_next = (a_next * n_next) - term.bottom;
    Term::new(a_next, n_next, b_next, term.s, term.sqrt_floor)
}

fn term_repeats_n_times(
    start: &Term,
    cycle_len: usize,
    mut repeat_times: usize,
    f: fn(Term) -> Term,
) -> bool {
    if repeat_times <= 1 {
        return true;
    }
    let mut iter = start.clone();
    let mut cycle = Vec::new();
    // Get first cycle
    for _ in 0..cycle_len {
        cycle.push(iter);
        iter = f(iter);
    }
    // Check repeats
    let mut i = 0;
    repeat_times -= 1;
    while repeat_times > 0 {
        if iter.front != cycle[i].front {
            return false;
        }
        i += 1;
        if i >= cycle.len() {
            i = 0;
            repeat_times -= 1;
        }
        iter = f(iter);
    }
    true
}

/// Detect cycles in sequence of numbers
/// Based on Brent's cycle detection algorithm
/// Credit: https://rosettacode.org/wiki/Cycle_detection#Python
///
/// Cycle is considered a cycle if it repeats TIMES_MUST_REPEAT times, over at least
/// MIN_CYCLE_LEN_CHECK numbers minimum.
///
/// Returns (a,b) where _a_ is the cycle length, and _b_ is the cycle start index (0-indexed), or
/// None if no cycle is detected.
fn sequence_cycle_length(f: fn(Term) -> Term, x0: Term) -> Option<usize> {
    let mut length_max = 1usize;  // limit to check length
    let mut start = x0;
    let mut start_i = 0usize; // used to update length_max occasionally
    const TIMES_MUST_REPEAT: usize = 5;  // must repeat this many times to be considered a cycle
    const MIN_CYCLE_LEN_CHECK: usize = 20;   // must check the cycle out this many terms
    const INDEX_LIMIT: usize = 1000;

    let get_cycles_check = |cycle_length: usize| -> usize {
        std::cmp::max(cycle_length * TIMES_MUST_REPEAT, MIN_CYCLE_LEN_CHECK / cycle_length)
    };

    while start_i < INDEX_LIMIT {
        // Increase max search for length after a while
        if start_i == length_max {
            length_max *= 2;
        }
        // Loop through length 1 to length_max check for cycle
        for length in 1..=length_max {
            if term_repeats_n_times(&start, length, get_cycles_check(length), f) {
                // Found repeating pattern. Move back until no longer repeats to find start:
                // let mut raeal_start = x0;
                // for real_start_i in 0..=start_i {
                //     if term_repeats_n_times(&real_start, length, get_cycles_check(length), f) {
                //         return Some((length, real_start_i));
                //     }
                //     real_start = f(real_start);
                // }

                // Don't care about start being the real start, just care about cycle length:
                return Some(length);
            }
        }
        // Increment start
        start = f(start);
        start_i += 1;
    }
    None
}

pub struct P064 {}
impl crate::Problem for P064 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut nums = (2u64..=10_000).filter(|n| !is_square(n)).collect::<Vec<u64>>();
        nums.par_iter_mut().for_each(|n| {
            match sequence_cycle_length(iter_term, Term::new_start(*n)) {
                Some(len) => *n = len as u64,
                None => *n = 0u64,
            };
        });
        nums.iter().filter(|len| *len % 2 == 1).count().to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 64 }
    fn answer_desc(&self) -> String { "Odd periods".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("1322".to_string()) }
}
