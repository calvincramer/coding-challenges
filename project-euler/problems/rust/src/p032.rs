use rust_math_tools::is_pandigital;
use std::collections::HashSet;
use std::iter::FromIterator;

pub struct P032 {}
impl crate::Problem for P032 {
    fn solve(&self, verbose: bool) -> String {
        let mut results = vec![];
        for m1 in 1u64..=9999 {
            for m2 in m1 + 1..=9999 {
                let prod = m1 * m2;
                let combined = m1
                    + (m2 * 10u64.pow(m1.to_string().len() as u32))
                    + (prod * 10u64.pow(m2.to_string().len() as u32 + m1.to_string().len() as u32));
                if combined < 123_456_789 {
                    continue; // Too small to be pandigital, need bigger m2
                } else if combined > 987_654_321 {
                    break; // m2 is too big to be pandigital
                } else if is_pandigital(combined as u64, 1, 9) {
                    // println!("{} {}", m1, m2);
                    results.push((m1, m2, prod, combined));
                }
            }
        }

        let set: HashSet<u64> = HashSet::from_iter(results.iter().map(|x| x.2));
        if verbose {
            println!("Results:");
            for res in &results {
                println!("\t{:?}", res);
            }
            println!("Uniq products: {:?}", set);
            println!(
                "Sum all products: {}",
                results.iter().map(|x| x.2).sum::<u64>()
            );
        }
        set.iter().sum::<u64>().to_string()
    }
    fn is_slow(&self) -> bool {
        false
    }
    fn problem_num(&self) -> i32 {
        32
    }
    fn answer_desc(&self) -> String {
        "Sum unique products".to_string()
    }
    fn real_answer(&self) -> crate::ProblemAnswer {
        crate::ProblemAnswer::Some("45228".to_string())
    }
}
