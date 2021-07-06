use rust_math_tools::get_all_factorizations;
use std::collections::HashSet;
use std::iter::FromIterator;

pub struct P088 {}
impl crate::Problem for P088 {
    fn solve(&self, verbose: bool) -> String {
        const MAX_K: usize = 12_001;
        let mut k_table = vec![u64::MAX; MAX_K];
        k_table[0] = 0;
        k_table[1] = 0;

        for n in 2..((MAX_K * 2) as u64) {
            let factorizations = get_all_factorizations(n);
            for i in 0..factorizations.len() {
                let factors = &factorizations[i];
                let k: usize = factors.len() + (n - factors.iter().sum::<u64>()) as usize;
                if k < MAX_K {
                    k_table[k] = std::cmp::min(k_table[k], n);
                }
            }
        }

        if verbose {
            for i in 0..MAX_K {
                println!("{}\t{}", i, k_table[i]);
            }
        }
        HashSet::<u64>::from_iter(k_table)
            .iter()
            .sum::<u64>()
            .to_string()
    }
    fn is_slow(&self) -> bool {
        true
    }
    fn problem_num(&self) -> i32 {
        88
    }
    fn answer_desc(&self) -> String {
        "Sum min prod-sum num".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("7587457".to_string())
    }
}
