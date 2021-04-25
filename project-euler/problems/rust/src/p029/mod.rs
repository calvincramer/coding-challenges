use std::collections::HashSet;
use std::iter::FromIterator;
use num_bigint::BigUint;

pub struct P029 {}
impl crate::Problem for P029 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut nums: Vec<BigUint> = vec![];
        for a in 2u64..=100 {
            for b in 2u32..=100 {
                nums.push(BigUint::from(a).pow(b));
            }
        }
        nums.sort();
        let set: HashSet<BigUint> = HashSet::from_iter(nums);
        (29, "Answer".to_string(), set.len().to_string())
        // Answer: 9183
    }
}
