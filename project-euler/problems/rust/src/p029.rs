use std::collections::HashSet;
use std::iter::FromIterator;
use num_bigint::BigUint;

pub struct P029 {}
impl crate::Problem for P029 {
    #[allow(unused_variables)]
    fn solve(&self, verbose: bool) -> String {
        let mut nums: Vec<BigUint> = vec![];
        for a in 2u64..=100 {
            for b in 2u32..=100 {
                nums.push(BigUint::from(a).pow(b));
            }
        }
        nums.sort();
        let set: HashSet<BigUint> = HashSet::from_iter(nums);
        set.len().to_string()
    }
    fn is_slow(&self) -> bool { false }
    fn problem_num(&self) -> i32 { 29 }
    fn answer_desc(&self) -> String { "Answer".to_string() }
    fn real_answer(&self) -> crate::ProblemAnswer { crate::ProblemAnswer::Some("9183".to_string()) }
}
