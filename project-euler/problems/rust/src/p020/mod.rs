use rust_math_tools::factorial;

pub struct P020 {}
impl crate::Problem for P020 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let fac100 = factorial(100);
        if verbose {
            println!("{}", fac100);
        }
        (20, "Answer".to_string(), fac100.to_string().chars().map(|c| c.to_digit(10).unwrap()).sum::<u32>().to_string())
        // Answer: 648
    }
}
