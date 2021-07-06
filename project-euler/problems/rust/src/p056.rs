use num_bigint::BigUint;
use rust_math_tools::SumDigits;

pub struct P056 {}
impl crate::Problem for P056 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // Fast enough that we don't need to be smart.
        let mut max = 0;
        for a in 1u64..100 {
            for b in 1..100 {
                let n = BigUint::from(a).pow(b);
                let sum_digits = n.sum_digits();
                if sum_digits > max {
                    max = sum_digits;
                }
            }
        }
        max.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        56
    }
    fn answer_desc(&self) -> String {
        "Max digital sum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("972".to_string())
    }
}
