extern crate rust_math_tools;
use rust_math_tools::read_all_lines;

fn main() {
    let x = read_all_lines(String::from("names.txt"));
    let s = x[0].as_str();
    let names : Vec<&str> = s.split(',').collect();
    for name in names {
        println!("{}", name)
    }
    let mut real_names = vec![];
    for name in names {
        real_names.push(name[1..])
    }
}
