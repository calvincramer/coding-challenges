pub mod p025 {
    use num_bigint::BigUint;

    pub fn run() {
        println!("Problem 25");
        let mut n1 = BigUint::from(1u64);
        let mut n2 = BigUint::from(1u64);
        let mut i = 2u64;

        while n2.to_string().len() < 1000 {
            i += 1;
            let temp = n1 + n2.clone();
            n1 = n2;
            n2 = temp;
        }
        println!("{}", n2.to_string());
        println!("fib_i = {}", i);
    }
    // Answer: 4782
}