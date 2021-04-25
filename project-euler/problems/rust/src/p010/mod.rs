use rust_math_tools::PrimeTest;

pub struct P010 {}
impl crate::Problem for P010 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut sum: u64 = 0;
        for n in 2u64..2_000_000 {
            if n.is_prime() {
                sum += n;
            }
        }
        (10, "Sum".to_string(), sum.to_string())
        // Answer: 142913828922
    }
}
