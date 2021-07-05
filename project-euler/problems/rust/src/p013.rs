use num_bigint::BigUint;
use num_traits::Zero;
use std::fs::File;
use std::io::{BufRead, BufReader};

pub struct P013 {}
impl crate::Problem for P013 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        // Big int sum:
        let mut sum: BigUint = Zero::zero();

        // Read numbers
        let f = File::open("src/input_files/p013_numbers.txt");
        match f {
            Err(e) => {
                println!("File doesn't exist {}", e);
                std::process::exit(1);
            }
            _ => (),
        }
        let lines = BufReader::new(f.unwrap()).lines();

        for line in lines {
            match line {
                Ok(l) => sum = sum + BigUint::parse_bytes(l.as_bytes(), 10).unwrap(),
                Err(e) => println!("error! {}", e),
            }
        }
        String::from(&sum.to_string().as_str()[0..10])
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 13 }
    fn answer_desc(&self) -> String { "Answer".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("5537376230".to_string()) }
}
