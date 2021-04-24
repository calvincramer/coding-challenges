pub mod p002 {
    pub fn run() {
        println!("Problem 2");
        let mut num1: u64 = 1;
        let mut num2: u64 = 2;
        let mut sum: u64 = 0;

        while num1 <= 4000000 {
            if num1 % 2 == 0 {
                sum += num1;
            }
            let next = num1 + num2;
            num1 = num2;
            num2 = next;
        }
        println!("{}", sum)
    }
    // Answer: 4613732
}
