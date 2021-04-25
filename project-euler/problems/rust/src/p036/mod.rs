use rust_math_tools::Palindrome;

pub struct P036 {}
impl crate::Problem for P036 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut sum = 0;
        for n in 0u64..1_000_000 {
            if n.is_palindrome() && format!("{:b}", n).is_palindrome() {
                sum += n;
                if verbose {
                    println!("{}", n)
                }
            }
        }
        (36, "Sum".to_string(), sum.to_string())
        // Answer: 872187
    }
}
