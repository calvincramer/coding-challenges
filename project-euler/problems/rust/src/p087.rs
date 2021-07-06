use integer_sqrt::IntegerSquareRoot;
use rust_math_tools::get_primes_under;

pub struct P087 {}
impl crate::Problem for P087 {
    fn solve(&self, verbose: bool) -> String {
        const TARGET: usize = 50_000_000;
        let primes = get_primes_under(TARGET.integer_sqrt() as u64);
        let mut seen = vec![0; TARGET];
        for p1 in &primes {
            let p1p1 = p1 * p1;
            for p2 in &primes {
                let p1p1p2p2p2 = p1p1 + p2 * p2 * p2;
                if p1p1p2p2p2 >= TARGET as u64 {
                    break;
                }
                for p3 in &primes {
                    let p1p1p2p2p2p3p3p3p3 = p1p1p2p2p2 + p3 * p3 * p3 * p3;
                    if (p1p1p2p2p2p3p3p3p3 as usize) < TARGET {
                        seen[p1p1p2p2p2p3p3p3p3 as usize] = 1;
                    } else {
                        break;
                    }
                }
            }
        }
        let total: u64 = seen.iter().sum();

        total.to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        87
    }
    fn answer_desc(&self) -> String {
        "Num p^2 + p^3 + p^4".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("1097343".to_string())
    }
}
