use num_bigint::BigUint;
use rust_math_tools::SumDigits;

pub struct P056 {}
impl crate::Problem for P056 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
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
        (56, "Max digital sum".to_string(), max.to_string())
        // 972
    }
}
