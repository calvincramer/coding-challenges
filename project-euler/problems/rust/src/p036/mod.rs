use rayon::prelude::*;
use rust_math_tools::Palindrome;

pub struct P036 {}
impl crate::Problem for P036 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let sum: u64 = (0u64..1_000_000).into_par_iter()
            .filter(|n| n.is_palindrome() && format!("{:b}", n).is_palindrome())
            .sum();

        (36, "Sum".to_string(), sum.to_string())
        // Answer: 872187
    }
}
