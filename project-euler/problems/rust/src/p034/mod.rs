pub mod p034 {
    static FAC_LKUP: [u64; 10] = [1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880];
    pub fn run() {
        println!("Problem 34");
        // Sum of factorial of each digit for 9,999,999 = 2,540,160, and 9! is 362,880, so this a max bound.
        let max = 9_999_999;

        let is_curious = |mut n: u64| -> bool {
            let orig = n;
            let mut temp_sum = 0;
            while n != 0 {
                temp_sum += FAC_LKUP[(n % 10) as usize];
                n /= 10;
            }
            temp_sum == orig
        };

        let mut sum = 0;
        for n in 3..=max {
            if is_curious(n) {
                sum += n;
                println!("{}", n);
            }
        }
        println!("Total: {}", sum);
    }
}
