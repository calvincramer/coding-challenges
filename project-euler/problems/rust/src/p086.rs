use rust_math_tools::is_square;

pub struct P086 {}
impl crate::Problem for P086 {
    fn solve(&self, verbose: bool) -> String {
        // Use M as one dimension (fixed), vary the other two dimensions
        // Grow M by 1 each iteration and calculate each cuboid of dimension (a,b,M)
        // Also, a <= b <= M, so shortest path is M^2 + (a+b)^2
        let mut num_integer_paths = 0u64;
        let mut m = 0u64;
        const MAX: u64 = 1_000_000;
        while num_integer_paths < MAX {
            // println!("m={}, num paths={}", m, num_integer_paths);
            m += 1;
            for a in 1..=m {
                for b in a..=m {
                    if is_square(&(m.pow(2) + (a+b).pow(2))) {
                        num_integer_paths += 1;
                    }
                }
            }
        }
        if verbose {
            println!("m={}, num paths={}", m, num_integer_paths);
        }
        m.to_string()
    }
    fn is_slow(&self) -> bool { true }
    fn problem_num(&self) -> i32 { 86 }
    fn answer_desc(&self) -> String { "Least M".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("1818".to_string()) }
}
// 14.92852s