use num_bigint::BigUint;

pub struct P025 {}
impl crate::Problem for P025 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut n1 = BigUint::from(1u64);
        let mut n2 = BigUint::from(1u64);
        let mut i = 2u64;

        while n2.to_string().len() < 1000 {
            i += 1;
            let temp = n1 + n2.clone();
            n1 = n2;
            n2 = temp;
        }
        if verbose {
            println!("{}", n2.to_string());
        }
        (25, "fib_i".to_string(), i.to_string())
        // Answer: 4782
    }
}
