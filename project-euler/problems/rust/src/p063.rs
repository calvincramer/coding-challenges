use rust_math_tools::num_digits_128;
use std::cmp::Ordering;

pub struct P063 {}
impl crate::Problem for P063 {
    fn solve(&self, verbose: bool) -> String {
        use Ordering::*;
        let mut count = 0;
        for a in 1..=9u128 {
            let mut n = 0u32;
            loop {
                n += 1;
                let num = a.pow(n);
                let n_digits = num_digits_128(num) as u32;
                match n.cmp(&n_digits) {
                    Less => continue,
                    Equal => {
                        if verbose {
                            println!("{} = {}^{}", num, a, n);
                        }
                        count += 1
                    },
                    Greater => break,
                }
            }
        }
        count.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 63 }
    fn answer_desc(&self) -> String { "Amount".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("49".to_string()) }
}
