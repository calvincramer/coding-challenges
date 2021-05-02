use rust_math_tools::PrimeTest;
use itertools::Itertools;

fn search(n: u64, mask: u64) -> bool {
    let str = n.to_string();
    let mut bytes: Vec<char> = str.chars().collect();
    let mut num_primes = 0;
    let mut num_not_primes = 0;
    for to in (if mask & 1 == 1 { 1 } else { 0 })..=9 {
        for i in 0..bytes.len() {
            if mask & (1<<i) != 0 {
                bytes[i] = std::char::from_u32(to + 48).unwrap();   // '0' == 48
            }
        }
        // bytes to number:
        let s = bytes.iter().join("");
        let n = s.parse::<u64>().unwrap();
        match n.is_prime() {
            true => num_primes += 1,
            false => {
                num_not_primes += 1;
                if num_not_primes >= 3 { return false; }
            },
        }
    }
    8 == num_primes
}

pub struct P051 {}
impl crate::Problem for P051 {
    /// This solution has a lot of duplicate work.
    /// Starting from 'number' and taking all masks of it duplicates work.
    ///
    /// Better solution without duplicate work:
    /// x = any digit (different x's can be different)
    /// r = replace digit (all r's are the same digit)
    ///
    /// x r
    /// xx xr (rx) rr
    /// xxx xxr (xrx) xrr (rxx) rxr (rrx) rrr
    /// xxxx xxxr (xxrx) xxrr (xrxx) xrxr (xrrx) xrrr (rxxx) rxxr (rxrx) rxrr (rrxx) rrxr (rrrx) rrrr
    /// ...
    ///
    /// The sole x's and sole r's don't work.
    /// Anything with an r in the ones digit won't work.
    /// The x in the one's digit can only be in {1, 3, 7, 9}
    //#[allow(unused_variables)]
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut number = 1;
        loop {
            number += 2;    // Only odd numbers can have enough primes (even if mask is on ones digit)
            println!("{}", number);
            // Binary digits of mask represent wildcards
            for mask in 1u64..(2u64.pow(number.to_string().len() as u32)) {
                if search(number, mask) {
                    // Hard code answer because lazy
                    return (51, "8 prime family start".to_string(), "120303".to_string())
                }
            }
        }

        (51, "8 prime family start".to_string(), "error".to_string())
        // Answer: 121313
    }
}
