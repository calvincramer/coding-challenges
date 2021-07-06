use rust_math_tools::PrimeTest;
use std::collections::HashSet;

fn get_digits(mut n: u64) -> HashSet<u64> {
    let mut set = HashSet::new();
    while n > 0 {
        set.insert(n % 10);
        n /= 10;
    }
    set
}

pub struct P049 {}
impl crate::Problem for P049 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        const INC: u64 = 3_330;
        for n1 in 1_489..=(9_999 - 2 * INC) {
            let n2 = n1 + INC;
            let n3 = n2 + INC;
            if !(n1.is_prime() && n2.is_prime() && n3.is_prime()) {
                continue;
            }
            // Numbers are permutations of each other?
            let n1_digits = get_digits(n1);
            let n2_digits = get_digits(n2);
            let n3_digits = get_digits(n3);
            if n1_digits != n2_digits || n1_digits != n3_digits {
                continue;
            }
            return format!("{}{}{}", n1, n2, n3);
        }
        "Error".to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        49
    }
    fn answer_desc(&self) -> String {
        "Number".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("296962999629".to_string())
    }
}
