fn div_up_to(num: u32, stop: u32) -> bool {
    for div in 1..=stop {
        if num % div != 0 {
            return false
        }
    }
    true
}

fn main() {
    let mut n : u32 = 20;
    loop {
        if div_up_to(n, 20) {
            println!("{}", n);
            break;
        }
        if n % 1000000 == 0 {
            println!("\t{}", n);
        }
        n += 20;    // Must be a divisor of 20
    }
    // Can figure out manually:
    println!("{}", 2*2*2*2 * 3*3 * 5 * 7 * 11 * 13 * 17 * 19)
}
// Answer: 232792560
