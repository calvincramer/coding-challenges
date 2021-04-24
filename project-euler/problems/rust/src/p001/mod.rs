pub mod p001 {
    pub fn run() {
        println!("Problem 1");
        let mut total: u32 = 0;
        for i in 0..1000 {
            if i % 3 == 0 || i % 5 == 0 {
                total += i;
            }
        }
        println!("Total: {}", total)
    }
    // Answer: 233168
}
