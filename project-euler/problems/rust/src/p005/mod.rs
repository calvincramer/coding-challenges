#[allow(dead_code)]
fn div_up_to(num: u32, stop: u32) -> bool {
    for div in 1..=stop {
        if num % div != 0 {
            return false;
        }
    }
    true
}

#[allow(dead_code)]
fn bad_approach() {
    let mut n: u32 = 20;
    loop {
        if div_up_to(n, 20) {
            println!("{}", n);
            break;
        }
        n += 20; // Must be a divisor of 20
    }
}

pub struct P005 {}
impl crate::Problem for P005 {
    #[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        // Can figure out manually too:
        (5, "Smallest".to_string(), (2*2*2*2*3*3*5*7*11*13*17*19).to_string())
        // Answer: 232792560
    }
}
