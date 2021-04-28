use rust_math_tools::{is_pandigital_1_to_9};

pub struct P038 {}
impl crate::Problem for P038 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        // n > 1 implies n is at least 2, and we are concatenating each product, any number with
        // five digits will be too large: 10_000 concat 20_000 = 1_000_020_000
        let stop: u64 = 10_000;
        let mut largest = 0;

        for num in 2..stop {
            // Generate string
            let mut s = String::new();
            let mut mult = 1;
            while s.len() < 9 {
                s = s + (num * mult).to_string().as_str();
                mult += 1;
            }
            let bigger_num = s.parse::<u64>().unwrap();
            if is_pandigital_1_to_9(bigger_num) {
                if bigger_num > largest {
                    largest = bigger_num;
                }
                if verbose {
                    println!("{} (1,...,{}) -> {}", num, mult-1, bigger_num)
                }
            }
        }

        (38, "Largest".to_string(), largest.to_string())
        // Answer: 932718654
    }
}
