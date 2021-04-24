pub mod p010 {
    use rust_math_tools::PrimeTest;

    pub fn run() {
        println!("Problem 10");
        let mut sum: u64 = 0;
        for n in 2u64..2_000_000 {
            if n.is_prime() {
                sum += n;
            }
        }
        println!("{}", sum);
    }
    // Answer: 142913828922
}
