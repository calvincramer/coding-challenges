use rust_math_tools::ncr;
use num_bigint::BigUint;

pub struct P053 {}
impl crate::Problem for P053 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let one_mill = BigUint::from(1_000_000u64);
        let mut total = 0;
        for n in 1..=100 {
            for r in 0..=n {
                if ncr(n, r) > one_mill {
                    total += 1;
                }
            }
        }
        (53, "Num > one million".to_string(), total.to_string())
        // Answer: 4075
    }
}
