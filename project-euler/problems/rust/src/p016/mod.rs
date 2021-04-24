pub mod p016 {
    use num_bigint::BigUint;
    use num_traits::One;
    use std::ops::Div;

    pub fn run() {
        println!("Problem 16");
        let two = BigUint::parse_bytes(b"2", 10).unwrap();
        let big = two.pow(1000);
        println!("{}", big);
        println!(
            "sum: {}",
            big.to_string()
                .as_bytes()
                .iter()
                .map(|&c| (c as u64) - 0x30)
                .sum::<u64>()
        );
    }
    // Answer: 1366
}
