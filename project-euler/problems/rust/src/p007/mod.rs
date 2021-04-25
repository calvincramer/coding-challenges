use self::rust_math_tools::PrimeTest;
use rust_math_tools;

pub struct P007 {}
impl crate::Problem for P007 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut num: u64 = 13;
        let mut prime_num: u32 = 6; // Sixth prime is 13
        while prime_num < 10001 {
            num += 2;
            if num.is_prime() {
                prime_num += 1;
            }
        }
        (7, "Num".to_string(), num.to_string())
        // Answer: 104743
    }

}
