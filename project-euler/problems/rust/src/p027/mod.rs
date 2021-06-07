use rust_math_tools::PrimeTest;

fn num_primes(a: i64, b: i64) -> u64 {
    let mut n = 0;
    let mut count = 0;
    loop {
        if (n * n + a * n + b).is_prime() {
            n += 1;
            count += 1;
        } else {
            return count;
        }
    }
}

pub struct P027 {}
impl crate::Problem for P027 {
    fn solve(&self, verbose: bool) -> String {
        let mut best_a = 0;
        let mut best_b = 0;
        let mut best_p = 0;
        for a in -999..1000 {
            for b in -1000..=1000 {
                let temp_p = num_primes(a, b);
                if temp_p > best_p {
                    best_p = temp_p;
                    best_a = a;
                    best_b = b;
                }
            }
        }
        if verbose {
            println!("num_primes={} a={} b={} a*b={}", best_p, best_a, best_b, best_a * best_b)
        }
        (best_a * best_b).to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 27 }
    fn answer_desc(&self) -> String { "a*b".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("-59231".to_string()) }
}
