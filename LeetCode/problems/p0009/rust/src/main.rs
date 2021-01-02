//[COMPLETED]

struct Solution;
impl Solution {
    pub fn is_palindrome(x: i32) -> bool {
        let s: String = x.to_string();
        let mut mid: usize = s.len() / 2;
        let low = &s[0..mid];
        if s.len() % 2 == 1 {
            mid += 1;
        }
        let high = &s[mid..];
        high.chars().rev().eq(low.chars())
    }
}

fn main() {
    println!("{} expected true", Solution::is_palindrome(12321));
    println!("{} expected true", Solution::is_palindrome(1221));
    println!("{} expected false", Solution::is_palindrome(-1221));
}
