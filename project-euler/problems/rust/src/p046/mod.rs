use rust_math_tools::PrimeTest;
use integer_sqrt::IntegerSquareRoot;

const MAX: u64 = 6_000;

fn is_composite(n: u64) -> bool {
    ! n.is_prime()
}

fn verify_goldbach(n: u64, primes: &Vec<u64>) -> bool {
    for p in primes {
        if *p > n { return false; }
        let mut twice_square = n - p;
        if twice_square % 2 != 0 { continue; }
        twice_square /= 2;
        if twice_square.integer_sqrt().pow(2) == twice_square { return true; }
    }
    false
}

pub struct P046 {}
impl crate::Problem for P046 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        // Generate primes
        let mut primes = vec![];
        for n in 0u64..MAX { if n.is_prime() { primes.push(n); } }

        for n in (9..MAX).step_by(2) {
            if is_composite(n) && !verify_goldbach(n, &primes) {
                return (46, "Smallest".to_string(), n.to_string())
            }
        }
        (46, "Smallest".to_string(), "Error".to_string())
        // Answer: 5777
    }
}
