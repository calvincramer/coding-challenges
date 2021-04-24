pub mod p017 {
    use rust_math_tools::num_to_string;

    fn count_chars(s: &String) -> u32 {
        s.chars()
            .map(char::is_alphabetic)
            .map(|b| b as u32)
            .sum::<u32>()
    }

    pub fn run() {
        println!("Problem 17");
        let mut total: u32 = 0;
        for n in 1..=1000 {
            let s = num_to_string(n);
            total += count_chars(&s);
            println!("{} -> {} -> {}", n, s, count_chars(&s));
        }
        println!("Total: {}", total);
    }
    // Answer: 21124
}
