use rust_math_tools::PrimeTest;

fn truncatably_prime(n: u64) -> bool {
    let s = n.to_string();
    for stop in 0..s.len() {
        let right = (&s[..(s.len() - stop)]).parse::<u64>().unwrap();
        if !right.is_prime() {
            return false;
        }
        let left = (&s[stop..]).parse::<u64>().unwrap();
        if !left.is_prime() {
            return false;
        }
    }
    true
}

pub struct P037 {}
impl crate::Problem for P037 {
    fn solve(&self, verbose: bool) -> String {
        let mut count = 0;
        let mut n = 11u64;
        let mut sum = 0;

        while count < 11 {
            if truncatably_prime(n) {
                if verbose {
                    println!("{}", n);
                }
                count += 1;
                sum += n;
            }
            n += 2;
        }
        sum.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        37
    }
    fn answer_desc(&self) -> String {
        "Sum".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("748317".to_string())
    }
}
