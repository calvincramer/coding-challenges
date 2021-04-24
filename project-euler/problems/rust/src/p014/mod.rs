pub mod p014 {
    use rust_math_tools::collatz_steps;

    pub fn run() {
        println!("Problem 14");
        let res = (1..1_000_000)
            .map(|x| (x, collatz_steps(x)))
            .max_by_key(|x| x.1);
        match res {
            Some(x) => println!("{} steps {} times", x.0, x.1),
            None => println!("No result"),
        }
    }
    // Answer: 837799 (steps 525 times)
}
