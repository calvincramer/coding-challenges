//[COMPLETED]

//use std::vec::Vec;

struct Solution {}

impl Solution {
    pub fn two_sum(nums: Vec<i32>, target: i32) -> Vec<i32> {
        if nums.len() < 2 {
            return Vec::new();
        }
        for (i0, n0) in nums.iter().enumerate() {
            for (i1, n1) in nums.iter().enumerate() {
                if i0 == i1 { continue; }
                if n0 + n1 == target {
                    return vec![i0 as i32, i1 as i32];
                }
            }}
        return Vec::new();
    }
}

fn main() {
    println!("Hello");
    let v: Vec<i32> = vec![2, 7, 11, 15];
    let result = Solution::two_sum(v, 9);
    println!("result: {:?}", result);
}

/*
Better to do it in a hashmap
*/