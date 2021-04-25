use rust_math_tools::collatz_steps;

pub struct P014 {}
impl crate::Problem for P014 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let res = (1..1_000_000)
            .map(|x| (x, collatz_steps(x)))
            .max_by_key(|x| x.1);
        return match res {
            Some(x) => {
                if verbose {
                    println!("{} steps {} times", x.0, x.1);
                }
                (14, "Steps".to_string(), x.0.to_string())
            },
            None => (14, "Steps".to_string(), "No result".to_string()),
        }
        // Answer: 837799 (steps 525 times)
    }
}
