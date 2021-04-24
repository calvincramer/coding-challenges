pub mod p020 {
    use rust_math_tools::factorial;
    use num_bigint::BigUint;

    pub fn run() {
        println!("Problem 20");
        let fac100 = factorial(100);
        println!("{}", fac100);
        println!("{}", fac100.to_string().chars().map(|c| c.to_digit(10).unwrap()).sum::<u32>());
    }
    // Answer: 648
}