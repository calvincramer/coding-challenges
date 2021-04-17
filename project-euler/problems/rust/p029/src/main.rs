use std::collections::HashSet;
use std::iter::FromIterator;
use num_bigint::BigUint;

fn main() {
    let mut nums : Vec<BigUint> = vec![];
    for a in 2u64..=100 {
        for b in 2u32..=100 {
            nums.push(BigUint::from(a).pow(b));
        }
    }
    nums.sort();
    let set: HashSet<BigUint> = HashSet::from_iter(nums);
    println!("{}", set.len());
}
