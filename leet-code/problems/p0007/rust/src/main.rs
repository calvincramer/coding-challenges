//[COMPLETED]

struct Solution;

impl Solution {
    pub fn reverse(x: i32) -> i32 {
        let mut x: String = x.to_string();
//        println!("{}", x);
        x = x.chars().rev().collect();
        if x.chars().last().unwrap() == '-' {
            x.pop();
            x.insert_str(0, "-");
        }
//        println!("{}", x);
        match x.parse::<i32>() {
            Ok(n) => n,
            Err(_) => 0,
        }
    }
}

fn main() {
    let inp: i32 = -123;
    println!("reverse: {}", Solution::reverse(inp));
}