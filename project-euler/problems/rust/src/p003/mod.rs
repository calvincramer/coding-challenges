pub mod p003 {
    use integer_sqrt::IntegerSquareRoot;
    use rust_math_tools;
    use std::cmp;
    use self::rust_math_tools::PrimeTest;

    pub fn run() {
        println!("Problem 3");
        let num: u64 = 600851475143;
        let mut num_sqrt = num.integer_sqrt();
        let mut largest_prime_factor: u64 = 1;

        if num_sqrt.pow(2) == num {
            num_sqrt += 1;
        }

        for div in (3..=num_sqrt + 1).step_by(2) {
            if num % div == 0 {
                // Is a factor
                println!("Factor: {}", div);
                // Is a prime factor?
                if div.is_prime() {
                    largest_prime_factor = cmp::max(largest_prime_factor, div)
                }
            }
        }
        println!("\nLargest prime factor: {}", largest_prime_factor)
    }
// Answer: 6857
}