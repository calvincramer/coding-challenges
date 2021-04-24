pub mod p004 {
    use rust_math_tools::is_palindrome;
    use std::cmp;

    pub fn run() {
        println!("Problem 4");
        let mut largest: u64 = 0;
        for n1 in 100u64..1000u64 {
            for n2 in 100u64..1000u64 {
                if is_palindrome(n1 * n2) {
                    largest = cmp::max(largest, n1 * n2)
                }
            }
        }
        println!("{}", largest)
    }
    // Answer: 906609
}
