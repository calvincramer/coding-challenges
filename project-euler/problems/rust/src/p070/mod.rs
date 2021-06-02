use rust_math_tools::totient;
use itertools::Itertools;
use rayon::prelude::*;

fn get_low_perm(n: u64) -> String {
    n.to_string().chars().sorted().collect::<String>()
}

pub struct P070 {}
impl crate::Problem for P070 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        const MAX: usize = 10_000_000;
        let mut min_n = usize::MAX;
        let mut min_n_over_phi_n = f64::MAX;

        // Serial
        // for n in 8..MAX {
        //     let phi_n = totient(n as u64);
        //     let low_perm_n = get_low_perm(n as u64);
        //     let low_perm_phi_n = get_low_perm(phi_n);
        //     if low_perm_n != low_perm_phi_n { continue; }
        //     let n_over_phi_n = n as f64 / phi_n as f64;
        //     if n_over_phi_n < min_n_over_phi_n {
        //         min_n_over_phi_n = n_over_phi_n;
        //         min_n = n;
        //         if verbose {
        //             println!("n={} n/phi(n)={}", min_n, min_n_over_phi_n);
        //         }
        //     }
        // }

        // Parallel
        let mut totients: Vec<u64> = (0u64..(MAX as u64)).collect_vec();
        totients.par_iter_mut().for_each(|i| *i = totient(*i as u64));
        for n in 8..MAX {
            let low_perm_n = get_low_perm(n as u64);
            let low_perm_phi_n = get_low_perm(totients[n]);
            if low_perm_n != low_perm_phi_n { continue; }
            let n_over_phi_n = n as f64 / totients[n] as f64;
            if n_over_phi_n < min_n_over_phi_n {
                min_n_over_phi_n = n_over_phi_n;
                min_n = n;
                if verbose {
                    println!("n={} n/phi(n)={}", min_n, min_n_over_phi_n);
                }
            }
        }

        (70, "n".to_string(), min_n.to_string())
        // Answer: 8319823
    }
}
