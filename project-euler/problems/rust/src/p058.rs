use rust_math_tools::PrimeTest;

pub struct P058 {}
impl crate::Problem for P058 {
    #[allow(unused_variables)]
    #[rustfmt::skip]
    fn solve(&self, verbose: bool) -> String {
        let mut n = 0u64;  // Nth concentric ring (middle 1 is the 0th ring)
        let mut tr = 1; // Top right diagonal number
        let mut tl = 1; // Top left diagonal number
        let mut bl = 1; // Bottom left diagonal number
        let mut total_nums = 1u64; // Including middle 1
        let mut total_primes = 0u64;

        loop {
            // Get next nums
            let n_mult_8 = n*8;
            tr += n_mult_8 + 2;
            tl += n_mult_8 + 4;
            bl += n_mult_8 + 6;
            // Update
            n += 1;
            total_nums += 4;
            if tr.is_prime() { total_primes += 1; }
            if tl.is_prime() { total_primes += 1; }
            if bl.is_prime() { total_primes += 1; }
            // Done?
            if (total_primes as f64) / (total_nums as f64) < 0.1 { break; }
        }

        (n*2 + 1).to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        58
    }
    fn answer_desc(&self) -> String {
        "Side length".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("26241".to_string())
    }
}
