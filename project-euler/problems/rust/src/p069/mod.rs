use rust_math_tools::totient;
use rayon::prelude::*;
use itertools::Itertools;

pub struct P069 {}
impl crate::Problem for P069 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut max_n = u64::MIN;
        let mut max_phi_n = f64::MIN;

        // Serial
        // for n in 2..=1_000_000 {
        //     let phi_n = n as f64 / totient(n) as f64;
        //     if phi_n > max_phi_n {
        //         max_phi_n = phi_n;
        //         max_n = n;
        //         if verbose {
        //             println!("n={} n/phi(n)={}", max_n, max_phi_n);
        //         }
        //     }
        // }

        // Parallel
        const MAX: usize = 1_000_001;
        let mut vec: Vec<u64> = (0u64..(MAX as u64)).collect_vec();
        vec.par_iter_mut().for_each(|i| *i = totient(*i as u64));
        for i in 0..MAX {
            let phi_n = i as f64 / vec[i] as f64;
            if phi_n > max_phi_n {
                max_phi_n = phi_n;
                max_n = i as u64;
            }
        }

        (69, "Maximum".to_string(), max_n.to_string())
        // Answer: 510510
    }
}
