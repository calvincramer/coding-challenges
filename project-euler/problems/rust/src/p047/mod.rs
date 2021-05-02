use rust_math_tools::get_uniq_prime_factors;

pub struct P047 {}
impl crate::Problem for P047 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        const LENGTH: usize = 4;  // Four consecutive numbers with four unique prime factors
        let mut arr: [bool; LENGTH] = [false; LENGTH];
        let mut arr_i = 0;

        let mut n = 0;
        loop {
            let result = get_uniq_prime_factors(n).len() == LENGTH;
            arr[arr_i] = result;
            arr_i = (arr_i + 1) % LENGTH;

            // Stop at first occurrence
            if arr.iter().all(|b| *b) {
                return (47, "Number".to_string(), (n as usize - LENGTH + 1).to_string())
            }
            n += 1;
        }
        // Answer: 134043
    }
}
