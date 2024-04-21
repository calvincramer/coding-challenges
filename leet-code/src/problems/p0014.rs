#[COMPLETED]

struct Solution;

impl Solution {
    pub fn longest_common_prefix(strs: Vec<String>) -> String {
        if strs.len() == 0 {
            return "".to_string();
        } else if strs.len() == 1 {
            return strs[0].to_string();
        }

        let min_len: usize =  {
            let mut temp: usize = strs[0].len();
            for s in strs.iter() {
                if s.len() < temp {
                    temp = s.len()
            }}
            temp
        };
//        println!("{}", min_len);
        for i in 0..min_len {
            for j in 1..strs.len() {
                if strs[0].as_bytes()[i] != strs[j].as_bytes()[i] {
                    return strs[0][..i].to_string();
                }
            }
        }
        return strs[0][..min_len].to_string();
    }
}

fn main() {
    let strs: Vec<String> = vec!["flower".to_string(), "flow".to_string(), "flight".to_string()];
    let s = Solution::longest_common_prefix(strs);
    println!("{}", s);

    let strs: Vec<String> = vec!["aa".to_string(), "a".to_string()];
    let s = Solution::longest_common_prefix(strs);
    println!("{}", s);
}
