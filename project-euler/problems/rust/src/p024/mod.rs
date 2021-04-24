pub mod p024 {
    use rust_math_tools::permute_once;

    pub fn run() {
        println!("Problem 24");
        let mut arr = [0u64, 1, 2, 3, 4, 5, 6, 7, 8, 9];
        let mut permutation_num = 1u64;
        loop {
            if permutation_num == 1_000_000 {
                println!("{:?}", arr);
                for d in arr.iter() {
                    print!("{}", d);
                }
                println!();
                break;
            }
            if permute_once(&mut arr) == false {
                break;
            }
            permutation_num += 1;
        }
    }
    // Answer: 2783915460
}