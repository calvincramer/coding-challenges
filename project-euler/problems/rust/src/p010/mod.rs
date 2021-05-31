use rust_math_tools::PrimeTest;
use rayon::prelude::*;

pub struct P010 {}
impl crate::Problem for P010 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        // Iterative
        // let mut sum: u64 = 0;
        // for n in 2u64..2_000_000 {
        //     if n.is_prime() {
        //         sum += n;
        //     }
        // }

        // Parallel
        let sum = (2u64..2_000_000).into_par_iter().filter(|n| n.is_prime()).sum::<u64>();

        (10, "Sum".to_string(), sum.to_string())
        // Answer: 142913828922
    }
}
// old iterative debug 0.34016
