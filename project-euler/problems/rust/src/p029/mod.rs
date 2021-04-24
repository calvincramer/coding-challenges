pub mod p029 {
    use std::collections::HashSet;
    use std::iter::FromIterator;
    use num_bigint::BigUint;

    pub fn run() {
        println!("Problem 29");
        let mut nums: Vec<BigUint> = vec![];
        for a in 2u64..=100 {
            for b in 2u32..=100 {
                nums.push(BigUint::from(a).pow(b));
            }
        }
        nums.sort();
        let set: HashSet<BigUint> = HashSet::from_iter(nums);
        println!("{}", set.len());
    }
    // Answer: 9183
}