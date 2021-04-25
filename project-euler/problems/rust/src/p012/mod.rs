use rust_math_tools::num_divisors;

pub struct P012 {}
impl crate::Problem for P012 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut trinum_num: u64 = 1; // nth trinum
        let mut trinum_sum: u64 = 1; // actual triangle number
        while num_divisors(trinum_sum) <= 500 {
            trinum_num += 1;
            trinum_sum += trinum_num;
        }
        (12, "Sum".to_string(), trinum_sum.to_string())
        // Answer: 76576500
    }
}
