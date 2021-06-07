use num_bigint::BigUint;

pub struct P016 {}
impl crate::Problem for P016 {
    fn solve(&self, verbose: bool) -> String {
        let two = BigUint::parse_bytes(b"2", 10).unwrap();
        let big = two.pow(1000);
        if verbose {
            println!("{}", big);
        }
        big.to_string().as_bytes().iter().map(|&c| (c as u64) - 0x30).sum::<u64>().to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 16 }
    fn answer_desc(&self) -> String { "Sum".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("1366".to_string()) }
}
