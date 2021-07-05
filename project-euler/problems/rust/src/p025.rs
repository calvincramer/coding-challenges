use num_bigint::BigUint;

pub struct P025 {}
impl crate::Problem for P025 {
    fn solve(&self, verbose: bool) -> String {
        let mut n1 = BigUint::from(1u64);
        let mut n2 = BigUint::from(1u64);
        let mut i = 2u64;

        while n2.to_string().len() < 1000 {
            i += 1;
            let temp = n1 + n2.clone();
            n1 = n2;
            n2 = temp;
        }
        if verbose {
            println!("{}", n2.to_string());
        }
        i.to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 25 }
    fn answer_desc(&self) -> String { "fib_i".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("4782".to_string()) }
}
