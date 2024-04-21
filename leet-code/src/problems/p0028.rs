//[COMPLETED]

struct Solution;

//use std::cmp::max;

impl Solution {
    pub fn str_str(haystack: String, needle: String) -> i32 {
        if needle.len() == 0 { return 0; }
        if haystack.len() == 0 { return -1; }
        if haystack.len() < needle.len() { return -1; }
        if haystack.len() == needle.len() { if haystack == needle { return 0 } else { return -1 }; }
        for i in 0..(haystack.len() - needle.len() + 1) {
            if &haystack[i..(i + needle.len())] == needle { return i as i32; }
        }
        -1
    }
}

fn main() {
    println!("{} expected 2",
        Solution::str_str("hello".to_string(), "ll".to_string()));
    println!("{} expected -1",
        Solution::str_str("aaaaaa".to_string(), "ba".to_string()));
    println!("{} expected ?",
             Solution::str_str("".to_string(), "a".to_string()));
    println!("{} expected 0",
             Solution::str_str("a".to_string(), "a".to_string()));
    println!("{} expected 9",
             Solution::str_str("mississippi".to_string(), "pi".to_string()));
}
