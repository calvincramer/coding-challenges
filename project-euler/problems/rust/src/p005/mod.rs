pub mod p005 {
    fn div_up_to(num: u32, stop: u32) -> bool {
        for div in 1..=stop {
            if num % div != 0 {
                return false;
            }
        }
        true
    }

    fn bad_approach() {
        let mut n: u32 = 20;
        loop {
            if div_up_to(n, 20) {
                println!("{}", n);
                break;
            }
            n += 20; // Must be a divisor of 20
        }
    }

    pub fn run() {
        println!("Problem 5");
        // bad_approach();
        // Can figure out manually too:
        println!("{}", 2 * 2 * 2 * 2 * 3 * 3 * 5 * 7 * 11 * 13 * 17 * 19)
    }
    // Answer: 232792560
}
