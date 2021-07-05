use rayon::prelude::*;

pub struct P030 {}
impl crate::Problem for P030 {
    fn solve(&self, verbose: bool) -> String {
        let sum_digits_pow_5 = |num: &u64| -> u64 {
            let mut sum = 0;
            let mut n: u64 = *num;
            while n > 0 {
                sum += (n % 10).pow(5);
                n /= 10;
            }
            return sum;
        };
        let results: Vec<u64> = (2u64..10_000_000).into_par_iter().filter(|n: &u64| *n == sum_digits_pow_5(n)).collect();
        if verbose {
            println!("{:?}", results);
        }
        results.iter().sum::<u64>().to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 30 }
    fn answer_desc(&self) -> String { "Sum".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("443839".to_string()) }
}
