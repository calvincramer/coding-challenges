pub mod p007 {
    use self::rust_math_tools::PrimeTest;
    use rust_math_tools;

    pub fn run() {
        println!("Problem 7");
        let mut num: u64 = 13;
        let mut prime_num: u32 = 6; // Sixth prime is 13
        while prime_num < 10001 {
            num += 2;
            if num.is_prime() {
                prime_num += 1;
            }
        }
        println!("{} {}", num, prime_num);
    }
    // Answer: 104743
}
